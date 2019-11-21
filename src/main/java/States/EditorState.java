package States;

import Utils.Utils;
import Utils.WORLDITEM;

import static Utils.Utils.copyMap;
import static Utils.WORLDITEM.*;

import WorldBuilder.World;
import WorldBuilder.worldRender;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

public class EditorState extends GameState {

    private WORLDITEM posingBlock = WALL;
    private WORLDITEM[][] buildingMap;
    private int nbSpawnPlayer = 0;
    private int nbSpawnMonster = 0;

    EditorState(GameStateManager gsm) {
        super(gsm);
        createScene();
        init();
    }

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

    private void playOnCurrentMap(){

        if(testCurrentMap()){
            gsm.changeToEditorTest(buildingMap);
            return;
        }
        else{
            return;
        }
    }

    private void mouseInput(MouseEvent e){
        int x = (int) e.getX();
        int y = (int) e.getY();

        int coords[] = Utils.getSquare(x,y);

        if(isCorrect(coords)) {
            if(manageSpawn(coords)) {
                buildingMap[coords[0]][coords[1]] = posingBlock;
            }
        }
    }

    private boolean isCorrect(int[] coords) {
        return coords[0] >= 0 && coords[1] >= 2 && coords[0] < Utils.mapSize && coords[1] < Utils.mapSize;
    }

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

    public void createAnimTimer() {
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {

            }
        };
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

    private boolean checkCorridor(int i, int j){

        if(buildingMap[i+1][j] != WALL && buildingMap[i][j+1] != WALL && buildingMap[i+1][j+1] != WALL) {
            return false;
        }

        return true;
    }

    private boolean testCurrentMap(){

        int nbRice = 0;
        int nbSpP = 0;
        int nbSpM = 0;

        for(int i=0; i<Utils.mapSize; i++){
            for(int j=0; j<Utils.mapSize; j++){

                if(j > 1 && i < 24 && buildingMap[i][j] != WALL){
                    if(!checkCorridor(i, j)){

                        Alert alert = new Alert(Alert.AlertType.NONE);
                        alert.setTitle("Erreur");
                        alert.setHeaderText("Votre carte n'est pas valide");
                        alert.setContentText("Votre carte ne doit être composé que de couloir !! Ce n'est pas le cas en "+(i+1) +", "+(j-1));

                        ButtonType btnOk = new ButtonType("Je m'excuse ô grand maître");
                        alert.getButtonTypes().addAll(btnOk);
                        alert.showAndWait();

                        return false;
                    }
                }

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
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setTitle("Erreur");
            alert.setHeaderText("Votre carte n'est pas valide");
            alert.setContentText("Il doit y avoir au moins un grain de riz sur la map!");

            ButtonType btnOk = new ButtonType("Je m'excuse ô grand maître");
            alert.getButtonTypes().addAll(btnOk);
            alert.showAndWait();
            return false;
        }

        if(nbSpP != 1 || nbSpM != 4){

            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setTitle("Erreur");
            alert.setHeaderText("Votre carte n'est pas valide");
            alert.setContentText("Il doit y avoir un spaw (bleu) pour le joueur, et 4 spawn (rouge) pour les monstres !");

            ButtonType btnOk = new ButtonType("Je m'excuse ô grand maître");
            alert.getButtonTypes().addAll(btnOk);
            alert.showAndWait();
            return false;

        }

        if(!testRice()){

            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setTitle("Erreur");
            alert.setHeaderText("Votre carte n'est pas valide");
            alert.setContentText("Le joueur ne peut pas accéder à tout les grains de riz !! Il faut aussi que les monstres puissent accéder au joueur !!");
            ButtonType btnOk = new ButtonType("Je m'excuse ô grand maître");
            alert.getButtonTypes().addAll(btnOk);
            alert.showAndWait();

            return false;

        }

        return true;

    }

    private boolean testRice(){

        WORLDITEM[][] tempMap = copyMap(buildingMap);

        for(int i=0; i<Utils.mapSize; i++) {
            for (int j = 0; j < Utils.mapSize; j++) {

                if(tempMap[i][j] == SPAWN_PLAYER){

                    tempMap[i][j] = WALL;

                    if(tempMap[i][j+1] != WALL)
                        tryDown(tempMap, i, j+1);
                    if(tempMap[i][j-1] != WALL)
                        tryUp(tempMap, i, j-1);
                    if(tempMap[i-1][j] != WALL)
                        tryLeft(tempMap, i-1, j);
                    if(tempMap[i+1][j] != WALL)
                        tryRight(tempMap, i+1, j);
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

    private void tryDown(WORLDITEM[][] tempMap, int i, int j){

        tempMap[i][j] = WALL;

        if(tempMap[i][j+1] != WALL)
            tryDown(tempMap, i, j+1);
        if(tempMap[i][j-1] != WALL)
            tryUp(tempMap, i, j-1);
        if(tempMap[i-1][j] != WALL)
            tryLeft(tempMap, i-1, j);
        if(tempMap[i+1][j] != WALL)
            tryRight(tempMap, i+1, j);


    }

    private void tryUp(WORLDITEM[][] tempMap, int i, int j){

        tempMap[i][j] = WALL;

        if(tempMap[i][j+1] != WALL)
            tryDown(tempMap, i, j+1);
        if(tempMap[i][j-1] != WALL)
            tryUp(tempMap, i, j-1);
        if(tempMap[i-1][j] != WALL)
            tryLeft(tempMap, i-1, j);
        if(tempMap[i+1][j] != WALL)
            tryRight(tempMap, i+1, j);

    }

    private void tryLeft(WORLDITEM[][] tempMap, int i, int j){

        tempMap[i][j] = WALL;

        if(tempMap[i][j+1] != WALL)
            tryDown(tempMap, i, j+1);
        if(tempMap[i][j-1] != WALL)
            tryUp(tempMap, i, j-1);
        if(tempMap[i-1][j] != WALL)
            tryLeft(tempMap, i-1, j);
        if(tempMap[i+1][j] != WALL)
            tryRight(tempMap, i+1, j);

    }

    private void tryRight(WORLDITEM[][] tempMap, int i, int j){

        tempMap[i][j] = WALL;

        if(tempMap[i][j+1] != WALL)
            tryDown(tempMap, i, j+1);
        if(tempMap[i][j-1] != WALL)
            tryUp(tempMap, i, j-1);
        if(tempMap[i-1][j] != WALL)
            tryLeft(tempMap, i-1, j);
        if(tempMap[i+1][j] != WALL)
            tryRight(tempMap, i+1, j);

    }
}