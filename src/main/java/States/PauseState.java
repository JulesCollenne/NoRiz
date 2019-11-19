package States;

import Utils.Utils;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
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

public class PauseState extends GameState {
    PauseState(GameStateManager gsm) {
        super(gsm);
        init();
        createAnimTimer();
    }

    public void nextStep() {

    }

    @Override
    public void input(KeyEvent e) {

    }

    @Override
    public void render(GraphicsContext gc) {

    }

    @Override
    public void init() {
        Pane layout = new Pane();

        layout.setStyle("-fx-background-color: darkslategrey;");

        StackPane commandeP = new StackPane();
        commandeP.setPrefSize(Utils.canvasSize/2, Utils.canvasSize/3);
        commandeP.relocate(Utils.canvasSize/4, Utils.canvasSize/5);
        commandeP.setStyle("-fx-background-color: rgba(95, 158, 160, 0.1);");


        Text title = new Text("Pause");
        title.setX(Utils.canvasSize/2- 75);
        title.setY(90);
        title.setFont(new Font(40));
        title.setStyle("-fx-font-weight: bold");

        Text commande = new Text("Commandes : ");
        commande.setTextAlignment(TextAlignment.CENTER);
        StackPane.setAlignment(commande, Pos.TOP_CENTER);
        commande.setFont(new Font(20));
        commandeP.getChildren().addAll(commande);

        ModeButton rejouer = new ModeButton(new Image("Buttons/sign_rejouer.png"), "Rejouer");
        rejouer.setLayoutX(((Utils.canvasSize/3)/2) - (new Image("Buttons/sign_return_menu.png").getWidth()/2));
        rejouer.setLayoutY((2*Utils.canvasSize)/3);
        rejouer.handler(gsm);

        ModeButton menu = new ModeButton(new Image("Buttons/sign_return_menu.png"), "Menu");
        menu.setLayoutX( ((((2*Utils.canvasSize)/3) + (Utils.canvasSize/3))/2) - (new Image("Buttons/sign_return_menu.png").getWidth()/2));
        menu.setLayoutY((2*Utils.canvasSize)/3);
        menu.handler(gsm);

        /**
         * TODO: Faire le skin des boutons restant et corriger le bouton retour au jeu en cours
         */

        ModeButton retour = new ModeButton(new Image("Buttons/sign_facile.png"), "Rejouer");
        retour.setLayoutX( (((2*Utils.canvasSize)/3) + Utils.canvasSize)/2 - (new Image("Buttons/sign_return_menu.png").getWidth()/2));
        retour.setLayoutY((2*Utils.canvasSize)/3);

        ImageView noriz = new ImageView(new Image("Player/nori_droite0.png"));
        noriz.setX((((2*Utils.canvasSize)/3) + Utils.canvasSize)/2);
        noriz.setY(Utils.canvasSize/3);

        layout.getChildren().addAll(title, commandeP, rejouer, menu, retour, noriz);


        theScene = new Scene(layout, Utils.canvasSize, Utils.canvasSize);

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

