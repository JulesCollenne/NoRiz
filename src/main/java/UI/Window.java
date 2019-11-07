package UI;

import Input.KeysManager;
import States.GameStateManager;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.animation.AnimationTimer;

public class Window extends Application
{

    final int WIDTH = 1000;
    final int HEIGHT = 1000;

    GameStateManager gsm = new GameStateManager();

    public KeysManager key = new KeysManager();

    long lastNanoTime = System.nanoTime();

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage theStage)
    {
        theStage.setTitle("NoRiz");

        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );

        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);

        theScene.setOnKeyPressed(
                e -> gsm.input(e));

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Font theFont = Font.font( "Helvetica", FontWeight.BOLD, 24 );
        gc.setFont(theFont);
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);

        /*
        Sprite briefcase = new Sprite();
        briefcase.setImage("briefcase.png");
        briefcase.setPosition(200, 0);
        */

        lastNanoTime = System.nanoTime();

        int score = 0;

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                // calculate time since last update.
                double elapsedTime = (currentNanoTime - lastNanoTime) / 1000000000.0;
                lastNanoTime = currentNanoTime;

                // game logic

                gsm.nextStep();
                initInput();

                // render

                gc.clearRect(0, 0, WIDTH,HEIGHT);
                gsm.render(gc);

            }
        }.start();

        theStage.show();
    }

    private void initInput() {
        key.initKeys();
    }
}