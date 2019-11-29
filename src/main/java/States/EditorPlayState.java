package States;

import UI.inGameUserInterface;
import WorldBuilder.worldRender;
import Utils.Utils;
import javafx.scene.canvas.GraphicsContext;

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

    @Override
    void manageScore() {
        return;
    }

    @Override
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
