package States;

import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

public abstract class GameState {
    Scene theScene;
    GameStateManager gsm;
    AnimationTimer animationTimer;
    //Timeline animationTimer2;

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
}

