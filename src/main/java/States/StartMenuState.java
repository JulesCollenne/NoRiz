package States;

import Utils.Utils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;

public class StartMenuState extends GameState{

    boolean firstRender = true;

    private final int START  = 0;
    private final int PLAY  = 1;
    private final int PAUSE  = 2;

    StartMenuState(GameStateManager gsm) {
        super(gsm);
        initScene();
    }

    private void initScene() {
        Pane layout = new Pane();

        layout.setStyle("-fx-background-color: darkslategrey;");

        Button easy = new ImageButton(new Image("sign_facile.png"), "Facile");
        easy.setStyle("-fx-background-color: transparent;");
        easy.setLayoutX(165 - (230 / 2));
        easy.setLayoutY(495 - (87.5 / 2));
        easy.setOnAction(actionEvent -> {
            gsm.difficulty = 0;
            gsm.changeState(PLAY);
        });

        Button medium = new ImageButton(new Image("sign_moyen.png"), "Medium");
        medium.setStyle("-fx-background-color:transparent");
        medium.setLayoutX(500 - (230 / 2));
        medium.setLayoutY(495 - (87.5 / 2));

        Button hard = new ImageButton(new Image("sign_difficile.png"), "Hard");
        hard.setStyle("-fx-background-color: darkslategrey;");
        hard.setLayoutX(830 - (230 / 2));
        hard.setLayoutY(495 - (87.5 / 2));


        layout.getChildren().addAll(easy, medium, hard);

        theScene = new Scene(layout, 1000, 1000);

        firstRender = false;
    }

    public void nextStep() {

    }

    @Override
    public void input(KeyEvent e) {

    }

    @Override
    public void render(GraphicsContext gc) {

    }
}
