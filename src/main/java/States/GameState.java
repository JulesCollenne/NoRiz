package States;

import Input.KeysManager;
import Input.MouseManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

import java.awt.*;

public abstract class GameState {
    GameStateManager gsm;

    public GameState(GameStateManager gsm){
        this.gsm = gsm;
    }
    abstract void draw(Graphics g);
    abstract void input(KeysManager key, MouseManager mouse);

    public abstract void nextStep();

    public abstract void input(KeyEvent e);

    public abstract void render(GraphicsContext gc);
}
