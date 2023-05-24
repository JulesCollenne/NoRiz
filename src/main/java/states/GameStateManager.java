package states;

import bonusItem.*;
import collider.Collider;
import entity.Monster;
import entity.Noriz;
import sounds.SoundManager;
import strategy.*;
import ui.inGameUserInterface;
import utils.TypeEffectBonus;
import utils.WORLDITEM;
import worldBuilder.World;
import javafx.stage.Stage;

import java.io.*;
import java.net.URISyntaxException;
import java.util.Scanner;

import static utils.Utils.*;

public class GameStateManager{

    private int currentState = START;
    private GameState[] gameStates = new GameState[nbStates];

    private Stage theStage;

    World world = new World();

    Collider collider = new Collider(world);
    public Noriz noriz = new Noriz(collider, caseDimension,caseDimension*3,1);
    CollectableItem[] collectableItems = new CollectableItem[6];


    public boolean isEditorTest = false;
    public SoundManager sm = new SoundManager();
    public Monster[] monsters = new Monster[4];

    public DIF difficulty = DIF.EASY;

    public int currentScore;


    /*
    Sauvegarde
     */

    public int bestScore;
    public int storyProgress;
    public int volume_musique;
    public int volume_effet;

    //

    public GameStateManager(Stage theStage){
        this.theStage = theStage;
        initScene();

        manageSaveFile();

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
        collectableItems[2] = new BSmall(TypeEffectBonus.effectOnNori);
        collectableItems[3] = new BGhost(TypeEffectBonus.effectOnNori);
        collectableItems[4] = new MstopNoriz(TypeEffectBonus.effectOnNori);
        collectableItems[5] = new MreverseControls(TypeEffectBonus.effectOnNori);

    }

    private void initScene() {
    }

    /**
     * Change the currentState
     * @param newState the new state
     */
    public void changeState(int newState){
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
        gameStates[currentState].lastTime = System.nanoTime();
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

    public void reprendreJeu(){
        gameStates[currentState].animationTimer.stop();
        if(difficulty == DIF.ARCADE)
            currentState = ARCADE;
        else
            currentState = STORY;
        theStage.setScene(gameStates[currentState].theScene);
        theStage.show();
        gameStates[currentState].lastTime = System.nanoTime();
        gameStates[currentState].animationTimer.start();
    }

    /**
     * Create 4 monsters.
     */
    private void createMonsters(){
        //Coordonnée de départ dans le cas de notre map test: Faire en sorte que les coordonnés de départ correspondent au niveaux dans lequel on est

        //monsters[0] = new Monster(10 * caseDimension, 10 * caseDimension + (2*caseDimension), 1, new AngleStrat(noriz), "cat_follow_", collider);                                              //Monstre AngleStrat
        //Monstre RandomStrat

        monsters[0] = new Monster(10 * caseDimension, 10 * caseDimension + (2*caseDimension), 1, new AngleStrat(noriz), "cat_follow_", collider);                                              //Monstre AngleStrat
        monsters[1] = new Monster(10 * caseDimension, 11 * caseDimension + (2*caseDimension), 1, new RandomStrat(), "cat_random_", collider);                                                     //Monstre RandomStrat
        monsters[2] = new Monster(10 * caseDimension, 10 * caseDimension + (2*caseDimension), 1, new BonusStrat(world), "cat_bonus_", collider);                                                //Monstre BonusStrat
        monsters[3] = new Monster(10 * caseDimension, 10* caseDimension + (2*caseDimension), 1, new HalfRandomStratHalfAngleStrat(noriz), "cat_50_", collider);                                                 //Monstre RandomStrat
    }


    private void manageSaveFile(){

        try {
//            File saveFile = new File(getClass().getResource("/save/saveFile").toURI());
            File jarFile = new File(getClass().getProtectionDomain().getCodeSource().getLocation().toURI());
            File saveFile = new File(jarFile.getParent(), "saveFile");
//            InputStream in = getClass().getClassLoader().getResourceAsStream("/save/saveFile");
//            InputStream saveFile = getClass().getResourceAsStream("/save/saveFile");
            if(saveFile.createNewFile()){
                Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(saveFile)));
                writer.write("B_S = 0\nP_S = 0\nV_M = 20\nV_E = 20");
                writer.close();
                bestScore = 0;
                storyProgress = 0;
                volume_effet = 20;
                volume_musique = 20;
            }
            else{
                getSaveProgress();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }

    private void getSaveProgress(){

        try {
            File file = new File("saveFile");
            if (!file.exists()) {
                try {
                    file.createNewFile();
                    System.out.println("File created: " + file.getAbsolutePath());
                } catch (IOException e) {
                    System.err.println("Error creating the file: " + e.getMessage());
                }
            }
            FileInputStream fileInputStream = new FileInputStream("saveFile");

            Scanner sc = new Scanner(fileInputStream);

            while (sc.hasNextLine()) {

                String line = sc.nextLine();

                switch (line.substring(0, 3)) {
                    case "P_S" -> storyProgress = Integer.parseInt(line.substring(6));
                    case "B_S" -> bestScore = Integer.parseInt(line.substring(6));
                    case "V_M" -> volume_musique = Integer.parseInt(line.substring(6));
                    case "V_E" -> volume_effet = Integer.parseInt(line.substring(6));
                }
            }

            sc.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void initMonsters(){
        for (Monster monster : monsters){
            monster.init();
        }
    }


}

