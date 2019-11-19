package States;

import Entity.Entity;
import Entity.Monster;
import UI.inGameUserInterface;
import Utils.Utils;
import Utils.myGameData;
import Utils.WORLDITEM;
import WorldBuilder.matrixWorld;
import WorldBuilder.worldRender;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PlayState extends GameState {

    private boolean firstRender;
    private inGameUserInterface ui;

    private myGameData myData;
    private long startTimer;

    private WORLDITEM[][] map;

    private Stage theStage;

    PlayState(GameStateManager gsm, inGameUserInterface ui, Stage theStage) {
        super(gsm);
        firstRender = true;
        this.ui = ui;
        init();
        createScene();
        this.theStage = theStage;
    }

    PlayState(GameStateManager gsm, inGameUserInterface ui, Stage theStage, myGameData myData) {
        super(gsm);
        firstRender = true;
        this.myData = myData;
        this.ui = ui;
        init();
        createScene();
        this.theStage = theStage;
    }

    private void createScene() {
        Group root = new Group();
        theScene = new Scene( root );
        root.setStyle("-fx-background-color: darkslategrey;");
        Canvas canvas = new Canvas(Utils.canvasSize, Utils.canvasSize);

        root.getChildren().addAll(canvas);

        theScene.setOnKeyPressed(this::input);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Font theFont = Font.font( "Helvetica", FontWeight.BOLD, 24 );
        gc.setFont(theFont);
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);

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
    }

    public void init(){
        firstRender = true;
        gsm.player.init();
        for (Monster monster :
                gsm.monsters) {
            monster.init();
        }
        initMyData();
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

    @Override
    public void createAnimTimer() {

    }

    public void nextStep() {

        if(myData.nbLife <= 0)
            return;

        searchEntityCollisions();
        takeRice();
        takeItemBonus();

        gsm.player.nextStep();
        for (Monster monster : gsm.monsters) {
            monster.nextStep();
        }
    }

    private void takeRice(){

        int coords[] = Utils.getSquare(gsm.player.getCenterX(), gsm.player.getCenterY());
        if(gsm.collider.takeRice(gsm.player.getCenterX(), gsm.player.getCenterY())){
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

    private void takeItemBonus(){
        if(gsm.collider.takeItemBonus(gsm.player.getCenterX(), gsm.player.getCenterY())){

            for(int i =0; i< gsm.monsters.length; i++){
                gsm.monsters[i].frozen = true;
            }

            Thread durationBonus = new Thread(){
                public void run() {
                    long startBonusEffect = System.nanoTime();
                    long currentTimer = System.nanoTime();
                    while (Math.abs((currentTimer / 1000000000) - (startBonusEffect / 1000000000)) <= 3) {
                        startBonusEffect = System.nanoTime();
                    }
                    for (int i = 0; i < gsm.monsters.length; i++) {
                        gsm.monsters[i].frozen = false;
                    }
                }
            };
            durationBonus.start();

        }
    }

    private void searchEntityCollisions() {
        for (Entity monster : gsm.monsters) {
            if(gsm.collider.isThereEntityCollision(gsm.player, monster)){
                resetPosition();
                myData.nbLife--;
                if(myData.nbLife == 0)
                    gsm.changeState(3);
            }
        }
    }

    private void resetPosition(){

        gsm.player.resetPosition();
        for (Entity monster : gsm.monsters) {
            monster.resetPosition();
        }

    }

    @Override
    public void input(KeyEvent e) {
        if(e.getCode() == KeyCode.SPACE || e.getCode() == KeyCode.ESCAPE){
            gsm.changeState(2);
        }
        if(myData.nbLife > 0)
            gsm.player.input(e);
        else{
            gameOverInputs(e);
        }
    }

    private void gameOverInputs(KeyEvent e){
        switch (e.getCode()) {
            case ENTER:
                gsm.changeState(0);
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

        gsm.player.render(gc);

        for(Monster monster : gsm.monsters){
            monster.render(gc);
        }



        long timer = getTimer();

        if(timer <= 0) {
            gsm.changeState(3);
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



}

