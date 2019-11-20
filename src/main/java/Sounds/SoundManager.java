package Sounds;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;

public class SoundManager {

    public MediaPlayer backGround;
    public MediaPlayer hurt;

    public SoundManager(){
        //loadSounds();
    }

    /**
     * Load the sounds we'll be using
     */
    private void loadSounds(){
        try {
            String musicFile = "Sounds/music.mp3";
            Media sound = new Media(new File(musicFile).toURI().toString());
            backGround = new MediaPlayer(sound);
        }catch (MediaException e){
            e.printStackTrace();
            System.out.println("Ce son n'existe pas !");
        }
    }

    /**
     * Deprecated function. Should not be used anymore except if there's bug about it
     * @param scene currentScene
     * @param play do we play or stop the song
     */
    public void backGroundMusic(Scene scene, boolean play){
        if(play) {
            backGround.setAutoPlay(true);
            MediaView mediaView = new MediaView(backGround);
            ((Group) scene.getRoot()).getChildren().add(mediaView);
        }
        else{
            backGround.stop();
        }
    }

    public void hurtSound(){

    }

}
