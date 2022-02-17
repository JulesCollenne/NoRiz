package states;

import ui.ModeButton;
import utils.Utils;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import worldBuilder.World;

import java.io.*;
import java.util.Scanner;

public class OptionsState extends GameState{


    OptionsState(GameStateManager gsm) {
        super(gsm);
        createScene();
        createAnimTimer();
    }

    private void createAnimTimer() {
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {

            }
        };
    }

    private void createScene() {
        Pane layout = new Pane();
        Color color = Color.WHITE;
        layout.setStyle("-fx-background-color: darkslategrey;");

        double tempWidth = new Image(World.class.getResource("/Buttons/sign_menu.png").toString()).getWidth();

        Text title = new Text("Option");
        title.setX(Utils.canvasSize/2.0- 75);
        title.setY(90);
        title.setFont(new Font(45));
        title.setFill(color);
        title.setStyle("-fx-font-weight: bold");

        GridPane sound = new GridPane();
        sound.setPadding(new Insets(10, 10, 10, 10));
        sound.getColumnConstraints().add(new ColumnConstraints(150));
        sound.getColumnConstraints().add(new ColumnConstraints(100));
        sound.getColumnConstraints().add(new ColumnConstraints(400));
        sound.getColumnConstraints().add(new ColumnConstraints(250));

        sound.getRowConstraints().add(new RowConstraints(150));
        sound.getRowConstraints().add(new RowConstraints(200));
        sound.getRowConstraints().add(new RowConstraints(200));
        sound.getRowConstraints().add(new RowConstraints(250));



        Label volumeMusique = new Label("Musique");
        volumeMusique.setTextFill(color);
        volumeMusique.setStyle("-fx-font-weight: bold");

        Slider soundVolume = new Slider();
        soundVolume.setMin(0);
        soundVolume.setMax(50);
        soundVolume.setBlockIncrement(1);

        Label volumeEffet = new Label("Effets");
        volumeEffet.setTextFill(color);
        volumeEffet.setStyle("-fx-font-weight: bold");

        Slider soundEffect = new Slider();
        soundEffect.setMin(0);
        soundEffect.setMax(50);
        soundEffect.setBlockIncrement(1);


        GridPane.setRowIndex(volumeMusique,1);
        GridPane.setColumnIndex(volumeMusique,1);

        GridPane.setRowIndex(soundVolume,1);
        GridPane.setColumnIndex(soundVolume,2);

        GridPane.setRowIndex(volumeEffet,2);
        GridPane.setColumnIndex(volumeEffet,1);

        GridPane.setRowIndex(soundEffect,2);
        GridPane.setColumnIndex(soundEffect,2);

        soundVolume.valueProperty().addListener((observableValue, old_val, new_val) -> setMusicVolume(new_val.intValue()));

        soundEffect.valueProperty().addListener((observableValue, old_val, new_val) -> setEffectVolume(new_val.intValue()));

        ModeButton retour = new ModeButton(new Image(World.class.getResource("/Buttons/sign_menu.png").toString()), "Menu");
        retour.setLayoutX(10);
        retour.setLayoutY(10);
        retour.handler(gsm);

        sound.getChildren().addAll(volumeMusique,soundVolume,volumeEffet,soundEffect);

        layout.getChildren().addAll(sound, title, retour);

        theScene = new Scene(layout, Utils.canvasSize, Utils.canvasSize);

        theScene.setOnKeyPressed(
                this::keyInput);
    }

    public void init() {
    }

    public void nextStep(double deltaTime) {

    }

    @Override
    public void keyInput(KeyEvent e) {
        switch (e.getCode()) {
            case ESCAPE:
                gsm.changeState(0);
                break;
        }
    }

    @Override
    public void render(GraphicsContext gc) {

    }

    void setMusicVolume(int new_val){

        gsm.sm.setSoundVolume(new_val);
        saveMusicVolume(new_val);

    }

    void saveMusicVolume(int new_val){

        FileInputStream file;
        try {
            file = new FileInputStream("src/main/resources/save/saveFile");

            String fileString = "";

            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {

                String line = sc.nextLine();

                if (line.substring(0, 3).equals("V_M")) {

                    fileString += "V_M = "+new_val+"\n";

                }
                else{

                    fileString += line+"\n";

                }
            }

            try {
                Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("src/main/resources/save/saveFile"))));
                writer.write(fileString);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    void setEffectVolume(int new_val){

        gsm.sm.setSoundVolume(new_val);
        saveEffectVolume(new_val);

    }

    void saveEffectVolume(int new_val){

        FileInputStream file;
        try {
            file = new FileInputStream("src/main/resources/save/saveFile");

            String fileString = "";

            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {

                String line = sc.nextLine();

                if (line.substring(0, 3).equals("V_E")) {

                    fileString += "V_E = "+new_val+"\n";

                }
                else{

                    fileString += line+"\n";

                }
            }

            try {
                Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("src/main/resources/save/saveFile"))));
                writer.write(fileString);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }



}
