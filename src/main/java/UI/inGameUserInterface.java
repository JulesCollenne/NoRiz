package UI;

import States.GameStateManager;
import Utils.Utils;
import WorldBuilder.matrixWorld;
import javafx.scene.canvas.GraphicsContext;

public class inGameUserInterface {

    GameStateManager gsm;

    public static long roundTimer = Utils.roundDuration; // en seconde
    public static long startTimer = 0;

    public static int currentNbRice = 0;

    public inGameUserInterface(GameStateManager gsm){

        this.gsm = gsm;

    }

    public static void reset(){

        startTimer = 0;
        currentNbRice = 0;

    }

    public void renderTimer(GraphicsContext gc){

        long timer;
        long currentTimer;

        currentTimer = System.nanoTime();

        timer = Math.abs((currentTimer/1000000000) - (inGameUserInterface.startTimer/1000000000));

        if(timer > inGameUserInterface.roundTimer) {
            inGameUserInterface.reset();
            gsm.changeState(3);
        }

        if(inGameUserInterface.roundTimer - timer >= 100)
            gc.fillText(inGameUserInterface.roundTimer - timer+"",Utils.canvasSize-(Utils.caseDimension+ Utils.caseDimension/2.0), 18+((20/100.0)*Utils.caseDimension));
        else
            gc.fillText(inGameUserInterface.roundTimer - timer+"",Utils.canvasSize-(Utils.caseDimension), 18+((20/100.0)*Utils.caseDimension));

    }

    public void setNbRice(){

        switch(gsm.difficulty){

            case EASY:
                inGameUserInterface.currentNbRice = matrixWorld.nbRiceWorld1;
                break;

            case MEDIUM:
                inGameUserInterface.currentNbRice = matrixWorld.nbRiceWorld2;
                break;

            case HARD:
                inGameUserInterface.currentNbRice = matrixWorld.nbRiceWorld3;
                break;

            default:
                inGameUserInterface.currentNbRice = matrixWorld.nbRiceWorld1;
                break;

        }
    }

    public void renderNbRice(GraphicsContext gc){

        gc.fillText(inGameUserInterface.currentNbRice+"",(10/100.0)*Utils.caseDimension, 18+(10/100.0)*Utils.caseDimension);

    }

}
