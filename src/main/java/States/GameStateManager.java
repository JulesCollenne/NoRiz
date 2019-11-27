package States;

import BONUSITEM.*;
import Collider.Collider;
import Entity.Monster;
import Entity.Player;
import Sounds.SoundManager;
import Strategy.AngleStrat;
import Strategy.BonusStrat;
import Strategy.HalfRandomStratHalfAngleStrat;
import Strategy.RandomStrat;
import UI.inGameUserInterface;
import Utils.TypeEffectBonus;
import Utils.WORLDITEM;
import WorldBuilder.World;
import javafx.stage.Stage;

import static Utils.Utils.*;

public class GameStateManager{

    private int currentState = START;
    private GameState gameStates[] = new GameState[nbStates];

    private Stage theStage;

    World world = new World();

    Collider collider = new Collider(world);
    public Player player = new Player(collider, caseDimension,caseDimension*3,1);
    CollectableItem[] collectableItems = new CollectableItem[4];


    boolean isEditorTest = false;
    SoundManager sm = new SoundManager();
    Monster[] monsters = new Monster[4];

    DIF difficulty = DIF.EASY;

    public GameStateManager(Stage theStage){
        this.theStage = theStage;
        initScene();

        world.build(difficulty);

        createMonsters();
        createBonuses();

        inGameUserInterface ui = new inGameUserInterface();
        gameStates[START] = new StartMenuState(this);
        gameStates[STORY] = new StoryPlayState(this, ui);
        gameStates[PAUSE] = new PauseState(this);
        gameStates[GAMEOVER] = new GameOverState(this);
        gameStates[EDITOR] = new EditorState(this);
        gameStates[OPTIONS] = new OptionsState(this);
        gameStates[SKIN] = new SkinState(this);
        gameStates[CINEMATIQUE] = new CinematiqueState(this);
        gameStates[WIN] = new WinState(this);
        gameStates[ARCADE] = new ArcadeState(this, ui);
        gameStates[EDITORPLAY] = new EditorPlayState(this, ui);

        changeState(START);
    }

    private void createBonuses() {

        collectableItems[0] = new BstopMonsters(TypeEffectBonus.effectOnMonsters);
        collectableItems[1] = new BcanEatMonsters(TypeEffectBonus.effectOnNori);
        collectableItems[2] = new MstopNoriz(TypeEffectBonus.effectOnNori);
        collectableItems[3] = new MreverseControls(TypeEffectBonus.effectOnNori);
    }

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
        if(currentState == STORY || currentState == ARCADE) {
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
        currentState = EDITORPLAY;
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
        currentState = STORY;
        theStage.setScene(gameStates[currentState].theScene);
        theStage.show();
        gameStates[currentState].animationTimer.start();

    }

    /**
     * Create 4 monsters
     */
    private void createMonsters(){
        //Coordonnée de départ dans le cas de notre map test: Faire en sorte que les coordonnés de départ correspondent au niveaux dans lequel on est

        monsters[0] = new Monster(10 * caseDimension, 10 * caseDimension + (2*caseDimension), 1, new AngleStrat(player), "cat_follow_", collider);                                              //Monstre AngleStrat
        monsters[1] = new Monster(10 * caseDimension, 11 * caseDimension + (2*caseDimension), 1, new RandomStrat(), "cat_random_", collider);                                                     //Monstre RandomStrat
        monsters[2] = new Monster(10 * caseDimension, 10 * caseDimension + (2*caseDimension), 1, new BonusStrat(world), "cat_bonus_", collider);                                                //Monstre BonusStrat
        monsters[3] = new Monster(10 * caseDimension, 10* caseDimension + (2*caseDimension), 1, new HalfRandomStratHalfAngleStrat(player), "cat_50_", collider);                                                 //Monstre RandomStrat
    }
}

