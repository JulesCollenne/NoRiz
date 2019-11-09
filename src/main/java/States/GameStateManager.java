package States;

import Collider.Collider;
import Entity.Monster;
import Entity.Player;
import Strategy.*;
import Utils.Utils;
import Utils.WORLDITEM;
import WorldBuilder.World;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class GameStateManager {

    private final int START  = 0;
    private final int PLAY  = 1;
    private final int PAUSE  = 2;

    private int currentState = START;

    private GameState gameStates[] = new GameState[3];

    public Player player = new Player(this, Utils.caseDimension,Utils.caseDimension,1);
    public Monster[] monsters = new Monster[4];

    public WORLDITEM[][] map;

    public Stage theStage;

    public World world = new World(Utils.mapSize,Utils.mapSize);

    public Collider collider = new Collider(this);

    public GameStateManager(Stage theStage){
        this.theStage = theStage;
        changeState(START);
        map = world.build();
        createMonsters();
    }

    /**
     * Create 4 monsters
     */

    private void createMonsters(){
        //Coordonnée de départ dans le cas de notre map test: Faire en sorte que les coordonnés de départ correspondent au niveaux dans lequel on est
        monsters[0] = new Monster(10 * Utils.caseDimension, 10 * Utils.caseDimension, 1, new AngleStrat(this),this, "Jean-Luc Massat");                       //Monstre AngleStrat
        monsters[1] = new Monster(Utils.caseDimension, 10 * Utils.caseDimension, 1, new RandomStrat(),this, "Hamri");                               //Monstre RandomStrat
        monsters[2] = new Monster(10 * Utils.caseDimension, Utils.caseDimension, 1, new FollowStrat(),this, "Mr POC");                               //Monstre FollowStrat
        monsters[3] = new Monster(10 * Utils.caseDimension, 10 * Utils.caseDimension, 1, new BonusStrat(),this, "Di Molfetta");                                //Monstre BonusStrat
    }

    /**
     * Change the currentState
     * @param newState the new state
     */
    private void changeState(int newState){
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

    public void nextStep() {
        gameStates[currentState].nextStep();
    }

    public void input(KeyEvent e) {
        gameStates[currentState].input(e);
    }

    public void render(GraphicsContext gc) {
        gameStates[currentState].render(gc);
    }
}
