package states;

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

public class GameOverState extends GameState {

    GameOverState(GameStateManager gsm) {
        super(gsm);
        createAnimTimer();
    }

    public void nextStep() {

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

        double tempWidth = new Image("Buttons/sign_return_menu.png").getWidth();


        Text title = new Text("Game Over !");
        title.setX(Utils.canvasSize/2.0- 175);
        title.setY(Utils.canvasSize/3);
        title.setFont(new Font(45));
        title.setFill(color);
        title.setStyle("-fx-font-weight: bold");

        ModeButton rejouer = new ModeButton(new Image("Buttons/sign_rejouer.png"), "Rejouer");
        rejouer.setLayoutX(((Utils.canvasSize/2)/2) - (tempWidth/2));
        rejouer.setLayoutY(Utils.canvasSize/2);
        rejouer.setMaxSize((20 /100.0)*Utils.canvasSize,(10 /100.0)*Utils.canvasSize);
        rejouer.handler(gsm);

        ModeButton menu = new ModeButton(new Image("Buttons/sign_return_menu.png"), "Menu");
        menu.setLayoutX( ((2*Utils.canvasSize) - (Utils.canvasSize/2))/2 - (tempWidth/2));
        menu.setLayoutY(Utils.canvasSize/2);
        menu.handler(gsm);



        layout.getChildren().addAll(rejouer, menu, title);

        theScene = new Scene(layout, Utils.canvasSize, Utils.canvasSize);
    }

    private void createAnimTimer() {
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {

            }
        };
    }
}

