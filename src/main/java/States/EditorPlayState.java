package States;

import UI.inGameUserInterface;

public class EditorPlayState extends PlayState {
    EditorPlayState(GameStateManager gsm, inGameUserInterface ui) {
        super(gsm, ui);
    }

    @Override
    void win() {
        gsm.returnToEditor();
    }

    void playerDie(){
        gsm.returnToEditor();
    }
}
