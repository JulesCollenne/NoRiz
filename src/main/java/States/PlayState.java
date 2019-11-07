package States;

import Entity.Monster;
import Input.KeysManager;
import Input.MouseManager;

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

    public void draw(Graphics g) {

        gsm.player.draw(g);

        for(Monster monster : gsm.monsters){
            monster.draw(g);
        }



    }
}
