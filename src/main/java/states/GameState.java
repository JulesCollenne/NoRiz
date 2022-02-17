package states;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

public abstract class GameState {
    Scene theScene;
    GameStateManager gsm;
    AnimationTimer animationTimer;
    //Timeline animationTimer2;
    int timer = 0;
    long lastTime;

    GameState(GameStateManager gsm){
        this.gsm = gsm;
    }

    public abstract void nextStep(double deltaTime);

    /**
     * Gere les entrées au clavier lorsqu'une touche est appuyée
     * @param e the pressed keys
     */
    public abstract void keyInput(KeyEvent e);

    public abstract void render(GraphicsContext gc);

    public abstract void init();

    public void createAnimTimer(GraphicsContext gc) {

        animationTimer = new AnimationTimer() {
            public void handle(long currentNanoTime) {

                long elapsedNanos = currentNanoTime - lastTime ;
                //System.out.println("FPS : " + 1000000000. / elapsedNanos);
                // System.out.println(elapsedNanos / 1000000000.);
                double deltaTime = elapsedNanos / 10000000.;

                //if(timer % 2 == 0){
                  //  timer ++;
                    //return;
                //}
                // game logic
                nextStep(deltaTime);

                // render
                render(gc);

                lastTime = currentNanoTime;
                timer++;
            }
        };
    }

}

