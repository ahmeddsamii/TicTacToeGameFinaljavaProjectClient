package tic.toe.game;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import screens.LoginScreen;

public class Mynav {

    public static Scene scene;
    public  static Stage stage;

    
    public static void navigateTo(Parent destination, Scene currentScene) {
        if (currentScene != null) {
            currentScene.setRoot(destination);
        } else {
            System.err.println("Unable to navigate: current scene is null");
        }
    }
    
    public static void navigateTo(Parent destinationRoot, Stage currentStage) {
        if (destinationRoot != null && currentStage != null) {
            Scene scene = new Scene(destinationRoot);
            currentStage.setScene(scene);
            currentStage.show();
            currentStage.setResizable(false);
        } else {
            System.err.println("Unable to navigate: destinationRoot or currentStage is null");
        }
    }

    public static void navigateTo(Parent distinationRoot, MouseEvent event) {
        if (event != null && event.getSource() != null) {
            scene = new Scene(distinationRoot);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            showScene();
        } else {
            System.err.println("Unable to navigate: event or source is null");
        }
    }

    public static void navigateTo(Parent distinationRoot, ActionEvent event) {
        if (event != null && event.getSource() != null) {
            scene = new Scene(distinationRoot);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            showScene();
        } else {
            System.err.println("Unable to navigate: event or source is null");
        }
    }

    private static void showScene() {
        if (stage != null && scene != null) {
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
        } else {
            System.err.println("Unable to show scene: stage or scene is null");
        }
    }

}
