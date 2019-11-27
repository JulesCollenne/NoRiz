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

    public void render(GraphicsContext gc){

        if(firstRender){
            startTimer = System.nanoTime();
            gc.clearRect(0,0,Utils.canvasSize,Utils.canvasSize);
            firstRender = false;
            worldRender.renderMap(gc, map, false);
        }
        worldRender.renderMap(gc, map, false);
        //worldRender.renderItems(gc, map, false);

        renderEntities(gc);

        ui.render(gc, myData.nbLife, myData.nbRiz, getTimer());

    }
}
