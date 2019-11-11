package States;

import Utils.Utils;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameOverState extends GameState {

    GameOverState(GameStateManager gsm) {
        super(gsm);
        initScene();
    }

    public void nextStep() {

    }

    @Override
    public void input(KeyEvent e) {

    }

    @Override
    public void render(GraphicsContext gc) {

    }

    @Override
    public void initScene() {
        Pane layout = new Pane();

        layout.setStyle("-fx-background-color: darkslategrey;");


        Text title = new Text();
        title.setX((20/100.0)*Utils.canvasSize);
        title.setY((20/100.0)*Utils.canvasSize);
        title.setFont(new Font(40));
        title.setText("T'es rincé frérot");


        ModeButton menu = new ModeButton(new Image("Buttons/sign_return_menu.png"), "Menu");
        menu.setLayoutX((36.5/100.0)*Utils.canvasSize);
        menu.setLayoutY((40/100.0)*Utils.canvasSize);
        menu.handler(gsm);

        ModeButton rejouer = new ModeButton(new Image("Buttons/sign_rejouer.png"), "Rejouer");
        rejouer.setLayoutX((3/100.0)*Utils.canvasSize);
        rejouer.setLayoutY((40/100.0)*Utils.canvasSize);
        rejouer.setMaxSize((20 /100.0)*Utils.canvasSize,(10 /100.0)*Utils.canvasSize);
        rejouer.handler(gsm);

        layout.getChildren().addAll(rejouer, menu, title);


        theScene = new Scene(layout, Utils.canvasSize, Utils.canvasSize);
    }
}

