package screens;

import ServerHanlder.ServerHandler;
import com.sun.org.apache.xpath.internal.operations.Mod;
import screens.*;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import tic.toe.game.Mynav;
import videos.VideoPlayer;

public class OnlineHomePageScreen extends AnchorPane {

    protected final AnchorPane anchorPane;
    protected final GridPane gridPane;
    protected final ColumnConstraints columnConstraints;
    protected final ColumnConstraints columnConstraints0;
    protected final ColumnConstraints columnConstraints1;
    protected final RowConstraints rowConstraints;
    protected final RowConstraints rowConstraints0;
    protected final RowConstraints rowConstraints1;

    protected final Label label;
    protected final Label label0;
    protected final Label label1;
    protected final Label label2;
    protected final Label _xScoreLabel;
    protected final Label label3;
    protected final Label _oScoreLabel;
    protected final Button _newGameBtn;
    protected final Button _exitBtn;
MoveDTO mov;
    private VideoPlayer videoPlayer;
    //private final String mediaFilePathWin = "file:///C:/Users/Ahmed/Downloads/20-7/20-7/GitProjectTicTacToeClient/src/videos/win.mp4";
    //private final String mediaFilePathLose = "file:///C:/Users/Ahmed/Downloads/20-7/20-7/GitProjectTicTacToeClient/src/videos/lose.mp4";
    

    private char currentPlayer = 'X'; // 'X' starts the game
    private int xScore = 0;
    private int oScore = 0;
    private Button[][] buttons = new Button[3][3];

