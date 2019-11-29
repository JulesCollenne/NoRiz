package States;

import javafx.animation.*;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

public abstract class GameState {
    Scene theScene;
    GameStateManager gsm;
    AnimationTimer animationTimer;
    Timeline animationTimer2;

    GameState(GameStateManager gsm){
        this.gsm = gsm;
    }

    public abstract void nextStep();

    /**
     * Gere les entrées au clavier lorsqu'une touche est appuyée
     * @param e the pressed keys
     */
    public abstract void keyInput(KeyEvent e);

    public abstract void render(GraphicsContext gc);

    public abstract void init();

    public void createAnimTimer(GraphicsContext gc) {

        /*

        animationTimer = new AnimationTimer() {
            public void handle(long currentNanoTime) {

                /*
                long elapsedNanos = currentNanoTime - lastTime ;
                System.out.println("FPS : " + 1000000000. / elapsedNanos);

                // game logic
                nextStep();

                // render
                render(gc);

                //lastTime = currentNanoTime;
            }
        };
*/
        animationTimer2 = new Timeline(new KeyFrame(Duration.seconds(1/80.), event -> {
            // game logic
            nextStep();
            // render
            render(gc);
        }));

        animationTimer2.setCycleCount(Animation.INDEFINITE);
        animationTimer2.setRate(1);

    }

}

