package States;

import Input.KeysManager;
import Input.MouseManager;

import java.awt.*;

public class PlayState extends GameState {
    public PlayState(GameStateManager gsm) {
        super(gsm);
    }

    void input(KeysManager key, MouseManager mouse) {
        if(key.keys[key.KEY_R]){
            gsm.player.changeFacing(3);
        }
    }

    public void nextStep() {
        gsm.player.nextStep();
    }

    public void draw(Graphics g) {
        gsm.player.draw(g);
        //TODO affiche le monde et tout les composants
    }
}