    public OnlineHomePageScreen() {

        // Adjusting media file path to URI format
        videoPlayer = new VideoPlayer();
        anchorPane = new AnchorPane();
        gridPane = new GridPane();
        columnConstraints = new ColumnConstraints();
        columnConstraints0 = new ColumnConstraints();
        columnConstraints1 = new ColumnConstraints();
        rowConstraints = new RowConstraints();
        rowConstraints0 = new RowConstraints();
        rowConstraints1 = new RowConstraints();

        label = new Label();
        label0 = new Label();
        label1 = new Label();
        label2 = new Label();
        _xScoreLabel = new Label();
        label3 = new Label();
        _oScoreLabel = new Label();
        _newGameBtn = new Button();
        _exitBtn = new Button();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(800.0);
        setPrefWidth(1200.0);
        setStyle("-fx-background-color: #008080;");

        anchorPane.setLayoutX(20.0);
        anchorPane.setLayoutY(20.0);
        anchorPane.setPrefHeight(760.0);
        anchorPane.setPrefWidth(1150.0);
        anchorPane.setStyle("-fx-background-color: #FFA500;");

        gridPane.setLayoutX(53.0);
        gridPane.setLayoutY(227.0);
        gridPane.setPrefHeight(450.0);
        gridPane.setPrefWidth(524.0);
        gridPane.setStyle("-fx-background-color: #FFA500; -fx-background-radius: 50;");

        columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints.setMinWidth(10.0);
        columnConstraints.setPrefWidth(100.0);

        columnConstraints0.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints0.setMinWidth(10.0);
        columnConstraints0.setPrefWidth(100.0);

        columnConstraints1.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints1.setMinWidth(10.0);
        columnConstraints1.setPrefWidth(100.0);

        rowConstraints.setMinHeight(10.0);
        rowConstraints.setPrefHeight(30.0);
        rowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints0.setMinHeight(10.0);
        rowConstraints0.setPrefHeight(30.0);
        rowConstraints0.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints1.setMinHeight(10.0);
        rowConstraints1.setPrefHeight(30.0);
        rowConstraints1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        // Initialize buttons in a 3x3 grid
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new Button();
                buttons[row][col].setMinSize(170, 143);
                buttons[row][col].setMaxSize(170, 143);
                buttons[row][col].setStyle("-fx-background-color: #008080; -fx-background-radius: 20;");
                buttons[row][col].setFont(Font.font("System Bold Italic", 40));
                int finalRow = row;
                int finalCol = col;
                buttons[row][col].setOnAction(event -> handleButtonClick(finalRow, finalCol));
                gridPane.add(buttons[row][col], col, row);
            }
        }

        label.setLayoutX(365.0);
        label.setLayoutY(20.0);
        label.setText("Tic Tac Toe game");
        label.setTextFill(javafx.scene.paint.Color.TEAL);
        label.setFont(new Font("System Bold Italic", 55.0));

        label0.setLayoutX(844.0);
        label0.setLayoutY(267.0);
        label0.setText("Player");
        label0.setTextFill(javafx.scene.paint.Color.TEAL);
        label0.setFont(new Font("System Bold Italic", 30.0));

        label1.setLayoutX(985.0);
        label1.setLayoutY(267.0);
        label1.setText("Score");
        label1.setTextFill(javafx.scene.paint.Color.TEAL);
        label1.setFont(new Font("System Bold Italic", 30.0));

        label2.setLayoutX(867.0);
        label2.setLayoutY(326.0);
        label2.setText("X");
        label2.setTextFill(javafx.scene.paint.Color.TEAL);
        label2.setFont(new Font("System Bold Italic", 30.0));

        _xScoreLabel.setLayoutX(1009.0);
        _xScoreLabel.setLayoutY(326.0);
        _xScoreLabel.setText("0");
        _xScoreLabel.setTextFill(javafx.scene.paint.Color.TEAL);
        _xScoreLabel.setFont(new Font("System Bold", 30.0));

        label3.setLayoutX(867.0);
        label3.setLayoutY(390.0);
        label3.setText("O");
        label3.setTextFill(javafx.scene.paint.Color.TEAL);
        label3.setFont(new Font("System Bold Italic", 30.0));

        _oScoreLabel.setLayoutX(1009.0);
        _oScoreLabel.setLayoutY(390.0);
        _oScoreLabel.setText("0");
        _oScoreLabel.setTextFill(javafx.scene.paint.Color.TEAL);
        _oScoreLabel.setFont(new Font("System Bold", 30.0));

        _newGameBtn.setLayoutX(820.0);
        _newGameBtn.setLayoutY(484.0);
        _newGameBtn.setMaxHeight(50.0);
        _newGameBtn.setMaxWidth(250.0);
        _newGameBtn.setMinHeight(50.0);
        _newGameBtn.setMinWidth(250.0);
        _newGameBtn.setMnemonicParsing(false);
        _newGameBtn.setOnAction(this::handleOnNewGameBtn);
        _newGameBtn.setPrefHeight(50.0);
        _newGameBtn.setPrefWidth(250.0);
        _newGameBtn.setStyle("-fx-background-color: #008080; -fx-background-radius: 20;");
        _newGameBtn.setText("New game");
        _newGameBtn.setTextFill(javafx.scene.paint.Color.ORANGE);
        _newGameBtn.setFont(new Font("System Bold Italic", 21.0));

        _exitBtn.setLayoutX(820.0);
        _exitBtn.setLayoutY(580.0);
        _exitBtn.setMaxHeight(50.0);
        _exitBtn.setMaxWidth(250.0);
        _exitBtn.setMinHeight(50.0);
        _exitBtn.setMinWidth(250.0);
        _exitBtn.setMnemonicParsing(false);
        _exitBtn.setOnAction(this::handleOnExitBtn);
        _exitBtn.setPrefHeight(50.0);
        _exitBtn.setPrefWidth(250.0);
        _exitBtn.setStyle("-fx-background-color: #008080; -fx-background-radius: 20;");
        _exitBtn.setText("Exit");
        _exitBtn.setTextFill(javafx.scene.paint.Color.valueOf("#941b1b"));
        _exitBtn.setFont(new Font("System Bold Italic", 21.0));

        
       

        gridPane.getColumnConstraints().addAll(columnConstraints, columnConstraints0, columnConstraints1);
        gridPane.getRowConstraints().addAll(rowConstraints, rowConstraints0, rowConstraints1);

        anchorPane.getChildren().addAll(gridPane, label, label0, label1, label2, _xScoreLabel, label3, _oScoreLabel,
                _newGameBtn, _exitBtn);
        getChildren().add(anchorPane);
    }

    // Event handler for button clicks in the grid
