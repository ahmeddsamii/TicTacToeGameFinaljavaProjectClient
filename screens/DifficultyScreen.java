package screens;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import screens.HomePageScreenEasy;
import screens.MultiModesScreen;
import tic.toe.game.Mynav;

public class DifficultyScreen extends AnchorPane {

    protected final AnchorPane anchorPane;
    protected final Button _easyLevelButton;
    protected final Button _mediumLevelButton;
    protected final Button _hardLevelButton;
    protected final Button _backButton; // New back button

    public DifficultyScreen() {

        anchorPane = new AnchorPane();
        _easyLevelButton = new Button();
        _mediumLevelButton = new Button();
        _hardLevelButton = new Button();
        _backButton = new Button(); // Initialize the back button

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(600.0);
        setPrefWidth(880.0);
        setStyle("-fx-background-color: #008080;");

        AnchorPane.setBottomAnchor(anchorPane, 20.0);
        AnchorPane.setLeftAnchor(anchorPane, 20.0);
        AnchorPane.setRightAnchor(anchorPane, 20.0);
        AnchorPane.setTopAnchor(anchorPane, 20.0);
        anchorPane.setLayoutX(20.0);
        anchorPane.setLayoutY(20.0);
        anchorPane.setPrefHeight(300.0);
        anchorPane.setPrefWidth(800.0);
        anchorPane.setStyle("-fx-background-color: #FFA500;");

        _easyLevelButton.setLayoutX(14.0);
        _easyLevelButton.setLayoutY(103.0);
        _easyLevelButton.setMnemonicParsing(false);
        _easyLevelButton.setOnAction(this::handleOnBtnEasyLevel);
        _easyLevelButton.setPrefHeight(89.0);
        _easyLevelButton.setPrefWidth(291.0);
        _easyLevelButton.setStyle("-fx-background-color: #008080; -fx-background-radius: 50px;");
        _easyLevelButton.setText("Easy Level");
        _easyLevelButton.setTextFill(javafx.scene.paint.Color.ORANGE);
        _easyLevelButton.setFont(new Font("System Bold Italic", 40.0));

        _mediumLevelButton.setLayoutX(242.0);
        _mediumLevelButton.setLayoutY(236.0);
        _mediumLevelButton.setMnemonicParsing(false);
        _mediumLevelButton.setOnAction(this::handleOnBtnMediumLevel);
        _mediumLevelButton.setPrefHeight(89.0);
        _mediumLevelButton.setPrefWidth(356.0);
        _mediumLevelButton.setStyle("-fx-background-color: #008080; -fx-background-radius: 50px;");
        _mediumLevelButton.setText("Medium Level");
        _mediumLevelButton.setTextFill(javafx.scene.paint.Color.ORANGE);
        _mediumLevelButton.setFont(new Font("System Bold Italic", 40.0));

        _hardLevelButton.setLayoutX(535.0);
        _hardLevelButton.setLayoutY(372.0);
        _hardLevelButton.setMnemonicParsing(false);
        _hardLevelButton.setOnAction(this::handleOnBtnHardLevel);
        _hardLevelButton.setPrefHeight(89.0);
        _hardLevelButton.setPrefWidth(291.0);
        _hardLevelButton.setStyle("-fx-background-color: #008080; -fx-background-radius: 50px;");
        _hardLevelButton.setText("Hard Level");
        _hardLevelButton.setTextFill(javafx.scene.paint.Color.ORANGE);
        _hardLevelButton.setFont(new Font("System Bold Italic", 40.0));

        // Configure the back button
        _backButton.setLayoutX(20.0);
        _backButton.setLayoutY(500.0); // Position at the bottom left
        _backButton.setMnemonicParsing(false);
        _backButton.setOnAction(this::handleOnBtnBack); // Set the back button action
        _backButton.setPrefHeight(50.0);
        _backButton.setPrefWidth(100.0);
        _backButton.setStyle("-fx-background-color: #008080; -fx-background-radius: 50px;");
        _backButton.setText("Back");
        _backButton.setTextFill(javafx.scene.paint.Color.ORANGE);
        _backButton.setFont(new Font("System Bold Italic", 20.0));

        anchorPane.getChildren().addAll(_easyLevelButton, _mediumLevelButton, _hardLevelButton, _backButton);
        getChildren().add(anchorPane);
    }

    protected void handleOnBtnEasyLevel(javafx.event.ActionEvent actionEvent) {
        Parent parent = new HomePageScreenEasy();
        Mynav.navigateTo(parent, actionEvent);
    }

    protected void handleOnBtnMediumLevel(javafx.event.ActionEvent actionEvent) {
        Parent parent = new HomePageScreenMedium();
        Mynav.navigateTo(parent, actionEvent);
    }

    protected void handleOnBtnHardLevel(javafx.event.ActionEvent actionEvent) {
        Parent parent = new HomePageScreenHard();
        Mynav.navigateTo(parent, actionEvent);
    }

    protected void handleOnBtnBack(javafx.event.ActionEvent actionEvent) {
        Parent parent = new MultiModesScreen();
        Mynav.navigateTo(parent, actionEvent);
    }
}