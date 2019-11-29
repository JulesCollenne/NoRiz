package States;

import UI.inGameUserInterface;
import WorldBuilder.worldRender;
import Utils.Utils;
import javafx.scene.canvas.GraphicsContext;

import java.io.File;

public class ArcadeState extends PlayState {


    ArcadeState(GameStateManager gsm, inGameUserInterface ui) {
        super(gsm, ui);
    }

    @Override
    void win() {

        myData.score += myData.nbLife * 100;

        gsm.currentScore = myData.score;

        gsm.changeState(6);

    }

    void playerDie(){

        saveBestScore(myData.score);
        gsm.currentScore = 0;
        gameOver();
    }

    @Override
    void manageScore() {
        myData.score = gsm.currentScore;
    }

    @Override
    public void render(GraphicsContext gc){
        super.render(gc);

        ui.render(gc, myData.nbLife, myData.nbRiz, getTimer(), myData.score);
    }


    void saveBestScore(int score){




    }


}
