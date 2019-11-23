package States;

import BONUSITEM.CollectableItem;
import BONUSITEM.MstopNoriz;
import Collider.Collider;
import Entity.Monster;
import Entity.Player;
import Sounds.SoundManager;
import Strategy.AngleStrat;
import Strategy.BonusStrat;
import Strategy.HalfRandomStratHalfAngleStrat;
import Strategy.RandomStrat;
import UI.inGameUserInterface;
import Utils.WORLDITEM;
import WorldBuilder.World;
import javafx.scene.control.Skin;
import javafx.stage.Stage;

import static Utils.Utils.*;

public class GameStateManager{

    private int currentState = START;
    private GameState gameStates[] = new GameState[nbStates];

    private Stage theStage;

    World world = new World();

    Collider collider = new Collider(world);
    public Player player = new Player(collider, caseDimension,caseDimension*3,1);
    public CollectableItem[] collectableItems = new CollectableItem[2];
    //public CollectableItem[] maluses = new CollectableItem[2];


    public boolean isEditorTest = false;
    SoundManager sm = new SoundManager();
    Monster[] monsters = new Monster[4];

    public enum DIF {EASY, MEDIUM, HARD}

    DIF difficulty = DIF.EASY;

    public GameStateManager(Stage theStage){
        this.theStage = theStage;
        initScene();

        world.build(difficulty);

        createMonsters();
        createBonuses();
        //createMaluses();

        inGameUserInterface ui = new inGameUserInterface(this);
        gameStates[START] = new StartMenuState(this);
        gameStates[PLAY] = new PlayState(this, ui);
        gameStates[PAUSE] = new PauseState(this);
        gameStates[GAMEOVER] = new GameOverState(this);
        gameStates[EDITOR] = new EditorState(this);
        gameStates[OPTIONS] = new OptionsState(this);
        gameStates[ARCADE] = new ArcadeState(this,ui);
        gameStates[SKIN] = new SkinState(this);
        gameStates[CINEMATIQUE] = new CinematiqueState(this);

        changeState(START);
    }

    private void createBonuses() {
       // bonuses[0] = new BstopMonsters();
       // bonuses[1] = new BcanEatMonsters();
        collectableItems[0] = new MstopNoriz();
        collectableItems[1] = new MstopNoriz();

    }

 /*   private void createMaluses(){
        maluses[0] = new MstopNoriz();
        maluses[1] = new MstopNoriz();
    }*/

    private void initScene() {
    }

    /**
     * Change the currentState
     * @param newState the new state
     */
    void changeState(int newState){
        //if(currentState != PLAY)
        gameStates[currentState].animationTimer.stop();
        //else
            //gameStates[currentState].animationTimer2.stop();
        currentState = newState;
        if(currentState == PLAY) {
            world.build(difficulty);
        }
        gameStates[currentState].init();
        theStage.setScene(gameStates[currentState].theScene);
        theStage.show();
        //if(currentState != PLAY)
        gameStates[currentState].animationTimer.start();
        //else
            //gameStates[currentState].animationTimer2.play();
    }

    void changeToEditorTest(WORLDITEM[][] map){
        isEditorTest = true;
        gameStates[currentState].animationTimer.stop();
        currentState = PLAY;
        world.build(map);
        gameStates[currentState].init();
        theStage.setScene(gameStates[currentState].theScene);
        theStage.show();
        gameStates[currentState].animationTimer.start();
    }
    void returnToEditor(){
        isEditorTest = false;
        gameStates[currentState].animationTimer.stop();
        currentState = EDITOR;
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

        monsters[0] = new Monster(10 * caseDimension, 10 * caseDimension + (2*caseDimension), 1, new AngleStrat(player), "Cat hamaran", collider);                                              //Monstre AngleStrat
        monsters[1] = new Monster(10 * caseDimension, 11 * caseDimension + (2*caseDimension), 1, new RandomStrat(), "Cat holik", collider);                                                     //Monstre RandomStrat
        monsters[2] = new Monster(10 * caseDimension, 10 * caseDimension + (2*caseDimension), 1, new BonusStrat(world), "Cat o'dick", collider);                                                //Monstre BonusStrat
        monsters[3] = new Monster(10 * caseDimension, 10* caseDimension + (2*caseDimension), 1, new HalfRandomStratHalfAngleStrat(player), "Cat reuh", collider);                               //Monstre Half
    }
}

