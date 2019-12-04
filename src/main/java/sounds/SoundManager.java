package sounds;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.File;

public class SoundManager {

    public MediaPlayer backGround;
    public MediaPlayer hurt;

    private int soundEffect;
    private int soundVolume;

    public SoundManager(){
        //loadSounds();
    }

    /**
     * Load the sounds we'll be using
     */
    private void loadSounds(){
        try {
            String musicFile = "src/main/resources/sounds/music.mp3";
            Media sound = new Media(new File(musicFile).toURI().toString());
            backGround = new MediaPlayer(sound);
            Media hurtSound = new Media(new File("src/main/resources/sounds/music.mp3").toURI().toString());
            hurt = new MediaPlayer(hurtSound);
            hurt.setStopTime(new Duration(1000));
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

    public void hurtSound(Scene scene){
        hurt.setAutoPlay(true);
        MediaView hMediaView = new MediaView(hurt);
        ((Group) scene.getRoot()).getChildren().add(hMediaView);
    }

    public void setSoundEffect(int newVal){
        this.soundEffect = newVal;
    }
    public void setSoundVolume(int newVal){
        this.soundVolume = newVal;
    }

    public int getSoundEffect(){
        return soundEffect;
    }

    public int getSoundVolume(){
        return soundVolume;
    }
}
