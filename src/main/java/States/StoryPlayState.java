package States;

import UI.inGameUserInterface;
import Utils.Utils;

public class StoryPlayState extends PlayState {
    StoryPlayState(GameStateManager gsm, inGameUserInterface ui) {
        super(gsm, ui);
    }

    void win() {
        gsm.changeState(9);
    }

    void playerDie(){
        gameOver();
    }
}
