package States;

import Utils.Utils;
import Utils.WORLDITEM;
import WorldBuilder.worldRender;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import static Utils.Utils.*;
import static Utils.WORLDITEM.*;

public class EditorState extends GameState {

    private WORLDITEM posingBlock = WALL;
    private WORLDITEM[][] buildingMap;
    private int nbSpawnPlayer = 0;
    private int nbSpawnMonster = 0;

    /**
     * Constructor
     */
    EditorState(GameStateManager gsm) {
        super(gsm);
        createScene();
        init();
    }

    /**
     * Create the scene, used in the constructor
     */
    private void createScene() {
        Group root = new Group();
        theScene = new Scene( root );
        root.setStyle("-fx-background-color: darkslategrey;");

        Canvas canvas = new Canvas(Utils.canvasSize, Utils.canvasSize);

        root.getChildren().addAll(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Font theFont = Font.font( "Helvetica", FontWeight.BOLD, 24 );
        gc.setFont(theFont);
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);


        Image im = new Image("Buttons/sign_test.png");
        Button test = new Button();
        test.setStyle("-fx-border-width: 0; -fx-background-color: transparent; -fx-border-color: transparent; -fx-background-radius: 0");
        test.setLayoutX(Utils.canvasSize - im.getWidth()- 20);
        test.setLayoutY(Utils.caseDimension+10);
        test.setGraphic(new ImageView(im));
        test.setOnAction(e -> playOnCurrentMap());


        root.getChildren().addAll(test);

        animationTimer = new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                // game logic
                nextStep();
                // render
                render(gc);
            }
        };

        theScene.setOnKeyPressed(
                this::keyInput);

        theScene.setOnMousePressed(
                this::mouseInput);

        theScene.setOnMouseDragged(this::mouseInput);

    }

    @Override
    public void nextStep() {

    }

    @Override
    public void keyInput(KeyEvent e) {
        switch (e.getCode()) {
            case A:
                setPosingBlock(ROAD);
                break;
            case Z:
                setPosingBlock(WALL);
                break;
            case E:
                setPosingBlock(BONUS);
                break;
            case R:
                setPosingBlock(RICE);
                break;
            case P:
                setPosingBlock(SPAWN_PLAYER);
                break;
            case M:
                setPosingBlock(SPAWN_MONSTER);
                break;
            case S:
                saveMap();
                break;
            case L:
                loadMap();
                nbSpawnMonster = 4;
                nbSpawnPlayer = 1;
                break;
            case ESCAPE:
                gsm.changeState(0);
                break;
        }
    }

    /**
     *
     * @param posingBlock set this block as the current Posing block
     */
    private void setPosingBlock(WORLDITEM posingBlock) {
        this.posingBlock = posingBlock;
    }

    /**
     * Allow player to play on the map created
     */
    private void playOnCurrentMap(){
        if(testCurrentMap())
            gsm.changeToEditorTest(buildingMap);
    }

    /**
     * Manage input of the mouse
     */
    private void mouseInput(MouseEvent e){
        int x = (int) e.getX();
        int y = (int) e.getY();

        int coords[] = Utils.getSquareMouse(x,y);

        if(isCorrect(coords)) {
            if(manageSpawn(coords)) {
                buildingMap[coords[0]][coords[1]] = posingBlock;
            }
        }
    }

    /**
     * Check if the coords of the mouse are not outside the window
     */
    private boolean isCorrect(int[] coords) {
        return coords[0] >= 0 && coords[1] >= 2 && coords[0] < Utils.mapSize && coords[1] < Utils.mapSize;
    }


    /**
     *
     * Cette méthode permet de s'assurer qu'on ne puisse pas poser plus d'un spawn pour le joueur, et 4 spawns pour les monstres
     *
     */
    private boolean manageSpawn(int[] coords){

        WORLDITEM item = buildingMap[coords[0]][coords[1]];

        if(posingBlock == SPAWN_MONSTER){
            if(nbSpawnMonster < 4)
                nbSpawnMonster++;
            else
                return false;
        }
        if(posingBlock == SPAWN_PLAYER){
            if(nbSpawnPlayer < 1)
                nbSpawnPlayer++;
            else
                return false;
        }

        if(item == SPAWN_MONSTER){
            nbSpawnMonster--;
        }
        if(item == SPAWN_PLAYER){
            nbSpawnPlayer--;
        }

        return true;
    }

    @Override
    public void render(GraphicsContext gc) {

        worldRender.renderMap(gc, buildingMap, true);
        Image Header = new Image("UI/headerEditor.png", 800, 64, true, false);
        gc.drawImage(Header, 0, 0);

    }

    @Override
    public void init() {
        posingBlock = WALL;
        buildingMap = gsm.world.makeCleanMap();
        nbSpawnMonster = 0;
        nbSpawnPlayer = 0;
    }

    /**
     * Save the current map in a file
     */
    private void saveMap(){

        if(testCurrentMap()) {
            gsm.world.saveMap(buildingMap);
        }
    }

    /**
     * Load a map from a file to the current map
     */
    private void loadMap(){
        WORLDITEM[][] tempMap = gsm.world.loadMap();
        if(tempMap != null){
            buildingMap = tempMap;
            nbSpawnPlayer = 0;
            nbSpawnMonster = 0;
        }
    }


    /**
     *
     * Entrée: Positon d'un item ROAD dans la matrice
     * S'assure que cet item fait partit d'un couloir
     *
     */
    private boolean checkCorridor(int i, int j){
        return buildingMap[i + 1][j] == WALL || buildingMap[i][j + 1] == WALL || buildingMap[i + 1][j + 1] == WALL;
    }


    /**
     *
     * Gère tout les tests liés à la validité de la map
     *
     */
    private boolean testCurrentMap(){

        int nbRice = 0;
        int nbSpP = 0;
        int nbSpM = 0;

        for(int i=0; i<Utils.mapSize; i++){
            for(int j=0; j<Utils.mapSize; j++){

                /*
                if(j > 1 && i < mapSize-1 && buildingMap[i][j] != WALL){
                    if(!checkCorridor(i, j)){
                        alertCorridor(i,j);
                        return false;
                    }
                }
                */

                switch(buildingMap[i][j]){

                    case RICE:
                        nbRice++;
                        break;
                    case SPAWN_PLAYER:
                        nbSpP++;
                        break;
                    case SPAWN_MONSTER:
                        nbSpM++;
                        break;
                    case BONUS:

                        break;
                    default:
                        break;
                }
            }
        }
        if(nbRice <= 0){
            alertNbRice();
            return false;
        }
        if(nbSpP != 1 || nbSpM != 4){
            alertSpawn();
            return false;
        }
        if(!testPossible()){
            alertIncorrectMap();
            return false;
        }
        return true;
    }

    /**
     *
     * Test que tous les grains de riz et les spawns de monstres sont accessibles depuis le spawn du joueur:
     *      -   Parcour récursivement la carte en remplacant les routes parcourus par des WALL
     *      -   S'il reste des grains de riz/spawn de monstres à la fin, la map n'est pas valide
     *
     */
    private boolean testPossible(){

        WORLDITEM[][] tempMap = copyMap(buildingMap);

        for(int i=0; i<Utils.mapSize; i++) {
            for (int j = 0; j < Utils.mapSize; j++) {

                if(tempMap[i][j] == SPAWN_PLAYER){

                    search(tempMap, i, j);
                }
            }
        }

        for(int i=0; i<Utils.mapSize; i++) {
            for (int j = 0; j < Utils.mapSize; j++) {
                if(tempMap[i][j] == RICE || tempMap[i][j] == SPAWN_MONSTER){
                    return false;
                }
            }
        }

        return true;
    }


    /**
     *  Pour les 4 fonctions goTo:
     *     permet de parcourir récursivement la map
     */
    private void search(WORLDITEM[][] tempMap, int i, int j){

        tempMap[i][j] = WALL;

        if(tempMap[i][(j+1) % mapSize] != WALL)
            search(tempMap, i, (j+1) % mapSize);
        if(tempMap[i][(((j-1) % mapSize) + mapSize) % mapSize] != WALL)
            search(tempMap, i, (((j-1) % mapSize) + mapSize) % mapSize);
        if(tempMap[(((i-1) % mapSize) + mapSize) % mapSize][j] != WALL)
            search(tempMap, (((i-1) % mapSize) + mapSize) % mapSize, j);
        if(tempMap[(i+1) % mapSize][j] != WALL)
            search(tempMap, (i+1) % mapSize, j);

    }

    /**
     * Show an alert if the map of the editor is not only corridors
     */
    private void alertCorridor(int i, int j) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Erreur");
        alert.setHeaderText("Votre carte n'est pas valide");
        alert.setContentText("Votre carte ne doit être composé que de couloir !! Ce n'est pas le cas en "+(i+1) +", "+(j-1));

        ButtonType btnOk = new ButtonType("Je m'excuse ô grand maître");
        alert.getButtonTypes().addAll(btnOk);
        alert.showAndWait();
    }

    /**
     * Show an alert if the map is incorrect : If the player can't take all the rice, or if monsters can't get the player
     */
    private void alertIncorrectMap() {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Erreur");
        alert.setHeaderText("Votre carte n'est pas valide");
        alert.setContentText("Le joueur ne peut pas accéder à tout les grains de riz !! Il faut aussi que les monstres puissent accéder au joueur !!");
        ButtonType btnOk = new ButtonType("Je m'excuse ô grand maître");
        alert.getButtonTypes().addAll(btnOk);
        alert.showAndWait();
    }

    /**
     * Show an alert if the spawns on the map are incorrect
     */
    private void alertSpawn() {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Erreur");
        alert.setHeaderText("Votre carte n'est pas valide");
        alert.setContentText("Il doit y avoir un spaw (bleu) pour le joueur, et 4 spawn (rouge) pour les monstres !");

        ButtonType btnOk = new ButtonType("Je m'excuse ô grand maître");
        alert.getButtonTypes().addAll(btnOk);
        alert.showAndWait();
    }

    /**
     * Show an alert if the number of rice in the map is = 0
     */
    private void alertNbRice() {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Erreur");
        alert.setHeaderText("Votre carte n'est pas valide");
        alert.setContentText("Il doit y avoir au moins un grain de riz sur la map!");

        ButtonType btnOk = new ButtonType("Je m'excuse ô grand maître");
        alert.getButtonTypes().addAll(btnOk);
        alert.showAndWait();
    }
}