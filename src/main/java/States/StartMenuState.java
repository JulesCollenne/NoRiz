package States;

import Input.KeysManager;
import Input.MouseManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

import java.awt.*;

public class StartMenuState extends GameState {
    public StartMenuState(GameStateManager gsm) {
        super(gsm);
    }

    void draw(Graphics g) {
        g.fillOval(50,50,400,50);
    }

    void input(KeysManager key, MouseManager mouse) {

    }

    public void nextStep() {

    }

    @Override
    public void input(KeyEvent e) {

    }

    @Override
    public void render(GraphicsContext gc) {

    }
}
