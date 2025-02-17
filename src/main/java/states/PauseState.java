package states;

import entity.Monster;
import javafx.scene.image.ImageView;
import ui.ModeButton;
import utils.Utils;
import worldBuilder.World;
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

    public void nextStep(double deltaTime) {

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

        Canvas canvas = new Canvas(Utils.canvasWidth, Utils.canvasHeight);


        ImageView pause = new ImageView(new Image(World.class.getResource("/Buttons/pause.png").toString()));
        pause.setX(Utils.canvasWidth/2.0- 85);
        pause.setY(90);

        ImageView commandes = new ImageView(new Image(World.class.getResource("/Buttons/sign_commandes.png").toString()));
        commandes.setX(Utils.canvasWidth/2.0- 200);
        commandes.setY(Utils.canvasHeight/2.0 - 175);

        /*Text commande = new Text("Commandes : ");
        commande.setTextAlignment(TextAlignment.CENTER);
        StackPane.setAlignment(commande, Pos.TOP_CENTER);
        commande.setFont(new Font(20));
        commandeP.getChildren().addAll(commande);*/

        ModeButton reprendre = new ModeButton(new Image(World.class.getResource("/Buttons/sign_reprendre.png").toString()), "Reprendre");
        reprendre.setLayoutX(((Utils.canvasWidth/3.0)/2) - (new Image(World.class.getResource("/Buttons/sign_menu.png").toString()).getWidth()/2));
        reprendre.setLayoutY((2*Utils.canvasHeight)/3.0);
        reprendre.handler(gsm);

        /*
         * TODO: Faire le skin des boutons restant et corriger le bouton retour au jeu en cours
         */

        ModeButton retour = new ModeButton(new Image(World.class.getResource("/Buttons/sign_menu.png").toString()), "Menu");
        retour.setLayoutX( (((2*Utils.canvasWidth)/3.0) + Utils.canvasWidth)/2 - (new Image(World.class.getResource("/Buttons/sign_menu.png").toString()).getWidth()/2));
        retour.setLayoutY((2*Utils.canvasHeight)/3.0);
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
        gc.fillRect(0, 0, Utils.canvasWidth, Utils.canvasHeight + (2*Utils.canvasHeight));


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

