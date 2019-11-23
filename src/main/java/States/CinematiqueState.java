package States;

import Utils.Utils;
import javafx.animation.AnimationTimer;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static Utils.WORLDITEM.ROAD;

/**
 *
 * Permet l'affichage des cinématiques de début de niveau
 *
 */

public class CinematiqueState extends GameState{


    Image backGroundImage;
    Image currentTalker = new Image("monsters/catastrophe_droite0.png");;
    ArrayList<String> dialogList = new ArrayList<>();

    public CinematiqueState(GameStateManager gsm) {
        super(gsm);
        init();
        createAnimTimer();
    }

    public void createAnimTimer() {
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {

            }
        };
    }

    private void createScene() {
        Pane layout = new Pane();

        BackgroundSize backgroundSize = new BackgroundSize(Utils.canvasSize, Utils.canvasSize, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(backGroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        layout.setBackground(new Background(backgroundImage));

        ImageView talker = new ImageView(currentTalker);
        talker.setX((8/100.)*Utils.canvasSize);
        talker.setY((70/100.)*Utils.canvasSize);

        Rectangle cadreTalking = new Rectangle();
        cadreTalking.setX(talker.getX()-5);
        cadreTalking.setY(talker.getY()-5);
        cadreTalking.setWidth(currentTalker.getWidth()+10);
        cadreTalking.setHeight(currentTalker.getHeight()+10);
        cadreTalking.setFill(Color.WHITE);

        Rectangle borderCadreTalking = new Rectangle();
        borderCadreTalking.setX(cadreTalking.getX()-5);
        borderCadreTalking.setY(cadreTalking.getY()-5);
        borderCadreTalking.setWidth(cadreTalking.getWidth()+10);
        borderCadreTalking.setHeight(cadreTalking.getHeight()+10);
        borderCadreTalking.setFill(Color.BLACK);

        Rectangle cadreDialog = new Rectangle();
        cadreDialog.setX(cadreTalking.getX()+cadreTalking.getWidth()+5);
        cadreDialog.setY(cadreTalking.getY());
        cadreDialog.setWidth((80/100.)*Utils.canvasSize - currentTalker.getWidth());
        cadreDialog.setHeight(cadreTalking.getHeight());
        cadreDialog.setFill(Color.WHITE);

        Rectangle borderCadreDialog = new Rectangle();
        borderCadreDialog.setX(cadreDialog.getX()-5);
        borderCadreDialog.setY(cadreDialog.getY()-5);
        borderCadreDialog.setWidth(cadreDialog.getWidth()+10);
        borderCadreDialog.setHeight(cadreDialog.getHeight()+10);
        borderCadreDialog.setFill(Color.BLACK);


        Image arrow = new Image("cinematiques/arrow.png");
        Button nextDialog = new Button();
        nextDialog.setStyle("-fx-border-width: 0; -fx-background-color: transparent; -fx-border-color: transparent; -fx-background-radius: 0");
        nextDialog.setLayoutX(cadreDialog.getX() + cadreDialog.getWidth() - arrow.getWidth()*1.4);
        nextDialog.setLayoutY(cadreDialog.getY() + cadreDialog.getHeight() - arrow.getHeight());
        nextDialog.setGraphic(new ImageView(arrow));
        nextDialog.setOnAction(e -> nextDialog());


        HBox hbox = new HBox();
        hbox.setLayoutX((30/100.)*Utils.canvasSize);
        hbox.setLayoutY((95/100.)*Utils.canvasSize);
        Text skipInstructions = new Text("Appuyez sur la touche espace pour passer le dialogue");
        skipInstructions.setFont(new Font(15));
        skipInstructions.setFill(Color.BLACK);
        skipInstructions.setStyle("-fx-font-weight: bold;");
        hbox.getChildren().add(skipInstructions);
        hbox.setStyle("-fx-border-color: red; -fx-background-color: white;");

        theScene = new Scene(layout, Utils.canvasSize, Utils.canvasSize);

        layout.getChildren().addAll(borderCadreTalking, cadreTalking, borderCadreDialog, cadreDialog, talker, nextDialog, hbox);

        theScene.setOnKeyPressed(
                this::keyInput);
    }

    public void init() {
        getCinematiqueData();
        createScene();
    }

    private Node createBorderedText(String text) {
        final HBox hbox = new HBox();
        hbox.getChildren().add(new Text(text));
        hbox.setStyle("-fx-border-color: red;");
        return hbox ;
    }


    /**
     *
     * Permet d'initialiser la cinématique selon le niveau actuel
     *
     */
    private void getCinematiqueData(){

        getBackgroundImage();

        getDialog();

    }


    /**
     *
     * Initialise l'image de fond
     *
     */
    private void getBackgroundImage(){

        switch(gsm.difficulty){

            case EASY:
                backGroundImage = new Image("cinematiques/background_EASY.jpg");
                break;

            case MEDIUM:
                System.out.println("ici");
                backGroundImage = new Image("cinematiques/background_MEDIUM.jpg");
                break;

            case HARD:
                backGroundImage = new Image("cinematiques/background_EASY.jpg");
                break;

            default:
                gsm.changeState(1);
                break;
        }

    }


    /**
     *
     * Récupère les dialogues de la cinématiques depuis un fichier text de la forme: N=Noriz, M=Monstres
     * N Yo les potes
     * M bien ou bien ?
     * N Tranquille frère
     */
    private void getDialog() {

        File tmpFile = new File("C:\\Users\\Anthony\\IdeaProjects\\NoRiz4\\src\\main\\resources\\cinematiques\\dialog_EASY.txt");

        try {
            FileInputStream file = new FileInputStream(tmpFile);
            switch (gsm.difficulty) {

                case EASY:
                    file = new FileInputStream(tmpFile);                    break;

                case MEDIUM:
                    file = new FileInputStream(tmpFile);                    break;

                case HARD:
                    file = new FileInputStream(tmpFile);                    break;

                default:
                    gsm.changeState(1);
                    break;
            }

            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String temp = sc.nextLine();
                //System.out.println(temp);
                dialogList.add(temp);
            }

            sc.close();
        }
        catch(IOException e) { e.printStackTrace(); }

    }

    public void nextDialog(){

        if(!dialogList.isEmpty()) {
            System.out.println("Next sentence");
        }
        else{
            gsm.changeState(1);
        }

    }

    public void nextStep() {

    }

    @Override
    public void keyInput(KeyEvent e) {

        switch (e.getCode()) {
            case ESCAPE:
                gsm.changeState(1);
                break;
            case SPACE:
                gsm.changeState(1);
                break;
        }
    }

    @Override
    public void render(GraphicsContext gc) {

    }
}