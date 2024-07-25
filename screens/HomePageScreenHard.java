package screens;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import static javafx.scene.layout.Region.USE_PREF_SIZE;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import tic.toe.game.Mynav;
import videos.VideoPlayer;

public class HomePageScreenHard extends AnchorPane {

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
    protected final Button _recordTheGameBtn;
    protected final Button _backBtn;
    protected final Button _replayGameBtn;

    private VideoPlayer videoPlayer;
    private final String mediaFilePathWin = "file:///C:/Users/Ahmed/Downloads/20-7/20-7/GitProjectTicTacToeClient/src/videos/win.mp4";
    private final String mediaFilePathLose = "file:///C:/Users/Ahmed/Downloads/20-7/20-7/GitProjectTicTacToeClient/src/videos/lose.mp4";
    private char currentPlayer = 'X'; // 'X' starts the game
    private int xScore = 0;
    private int oScore = 0;
    private Button[][] buttons = new Button[3][3];

    private boolean isRecording = false;
    private File recordFile;
    private int count = 0;

    public HomePageScreenHard() {

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
        _recordTheGameBtn = new Button();
        _backBtn = new Button();
        _replayGameBtn = new Button();

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
                buttons[row][col].setOnAction(event -> handleButtonClick(event));
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
        _exitBtn.setLayoutY(700.0);
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
        _exitBtn.setTextFill(javafx.scene.paint.Color.ORANGE);
        _exitBtn.setFont(new Font("System Bold Italic", 21.0));

        _recordTheGameBtn.setLayoutX(820.0);
        _recordTheGameBtn.setLayoutY(556.0);
        _recordTheGameBtn.setMaxHeight(50.0);
        _recordTheGameBtn.setMaxWidth(250.0);
        _recordTheGameBtn.setMinHeight(50.0);
        _recordTheGameBtn.setMinWidth(250.0);
        _recordTheGameBtn.setMnemonicParsing(false);
        _recordTheGameBtn.setOnAction(this::handleOnRecordGameBtn);
        _recordTheGameBtn.setPrefHeight(50.0);
        _recordTheGameBtn.setPrefWidth(250.0);
        _recordTheGameBtn.setStyle("-fx-background-color: #008080; -fx-background-radius: 20;");
        _recordTheGameBtn.setText("Record The Game");
        _recordTheGameBtn.setTextFill(javafx.scene.paint.Color.ORANGE);
        _recordTheGameBtn.setFont(new Font("System Bold Italic", 21.0));

        _backBtn.setLayoutX(25.0);
        _backBtn.setLayoutY(44.0);
        _backBtn.setMaxHeight(50.0);
        _backBtn.setMaxWidth(250.0);
        _backBtn.setMinHeight(50.0);
        _backBtn.setMinWidth(250.0);
        _backBtn.setMnemonicParsing(false);
        _backBtn.setOnAction(this::handleOnBackBtn);
        _backBtn.setPrefHeight(50.0);
        _backBtn.setPrefWidth(250.0);
        _backBtn.setStyle("-fx-background-color: #008080; -fx-background-radius: 20;");
        _backBtn.setText("Back");
        _backBtn.setTextFill(javafx.scene.paint.Color.ORANGE);
        _backBtn.setFont(new Font("System Bold Italic", 21.0));

        _replayGameBtn.setLayoutX(820.0);
        _replayGameBtn.setLayoutY(627.0);
        _replayGameBtn.setMaxHeight(50.0);
        _replayGameBtn.setMaxWidth(250.0);
        _replayGameBtn.setMinHeight(50.0);
        _replayGameBtn.setMinWidth(250.0);
        _replayGameBtn.setMnemonicParsing(false);
        _replayGameBtn.setOnAction(this::handleOnReplayGameBtn);
        _replayGameBtn.setPrefHeight(50.0);
        _replayGameBtn.setPrefWidth(250.0);
        _replayGameBtn.setStyle("-fx-background-color: #008080; -fx-background-radius: 20;");
        _replayGameBtn.setText("Replay Game");
        _replayGameBtn.setTextFill(javafx.scene.paint.Color.ORANGE);
        _replayGameBtn.setFont(new Font("System Bold Italic", 21.0));

        getChildren().add(anchorPane);
        anchorPane.getChildren().add(gridPane);
        gridPane.getColumnConstraints().add(columnConstraints);
        gridPane.getColumnConstraints().add(columnConstraints0);
        gridPane.getColumnConstraints().add(columnConstraints1);
        gridPane.getRowConstraints().add(rowConstraints);
        gridPane.getRowConstraints().add(rowConstraints0);
        gridPane.getRowConstraints().add(rowConstraints1);
        anchorPane.getChildren().add(label);
        anchorPane.getChildren().add(label0);
        anchorPane.getChildren().add(label1);
        anchorPane.getChildren().add(label2);
        anchorPane.getChildren().add(_xScoreLabel);
        anchorPane.getChildren().add(label3);
        anchorPane.getChildren().add(_oScoreLabel);
        anchorPane.getChildren().add(_newGameBtn);
        anchorPane.getChildren().add(_exitBtn);
        anchorPane.getChildren().add(_recordTheGameBtn);
        anchorPane.getChildren().add(_backBtn);
        anchorPane.getChildren().add(_replayGameBtn);
    }

