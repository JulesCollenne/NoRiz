package states;

import utils.Utils;
import javafx.animation.AnimationTimer;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
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
import worldBuilder.World;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * Permet l'affichage des cinématiques de début de niveau
 *
 */

public class CinematiqueState extends GameState{

    private Image backGroundImage;
    private ArrayList<String> dialogList = new ArrayList<>();

    private Image currentTalker = new Image(World.class.getResource("/monsters/catastrophe_droite0.png").toString());


    private double posXDialog;
    private double posYDialog;

    private double posXTalker;
    private double posYTalker;

    private Color dialogColor = new Color(1,1,1,1);

    CinematiqueState(GameStateManager gsm) {
        super(gsm);
        init();
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

        setBackground(layout);

        ImageView talker = setTalker();

        Rectangle cadreTalking = setCadreTalking(talker);

        posXTalker = talker.getX();
        posYTalker = talker.getY();

        Rectangle borderCadreTalking = setBorderCadreTalking(cadreTalking);

        Rectangle cadreDialog = setCadreDialog(cadreTalking);

        Rectangle borderCadreDialog = setBorderCadreDialog(cadreDialog);

        Canvas canvas = new Canvas(Utils.canvasSize, Utils.canvasSize);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        nextDialog(gc);

        Image arrow = new Image(World.class.getResource("/cinematiques/arrow.png").toString());
        Button nextDialogButton = setNextDialogButton(cadreDialog, arrow, gc);

        HBox hbox = new HBox();
        hbox.setLayoutX((30/100.)*Utils.canvasSize);
        hbox.setLayoutY((95/100.)*Utils.canvasSize);

        Text skipInstructions = setSkipInstructions();

        hbox.getChildren().add(skipInstructions);
        hbox.setStyle("-fx-border-color: red; -fx-background-color: white;");

        posXDialog = cadreDialog.getX() + 30;
        posYDialog = cadreDialog.getY() + 28;

        theScene = new Scene(layout, Utils.canvasSize, Utils.canvasSize);
        layout.getChildren().addAll(borderCadreTalking, cadreTalking, borderCadreDialog, cadreDialog, hbox, canvas, nextDialogButton);


        theScene.setOnKeyPressed(
                this::keyInput);
    }

    private Text setSkipInstructions() {
        Text skipInstructions = new Text("Appuyez sur la touche échap pour passer le dialogue");
        skipInstructions.setFont(new Font(15));
        skipInstructions.setFill(Color.BLACK);
        skipInstructions.setStyle("-fx-font-weight: bold;");
        return skipInstructions;
    }

    private Button setNextDialogButton(Rectangle cadreDialog, Image arrow, GraphicsContext gc) {
        Button nextDialogButton = new Button();
        nextDialogButton.setStyle("-fx-border-width: 0; -fx-background-color: transparent; -fx-border-color: transparent; -fx-background-radius: 0");
        nextDialogButton.setLayoutX(cadreDialog.getX() + cadreDialog.getWidth() - arrow.getWidth()*1.4);
        nextDialogButton.setLayoutY(cadreDialog.getY() + cadreDialog.getHeight() - arrow.getHeight());
        nextDialogButton.setGraphic(new ImageView(arrow));
        nextDialogButton.setOnAction(e -> nextDialog(gc));
        return nextDialogButton;
    }

    private Rectangle setBorderCadreDialog(Rectangle cadreDialog) {
        Rectangle borderCadreDialog = new Rectangle();
        borderCadreDialog.setX(cadreDialog.getX()-5);
        borderCadreDialog.setY(cadreDialog.getY()-5);
        borderCadreDialog.setWidth(cadreDialog.getWidth()+10);
        borderCadreDialog.setHeight(cadreDialog.getHeight()+10);
        borderCadreDialog.setFill(Color.BLACK);
        return borderCadreDialog;
    }

