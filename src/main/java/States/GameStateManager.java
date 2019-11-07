package States;

import Collider.Collider;
import Entity.Monster;
import Entity.Player;
import Input.KeysManager;
import Input.MouseManager;
import Strategy.*;
import UI.GamePanel;
import WorldBuilder.WorldBuilder;

import java.awt.*;

public class GameStateManager {

    GamePanel gamePanel;

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

    public GameStateManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        changeState(PLAY);
        map = worldBuilder.build();
        createMonster();
    }

    public void createMonster(){
        monsters[0] = new Monster(200, 200, 1, new AngleStrat(this));                       //Monstre AngleStrat
        monsters[1] = new Monster(300, 300, 1, new RandomStrat());                               //Monstre RandomStrat
        monsters[2] = new Monster(400, 400, 1, new FollowStrat());                               //Monstre FollowStrat
        monsters[3] = new Monster(500, 500, 1, new BonusStrat());                                //Monstre BonusStrat
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

    public void input(KeysManager key, MouseManager mouse){
        for(int i=0; i<gameStates.length; i++){
            if(gameStates[i] != null){
                gameStates[i].input(key, mouse);
            }
        }
    }

    public void draw(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(0,0,1000,1000);
        for(int i = 0; i < gameStates.length; i++)
            if(gameStates[i] != null)
                gameStates[i].draw(g);
    }

    public void nextStep() {
        gameStates[currentState].nextStep();
    }
}
