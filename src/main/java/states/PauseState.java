package states;

import entity.Monster;
import javafx.scene.image.ImageView;
import ui.ModeButton;
import utils.Utils;
import worldBuilder.worldRender;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class PauseState extends GameState {

    PauseState(GameStateManager gsm) {
        super(gsm);
        init();
        createAnimTimer();
    }

    public void nextStep() {

    }

    @Override
    public void keyInput(KeyEvent e) {

        if(e.getCode() == KeyCode.ESCAPE || e.getCode() == KeyCode.SPACE){
            gsm.reprendreJeu();
        }

    }

    @Override
    public void render(GraphicsContext gc) {

    }

    @Override
    public void init() {

        Group root = new Group();
        theScene = new Scene( root );
        root.setStyle("-fx-background-color: darkslategrey;");

        Color color;

        Canvas canvas = new Canvas(Utils.canvasSize, Utils.canvasSize);


        ImageView pause = new ImageView(new Image("Buttons/pause.png"));
        pause.setX(Utils.canvasSize/2.0- 85);
        pause.setY(90);

        ImageView commandes = new ImageView( new Image("Buttons/sign_commandes.png"));
        commandes.setX(Utils.canvasSize/2.0- 200);
        commandes.setY(Utils.canvasSize/2.0 - 175);

        /*Text commande = new Text("Commandes : ");
        commande.setTextAlignment(TextAlignment.CENTER);
        StackPane.setAlignment(commande, Pos.TOP_CENTER);
        commande.setFont(new Font(20));
        commandeP.getChildren().addAll(commande);*/

        ModeButton reprendre = new ModeButton(new Image("Buttons/sign_reprendre.png"), "Reprendre");
        reprendre.setLayoutX(((Utils.canvasSize/3.0)/2) - (new Image("Buttons/sign_menu.png").getWidth()/2));
        reprendre.setLayoutY((2*Utils.canvasSize)/3.0);
        reprendre.handler(gsm);

        /*
         * TODO: Faire le skin des boutons restant et corriger le bouton retour au jeu en cours
         */

        ModeButton retour = new ModeButton(new Image("Buttons/sign_menu.png"), "Menu");
        retour.setLayoutX( (((2*Utils.canvasSize)/3.0) + Utils.canvasSize)/2 - (new Image("Buttons/sign_menu.png").getWidth()/2));
        retour.setLayoutY((2*Utils.canvasSize)/3.0);
        retour.handler(gsm);

        root.getChildren().addAll(canvas, pause, commandes, reprendre, retour);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        worldRender.renderMap(gc, gsm.world.map, false);
        gsm.noriz.render(gc);
        for (Monster monster : gsm.monsters) {
            monster.render(gc);
        }

        color = Color.rgb(0,0,0,0.7);
        gc.setFill(color);
        gc.fillRect(0, 0, Utils.canvasSize, Utils.canvasSize + (2*Utils.caseDimension));


        theScene.setOnKeyPressed(
                this::keyInput);

    }

    private void createAnimTimer() {
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {

            }
        };
    }
}