public void handleButtonClick(int row, int col) {
    if (buttons[row][col].getText().isEmpty()) {
        buttons[row][col].setText(String.valueOf(currentPlayer));
        buttons[row][col].setDisable(true);

        mov = new MoveDTO();
        mov.setCol(col);
        mov.setRow(row);
        mov.setReciver_userName(AvailabilityScreen.recever);
        mov.setSender_userName(LoginScreen.player.getUsername());
        ServerHandler.connect().sendMoveToServer(mov);

        checkWin();
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }
}


    // Method to check if current player wins
    private void checkWin() {
        boolean emptyFound = false;

        // Check rows
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(buttons[i][1].getText())
                    && buttons[i][0].getText().equals(buttons[i][2].getText())
                    && !buttons[i][0].getText().isEmpty()) {
                handleWin(buttons[i][0].getText().charAt(0));
                return;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (buttons[0][i].getText().equals(buttons[1][i].getText())
                    && buttons[0][i].getText().equals(buttons[2][i].getText())
                    && !buttons[0][i].getText().isEmpty()) {
                handleWin(buttons[0][i].getText().charAt(0));
                return;
            }
        }

        // Check diagonals
        if (buttons[0][0].getText().equals(buttons[1][1].getText())
                && buttons[0][0].getText().equals(buttons[2][2].getText())
                && !buttons[0][0].getText().isEmpty()) {
            handleWin(buttons[0][0].getText().charAt(0));
            return;
        }

        if (buttons[0][2].getText().equals(buttons[1][1].getText())
                && buttons[0][2].getText().equals(buttons[2][0].getText())
                && !buttons[0][2].getText().isEmpty()) {
            handleWin(buttons[0][2].getText().charAt(0));
            return;
        }

        // Check if all buttons are filled
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().isEmpty()) {
                    emptyFound = true;
                    break;
                }
            }
            if (emptyFound) {
                break;
            }
        }

        // If no winner and no empty cells left, it's a draw
        if (!emptyFound) {
            handleDraw();
        }
    }

    private void handleDraw() {
        // Handle draw scenario here 
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "It's a draw", ButtonType.OK);
        alert.show();
        resetGame();
        enableAllButtons();
    }

    // Adjusted handleWin method
    private void handleWin(char winner) {
        if (winner == 'X') {
            xScore++;
            _xScoreLabel.setText(String.valueOf(xScore));

            //videoPlayer.playVideo(mediaFilePathWin);

        } else {
            oScore++;
            _oScoreLabel.setText(String.valueOf(oScore));
            //videoPlayer.playVideo(mediaFilePathLose);
        }

        // Reset the game after a win
        resetGame();
        disableAllButtons();
    }

    // Method to reset the game
    private void resetGame() {
        currentPlayer = 'X';
        // Clear all buttons
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
            }
        }
    }

    private void enableAllButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setDisable(false);
            }
        }
    }

    private void disableAllButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setDisable(false);
            }
        }
    }

    protected void handleOnNewGameBtn(ActionEvent actionEvent) {
        resetGame();
        enableAllButtons();
    }

    protected void handleOnExitBtn(ActionEvent actionEvent) {
        Platform.exit();
    }

    

    protected void handleOnBackBtn(ActionEvent actionEvent) {
        Parent root = new OnlineAndOfflineScreen();
        Mynav.navigateTo(root, actionEvent);
    }
     public void setMove(MoveDTO move) {

            int row = move.getRow();
            int col = move.getCol();
            buttons[row][col].setText(String.valueOf(currentPlayer));
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }
}
