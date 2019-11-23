package States;

import Utils.Utils;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class SkinState extends GameState {

    public SkinState(GameStateManager gsm) {
        super(gsm);
        createScene();
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
        Color color = Color.WHITE;
        layout.setStyle("-fx-background-color: darkslategrey;");

        double tempWidth = new Image("Buttons/sign_return_menu.png").getWidth();

        layout.getChildren().addAll();

        theScene = new Scene(layout, Utils.canvasSize, Utils.canvasSize);

        theScene.setOnKeyPressed(
                this::keyInput);
    }

    @Override
    public void nextStep() {

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

    @Override
    public void init() {

    }
}
