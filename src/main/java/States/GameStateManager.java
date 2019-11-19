package States;

import Collider.Collider;
import Entity.Monster;
import Entity.Player;
import Sounds.SoundManager;
import Strategy.*;
import UI.inGameUserInterface;
import WorldBuilder.World;
import javafx.stage.Stage;

import static Utils.Utils.*;

public class GameStateManager {

    private int currentState = START;
    private GameState gameStates[] = new GameState[5];

    private Stage theStage;

    World world = new World();

    Collider collider = new Collider(world);
    public Player player = new Player(collider, caseDimension,caseDimension*3,1);
    Monster[] monsters = new Monster[4];

    SoundManager sm = new SoundManager();

    public enum DIF {EASY, MEDIUM, HARD}

    DIF difficulty = DIF.EASY;

    public GameStateManager(Stage theStage){
        this.theStage = theStage;
        initScene();

        world.build(difficulty);

        createMonsters();

        gameStates[START] = new StartMenuState(this);
        inGameUserInterface ui = new inGameUserInterface(this);
        gameStates[PLAY] = new PlayState(this, ui);
        gameStates[PAUSE] = new PauseState(this);
        gameStates[GAMEOVER] = new GameOverState(this);
        gameStates[EDITOR] = new EditorState(this);

        changeState(START);
    }

    private void initScene() {
    }


    /**
     * Change the currentState
     * @param newState the new state
     */
    void changeState(int newState){
        gameStates[currentState].animationTimer.stop();
        currentState = newState;
        if(currentState == PLAY) {
            world.build(difficulty);
        }
        gameStates[currentState].init();
        theStage.setScene(gameStates[currentState].theScene);
        theStage.show();
        gameStates[currentState].animationTimer.start();
    }

    void reprendreJeu(){
        gameStates[currentState].animationTimer.stop();
        currentState = PLAY;
        theStage.setScene(gameStates[currentState].theScene);
        theStage.show();
        gameStates[currentState].animationTimer.start();

    }

    /**
     * Create 4 monsters
     */

    private void createMonsters(){
        //Coordonnée de départ dans le cas de notre map test: Faire en sorte que les coordonnés de départ correspondent au niveaux dans lequel on est
        monsters[0] = new Monster(10 * caseDimension, 10 * caseDimension + (2*caseDimension), 1, new AngleStrat(player), "AngleStrat", collider);                                               //Monstre AngleStrat
        monsters[1] = new Monster(10 * caseDimension, 11 * caseDimension + (2*caseDimension), 1, new RandomStrat(), "RandomStrat", collider);                                                   //Monstre RandomStrat
        monsters[2] = new Monster(10 * caseDimension, 10 * caseDimension + (2*caseDimension), 1, new BonusStrat(world), "BonusStrat", collider);                                                //Monstre BonusStrat
        monsters[3] = new Monster(10 * caseDimension, 10* caseDimension + (2*caseDimension), 1, new HalfRandomStratHalfAngleStrat(player), "Half RandomStrat Half AngleStrat", collider);       //Monstre BonusStrat
    }

}

