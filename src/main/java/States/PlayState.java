package States;

import Entity.Entity;
import Entity.Monster;
import UI.inGameUserInterface;
import Utils.Utils;
import WorldBuilder.matrixWorld;
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
    inGameUserInterface ui;

    // Info UI
    private int maxLife = 5;
    private int nbLife = maxLife;
    private int currentNbRice = 0;
    private static long roundTimer = Utils.roundDuration; // en seconde
    private static long startTimer = 0;
    // Info UI

    Stage theStage;

    PlayState(GameStateManager gsm, inGameUserInterface ui, Stage theStage) {
        super(gsm);
        firstRender = true;
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
        initUIInfo();
    }

    private void initUIInfo(){

        startTimer = 0;

        switch(gsm.difficulty){

            case EASY:
                currentNbRice = matrixWorld.nbRiceWorld1;
                break;

            case MEDIUM:
                currentNbRice = matrixWorld.nbRiceWorld2;
                break;

            case HARD:
                currentNbRice = matrixWorld.nbRiceWorld3;
                break;

            default:
                currentNbRice = matrixWorld.nbRiceWorld1;
                break;

        }

    }

    public void init(){
        firstRender = true;
        gsm.isGameOver = false;
        gsm.player.init();
        for (Monster monster :
                gsm.monsters) {
            monster.init();
        }
        initUIInfo();
        gsm.world.build(gsm.difficulty, theStage);
    }

    @Override
    public void createAnimTimer() {

    }

    public void nextStep() {

        if(gsm.isGameOver)
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

        if(gsm.collider.takeRice(gsm.player.getCenterX(), gsm.player.getCenterY())){
            currentNbRice -= 1;
        }
        if(currentNbRice == 0){
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
                gsm.player.gotHit();
                nbLife--;
                if(nbLife == 0)
                    gsm.player.die();
            }
        }
    }

    @Override
    public void input(KeyEvent e) {
        if(e.getCode() == KeyCode.SPACE || e.getCode() == KeyCode.ESCAPE){
            gsm.changeState(2);
        }
        if(!gsm.isGameOver)
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

        if(gsm.isGameOver){
            gc.fillText("Frero t'es dèd la, déso", Utils.canvasSize/2.0, Utils.canvasSize/2.0);
            return;
        }

        if(firstRender){
            startTimer = System.nanoTime();
            gc.clearRect(0,0,Utils.canvasSize,Utils.canvasSize);
            firstRender = false;
        }

        gsm.world.renderMap(gc);

        gsm.player.render(gc);

        for(Monster monster : gsm.monsters){
            monster.render(gc);
        }



        long timer = getTimer();
        if(timer > roundTimer) {
            gsm.changeState(3);
        }
        ui.render(gc, nbLife, currentNbRice, getTimer());

    }

    private long getTimer(){

        long timer;
        long currentTimer;
        currentTimer = System.nanoTime();
        timer = Math.abs((currentTimer/1000000000) - (startTimer/1000000000));
        return roundTimer - timer;
    }



}

