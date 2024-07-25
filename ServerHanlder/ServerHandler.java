package ServerHanlder;

import DataAccessLayer.PlayerDTO;
import DataAccessLayer.RequestDTO;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import screens.AvailabilityScreen;
import screens.LoginScreen;
import screens.MoveDTO;
import screens.MultiModesScreen;
import screens.OnlineHomePageScreen;
import tic.toe.game.Mynav;

public class ServerHandler {
    Parent root = new OnlineHomePageScreen();

    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private Socket socket;
    public static ArrayList<PlayerDTO> players = new ArrayList<>();
    private static ServerHandler connector;
    AvailabilityScreen av ;

    private ServerHandler() {
        try {
            socket = new Socket("127.0.0.1", 6007);
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());

        } catch (IOException ex) {
            Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static synchronized ServerHandler connect() {
        if (connector == null) {
            connector = new ServerHandler();
        }
        return connector;
    }

    public boolean login(PlayerDTO player) {

        try {
            player.setScreenIndicator(-1);
            oos.writeObject(player);
            oos.flush();
            System.out.println("Login attempt for user: " + player.getUsername());

            try {
                Object response = ois.readObject();

                if (response instanceof String) {
                    String message = (String) response;
                    if (message.equals("true")) {
                        return true;
                    } else if (message.equals("false")) {
                        return false;
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (IOException ex) {
            Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;

    }

    public boolean signOut(PlayerDTO player) {
        try {
            player.setScreenIndicator(4);
            oos.writeObject(player);
            oos.flush();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean signUp(PlayerDTO player) {

        try {
            player.setScreenIndicator(0);
            oos.writeObject(player);
            oos.flush();
            System.out.println("Sign Up successfully: " + player.getUsername());

            try {
                Object response = ois.readObject();

                if (response instanceof String) {
                    String message = (String) response;
                    if (message.equals("Registered successfully")) {
                        return true;
                    } else if (message.equals("This user already exists!")) {
                        return false;
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (IOException ex) {
            Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;

    }

    public void sendRequestToServer(String selectedUsername) {
        try {
            RequestDTO rdo = new RequestDTO();
            rdo.setSender_username(LoginScreen.player.getUsername());
            rdo.setReciver_username(selectedUsername);
            rdo.setScreenIndicator(5); // send request to play to the server  
            oos.writeObject(rdo);
            oos.flush();

            System.out.println("Sent request to server for user: " + selectedUsername);

        } catch (IOException ex) {
            Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

//    private void updatePlayerStatus(PlayerDTO player) {
//        if (player.getUsername().equals(currentUsername)) {
//            // This is the current user, don't add to the TableView
//            return;
//        }
//
//        if (!players.contains(player)) {
//            players.add(player);
//            //updateTableView();
//        } else {
//            int index = players.indexOf(player);
//            players.set(index, player);
//            //_tableView.refresh();
//        }
//    }
    public void readFromServer() {
        PlayerDTO player = new PlayerDTO();
        player.setScreenIndicator(3);
        try {
            oos.writeObject(player);
        } catch (IOException ex) {
            Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Object receivedObject = ois.readObject();
                        if (receivedObject instanceof PlayerDTO) {
                            PlayerDTO receivedPlayer = (PlayerDTO) receivedObject;
                            if (player.getScreenIndicator() == 3) {
                                players.add(receivedPlayer);

                            }

                        } else if (receivedObject instanceof RequestDTO) {
                            System.out.println("ana gwa el get on line plyaer w ana request dto");
                            RequestDTO request = (RequestDTO) receivedObject;
                            System.out.println("i got an object with screenIdicator of " + request.getScreenIndicator());
                            switch (request.getScreenIndicator()) {
                                case 5:
                                case 8:
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            showGameRequestAlert(request);
                                        }
                                    });
                                    break;
                                case 6:
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            showAcceptanceGametAlert(request);
                                        }
                                    });
                                    break;

                            }

                        }
                        else if(receivedObject instanceof MoveDTO)
                        {
                            MoveDTO m=(MoveDTO)receivedObject;
                            System.out.println("ana alaa in client "+m.getReciver_userName());
                        }
                    } catch (IOException | ClassNotFoundException ex) {
                        Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        });
        t.start();
    }

    public void handleIncomingRequests() 
    {
        System.out.println("calling the handle request ");
        new Thread(() -> {
            while (true) {
                try {
                    System.out.println("calling the handle request : dlwa2ty ana gwa el thread w gwa el while true  ");
                    Object obj = ois.readObject();
                    if (obj == null) {
                        System.out.println("el object byeggi fady ");
                    } else {
                        System.out.println(obj.getClass());
                    }
                    if (obj instanceof RequestDTO) {
                        RequestDTO request = (RequestDTO) obj;
                        System.out.println("recived a creen indcator of " + request.getScreenIndicator());
                        if (request.getScreenIndicator() == 5 || request.getScreenIndicator() == 8) {
                            showGameRequestAlert(request);
                        } else if (request.getScreenIndicator() == 6) {
                            System.out.println("");
                        }
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
                    break;
                }
            }
        }).start();
    }

    private void handleRequest(RequestDTO request) {
        if (request.getScreenIndicator() == 5) {
            showGameRequestAlert(request);
        }
    }

    private void showGameRequestAlert(RequestDTO request) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Do you want to play a game with " + request.getSender_username() + "?",
                ButtonType.YES, ButtonType.NO);
        alert.setTitle("Game Request");
        alert.setHeaderText("Game Request from " + request.getSender_username());

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                try {
                    request.setScreenIndicator(6);
                    oos.writeObject(request);
                   // av = new AvailabilityScreen(LoginScreen.player);
                   Parent root = new OnlineHomePageScreen();

                   Scene scene = new Scene(root);
                   Stage stage = new Stage();
                   stage.setScene(scene);
                   stage.show();
                  

//                   Mynav.scene = new Scene(root);
//                   //Mynav.stage = new Stage();
//                   Mynav.stage.setScene(Mynav.scene);
//                   Mynav.stage.show();
//                    // av.navScreen();

                } catch (IOException ex) {
                    Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("Request rejected");
            }
        });
    }

    private void showAcceptanceGametAlert(RequestDTO request) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                request.getSender_username() + "accepted your request",
                ButtonType.YES, ButtonType.NO);
        alert.setTitle("Game Request");
        alert.setHeaderText("Game Request");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
               Parent root = new OnlineHomePageScreen();

               Scene scene = new Scene(root);
              Stage stage = new Stage();
              stage.setScene(scene);
              stage.show();
                   //Parent root = new OnlineHomePageScreen();

//                   Mynav.scene = new Scene(root);
//                   //Mynav.stage = new Stage();
//                   Mynav.stage.setScene(Mynav.scene);
//                   Mynav.stage.show();
//                  //av.navScreen();
            } else {
                System.out.println("Request rejected");
            }
        });
    }
    
    public void sendMoveToServer (MoveDTO move)
    {
        try {
            oos.writeObject(move);
            System.out.println(move.getReciver_userName());
            oos.flush();
            
        } catch (IOException ex) {
            Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
