package ui;

import states.GameStateManager;
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

//TODO Bug : quand on prend 2 bonus, il y a des effets nul. Genre 2 bonus petit faiut que l'oin est bloqué en forme petite
// TODO Bug : le mode arcade reprend le dernier thème de graphiquem utilisé (un peu cheloiu, ça devrait pas etre le cas)