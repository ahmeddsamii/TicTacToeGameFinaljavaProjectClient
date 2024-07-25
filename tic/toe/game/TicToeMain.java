/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic.toe.game;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import screens.DifficultyScreen;
import screens.LoginScreen;
import screens.MultiModesScreen;
import screens.SignUpScreen;



/**
 *
 * @author Ahmed
 */
public class TicToeMain extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = new MultiModesScreen();
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }
    
}
