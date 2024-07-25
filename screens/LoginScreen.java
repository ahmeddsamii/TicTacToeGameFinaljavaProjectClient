package screens;

import DataAccessLayer.PlayerDTO;
import DataAccessLayer.RequestDTO;
import ServerHanlder.ServerHandler;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import tic.toe.game.Mynav;

public class LoginScreen extends AnchorPane {

    protected final AnchorPane anchorPane;
    protected final Label label;
    protected final Label label0;
    protected final TextField _idTextField;
    protected final TextField _passwordTextField;
    protected final TextField _ipAddressTextField;
    protected final Label label1;
    protected final Label _signUpLabel;
    protected final Button _submitBtn;
    protected final Label _ipLabel;
    protected final Label label2;
    protected final Label label3;
    public static PlayerDTO player;
    Socket s;

    public LoginScreen() {
        anchorPane = new AnchorPane();
        label = new Label();
        label0 = new Label();
        _idTextField = new TextField();
        _passwordTextField = new PasswordField();
        label1 = new Label();
        _signUpLabel = new Label();
        _submitBtn = new Button();
        label2 = new Label();
        label3 = new Label();
        _ipLabel = new Label();
        _ipAddressTextField = new TextField();
        player = new PlayerDTO();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(600.0);
        setPrefWidth(600.0);
        setStyle("-fx-background-color: #008080;");

        AnchorPane.setBottomAnchor(anchorPane, 20.0);
        AnchorPane.setLeftAnchor(anchorPane, 20.0);
        AnchorPane.setTopAnchor(anchorPane, 20.0);
        anchorPane.setPrefHeight(300.0);
        anchorPane.setPrefWidth(430.0);
        anchorPane.setStyle("-fx-background-color: #FFA500;");

        label.setLayoutX(46.0);
        label.setLayoutY(211.0);
        label.setText("Username:");
        label.setTextFill(javafx.scene.paint.Color.valueOf("#333333"));

        label0.setLayoutX(48.0);
        label0.setLayoutY(270.0);
        label0.setText("Password:");
        label0.setTextFill(javafx.scene.paint.Color.valueOf("#333333"));

        _ipLabel.setLayoutX(40.0);
        _ipLabel.setLayoutY(329.0);
        _ipLabel.setText("IP Address:");
        _ipLabel.setTextFill(javafx.scene.paint.Color.valueOf("#333333"));

        _idTextField.setLayoutX(125.0);
        _idTextField.setLayoutY(206.0);
        _idTextField.setOnAction(this::handleOnUsernameTextField);
        _idTextField.setPrefHeight(31.0);
        _idTextField.setPrefWidth(210.0);

        _passwordTextField.setLayoutX(125.0);
        _passwordTextField.setLayoutY(265.0);
        _passwordTextField.setOnAction(this::handleOnPasswordTextField);
        _passwordTextField.setPrefHeight(31.0);
        _passwordTextField.setPrefWidth(210.0);

        _ipAddressTextField.setLayoutX(125.0);
        _ipAddressTextField.setLayoutY(322.5);
        _ipAddressTextField.setPrefHeight(31.0);
        _ipAddressTextField.setPrefWidth(210.0);

        label1.setLayoutX(116.0);
        label1.setLayoutY(368.0);
        label1.setText("Don't have an account?");

        _signUpLabel.setLayoutX(285.0);
        _signUpLabel.setLayoutY(368.0);
        _signUpLabel.setOnMouseClicked(this::handleOnSignUpLabel);
        _signUpLabel.setText("Sign Up");
        _signUpLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        _signUpLabel.setUnderline(true);

        _submitBtn.setLayoutX(160.0);
        _submitBtn.setLayoutY(400.0);
        _submitBtn.setMnemonicParsing(false);
        _submitBtn.setOnAction(this::handleOnBtnSubmit);
        _submitBtn.setPrefHeight(38.0);
        _submitBtn.setPrefWidth(124.0);
        _submitBtn.setStyle("-fx-background-color: #008080;");
        _submitBtn.setText("Login");
        _submitBtn.setTextFill(javafx.scene.paint.Color.ORANGE);
        _submitBtn.setFont(new Font("Britannic Bold", 15.0));

        label2.setLayoutX(123.0);
        label2.setLayoutY(3.0);
        label2.setPrefHeight(174.0);
        label2.setPrefWidth(53.0);
        label2.setRotate(-4.8);
        label2.setText("X");
        label2.setTextAlignment(javafx.scene.text.TextAlignment.RIGHT);
        label2.setTextFill(javafx.scene.paint.Color.TEAL);
        label2.setFont(new Font("Lucida Calligraphy Italic", 96.0));

        label3.setLayoutX(221.0);
        label3.setLayoutY(36.0);
        label3.setRotate(-9.5);
        label3.setText("O");
        label3.setTextFill(javafx.scene.paint.Color.TEAL);
        label3.setFont(new Font("Palatino Linotype Bold", 96.0));

        anchorPane.getChildren().add(label);
        anchorPane.getChildren().add(label0);
        anchorPane.getChildren().add(_idTextField);
        anchorPane.getChildren().add(_passwordTextField);
        anchorPane.getChildren().add(label1);
        anchorPane.getChildren().add(_signUpLabel);
        anchorPane.getChildren().add(_submitBtn);
        anchorPane.getChildren().add(label2);
        anchorPane.getChildren().add(label3);
        anchorPane.getChildren().add(_ipLabel);
        anchorPane.getChildren().add(_ipAddressTextField);

        getChildren().add(anchorPane);
    }

    protected void handleOnUsernameTextField(javafx.event.ActionEvent actionEvent) {
    }

    protected void handleOnPasswordTextField(javafx.event.ActionEvent actionEvent) {
    }

    protected void handleOnSignUpLabel(javafx.scene.input.MouseEvent mouseEvent) {
        Parent root = new SignUpScreen();
        Mynav.navigateTo(root, mouseEvent);
    }

    protected void handleOnBtnSubmit(javafx.event.ActionEvent actionEvent) {
        player.setUsername(_idTextField.getText());
        player.setPassword(_passwordTextField.getText());
        player.setIP(_ipAddressTextField.getText());
        
        //ServerHandler serverHandler = new ServerHandler(ip, port);
        boolean check = ServerHandler.connect().login(player);
        if (check) {
            System.out.println("Login successful for user: " + player.getUsername());

            Parent root = new AvailabilityScreen(player);
            Mynav.navigateTo(root, actionEvent);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Login failed. Please check your credentials.");
            alert.show();
        }
    }
}