    private void handleButtonClick(ActionEvent event) {
        Button button = (Button) event.getSource();
        if (!button.getText().isEmpty()) {
            return;
        }
        button.setText(String.valueOf(currentPlayer));
        if (isRecording && recordFile != null) {
            recordMoveToFile(button);
        }
        if (checkWin(currentPlayer)) {
            updateScore(currentPlayer);
            showWinMessage(currentPlayer);
            clearBoard();
        } else if (isBoardFull()) {
            showDrawMessage();
            clearBoard();
        } else {
            switchPlayer();
            if (currentPlayer == 'O') {
                computerMove();
            }
        }
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    private void computerMove() {
        int[] bestMove = findBestMove();
        int row = bestMove[0];
        int col = bestMove[1];
        buttons[row][col].setText(String.valueOf(currentPlayer));
        if (isRecording && recordFile != null) {
            recordMoveToFile(buttons[row][col]);
        }
        if (checkWin(currentPlayer)) {
            if (currentPlayer == 'X') {
                updateScore(currentPlayer);
                showWinMessage(currentPlayer);
                clearBoard();

            } else if (currentPlayer == 'O') {
                updateScore(currentPlayer);
                showLoseMessage(currentPlayer);
                clearBoard();
            }
        } else if (isBoardFull()) {
            showDrawMessage();
            clearBoard();
        } else {
            switchPlayer();
        }
    }

    private int[] findBestMove() {
        int bestScore = Integer.MIN_VALUE;
        int[] move = new int[2];

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().isEmpty()) {
                    buttons[row][col].setText(String.valueOf(currentPlayer));
                    int score = minimax(buttons, 0, false);
                    buttons[row][col].setText("");
                    if (score > bestScore) {
                        bestScore = score;
                        move[0] = row;
                        move[1] = col;
                    }
                }
            }
        }

        return move;
    }

    private int minimax(Button[][] board, int depth, boolean isMaximizing) {
        if (checkWin('X')) {
            return -10;
        } else if (checkWin('O')) {
            return 10;
        } else if (isBoardFull()) {
            return 0;
        }

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (board[row][col].getText().isEmpty()) {
                        board[row][col].setText("O");
                        int score = minimax(board, depth + 1, false);
                        board[row][col].setText("");
                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (board[row][col].getText().isEmpty()) {
                        board[row][col].setText("X");
                        int score = minimax(board, depth + 1, true);
                        board[row][col].setText("");
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
            return bestScore;
        }
    }

    private boolean checkWin(char player) {
        for (int row = 0; row < 3; row++) {
            if (buttons[row][0].getText().equals(String.valueOf(player))
                    && buttons[row][1].getText().equals(String.valueOf(player))
                    && buttons[row][2].getText().equals(String.valueOf(player))) {
                return true;
            }
        }

        for (int col = 0; col < 3; col++) {
            if (buttons[0][col].getText().equals(String.valueOf(player))
                    && buttons[1][col].getText().equals(String.valueOf(player))
                    && buttons[2][col].getText().equals(String.valueOf(player))) {
                return true;
            }
        }

        if (buttons[0][0].getText().equals(String.valueOf(player))
                && buttons[1][1].getText().equals(String.valueOf(player))
                && buttons[2][2].getText().equals(String.valueOf(player))) {
            return true;
        }

        if (buttons[0][2].getText().equals(String.valueOf(player))
                && buttons[1][1].getText().equals(String.valueOf(player))
                && buttons[2][0].getText().equals(String.valueOf(player))) {
            return true;
        }

        return false;
    }

    private boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void updateScore(char player) {
        if (player == 'X') {
            xScore++;
            _xScoreLabel.setText(String.valueOf(xScore));
        } else if (player == 'O') {
            oScore++;
            _oScoreLabel.setText(String.valueOf(oScore));
        }
    }

    private void showLoseMessage(char player) {

        videoPlayer.playVideo(mediaFilePathLose);

    }

    private void showDrawMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "It's a draw!", ButtonType.OK);
        alert.showAndWait();
    }

    private void showWinMessage(char player) {

        videoPlayer.playVideo(mediaFilePathWin);

    }

    private void clearBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
            }
        }
        currentPlayer = 'X';
    }

    private void recordMoveToFile(Button button) {
        int row = GridPane.getRowIndex(button);
        int col = GridPane.getColumnIndex(button);
        try (FileWriter writer = new FileWriter(recordFile, true)) {
            writer.write(currentPlayer + "," + row + "," + col + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleOnRecordGameBtn(ActionEvent even) {
        if (!isRecording) {
            isRecording = true;
            String fileName = "Won_" + System.currentTimeMillis() + ".txt";
            recordFile = new File("C:\\Users\\Ahmed\\Downloads\\20-7\\20-7\\GitProjectTicTacToeClient\\recordEasy\\" + fileName);
            try (FileWriter writer = new FileWriter(recordFile)) {
                writer.write("");
            } catch (IOException e) {
                e.printStackTrace();
            }
            _recordTheGameBtn.setText("Stop Recording");
        } else {
            isRecording = false;
            _recordTheGameBtn.setText("Record the game");
        }
    }

    private void handleOnNewGameBtn(ActionEvent event) {
        clearBoard();
        currentPlayer = 'X';
    }

    private void handleOnExitBtn(ActionEvent event) {
        Platform.exit();
    }

    private void handleOnRecordTheGameBtn(ActionEvent event) {
        if (!isRecording) {
            isRecording = true;
            String fileName = "Won_" + System.currentTimeMillis() + ".txt";
            recordFile = new File("C:\\Users\\Ahmed\\Downloads\\20-7\\20-7\\GitProjectTicTacToeClient\\recordHard\\" + fileName);
            try (FileWriter writer = new FileWriter(recordFile)) {
                writer.write("");
            } catch (IOException e) {
                e.printStackTrace();
            }
            _recordTheGameBtn.setText("Stop Recording");
        } else {
            isRecording = false;
            _recordTheGameBtn.setText("Record the game");
        }
    }

    private void handleOnBackBtn(ActionEvent event) {
        Parent root = new DifficultyScreen();
        Mynav.navigateTo(root, event);
    }

    private void handleOnReplayGameBtn(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Replay File");
        fileChooser.setInitialDirectory(new File("C:\\Users\\Ahmed\\Downloads\\20-7\\20-7\\GitProjectTicTacToeClient\\recordEasy"));
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Text Files", "*.txt"));
        File replayFile = fileChooser.showOpenDialog(null);
        if (replayFile != null) {
            replayGame(replayFile);
        }
    }

    private void replayGame(File file) {
        new Thread(() -> {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                Platform.runLater(this::clearBoard);
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    char player = parts[0].charAt(0);
                    int row = Integer.parseInt(parts[1]);
                    int col = Integer.parseInt(parts[2]);
                    Platform.runLater(() -> buttons[row][col].setText(String.valueOf(player)));
                    Thread.sleep(500); // Delay for better visualization
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
