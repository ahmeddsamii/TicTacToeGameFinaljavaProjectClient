package screens;

import DataAccessLayer.PlayerDTO;
import DataAccessLayer.RequestDTO;
import ServerHanlder.ServerHandler;
import java.awt.Desktop.Action;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import static screens.LoginScreen.player;

import tic.toe.game.Mynav;

public class AvailabilityScreen extends AnchorPane {

    private final AnchorPane anchorPane;
    private final TableView<PlayerDTO> _tableView;
    private final TableColumn<PlayerDTO, String> col_playerName;
    private final TableColumn<PlayerDTO, String> col_available;
    private final Button _backBtn;

    private ArrayList<PlayerDTO> players;
    PlayerDTO pto;
    public  String currentUsername;
    Button _refreshBtn;
    ActionEvent action ;
    
    public static  String  recever;

    
 

    
    

    public AvailabilityScreen(PlayerDTO currentPlayer) {
        anchorPane = new AnchorPane();
        _tableView = new TableView<>();
        col_playerName = new TableColumn<>("Player name");
        col_available = new TableColumn<>("status");
        _backBtn = new Button("Sign Out");
        players = new ArrayList<>();

        _refreshBtn = new Button("Refresh");

        setId("AnchorPane");
        setPrefHeight(800.0);
        setPrefWidth(1100.0);
        setStyle("-fx-background-color: #008080;");

        anchorPane.setLayoutX(25.0);
        anchorPane.setLayoutY(20.0);
        anchorPane.setPrefHeight(760.0);
        anchorPane.setPrefWidth(1050.0);
        anchorPane.setStyle("-fx-background-color: #FFA500;");

        _tableView.setLayoutX(31.0);
        _tableView.setLayoutY(112.0);
        _tableView.setPrefHeight(634.0);
        _tableView.setPrefWidth(988.0);
        _tableView.setStyle("-fx-background-color: #008080;");

        col_playerName.setPrefWidth(494.0);
        col_playerName.setCellValueFactory(new PropertyValueFactory<>("username"));

        col_available.setPrefWidth(493.0);
        col_available.setCellValueFactory(new PropertyValueFactory<>("status"));

        _backBtn.setLayoutX(31.0);
        _backBtn.setLayoutY(28.0);
        _backBtn.setOnAction(this::handleOnBtnSignOut);
        _backBtn.setStyle("-fx-background-color: #008080; -fx-background-radius: 50;");
        _backBtn.setTextFill(javafx.scene.paint.Color.ORANGE);
        _backBtn.setFont(new Font("System Bold Italic", 21.0));

        _refreshBtn.setLayoutX(500.0);
        _refreshBtn.setLayoutY(28.0);
        // _refreshBtn.setOnAction(this::handleOnRefreshBtn);
        _refreshBtn.setStyle("-fx-background-color: #008080; -fx-background-radius: 50;");
        _refreshBtn.setTextFill(javafx.scene.paint.Color.ORANGE);
        _refreshBtn.setFont(new Font("System Bold Italic", 21.0));

        _tableView.getColumns().addAll(col_playerName, col_available);
        anchorPane.getChildren().addAll(_tableView, _backBtn, _refreshBtn);
        getChildren().add(anchorPane);

        setupTableCellFactory();
        this.currentUsername = LoginScreen.player.getUsername();

        // Start listening for requests from the server
        updateTableView();
        ServerHandler.connect().readFromServer();
        players = ServerHandler.players;
    }

    private void setupTableCellFactory() {
        col_playerName.setCellFactory(col -> {
            TableCell<PlayerDTO, String> cell = new TableCell<PlayerDTO, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        setText(item);
                    }
                }
            };
            // two clicks on the targeted player to send a rquest to 
            cell.setOnMouseClicked(event -> {
                if (!cell.isEmpty() && event.getClickCount() == 2) {
                    PlayerDTO selectedPlayer = cell.getTableView().getItems().get(cell.getIndex());
                    RequestDTO request = new RequestDTO();
                    if (selectedPlayer != null) {
                        Platform.runLater(() -> {
                            ServerHandler.connect().sendRequestToServer(selectedPlayer.getUsername());
                            recever =selectedPlayer.getUsername();
                            System.out.println("sent success");
                            // ServerHandler.connect().handleIncomingRequests();

                        });

                    }
                }
            });

            return cell;
        });
    }

