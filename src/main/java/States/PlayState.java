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


    public void createAnimTimer(GraphicsContext gc) {
        animationTimer = new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {

                long elapsedNanos = currentNanoTime - lastTime ;
                //System.out.println("FPS : " + 1000000000. / elapsedNanos);

                // game logic
                nextStep();

                // render
                render(gc);

                lastTime = currentNanoTime;
            }
        };
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
        if(player.getInvulnerable()){
            Entity monster;
            if((monster = isMonsterTouched()) != null)
                monstersTouched(monster);
        }
        else{
            if(isPlayerTouched())
                playerTouched();
        }
        takeRice();
        takeItemBonus();
    }

    private void takeRice(){

        int coords[] = Utils.getSquare(player.getCenterX(), player.getCenterY());

        if(map[coords[0]][coords[1]] == WORLDITEM.RICE){
            myData.nbRiz -= 1;
            map[coords[0]][coords[1]] = WORLDITEM.ROAD;
        }

        if(myData.nbRiz == 0){
            Text title = new Text();
            title.setX((20/100.0)*Utils.canvasSize);
            title.setY((20/100.0)*Utils.canvasSize);
            title.setFont(new Font(40));
            title.setText("bien joué frérot");
        }

    }

    /**
     * Check if x,y is on a bonus and take the bonus ( it should probably just check and not take it )
     * @param x coord
     * @param y coord
     * @return true if x,y is on a bonus
     */
    public boolean takeItemBonus(int x,int y) {
        int[] coords = Utils.getSquare(x,y);
        if(map[coords[0]][coords[1]] == WORLDITEM.BONUS) {
            map[coords[0]][coords[1]] = WORLDITEM.ROAD;
            return true;
        }
        return false;
    }

    private void takeItemBonus(){
        if(takeItemBonus(player.getCenterX(), player.getCenterY())){


            for(int i =0; i< monsters.length; i++){
                monsters[i].frozen = true;
            }
            Thread durationBonus = new Thread(){
                public void run() {
                    long startBonusEffect = System.nanoTime();
                    long currentTimer = System.nanoTime();
                    while (Math.abs((currentTimer / 1000000000) - (startBonusEffect / 1000000000)) <= 3) {
                        startBonusEffect = System.nanoTime();
                    }
                    for (int i = 0; i < monsters.length; i++) {
                        monsters[i].frozen = false;
                    }
                }
            };
            durationBonus.start();
        }
    }

    private boolean isPlayerTouched() {
        for (Entity monster : monsters) {
            if(gsm.collider.isTouching(player, monster))
               return true;
        }
        return false;
    }

    private Entity isMonsterTouched(){
        for (Entity monster : monsters) {
            if(gsm.collider.isTouching(player, monster))
                return monster;
        }
        return null;
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
        }

        worldRender.renderMap(gc, map);

        player.render(gc);

        for(Monster monster : monsters){
            monster.render(gc);
        }

        long timer = getTimer();

        if(timer <= 0) {
            gameOver();
        }
        ui.render(gc, myData.nbLife, myData.nbRiz, getTimer());

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

