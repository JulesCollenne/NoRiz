package States;

import Input.KeysManager;
import Input.MouseManager;

import java.awt.*;

public class StartMenuState extends GameState {
    public StartMenuState(GameStateManager gsm) {
        super(gsm);
    }

    void draw(Graphics g) {
        g.drawOval(50,50,50,50);
    }

    void input(KeysManager key, MouseManager mouse) {

    }

    public void nextStep() {

    }
}