    private Rectangle setCadreDialog(Rectangle cadreTalking) {
        Rectangle cadreDialog = new Rectangle();
        cadreDialog.setX(cadreTalking.getX()+cadreTalking.getWidth()+5);
        cadreDialog.setY(cadreTalking.getY());
        cadreDialog.setWidth((80/100.)*Utils.canvasSize - currentTalker.getWidth());
        cadreDialog.setHeight(cadreTalking.getHeight());
        cadreDialog.setFill(dialogColor);
        return cadreDialog;
    }

    private Rectangle setBorderCadreTalking(Rectangle cadreTalking) {
        Rectangle borderCadreTalking = new Rectangle();
        borderCadreTalking.setX(cadreTalking.getX()-5);
        borderCadreTalking.setY(cadreTalking.getY()-5);
        borderCadreTalking.setWidth(cadreTalking.getWidth()+10);
        borderCadreTalking.setHeight(cadreTalking.getHeight()+10);
        borderCadreTalking.setFill(Color.BLACK);
        return borderCadreTalking;
    }

    private Rectangle setCadreTalking(ImageView talker) {
        Rectangle cadreTalking = new Rectangle();
        cadreTalking.setX(talker.getX()-5);
        cadreTalking.setY(talker.getY()-5);
        cadreTalking.setWidth(currentTalker.getWidth()+10);
        cadreTalking.setHeight(currentTalker.getHeight()+10);
        cadreTalking.setFill(dialogColor);
        return cadreTalking;
    }

    private ImageView setTalker() {
        ImageView talker = new ImageView(currentTalker);
        talker.setX((8/100.)*Utils.canvasSize);
        talker.setY((70/100.)*Utils.canvasSize);
        return talker;
    }

    private void setBackground(Pane layout) {
        BackgroundSize backgroundSize = new BackgroundSize(Utils.canvasSize, Utils.canvasSize, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(backGroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        layout.setBackground(new Background(backgroundImage));
    }

    public void init() {
        getCinematiqueData();
        createScene();
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

        switch (gsm.difficulty) {
            case EASY -> backGroundImage = new Image(World.class.getResource("/cinematiques/background_EASY.jpg").toString());
            case MEDIUM -> backGroundImage = new Image(World.class.getResource("/cinematiques/background_MEDIUM.jpg").toString());
            case HARD -> backGroundImage = new Image(World.class.getResource("/cinematiques/background_HARD.jpg").toString());
            default -> gsm.changeState(1);
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

        dialogList.clear();

        try {
            FileInputStream file;
            switch (gsm.difficulty) {
                case EASY -> file = new FileInputStream("src/main/resources/cinematiques/dialog_EASY.txt");
                case MEDIUM -> file = new FileInputStream("src/main/resources/cinematiques/dialog_MEDIUM.txt");
                case HARD -> file = new FileInputStream("src/main/resources/cinematiques/dialog_HARD.txt");
                default -> {
                    file = new FileInputStream("src/main/resources/cinematiques/dialog_EASY.txt");
                    gsm.changeState(1);
                }
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


    /**
     *
     * Affiche le prochain dialogue
     *
     */
    private void nextDialog(GraphicsContext gc){

        if(!dialogList.isEmpty()) {
            String temp = dialogList.get(0);
            dialogList.remove(0);

            switch (temp.substring(0, 2)){

                case "N ":
                    currentTalker = new Image(World.class.getResource("/Player/nori_droite0.png").toString());      // Image de noriz a terme
                    break;

                case "M ":
                    currentTalker = new Image(World.class.getResource("/monsters/catastrophe_gauche0.png").toString());      // Meilleur image de monstre a terme
                    break;

            }

            String currentDialog = temp.substring(2);

            gc.setFill(dialogColor);
            gc.fillRect(posXDialog, posYDialog-25, (70/100.)*Utils.canvasSize, 30);
            gc.fillRect(posXTalker, posYTalker, 50, 50); //TODO ce serait mieux de mettre des variabels globales

            gc.setFill(Color.BLACK);
            gc.setFont(new Font(20));
            gc.fillText(currentDialog,posXDialog, posYDialog);

            gc.drawImage(currentTalker, posXTalker, posYTalker);

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
        }
    }

    @Override
    public void render(GraphicsContext gc) {

    }
}