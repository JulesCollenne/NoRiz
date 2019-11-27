package States;

import UI.inGameUserInterface;
import WorldBuilder.worldRender;
import Utils.Utils;
import javafx.scene.canvas.GraphicsContext;

public class ArcadeState extends PlayState {
    ArcadeState(GameStateManager gsm, inGameUserInterface ui) {
        super(gsm, ui);
    }

    @Override
    void win() {

        myData.score += myData.nbLife * 100;
        gsm.changeState(1);
    }

    void playerDie(){
        gameOver();
    }

    @Override
    public void render(GraphicsContext gc){
        super.render(gc);

        ui.render(gc, myData.nbLife, myData.nbRiz, getTimer(), myData.score);
    }


}
