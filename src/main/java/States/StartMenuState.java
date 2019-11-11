package States;

import Utils.Utils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;

public class StartMenuState extends GameState{

    //boolean firstRender = true;

    private final int START  = 0;
    private final int PLAY  = 1;
    private final int PAUSE  = 2;

    StartMenuState(GameStateManager gsm) {
        super(gsm);
        createScene();
    }

    private void createScene() {
            Pane layout = new Pane();

            layout.setStyle("-fx-background-color: darkslategrey;");

            ModeButton easy = new ModeButton(new Image("Buttons/sign_facile.png"), "Facile");
            easy.setLayoutX((3/100.0)*Utils.canvasSize);
            easy.setLayoutY((40/100.0)*Utils.canvasSize);
            easy.handler(gsm);

            Image mediumIm = new Image("Buttons/sign_moyen.png");
            ModeButton medium = new ModeButton(mediumIm, "Medium");
            medium.setLayoutX((36.5/100.0)*Utils.canvasSize);
            medium.setLayoutY((40/100.0)*Utils.canvasSize);
            medium.handler(gsm);

            ModeButton hard = new ModeButton(new Image("Buttons/sign_difficile.png"), "Hard");
            hard.setLayoutX((70/100.0)*Utils.canvasSize);
            hard.setLayoutY((40/100.0)*Utils.canvasSize);
            hard.handler(gsm);

            SkinButton skin = new SkinButton(new Image("Player/nori_droite0.png"), "Skin");
            skin.setLayoutX(10);
            skin.setLayoutY(10);
            skin.handler(gsm);


            ImageView noriz = new ImageView(new Image("Player/nori_droite0.png"));
            noriz.setX(333);
            noriz.setY(150);

            layout.getChildren().addAll(easy, medium, hard, skin, noriz);


            theScene = new Scene(layout, Utils.canvasSize, Utils.canvasSize);
    }

    public void initScene() {

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