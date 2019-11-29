package states;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import utils.Utils;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class WinState extends GameState {
    WinState(GameStateManager gsm) {
        super(gsm);
        init();
        createAnimTimer();
    }

    @Override
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

        layout.setStyle("-fx-background-color: darkslategrey;");

        Color color;

        Text title = new Text("Victoire !");
        title.setX(Utils.canvasSize/2.0 - 100);
        title.setY(90);
        title.setFont(new Font(45));
        color = Color.WHITE;
        title.setFill(color);
        title.setStyle("-fx-font-weight: bold");


        ModeButton menu = new ModeButton(new Image("Buttons/sign_return_menu.png"), "Menu");
        menu.setLayoutX((new Image("Buttons/sign_return_menu.png").getWidth()/2));
        menu.setLayoutY((40/100.0)*Utils.canvasSize);
        menu.handler(gsm);

        ModeButton niveau_suivant = new ModeButton(new Image("Buttons/sign_niveau_suivant.png"), "Niveau_Suivant");
        niveau_suivant.setLayoutX( (((2*Utils.canvasSize)/3.0) + Utils.canvasSize)/2 - (new Image("Buttons/sign_return_menu.png").getWidth()/2));
        niveau_suivant.setLayoutY((40/100.0)*Utils.canvasSize);
        niveau_suivant.setMaxSize((20 /100.0)*Utils.canvasSize,(10 /100.0)*Utils.canvasSize);
        niveau_suivant.handler(gsm);

        layout.getChildren().addAll(niveau_suivant, menu, title);

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
