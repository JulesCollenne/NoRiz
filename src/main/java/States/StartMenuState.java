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

    boolean firstRender = true;

    private final int START  = 0;
    private final int PLAY  = 1;
    private final int PAUSE  = 2;

    StartMenuState(GameStateManager gsm) {
        super(gsm);
        initScene();
    }

    private void initScene() {
        if(firstRender) {
            Pane layout = new Pane();

            layout.setStyle("-fx-background-color: darkslategrey;");

            ModeButton easy = new ModeButton(new Image("sign_facile.png"), "Facile");
            easy.setLayoutX(166.5-87.5);
            easy.setLayoutY(600-43.75);
            easy.handler(gsm);

            ModeButton medium = new ModeButton(new Image("sign_moyen.png"), "Medium");
            medium.setLayoutX(499.5-87.5);
            medium.setLayoutY(600-43.75);
            medium.handler(gsm);

            ModeButton hard = new ModeButton(new Image("sign_difficile.png"), "Hard");
            hard.setLayoutX(832.5-87.5);
            hard.setLayoutY(600-43.75);
            hard.handler(gsm);

            SkinButton skin = new SkinButton(new Image("nori_droite0.png"), "Skin");
            skin.setLayoutX(240);
            skin.setLayoutY(800);
            skin.handler(gsm);

            ImageView noriz = new ImageView(new Image("NORIZ.png"));
            noriz.setX(333);
            noriz.setY(150);

            layout.getChildren().addAll(easy, medium, hard, skin, noriz);


            Scene scene = new Scene(layout, 1000, 1000);

            gsm.theStage.setScene(scene);
            gsm.theStage.show();
            firstRender = false;
        }
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
