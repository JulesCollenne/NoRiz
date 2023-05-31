package states;

import ui.ModeButton;
import utils.Utils;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import worldBuilder.World;

public class GameOverState extends GameState {

    GameOverState(GameStateManager gsm) {
        super(gsm);
        createAnimTimer();
    }

    public void nextStep(double deltaTime) {

    }

    @Override
    public void keyInput(KeyEvent e) {

    }

    @Override
    public void render(GraphicsContext gc) {

    }

    @Override
    public void init() {
        Pane layout = new Pane();
        Color color = Color.WHITE;
        layout.setStyle("-fx-background-color: darkslategrey;");

        double tempWidth = new Image(World.class.getResource("/Buttons/sign_menu.png").toString()).getWidth();


        Text title = new Text("Game Over !");
        title.setX(Utils.canvasWidth/2.0- 175);
        title.setY(Utils.canvasHeight/3.0);
        title.setFont(new Font(45));
        title.setFill(color);
        title.setStyle("-fx-font-weight: bold");

        ModeButton rejouer = new ModeButton(new Image(World.class.getResource("/Buttons/sign_rejouer.png").toString()), "Rejouer");
        rejouer.setLayoutX(((Utils.canvasWidth/2)/2.) - (tempWidth/2));
        rejouer.setLayoutY(Utils.canvasHeight/2.);
        rejouer.setMaxSize((20 /100.0)*Utils.canvasWidth,(10 /100.0)*Utils.canvasHeight);
        rejouer.handler(gsm);

        ModeButton menu = new ModeButton(new Image(World.class.getResource("/Buttons/sign_menu.png").toString()), "Menu");
        menu.setLayoutX( ((2*Utils.canvasWidth) - (Utils.canvasWidth/2))/2. - (tempWidth/2));
        menu.setLayoutY(Utils.canvasHeight/2.);
        menu.handler(gsm);



        layout.getChildren().addAll(rejouer, menu, title);

        theScene = new Scene(layout, Utils.canvasWidth, Utils.canvasHeight);
    }

    private void createAnimTimer() {
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {

            }
        };
    }
}

