package States;

import UI.inGameUserInterface;

public class ArcadeState extends PlayState {
    ArcadeState(GameStateManager gsm, inGameUserInterface ui) {
        super(gsm, ui);
    }

    @Override
    void win() {
        gsm.changeState(1);
    }

    void playerDie(){
        gameOver();
    }

}
