package States;

import UI.inGameUserInterface;
import Utils.Utils;
import javafx.scene.canvas.GraphicsContext;
import WorldBuilder.worldRender;


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

    @Override
    void manageScore() {
        return;
    }

    public void render(GraphicsContext gc){

        super.render(gc);

        ui.render(gc, myData.nbLife, myData.nbRiz, getTimer());

    }
}
