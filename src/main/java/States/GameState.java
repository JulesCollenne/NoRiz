package States;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

public abstract class GameState {
    GameStateManager gsm;
    public Scene theScene;

    GameState(GameStateManager gsm){
        this.gsm = gsm;

    }

    public abstract void nextStep();

    public abstract void input(KeyEvent e);

    public abstract void render(GraphicsContext gc);

    public abstract void initScene();
}

