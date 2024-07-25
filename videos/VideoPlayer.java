/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videos;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;


/**
 *
 * @author Ahmed
 */
public class VideoPlayer extends Application{
    private MediaPlayer mediaPlayer;
    
    public void playVideo(String videoPath) {
        try {
            // Properly encode the spaces in the video path
            //videoPath = videoPath.replace(" ", "%20");
            Media media = new Media(videoPath);
            mediaPlayer = new MediaPlayer(media);
            MediaView mediaView = new MediaView(mediaPlayer);

            StackPane root = new StackPane();
            root.getChildren().add(mediaView);

            Stage videoStage = new Stage();
            Scene scene = new Scene(root, 800, 600);
            videoStage.setScene(scene);
            videoStage.show();

            mediaPlayer.play();
            mediaPlayer.setOnEndOfMedia(new Runnable() {
                @Override
                public void run() {
                   videoStage.close();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
