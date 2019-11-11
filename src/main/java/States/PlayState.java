package States;

import Entity.Monster;
import Utils.Utils;
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

public class PlayState extends GameState {

    private boolean firstRender;

    private long roundTimer = 105; // en seconde
    private long startTimer = 0;


    PlayState(GameStateManager gsm) {
        super(gsm);
        firstRender = true;

        initScene();
        createScene();
    }

    private void createScene() {
        Group root = new Group();
        theScene = new Scene( root );
        Canvas canvas = new Canvas(Utils.canvasSize, Utils.canvasSize);
        root.getChildren().add(canvas);

        theScene.setOnKeyPressed(
                gsm::input);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Font theFont = Font.font( "Helvetica", FontWeight.BOLD, 24 );
        gc.setFont(theFont);
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                // game logic

                gsm.nextStep();
                initInput();

                // render

                //Faudra peut etre le garder
                //gc.clearRect(0, 0, WIDTH,HEIGHT);
                gsm.render(gc);

            }
        }.start();
    }

    public void initScene(){
        firstRender = true;
        gsm.isGameOver = false;
        gsm.player.init();
        for (Monster monster :
                gsm.monsters) {
            monster.init();
        }
    }

    private void initInput() {
        gsm.key.initKeys();
    }

    public void nextStep() {

        if(gsm.isGameOver)
            return;

        gsm.collider.nextStep();

        gsm.player.nextStep();
        for (Monster monster : gsm.monsters) {
            monster.nextStep();
        }
    }

    @Override
    public void input(KeyEvent e) {
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

        long timer;
        long currentTimer;

        if(gsm.isGameOver){
            gc.fillText("Frero t'es dèd la, déso", Utils.canvasSize/2.0, Utils.canvasSize/2.0);
            return;
        }

        currentTimer = System.nanoTime();

        if(firstRender){
            startTimer = System.nanoTime();
            timer = Math.abs((currentTimer/1000000000) - (startTimer/1000000000));
            gc.clearRect(0,0,Utils.canvasSize,Utils.canvasSize);
            firstRender = false;
        }
        else{
            timer = Math.abs((currentTimer/1000000000) - (startTimer/1000000000));
            if(timer > roundTimer)
                gsm.isGameOver = true;
        }

        gsm.world.renderMap(gc);

        gsm.player.render(gc);

        if(roundTimer - timer >= 100)
            gc.fillText(roundTimer - timer+"",Utils.canvasSize-(Utils.caseDimension+ Utils.caseDimension/2.0), 18+((20/100.0)*Utils.caseDimension));
        else
            gc.fillText(roundTimer - timer+"",Utils.canvasSize-(Utils.caseDimension), 18+((20/100.0)*Utils.caseDimension));

        for(Monster monster : gsm.monsters){
            monster.render(gc);
        }

    }
}
