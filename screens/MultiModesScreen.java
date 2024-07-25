package screens;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import tic.toe.game.Mynav;

public class MultiModesScreen extends AnchorPane {

    protected final AnchorPane anchorPane;
    protected final ImageView _multiPlayerModeImageView;
    protected final ImageView _singlePlayerModeImageView;
    protected final Label label;
    protected final Label label0;

    public MultiModesScreen() {

        anchorPane = new AnchorPane();
        _multiPlayerModeImageView = new ImageView();
        _singlePlayerModeImageView = new ImageView();
        label = new Label();
        label0 = new Label();

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

        _multiPlayerModeImageView.setFitHeight(340.0);
        _multiPlayerModeImageView.setFitWidth(354.0);
        _multiPlayerModeImageView.setLayoutX(24.0);
        _multiPlayerModeImageView.setLayoutY(143.0);
        _multiPlayerModeImageView.setOnMouseClicked(this::handleOnMultiPlayerImgeView);
        _multiPlayerModeImageView.setPickOnBounds(true);
        _multiPlayerModeImageView.setImage(new Image(getClass().getResource("/images/ovso.jpg").toExternalForm()));

        _singlePlayerModeImageView.setFitHeight(340.0);
        _singlePlayerModeImageView.setFitWidth(397.0);
        _singlePlayerModeImageView.setLayoutX(420.0);
        _singlePlayerModeImageView.setLayoutY(143.0);
        _singlePlayerModeImageView.setOnMouseClicked(this::handleOnSinglePlayerImgeView);
        _singlePlayerModeImageView.setPickOnBounds(true);
        _singlePlayerModeImageView.setImage(new Image(getClass().getResource("/images/singleplayer.jpg").toExternalForm()));

        label.setLayoutX(63.0);
        label.setLayoutY(90.0);
        label.setText("Multi Player Mode");
        label.setFont(new Font("System Bold Italic", 30.0));

        label0.setLayoutX(484.0);
        label0.setLayoutY(98.0);
        label0.setText("Single Player Mode");
        label0.setFont(new Font("System Bold Italic", 30.0));

        anchorPane.getChildren().add(_multiPlayerModeImageView);
        anchorPane.getChildren().add(_singlePlayerModeImageView);
        anchorPane.getChildren().add(label);
        anchorPane.getChildren().add(label0);
        getChildren().add(anchorPane);

    }

    protected void handleOnMultiPlayerImgeView(javafx.scene.input.MouseEvent mouseEvent) {
        Parent root = new OnlineAndOfflineScreen();
        Mynav.navigateTo(root, mouseEvent);
    }

    protected void handleOnSinglePlayerImgeView(javafx.scene.input.MouseEvent mouseEvent) 
    {
    
        Parent root = new DifficultyScreen();
        Mynav.navigateTo(root, mouseEvent);
    }

}