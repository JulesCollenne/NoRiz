package Sounds;

import States.GameState;
import States.GameStateManager;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;

public class SoundManager {

    MediaPlayer backGround;

    public SoundManager(){
        /*
        try {
            String musicFile = "src/main/resources/Sounds/music.mp3";
            Media sound = new Media(new File(musicFile).toURI().toString());
            backGround = new MediaPlayer(sound);
        }catch (MediaException e){
            e.printStackTrace();
            System.out.println("Ce son n'existe pas !");
        }
        */
    }

    public void backGroundMusic(Scene scene){
            backGround.setAutoPlay(true);
            MediaView mediaView = new MediaView(backGround);
            ((Group)scene.getRoot()).getChildren().add(mediaView);
    }

    public void hurtSound(){

    }

}
