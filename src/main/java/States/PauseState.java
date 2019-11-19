package States;

import Utils.Utils;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import Utils.myGameData;

public class PauseState extends GameState {

    myGameData myData;

    PauseState(GameStateManager gsm, myGameData myData) {
        super(gsm);
        this.myData = myData;
        init();
        createAnimTimer();
    }

    public void nextStep() {

    }

    @Override
    public void input(KeyEvent e) {

        if(e.getCode() == KeyCode.ESCAPE || e.getCode() == KeyCode.SPACE){
            gsm.reprendreJeu();
        }

    }

    @Override
    public void render(GraphicsContext gc) {

    }

    @Override
    public void init() {
        Pane layout = new Pane();

        layout.setStyle("-fx-background-color: darkslategrey;");

        StackPane commandeP = new StackPane();
        commandeP.setPrefSize(Utils.canvasSize/2.0, Utils.canvasSize/3.0);
        commandeP.relocate(Utils.canvasSize/4.0, Utils.canvasSize/5.0);
        commandeP.setStyle("-fx-background-color: rgba(95, 158, 160, 0.1);");


        Text title = new Text("Pause");
        title.setX(Utils.canvasSize/2.0- 75);
        title.setY(90);
        title.setFont(new Font(40));
        title.setStyle("-fx-font-weight: bold");

        Text commande = new Text("Commandes : ");
        commande.setTextAlignment(TextAlignment.CENTER);
        StackPane.setAlignment(commande, Pos.TOP_CENTER);
        commande.setFont(new Font(20));
        commandeP.getChildren().addAll(commande);

        ModeButton reprendre = new ModeButton(new Image("Buttons/sign_rejouer.png"), "Reprendre");
        reprendre.setLayoutX(((Utils.canvasSize/3.0)/2) - (new Image("Buttons/sign_return_menu.png").getWidth()/2));
        reprendre.setLayoutY((2*Utils.canvasSize)/3.0);
        reprendre.handler(gsm);

        ModeButton menu = new ModeButton(new Image("Buttons/sign_return_menu.png"), "Menu");
        menu.setLayoutX( ((((2*Utils.canvasSize)/3.0) + (Utils.canvasSize/3.0))/2) - (new Image("Buttons/sign_return_menu.png").getWidth()/2));
        menu.setLayoutY((2*Utils.canvasSize)/3.0);
        menu.handler(gsm);

        /**
         * TODO: Faire le skin des boutons restant et corriger le bouton retour au jeu en cours
         */

        ModeButton retour = new ModeButton(new Image("Buttons/sign_facile.png"), "Rejouer");
        retour.setLayoutX( (((2*Utils.canvasSize)/3.0) + Utils.canvasSize)/2 - (new Image("Buttons/sign_return_menu.png").getWidth()/2));
        retour.setLayoutY((2*Utils.canvasSize)/3.0);

        ImageView noriz = new ImageView(new Image("Player/nori_droite0.png"));
        noriz.setX((((2*Utils.canvasSize)/3.0) + Utils.canvasSize)/2);
        noriz.setY(Utils.canvasSize/3.0);

        layout.getChildren().addAll(title, commandeP, reprendre, menu, retour, noriz);
        theScene = new Scene(layout);

        theScene.setOnKeyPressed(this::input);


    }

    @Override
    public void createAnimTimer() {
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {

            }
        };
    }
}

