package States;

import Collider.Collider;
import Entity.Monster;
import Entity.Player;
import WorldBuilder.WorldBuilder;

import java.awt.*;

public class GameStateManager {

    final int START  = 0;
    final int PLAY  = 1;
    final int PAUSE  = 2;

    int currentState = START;

    GameState gameStates[] = new GameState[3];

    public Player player = new Player(this, 100,100,1);
    public Monster[] monsters = new Monster[4];

    public int[][] map;

    WorldBuilder worldBuilder = new WorldBuilder(20,20,this);

    public Collider collider = new Collider(this);

    public GameStateManager(){
        changeState(PLAY);
        map = worldBuilder.build();
    }

    /**
     * Change the currentState
     * @param newState the new state
     */
    public void changeState(int newState){
        currentState = newState;
        for(int i=0; i < gameStates.length; i++){
            gameStates[i] = null;
        }
        switch(currentState) {
            case START:
                gameStates[currentState] = new StartMenuState(this);
                break;
            case PLAY:
                gameStates[currentState] = new PlayState(this);
                break;
            case PAUSE:
                gameStates[currentState] = new PauseState(this);
                break;
        }
    }

    public void input(){
        for(int i=0; i<gameStates.length; i++){
            if(gameStates[i] != null){
                gameStates[i].input();
            }
        }
    }

    public void draw(Graphics g){
        for(int i = 0; i < gameStates.length; i++)
            if(gameStates[i] != null)
                gameStates[i].draw(g);
    }

    public void nextStep() {
        gameStates[currentState].nextStep();
    }
}
