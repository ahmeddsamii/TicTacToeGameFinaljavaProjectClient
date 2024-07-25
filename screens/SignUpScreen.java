package screens;

import DataAccessLayer.PlayerDTO;
import ServerHanlder.ServerHandler;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import tic.toe.game.Mynav;

public class SignUpScreen extends AnchorPane {

    protected final AnchorPane anchorPane;
    protected final Label label;
    protected final Label label0;
    protected final Label labelEmail;
    protected final TextField _userCreateTextField;
    protected final TextField _emailTextField;
    protected final PasswordField _createPasswordTextField;
    protected final Label label1;
    protected final Label _signInLabel;
    protected final Button _createAccountBtn;
    protected final Label label2;
    protected final Label label3;
    protected final PasswordField _rePasswordTextField1;
    protected final Label label4;
    protected final Label _ipLabel;
    protected final TextField _ipTextField;

    private PlayerDTO pto;

    private Socket s;

    public SignUpScreen() {

        anchorPane = new AnchorPane();
        label = new Label();
        label0 = new Label();
        labelEmail = new Label();
        _userCreateTextField = new TextField();
        _emailTextField = new TextField();
        _createPasswordTextField = new PasswordField();
        label1 = new Label();
        _signInLabel = new Label();
        _createAccountBtn = new Button();
        label2 = new Label();
        label3 = new Label();
        _rePasswordTextField1 = new PasswordField();
        label4 = new Label();
        _ipLabel = new Label();
        _ipTextField = new TextField();

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

        label.setLayoutX(65.0);
        label.setLayoutY(181.0);
        label.setText("Username:");
        label.setTextFill(javafx.scene.paint.Color.valueOf("#333333"));

        labelEmail.setLayoutX(95.0);
        labelEmail.setLayoutY(230.0);
        labelEmail.setText("Email:");
        labelEmail.setTextFill(javafx.scene.paint.Color.valueOf("#333333"));

        _ipLabel.setLayoutX(60.0);
        _ipLabel.setLayoutY(279.0);
        _ipLabel.setText("Ip Address:");
        _ipLabel.setTextFill(javafx.scene.paint.Color.valueOf("#333333"));

        label0.setLayoutX(69.0);
        label0.setLayoutY(325.0);
        label0.setText("Password:");
        label0.setTextFill(javafx.scene.paint.Color.valueOf("#333333"));

        _userCreateTextField.setLayoutX(143.0);
        _userCreateTextField.setLayoutY(176.0);
        _userCreateTextField.setPrefHeight(31.0);
        _userCreateTextField.setPrefWidth(210.0);
        _userCreateTextField.setPromptText("Enter Username");

        _emailTextField.setLayoutX(143.0);
        _emailTextField.setLayoutY(225.0);
        _emailTextField.setPrefHeight(31.0);
        _emailTextField.setPrefWidth(210.0);
        _emailTextField.setPromptText("Enter email");

        _ipTextField.setLayoutX(143.0);
        _ipTextField.setLayoutY(273.0);
        _ipTextField.setPrefHeight(31.0);
        _ipTextField.setPrefWidth(210.0);
        _ipTextField.setPromptText("Enter Ip Address");

        _createPasswordTextField.setLayoutX(143.0);
        _createPasswordTextField.setLayoutY(320.0);
        _createPasswordTextField.setPrefHeight(31.0);
        _createPasswordTextField.setPrefWidth(210.0);
        _createPasswordTextField.setPromptText("Enter password");

        _rePasswordTextField1.setLayoutX(143.0);
        _rePasswordTextField1.setLayoutY(374.0);
        _rePasswordTextField1.setPrefHeight(31.0);
        _rePasswordTextField1.setPrefWidth(210.0);
        _rePasswordTextField1.setPromptText("Re-enter password");

        label1.setLayoutX(106.0);
        label1.setLayoutY(421.0);
        label1.setText("Already have an account?");

        _signInLabel.setLayoutX(284.0);
        _signInLabel.setLayoutY(421.0);
        _signInLabel.setText("Sign In");
        _signInLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        _signInLabel.setUnderline(true);
        _signInLabel.setOnMouseClicked(this::handleOnSignInLabel);

        _createAccountBtn.setLayoutX(160.0);
        _createAccountBtn.setLayoutY(470.0);
        _createAccountBtn.setMnemonicParsing(false);
        _createAccountBtn.setOnAction(this::handleOnBtnCreate);
        _createAccountBtn.setPrefHeight(38.0);
        _createAccountBtn.setPrefWidth(124.0);
        _createAccountBtn.setStyle("-fx-background-color: #008080;");
        _createAccountBtn.setText("Create account");
        _createAccountBtn.setTextFill(javafx.scene.paint.Color.ORANGE);
        _createAccountBtn.setFont(new Font("Britannic Bold", 15.0));

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

        label4.setLayoutX(50.0);
        label4.setLayoutY(377.0);
        label4.setText("re-password:");
        label4.setTextFill(javafx.scene.paint.Color.valueOf("#333333"));

        anchorPane.getChildren().addAll(label, _ipLabel, _ipTextField, label0, labelEmail, _userCreateTextField, _emailTextField,
                _createPasswordTextField, label1, _signInLabel, _createAccountBtn, label2, label3,
                _rePasswordTextField1, label4);
        getChildren().add(anchorPane);
    }

    protected void handleOnSignInLabel(javafx.scene.input.MouseEvent mouseEvent) {
        Parent root = new LoginScreen();
        Mynav.navigateTo(root, mouseEvent);
    }

    protected void handleOnBtnCreate(javafx.event.ActionEvent actionEvent) {

        pto = new PlayerDTO();
        pto.setUsername(_userCreateTextField.getText());
        pto.setEmail(_emailTextField.getText());
        pto.setPassword(_createPasswordTextField.getText());
        pto.setIP(_ipTextField.getText());

        // Check if any of the required fields are empty
        if (_createPasswordTextField.getText().isEmpty() || _userCreateTextField.getText().isEmpty()
                || _rePasswordTextField1.getText().isEmpty() || _emailTextField.getText().isEmpty() || _userCreateTextField.getText().startsWith(" ")) {
            showErrorAlert("Error", "All fields must be filled or cannot start with space");
        } else if (!_createPasswordTextField.getText().equals(_rePasswordTextField1.getText())) {
            showErrorAlert("Error", "The passwords do not match");
        }else
        {
            boolean checkSignUp = ServerHandler.connect().signUp(pto);
            if(checkSignUp)
            {
                showConfirmationAlert("Complete", "Sign up successfully");
            }else
            {
                showErrorAlert("Error", "This username is already exits!");
            }
        }

    }

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, new ButtonType("Okay"));
        alert.showAndWait();
    }

    void showConfirmationAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, message, new ButtonType("Okay"));

        alert.showAndWait();
    }

    void clearAllFields() {
        _userCreateTextField.clear();
        _createPasswordTextField.clear();
        _emailTextField.clear();
        _rePasswordTextField1.clear();
        _ipTextField.clear();
    }
}
