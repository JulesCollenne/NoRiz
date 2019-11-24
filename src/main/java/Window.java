import States.GameStateManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class
Window extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage theStage)
    {
        theStage.setTitle("NoRiz");
        new GameStateManager(theStage);
    }
}