//    private void sendRequestToServer(String selectedUsername) {
//        try {
//            RequestDTO rdo = new RequestDTO();
//            rdo.setSender_username(LoginScreen.pto.getUsername());
//            rdo.setReciver_username(selectedUsername);
//            rdo.setScreenIndicator(5); // send rquest to play to the server  
//            oos.writeObject(rdo);
//            oos.flush();
//        } catch (IOException ex) {
//            Logger.getLogger(AvailabilityScreen.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    protected void handleOnBtnSignOut(javafx.event.ActionEvent actionEvent) {
        pto = new PlayerDTO();
        action=actionEvent;
        pto.setUsername(LoginScreen.player.getUsername());
        boolean checkSignOut = ServerHandler.connect().signOut(pto);

        if (checkSignOut) {
            players.remove(pto);
            Parent root = new LoginScreen();
            Mynav.navigateTo(root, actionEvent);
        }

    }
    
    

//    private void handleOnRefreshBtn(ActionEvent event) {
//        try {
//            // Create a new PlayerDTO object to send a refresh request
//            PlayerDTO refreshRequest = new PlayerDTO();
//            refreshRequest.setUsername(LoginScreen.pto.getUsername());
//            refreshRequest.setScreenIndicator(3); // refresh online player list in the table in this screen 
//
//            oos.writeObject(refreshRequest);
//            oos.flush();
//
//            // Clear the current list of players
//            players.clear();
//
//            // Update the TableView
//            updateTableView();
//
//            _refreshBtn.setText("Refreshing...");
//
//            // Re-enable the button after a short delay until the data is restord 
//            new Thread(() -> {
//                try {
//                    Thread.sleep(2000);
//                    Platform.runLater(() -> {
//                        _refreshBtn.setDisable(false);
//                        _refreshBtn.setText("Refresh");
//                    });
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }).start();
//
//        } catch (IOException ex) {
//            Logger.getLogger(AvailabilityScreen.class.getName()).log(Level.SEVERE, "Error refreshing player list", ex);
//            showAlert("Failed to refresh the player list. Please try again.");
//        }
//    }
//    private void resetConnection() {
//        try {
//            if (ois != null) {
//                ois.close();
//            }
//            if (oos != null) {
//                oos.close();
//            }
//            if (socket != null) {
//                socket.close();
//            }
//            initializeConnection(LoginScreen.pto);
//        } catch (IOException ex) {
//            Logger.getLogger(AvailabilityScreen.class.getName()).log(Level.SEVERE, "Failed to reset connection", ex);
//        }
//    }
//
//    private void showGameRequestAlert(RequestDTO request) {
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to play a game with " + request.getSender_username() + "?", new ButtonType("Cancel"), new ButtonType("Okay"));
//        alert.setTitle("Game Request");
//        alert.setHeaderText("Game Request from " + request.getSender_username());
//
//        Optional<ButtonType> result = alert.showAndWait();
//        if (result.isPresent() && result.get() == ButtonType.OK) {
//            sendGameResponse(request, true);
//        } else {
//            sendGameResponse(request, false);
//        }
//    }
//    private void sendGameResponse(RequestDTO request, boolean accepted) {
//        try {
//            RequestDTO response = new RequestDTO();
//            response.setSender_username(LoginScreen.pto.getUsername());
//            response.setReciver_username(request.getSender_username());
//            response.setScreenIndicator(accepted ? 6 : 7);
//            oos.writeObject(response);
//            oos.flush();
//        } catch (IOException ex) {
//            Logger.getLogger(AvailabilityScreen.class.getName()).log(Level.SEVERE, "Error sending game response", ex);
//        }
//    }
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
        alert.showAndWait();
    }

//    private void updateTableView() {
//        Platform.runLater(() -> {
//            _tableView.getItems().clear();
//            for (PlayerDTO player : players) {
//                if (!player.getUsername().equals(currentUsername)) {
//                    _tableView.getItems().add(player);
//                }
//            }
//        });
//    }
//    public void initializeConnection(PlayerDTO playerDTO) {
//        try {
//            socket = new Socket("10.178.240.36", 6007);
//            oos = new ObjectOutputStream(socket.getOutputStream());
//            ois = new ObjectInputStream(socket.getInputStream());
//
//            playerDTO.setScreenIndicator(3);
//            oos.writeObject(playerDTO);
//            oos.flush();
//
//            Thread receiveThread = new Thread(this);
//            receiveThread.start();
//
//            this.currentUsername = playerDTO.getUsername();
//            updateTableView();
//        } catch (IOException ex) {
//            Logger.getLogger(AvailabilityScreen.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    private void updateTableView() {
        Platform.runLater(() -> {
            _tableView.getItems().clear();
            for (PlayerDTO player : players) {
                if (!player.getUsername().equals(currentUsername)) {
                    _tableView.getItems().add(player);
                }
            }
        });
    }
    
    public void navScreen ()
            
    {
        
         Parent root = new OnlineHomePageScreen();
            Mynav.navigateTo(root, action);
        
    }
   
}