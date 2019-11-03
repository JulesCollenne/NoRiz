package States;

import Entity.Monster;
import Entity.Player;

import java.awt.*;

public class GameStateManager {

    // 0 = StartMenuState, 1 = PlayState, 2 = PauseState
    GameState gameStates[] = new GameState[3];

    Player player = new Player(100,100,1);
    Monster[] monsters = new Monster[4];

    public GameStateManager(){
        gameStates[1] = new PlayState(this);
    }

    public void draw(Graphics g){
        for(int i = 0; i < gameStates.length; i++)
            if(gameStates[i] != null)
                gameStates[i].draw(g);
    }

}
