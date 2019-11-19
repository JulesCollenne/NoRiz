package States;

import Collider.Collider;
import Entity.Monster;
import Entity.Player;
import Strategy.AngleStrat;
import Strategy.BonusStrat;
import Strategy.FollowStrat;
import Strategy.RandomStrat;
import UI.inGameUserInterface;
import Utils.Utils;
import Utils.myGameData;
import WorldBuilder.World;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class GameStateManager {

    private final int START  = 0;
    private final int PLAY  = 1;
    private final int PAUSE  = 2;
    private final int GAMEOVER = 3;
    private final int EDITOR = 4;

    public int currentState = START;
    public GameState gameStates[] = new GameState[5];

    public Player player = new Player(this, Utils.caseDimension,Utils.caseDimension*3,1);
    public Monster[] monsters = new Monster[4];

    private Stage theStage;

    public World world = new World(Utils.mapSize,Utils.mapSize);

    public Collider collider = new Collider(world);

    private boolean stateChanged = false;

    public enum DIF {EASY, MEDIUM, HARD}

    public DIF difficulty = DIF.EASY;

    public GameStateManager(Stage theStage){
        this.theStage = theStage;

        initScene();

        world.build(difficulty);
        createMonsters();

        gameStates[START] = new StartMenuState(this);
        inGameUserInterface ui = new inGameUserInterface(this);
        gameStates[PLAY] = new PlayState(this, ui, theStage);
        gameStates[PAUSE] = new PauseState(this);
        gameStates[GAMEOVER] = new GameOverState(this);
        gameStates[EDITOR] = new EditorState(this);

        changeState(START);
    }

    private void initScene() {
    }

    /**
     * Create 4 monsters
     */

    private void createMonsters(){
        //Coordonnée de départ dans le cas de notre map test: Faire en sorte que les coordonnés de départ correspondent au niveaux dans lequel on est
        monsters[0] = new Monster(10 * Utils.caseDimension, 10 * Utils.caseDimension + (2*Utils.caseDimension), 1, new AngleStrat(player), "Jean-Luc Massat", collider);                       //Monstre AngleStrat
        monsters[1] = new Monster(10 * Utils.caseDimension, 11 * Utils.caseDimension+ (2*Utils.caseDimension), 1, new RandomStrat( 1), "Hamri", collider);                               //Monstre RandomStrat
        monsters[2] = new Monster(10 * Utils.caseDimension, Utils.caseDimension+ (2*Utils.caseDimension), 1, new FollowStrat(), "Mr POC", collider);                               //Monstre FollowStrat
        monsters[3] = new Monster(10 * Utils.caseDimension, 10 * Utils.caseDimension+ (2*Utils.caseDimension), 1, new BonusStrat(), "Di Molfetta", collider);                                //Monstre BonusStrat
    }

    /**
     * Change the currentState
     * @param newState the new state
     */
    public void changeState(int newState){
        gameStates[currentState].animationTimer.stop();
        currentState = newState;
        if(currentState == PLAY) {
            world.build(difficulty);
        }
        gameStates[currentState].init();
        theStage.setScene(gameStates[currentState].theScene);
        theStage.show();
        stateChanged = true;
        gameStates[currentState].animationTimer.start();
    }

    public void reprendreJeu(){

        gameStates[currentState].animationTimer.stop();
        currentState = PLAY;
        theStage.setScene(gameStates[currentState].theScene);
        theStage.show();
        stateChanged = true;
        gameStates[currentState].animationTimer.start();

    }

    public void nextStep() {
        gameStates[currentState].nextStep();
    }

    void input(KeyEvent e) {
        gameStates[currentState].input(e);
    }

    void render(GraphicsContext gc) {
        if(stateChanged){
            gc.clearRect(0,0,Utils.canvasSize,Utils.canvasSize);
            stateChanged = false;
        }
        gameStates[currentState].render(gc);
    }
}

