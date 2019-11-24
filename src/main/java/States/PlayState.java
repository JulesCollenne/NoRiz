package States;

import Entity.Entity;
import Entity.Monster;
import Entity.Player;
import UI.inGameUserInterface;
import Utils.Utils;
import Utils.WORLDITEM;
import Utils.myGameData;
import WorldBuilder.worldRender;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.Random;

import static Utils.DIRECTION.*;

public class PlayState extends GameState {

    private boolean firstRender;
    private inGameUserInterface ui;

    private myGameData myData;
    private long startTimer;

    private WORLDITEM[][] map;

    private Player player;
    private Monster monsters[];

    long lastTime = System.nanoTime();

    Random rand = new Random();

    PlayState(GameStateManager gsm, inGameUserInterface ui) {
        super(gsm);
        firstRender = true;
        this.ui = ui;
        createScene();
        player = gsm.player;
        monsters = gsm.monsters;
    }

    private void createScene() {

        Group root = new Group();
        theScene = new Scene( root );
        root.setStyle("-fx-background-color: darkslategrey;");
        Canvas canvas = new Canvas(Utils.canvasSize, Utils.canvasSize);

        root.getChildren().addAll(canvas);

        theScene.setOnKeyPressed(this::keyInput);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Font theFont = Font.font( "Helvetica", FontWeight.BOLD, 24 );
        gc.setFont(theFont);
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);

