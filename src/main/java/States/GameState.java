package States;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

public abstract class GameState {
    Scene theScene;
    GameStateManager gsm;
    AnimationTimer animationTimer;

    GameState(GameStateManager gsm){
        this.gsm = gsm;
    }

    public abstract void nextStep();

    public abstract void input(KeyEvent e);

    public abstract void render(GraphicsContext gc);

    public abstract void init();

    public abstract void createAnimTimer();
}

