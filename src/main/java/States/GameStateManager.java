package States;

import Entity.Monster;
import Entity.Player;
import WorldBuilder.WorldBuilder;

import java.awt.*;

public class GameStateManager {

    final int START  = 0;
    final int PLAY  = 1;
    final int PAUSE  = 2;

    // 0 = StartMenuState, 1 = PlayState, 2 = PauseState
    GameState gameStates[] = new GameState[3];

    Player player = new Player(100,100,1);
    Monster[] monsters = new Monster[4];

    int[][] map;

    WorldBuilder worldBuilder = new WorldBuilder();


    public GameStateManager(){
        gameStates[PLAY] = new PlayState(this);
        map = worldBuilder.build();
    }

    public void changeState(int state){
        for(int i=0; i < gameStates.length; i++){
            gameStates[i] = null;
        }
        switch(state) {
            case START:
                gameStates[state] = new StartMenuState(this);
            case PLAY:
                gameStates[state] = new PlayState(this);
            case PAUSE:
                gameStates[state] = new PauseState(this);
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

}
