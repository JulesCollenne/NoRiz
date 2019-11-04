package States;

import java.awt.*;

public abstract class GameState {
    GameStateManager gsm;

    public GameState(GameStateManager gsm){
        this.gsm = gsm;
    }
    abstract void draw(Graphics g);
    abstract void input();

}
