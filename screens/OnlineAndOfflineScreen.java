package screens;

import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import tic.toe.game.LeadBoardScreenBase;
import tic.toe.game.Mynav;

public class OnlineAndOfflineScreen extends AnchorPane {

    protected final AnchorPane anchorPane;
    protected final Label label;
    protected final Label label0;
    protected final ImageView _onlineModeImageView;
    protected final ImageView _offlineModeImageview;
    protected final Button _leadBoardBtn;
    protected final Button _signOutBtn;
    protected final Button _aboutBtn;
    protected final Button _recordBtn1;

    public OnlineAndOfflineScreen() {

        anchorPane = new AnchorPane();
        label = new Label();
        label0 = new Label();
        _onlineModeImageView = new ImageView();
        _offlineModeImageview = new ImageView();
        _leadBoardBtn = new Button();
        _signOutBtn = new Button();
        _aboutBtn = new Button();
        _recordBtn1 = new Button();

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

        label.setLayoutX(109.0);
        label.setLayoutY(98.0);
        label.setText("Online Mode");
        label.setFont(new Font("System Bold Italic", 30.0));

        label0.setLayoutX(528.0);
        label0.setLayoutY(98.0);
        label0.setText("Offline Mode");
        label0.setFont(new Font("System Bold Italic", 30.0));

        _onlineModeImageView.setFitHeight(227.0);
        _onlineModeImageView.setFitWidth(317.0);
        _onlineModeImageView.setLayoutX(44.0);
        _onlineModeImageView.setLayoutY(152.0);
        _onlineModeImageView.setOnMouseClicked(this::handleOnOnlineMode);
        _onlineModeImageView.setPickOnBounds(true);
        _onlineModeImageView.setImage(new Image(getClass().getResource("/images/online.jpg").toExternalForm()));

        _offlineModeImageview.setFitHeight(245.0);
        _offlineModeImageview.setFitWidth(293.0);
        _offlineModeImageview.setLayoutX(478.0);
        _offlineModeImageview.setLayoutY(150.0);
        _offlineModeImageview.setOnMouseClicked(this::handleOnOfflineMode);
        _offlineModeImageview.setPickOnBounds(true);
        _offlineModeImageview.setImage(new Image(getClass().getResource("/images/offline.png").toExternalForm()));

        _leadBoardBtn.setLayoutX(50.0);
        _leadBoardBtn.setLayoutY(468.0);
        _leadBoardBtn.setMnemonicParsing(false);
        _leadBoardBtn.setOnAction(this::handleOnBtnLeadBoard);
        _leadBoardBtn.setPrefHeight(61.0);
        _leadBoardBtn.setPrefWidth(153.0);
        _leadBoardBtn.setStyle("-fx-background-color: #008080;");
        _leadBoardBtn.setText("Lead Board");
        _leadBoardBtn.setTextFill(javafx.scene.paint.Color.ORANGE);
        _leadBoardBtn.setFont(new Font("System Bold Italic", 21.0));

        _signOutBtn.setLayoutX(604.0);
        _signOutBtn.setLayoutY(468.0);
        _signOutBtn.setMnemonicParsing(false);
        _signOutBtn.setOnAction(this::handleOnBtnBack);
        _signOutBtn.setPrefHeight(61.0);
        _signOutBtn.setPrefWidth(153.0);
        _signOutBtn.setStyle("-fx-background-color: #008080;");
        _signOutBtn.setText("Back");
        _signOutBtn.setTextFill(javafx.scene.paint.Color.ORANGE);
        _signOutBtn.setFont(new Font("System Bold Italic", 21.0));

        _aboutBtn.setLayoutX(236.0);
        _aboutBtn.setLayoutY(468.0);
        _aboutBtn.setMnemonicParsing(false);
        _aboutBtn.setOnAction(this::handleOnBtnAbout);
        _aboutBtn.setPrefHeight(61.0);
        _aboutBtn.setPrefWidth(153.0);
        _aboutBtn.setStyle("-fx-background-color: #008080;");
        _aboutBtn.setText("About");
        _aboutBtn.setTextFill(javafx.scene.paint.Color.ORANGE);
        _aboutBtn.setFont(new Font("System Bold Italic", 21.0));

        _recordBtn1.setLayoutX(420.0);
        _recordBtn1.setLayoutY(468.0);
        _recordBtn1.setMnemonicParsing(false);
        _recordBtn1.setOnAction(this::handleOnBtnRecords);
        _recordBtn1.setPrefHeight(61.0);
        _recordBtn1.setPrefWidth(153.0);
        _recordBtn1.setStyle("-fx-background-color: #008080;");
        _recordBtn1.setText("Records");
        _recordBtn1.setTextFill(javafx.scene.paint.Color.ORANGE);
        _recordBtn1.setFont(new Font("System Bold Italic", 21.0));

        anchorPane.getChildren().add(label);
        anchorPane.getChildren().add(label0);
        anchorPane.getChildren().add(_onlineModeImageView);
        anchorPane.getChildren().add(_offlineModeImageview);
        anchorPane.getChildren().add(_leadBoardBtn);
        anchorPane.getChildren().add(_signOutBtn);
        anchorPane.getChildren().add(_aboutBtn);
        anchorPane.getChildren().add(_recordBtn1);
        getChildren().add(anchorPane);

    }

    protected void handleOnOnlineMode(javafx.scene.input.MouseEvent mouseEvent) {
        Parent root = new LoginScreen();
        Mynav.navigateTo(root, mouseEvent);

    }

    ;
    
    protected void handleOnOfflineMode(javafx.scene.input.MouseEvent mouseEvent) {
        Parent parent = new HomePageScreen();
        Mynav.navigateTo(parent, mouseEvent);
    }

    protected void handleOnBtnLeadBoard(javafx.event.ActionEvent actionEvent) {
        
    }

    ;

    protected void handleOnBtnBack(javafx.event.ActionEvent actionEvent) {
        Parent parent = new MultiModesScreen();
        Mynav.navigateTo(parent, actionEvent);
    }

    ;

    protected void handleOnBtnAbout(javafx.event.ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("this is tic tac toe server client game and made by ahmed samy and alaa eisa and amr hani and nada salem");
        alert.show();
    }

    ;

    protected void handleOnBtnRecords(javafx.event.ActionEvent actionEvent) {
    }
;

}