        createAnimTimer(gc);
    }

    public void init(){
        firstRender = true;
        initMyData();
        player.init();
        for (Monster monster :
                monsters) {
            monster.init();
        }

        //gsm.sm.backGround.play();
    }

    public void initMyData(){

        map = Utils.copyMap(gsm.world.map);
        myData = new myGameData(map);

        int nbRice = 0;

        for (int i = 0; i < Utils.mapSize; i++){
            for (int j = 0; j < Utils.mapSize; j++){ //
                if(myData.map[i][j] == WORLDITEM.RICE)
                    nbRice++;
                if(myData.map[i][j] == WORLDITEM.SPAWN_PLAYER) {
                    gsm.player.setSpawn(i*Utils.caseDimension, j*Utils.caseDimension);
                }
            }
        }

        myData.nbRiz = nbRice;

        int k = 0;
        while(k<monsters.length){
            for (int i = 0; i < Utils.mapSize; i++) {
                for (int j = 0; j < Utils.mapSize; j++) { //
                    if (myData.map[i][j] == WORLDITEM.SPAWN_MONSTER) {
                        gsm.monsters[k].setSpawn(i*Utils.caseDimension, j*Utils.caseDimension);
                        k++;
                    }
                }
            }
        }

        startTimer = 0;

    }

    private void returnToEditor(){
        gsm.returnToEditor();
    }


    public void nextStep() {

        if(myData.nbLife <= 0)
            return;

        long timer = getTimer();


        if(timer <= 0 && !firstRender) {
            if(!gsm.isEditorTest)
                gameOver();
            else
                gsm.returnToEditor();
        }

        checkCollisions();

        player.nextStep();
        for (Monster monster : monsters) {
            monster.nextStep();
            //if(monster.getFacing() == STOP)
                //System.out.println(monster.name + " : " + monster.getFacing());
        }
    }

    private void checkCollisions() {
        checkEntityCollisions();
        checkSquare();
    }

    private void checkEntityCollisions() {
        for (Monster monster : monsters) {
            if(gsm.collider.isTouching(player, monster)) {
                if(player.getInvulnerable() > 0){
                    monster.die();
                }
                else{
                    playerTouched();
                }
            }
        }
    }

    private void checkSquare(){
        int coords[] = Utils.getSquare(player.getCenterX(), player.getCenterY());
        WORLDITEM item = map[coords[0]][coords[1]];
        if(item == WORLDITEM.RICE)
            takeRice(coords[0], coords[1]);
        if(item == WORLDITEM.BONUS)
            takeBonus(coords[0], coords[1]);

    }
    private void takeRice(int x, int y){
        myData.nbRiz -= 1;
        map[x][y] = WORLDITEM.ROAD;

        if(myData.nbRiz <= 0) {
            if (!gsm.isEditorTest)
                win();
            else
                gsm.returnToEditor();
        }
    }


    /**
     * TODO nouveau affichage
     */
    private void win() {
        if(gsm.difficulty == Utils.DIF.ARCADE){
            gsm.changeState(1);
        }
        else{
            gsm.changeState(9);
        }
    }

    private void takeBonus(int x, int y){
       //rand.nextInt(max - min + 1) + min;

        map[x][y] = WORLDITEM.ROAD;
        int r;
        if(gsm.difficulty == Utils.DIF.EASY)
            r= rand.nextInt(2); // que les bonus
        else if (gsm.difficulty == Utils.DIF.MEDIUM)
            r = rand.nextInt(4); // tout
        else
            r = rand.nextInt((3)+1); // 3 dernières cases du tableau : chance de tomber sur 1 bonus et 2 malus

        gsm.collectableItems[r].effect(player,monsters);

    }

    public void playerTouched(){
        resetPosition();
        resetFrozen();
        myData.nbLife--;
        if(myData.nbLife == 0)
            playerDie();
    }

    public void resetFrozen(){
        player.frozen = 0;
    }

    /*public void monstersTouched(Entity monster){
        monster.resetPosition();
    }*/

    private void playerDie(){
        if(!gsm.isEditorTest)
            gameOver();
        else
            gsm.returnToEditor();
    }

    private void resetPosition(){

        player.resetPosition();
        for (Entity monster : monsters) {
            monster.resetPosition();
        }

    }

    @Override
    public void keyInput(KeyEvent e) {
        switch (e.getCode()) {
            case Q:
                if(player.getReversed() > 0)
                    player.setNextFacing(RIGHT);
                else
                    player.setNextFacing(LEFT);
                break;
            case D:
                if(player.getReversed() > 0)
                    player.setNextFacing(LEFT);
                else
                    player.setNextFacing(RIGHT);
                break;
            case S:
                if(player.getReversed() > 0)
                    player.setNextFacing(UP);
                else
                    player.setNextFacing(DOWN);
                break;
            case Z:
                if(player.getReversed() > 0)
                    player.setNextFacing(DOWN);
                else
                    player.setNextFacing(UP);
                break;
            case SPACE:
                if(!gsm.isEditorTest)
                    pause();
                else
                    returnToEditor();
                break;
            case ESCAPE:
                if(!gsm.isEditorTest)
                    pause();
                else
                    returnToEditor();
                break;
            case A:
                System.out.println("OHHHHH !!! Pourquoi tu appuies sur A, mon vieux ?\n On est pas pote de UN, de DEUX, c'est une sorte de point G pour moi, le A... Alors fais un peu plus gaffe la prochaine fois... ;)");
                break;
        }
    }

    @Override
    public void render(GraphicsContext gc) {

        if(myData.nbLife <= 0){
            gc.fillText("Frero t'es dèd la, déso", Utils.canvasSize/2.0, Utils.canvasSize/2.0);
            return;
        }

        if(firstRender){
            startTimer = System.nanoTime();
            gc.clearRect(0,0,Utils.canvasSize,Utils.canvasSize);
            firstRender = false;
            worldRender.renderMap(gc, map, false);
        }

        worldRender.renderMap(gc, map, false);
        //worldRender.renderItems(gc, map, false);

        renderEntities(gc);

        ui.render(gc, myData.nbLife, myData.nbRiz, getTimer());


    }

    private void renderEntities(GraphicsContext gc){

        /*
        int square[] = Utils.getSquare(player.getCenterX(), player.getCenterY());
        worldRender.renderSquare(gc, map, square[0], square[1]);
        square = Utils.getSquare(player.getCenterX()-player.getFacingX(player.getFacing()), player.getCenterY()-player.getFacingY(player.getFacing()));
        worldRender.renderSquare(gc, map, square[0], square[1]);
        */
        player.render(gc);

        for(Monster monster : monsters){
            /*
            square = Utils.getSquare(monster.getCenterX(), monster.getCenterY());
            worldRender.renderSquare(gc, map, square[0], square[1]);
            square = Utils.getSquare(monster.getCenterX()-monster.getFacingX(monster.getFacing()), monster.getCenterY()-monster.getFacingY(monster.getFacing()));
            worldRender.renderSquare(gc, map, square[0], square[1]);
            */
            monster.render(gc);
        }
    }

    private long getTimer(){

        long timer;
        long currentTimer;
        currentTimer = System.nanoTime();
        timer = Math.abs((currentTimer/1000000000) - (startTimer/1000000000));
        return myData.leftTime - timer;
    }

    private void retourMenu(){
        gsm.changeState(0);
    }

    private void pause(){
        gsm.changeState(2);
    }

    private void gameOver(){
        gsm.changeState(3);
        //gsm.sm.backGround.stop();
    }

}

