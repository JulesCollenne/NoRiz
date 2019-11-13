package UI;

import States.GameStateManager;
import Utils.Utils;
import WorldBuilder.matrixWorld;
import javafx.scene.canvas.GraphicsContext;

public class inGameUserInterface {

    private GameStateManager gsm;

    private static long roundTimer = Utils.roundDuration; // en seconde
    public static long startTimer = 0;

    public static int currentNbRice = 0;

    public inGameUserInterface(GameStateManager gsm){

        this.gsm = gsm;

    }

    public void init(){

        startTimer = 0;

        switch(gsm.difficulty){

            case EASY:
                currentNbRice = matrixWorld.nbRiceWorld1;
                break;

            case MEDIUM:
                currentNbRice = matrixWorld.nbRiceWorld2;
                break;

            case HARD:
                currentNbRice = matrixWorld.nbRiceWorld3;
                break;

            default:
                currentNbRice = matrixWorld.nbRiceWorld1;
                break;

        }

    }

    public void render(GraphicsContext gc){

        renderTimer(gc);
        renderNbRice(gc);

    }

    private void renderTimer(GraphicsContext gc){

        long timer;
        long currentTimer;

        currentTimer = System.nanoTime();

        timer = Math.abs((currentTimer/1000000000) - (startTimer/1000000000));

        if(timer > roundTimer) {
            gsm.changeState(3);
        }

        if(roundTimer - timer >= 100)
            gc.fillText(roundTimer - timer+"",Utils.canvasSize-(Utils.caseDimension+ Utils.caseDimension/2.0), 18+((20/100.0)*Utils.caseDimension));
        else
            gc.fillText(roundTimer - timer+"",Utils.canvasSize-(Utils.caseDimension), 18+((20/100.0)*Utils.caseDimension));

    }

    private void renderNbRice(GraphicsContext gc){

        gc.fillText(currentNbRice+"",(10/100.0)*Utils.caseDimension, 18+(10/100.0)*Utils.caseDimension);

    }

}
