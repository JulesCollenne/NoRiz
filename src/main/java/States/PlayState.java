package States;

import java.awt.*;

public class PlayState extends GameState {
    public PlayState(GameStateManager gsm) {
        super(gsm);
    }

    void input() {

    }

    public void nextStep() {

    }

    public void draw(Graphics g) {
        gsm.player.draw(g);
        //TODO affiche le monde et tout les composants
    }
}
