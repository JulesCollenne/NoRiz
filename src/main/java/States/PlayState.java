package States;

import Entity.Entity;
import Entity.Monster;
import Entity.Player;
import UI.inGameUserInterface;
import Utils.Utils;
import Utils.WORLDITEM;
import Utils.myGameData;
import WorldBuilder.matrixWorld;
import WorldBuilder.worldRender;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

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
        player.init();
        for (Monster monster :
                monsters) {
            monster.init();
        }

            initMyData();

        //gsm.sm.backGround.play();
    }

    public void initMyData(){

        map = Utils.copyMap(gsm.world.build(gsm.difficulty));
        myData = new myGameData(map);

        switch(gsm.difficulty){

            case EASY:
                myData.nbRiz = matrixWorld.nbRiceWorld1;
                break;

            case MEDIUM:
                myData.nbRiz = matrixWorld.nbRiceWorld2;
                break;

            case HARD:
                myData.nbRiz = matrixWorld.nbRiceWorld3;
                break;

            default:
                myData.nbRiz = matrixWorld.nbRiceWorld1;
                break;

        }

        startTimer = 0;

    }

    /**
     *
     * @param gc
     */
    public void createAnimTimer(GraphicsContext gc) {

        animationTimer = new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {

                /*
                long elapsedNanos = currentNanoTime - lastTime ;
                System.out.println("FPS : " + 1000000000. / elapsedNanos);
*/
                // game logic
                nextStep();

                // render
                render(gc);

                //lastTime = currentNanoTime;
            }
        };

        /*
    animationTimer2 = new Timeline(new KeyFrame(Duration.seconds(1/80.), event -> {

        // game logic
        nextStep();

        // render
        render(gc);

    }));
    */

    //animationTimer2.setCycleCount(Animation.INDEFINITE);
    }

    public void nextStep() {

        if(myData.nbLife <= 0)
            return;

        checkCollisions();

        player.nextStep();
        for (Monster monster : monsters) {
            monster.nextStep();
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

        if(myData.nbRiz == 0)
            win();
    }

    private void win() {
        Text title = new Text();
        title.setX((20/100.0)*Utils.canvasSize);
        title.setY((20/100.0)*Utils.canvasSize);
        title.setFont(new Font(40));
        title.setText("bien joué frérot");
    }

    private void takeBonus(int x, int y){
        map[x][y] = WORLDITEM.ROAD;
        if(rand.nextBoolean())
            gsm.bonuses[0].effect(monsters);
        else
            gsm.bonuses[1].effect(player);
    }

    private boolean isPlayerTouched() {
        for (Entity monster : monsters) {
            if(gsm.collider.isTouching(player, monster))
               return true;
        }
        return false;
    }

    private Entity monsterTouched(){
        for (Entity monster : monsters) {
            if(gsm.collider.isTouching(player, monster))
                return monster;
        }
        return null;
    }

    public void monsterDie(Entity monster){
        monster.resetPosition();
    }

    public void playerTouched(){
        resetPosition();
        myData.nbLife--;
        if(myData.nbLife == 0)
            playerDie();
    }

    public void monstersTouched(Entity monster){
        monster.resetPosition();
    }

    private void playerDie(){
        gameOver();
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
                player.setNextFacing(LEFT);
                break;
            case D:
                player.setNextFacing(RIGHT);
                break;
            case S:
                player.setNextFacing(DOWN);
                break;
            case Z:
                player.setNextFacing(UP);
                break;
            case SPACE:
                pause();
                break;
            case ESCAPE:
                pause();
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

        long timer = getTimer();

        if(timer <= 0) {
            gameOver();
        }
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
        gsm.sm.backGround.stop();
    }

}

