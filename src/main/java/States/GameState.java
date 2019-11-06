package States;

import Input.KeysManager;
import Input.MouseManager;

import java.awt.*;

public abstract class GameState {
    GameStateManager gsm;

    public GameState(GameStateManager gsm){
        this.gsm = gsm;
    }
    abstract void draw(Graphics g);
    abstract void input(KeysManager key, MouseManager mouse);

    public abstract void nextStep();
}
