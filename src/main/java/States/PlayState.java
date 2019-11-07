package States;

import Entity.Monster;
import Input.KeysManager;
import Input.MouseManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

import java.awt.*;

public class PlayState extends GameState {
    public PlayState(GameStateManager gsm) {
        super(gsm);
    }

    void input(KeysManager key, MouseManager mouse) {
        gsm.player.input(key, mouse);
    }

    public void nextStep() {
        gsm.player.nextStep();
    }

    @Override
    public void input(KeyEvent e) {
        gsm.player.input(e);
    }

    @Override
    public void render(GraphicsContext gc) {
        gsm.player.render(gc);

        for(Monster monster : gsm.monsters){
            monster.render(gc);
        }

    }
}
