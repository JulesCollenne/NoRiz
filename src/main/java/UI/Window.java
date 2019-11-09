package UI;

import States.GameStateManager;
import javafx.application.Application;
import javafx.stage.Stage;

import static Utils.Utils.*;

public class Window extends Application
{

    final int WIDTH = mapSize*caseDimension;
    final int HEIGHT = mapSize*caseDimension;

    private GameStateManager gsm;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage theStage)
    {
        theStage.setTitle("NoRiz");
        gsm = new GameStateManager(theStage);
    }
}