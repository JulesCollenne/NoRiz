package States;

import Entity.Monster;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class PlayState extends GameState {

    private boolean firstRender;

    PlayState(GameStateManager gsm) {
        super(gsm);
        firstRender = true;

        initScene();

    }

    private void initScene(){
        Group root = new Group();
        theScene = new Scene( root );
        Canvas canvas = new Canvas(1000, 1000);
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

    private void initInput() {
        gsm.key.initKeys();
    }

    public void nextStep() {
        gsm.player.nextStep();
        for (Monster monster : gsm.monsters) {
            monster.nextStep();
        }
    }

    @Override
    public void input(KeyEvent e) {
        gsm.player.input(e);
    }

    @Override
    public void render(GraphicsContext gc) {

        if(firstRender){
            firstRender = false;
        }

        //if(firstRender){

            gsm.world.renderMap(gc);
            firstRender = false;
        //}

        gsm.player.render(gc);

        for(Monster monster : gsm.monsters){
            monster.render(gc);
        }

    }
}
