package states;

import entity.Entity;
import entity.Monster;
import entity.Noriz;
import ui.inGameUserInterface;
import utils.Utils;
import utils.WORLDITEM;
import utils.TypeEffectBonus;
import utils.myGameData;
import worldBuilder.worldRender;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.Random;

import static utils.DIRECTION.*;

public abstract class PlayState extends GameState {

    boolean firstRender;
    inGameUserInterface ui;

    myGameData myData;
    long startTimer;

    WORLDITEM[][] map;

    Noriz noriz;
    Monster[] monsters;

    Random rand = new Random();

    PlayState(GameStateManager gsm, inGameUserInterface ui) {
        super(gsm);
        firstRender = true;
        this.ui = ui;
        createScene();
        noriz = gsm.noriz;
        monsters = gsm.monsters;
    }

    abstract void win();
    abstract void playerDie();
    abstract void manageScore();


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
        gsm.sm.menu.stop();
        firstRender = true;
        initMyData();
        noriz.init();
        for (Monster monster :
                monsters) {
            monster.init();
        }

        gsm.sm.backGround.play();
    }

    private void initMyData(){

        map = gsm.world.map;
        //map = Utils.copyMap(gsm.world.map);
        myData = new myGameData(map);

        manageScore();

        int nbRice = 0;

        for (int i = 0; i < Utils.mapSize; i++){
            for (int j = 0; j < Utils.mapSize; j++){ //
                if(myData.map[i][j] == WORLDITEM.RICE)
                    nbRice++;
                if(myData.map[i][j] == WORLDITEM.SPAWN_PLAYER) {
                    gsm.noriz.setSpawn(i*Utils.caseDimension, j*Utils.caseDimension);
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

        noriz.nextStep();
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
            if(gsm.collider.isTouching(noriz, monster)) {
                if(noriz.getInvulnerable() > 0){
                    monster.die();
                    myData.score += 100;
                }
                else{
                    playerTouched();
                }
            }
        }
    }

    private void checkSquare(){
        int[] coords = Utils.getSquare(noriz.getCenterX(), noriz.getCenterY());
        WORLDITEM item = map[coords[0]][coords[1]];
        if(item == WORLDITEM.RICE)
            takeRice(coords[0], coords[1]);
        if(item == WORLDITEM.BONUS)
            takeBonus(coords[0], coords[1]);

    }
    private void takeRice(int x, int y){
        myData.nbRiz -= 1;
        map[x][y] = WORLDITEM.ROAD;
        myData.score += 10;

        if(myData.nbRiz <= 0)
            win();
    }

    private void takeBonus(int x, int y){
       //rand.nextInt(max - min + 1) + min;

        map[x][y] = WORLDITEM.ROAD;
        int r;
        if(gsm.difficulty == Utils.DIF.EASY)
            r= rand.nextInt(4); // que les bonus de 0 à 4
        else if (gsm.difficulty == Utils.DIF.MEDIUM)
            r = rand.nextInt(5); // tout sauf dernier malus
        else
            r = rand.nextInt(6); // tout

        if(gsm.collectableItems[r].getTypeEffectBonus() == TypeEffectBonus.effectOnNori)
        gsm.collectableItems[r].effect(noriz);
        else
            gsm.collectableItems[r].effect(monsters);
    }

    private void playerTouched(){
        resetPosition();
        resetFrozen();
        myData.nbLife--;
        if(myData.nbLife == 0)
            playerDie();
    }

    private void resetFrozen(){
        noriz.frozen = 0;
    }

    /*public void monstersTouched(Entity monster){
        monster.resetPosition();
    }*/

    private void resetPosition(){

        noriz.resetPosition();
        for (Entity monster : monsters) {
            monster.resetPosition();
        }

    }

    @Override
    public void keyInput(KeyEvent e) {
        switch (e.getCode()) {
            case Q:
                if(noriz.getReversed() > 0)
                    noriz.setNextFacing(RIGHT);
                else
                    noriz.setNextFacing(LEFT);
                break;
            case D:
                if(noriz.getReversed() > 0)
                    noriz.setNextFacing(LEFT);
                else
                    noriz.setNextFacing(RIGHT);
                break;
            case S:
                if(noriz.getReversed() > 0)
                    noriz.setNextFacing(UP);
                else
                    noriz.setNextFacing(DOWN);
                break;
            case Z:
                if(noriz.getReversed() > 0)
                    noriz.setNextFacing(DOWN);
                else
                    noriz.setNextFacing(UP);
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
                System.out.println("Easter_egg_nul.png");
                break;
            case W:
                noriz.setTimeGhosted(200);
                break;
            case X:
                noriz.setIsSmall(50);
                noriz.speed++;
                noriz.setSize(noriz.getSize()/2);
                break;
            case C:
                noriz.setSize(noriz.getSize()*2);
                break;
                /*
                            case UP:
                for(int i=0;i<4;i++)
                    monsters[i].setNextFacing(UP);
                break;
            case DOWN:
                for(int i=0;i<4;i++)
                    monsters[i].setNextFacing(DOWN);
                break;
            case LEFT:
                for(int i=0;i<4;i++)
                    monsters[i].setNextFacing(LEFT);
                break;
            case RIGHT:
                for(int i=0;i<4;i++)
                    monsters[i].setNextFacing(RIGHT);
                break;
                 */
        }
    }

    @Override
    public void render(GraphicsContext gc){
        if(firstRender){
            startTimer = System.nanoTime();
            gc.clearRect(0,0,Utils.canvasSize,Utils.canvasSize);
            firstRender = false;
            worldRender.renderMap(gc, map, false);
        }
        worldRender.renderMap(gc, map, false);
        //worldRender.renderItems(gc, map, false);

        renderEntities(gc);
    }

    void renderEntities(GraphicsContext gc){

        /*
        int square[] = Utils.getSquare(player.getCenterX(), player.getCenterY());
        worldRender.renderSquare(gc, map, square[0], square[1]);
        square = Utils.getSquare(player.getCenterX()-player.getFacingX(player.getFacing()), player.getCenterY()-player.getFacingY(player.getFacing()));
        worldRender.renderSquare(gc, map, square[0], square[1]);
        */
        noriz.render(gc);

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

    long getTimer(){

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

    void gameOver(){
        gsm.changeState(3);
        gsm.sm.backGround.stop();
    }

}

