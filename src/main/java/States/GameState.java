package States;

import Input.KeysManager;
import Input.MouseManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

public abstract class GameState {
    GameStateManager gsm;

    GameState(GameStateManager gsm){
        this.gsm = gsm;
    }

    abstract void input(KeysManager key, MouseManager mouse);

    public abstract void nextStep();

    public abstract void input(KeyEvent e);

    public abstract void render(GraphicsContext gc);
}
