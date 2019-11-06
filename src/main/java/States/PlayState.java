package States;

import Input.KeysManager;
import Input.MouseManager;

import java.awt.*;

public class PlayState extends GameState {
    public PlayState(GameStateManager gsm) {
        super(gsm);
    }

    void input(KeysManager key, MouseManager mouse) {
        if(key.keys[key.KEY_Q])
            gsm.player.changeFacing(2);
        if(key.keys[key.KEY_D])
            gsm.player.changeFacing(3);
        if(key.keys[key.KEY_S])
            gsm.player.changeFacing(0);
        if(key.keys[key.KEY_Z])
            gsm.player.changeFacing(1);
    }

    public void nextStep() {
        gsm.player.nextStep();
    }

    public void draw(Graphics g) {
        gsm.player.draw(g);
        //TODO affiche le monde et tout les composants
    }
}
