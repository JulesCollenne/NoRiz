package States;

import Utils.Utils;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;

public class StartMenuState extends GameState{

    //boolean firstRender = true;

    private final int START  = 0;
    private final int PLAY  = 1;
    private final int PAUSE  = 2;

    static Image backGroundImage = new Image("textures/noriz01.jpg");

    public StartMenuState(GameStateManager gsm) {
        super(gsm);
        createScene();
        createAnimTimer();
    }

    public void createAnimTimer() {
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {

            }
        };
    }

    private void createScene() {
        Pane layout = new Pane();

        //layout.setStyle("-fx-background-color: darkslategrey;");

        BackgroundSize backgroundSize = new BackgroundSize(Utils.canvasSize, Utils.canvasSize, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(backGroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        layout.setBackground(new Background(backgroundImage));

        double tempWidth = new Image("Buttons/sign_return_menu.png").getWidth();

        ModeButton easy = new ModeButton(new Image("Buttons/sign_facile.png"), "Facile");
        easy.setLayoutX(((Utils.canvasSize/3.0)/2) - (tempWidth/2));
        easy.setLayoutY((60/100.0)*Utils.canvasSize);
        easy.handler(gsm);

        Image mediumIm = new Image("Buttons/sign_moyen.png");
        ModeButton medium = new ModeButton(mediumIm, "Medium");
        medium.setLayoutX( ((((2*Utils.canvasSize)/3.) + (Utils.canvasSize/3.))/2) - (tempWidth/2));
        medium.setLayoutY((60/100.0)*Utils.canvasSize);
        medium.handler(gsm);

        ModeButton hard = new ModeButton(new Image("Buttons/sign_difficile.png"), "Hard");
        hard.setLayoutX( (((2*Utils.canvasSize)/3.) + Utils.canvasSize)/2 - (tempWidth/2));
        hard.setLayoutY((60/100.0)*Utils.canvasSize);
        hard.handler(gsm);

        SkinButton skin = new SkinButton(new Image("Player/nori_droite0.png"), "Skin");
        skin.setLayoutX(((Utils.canvasSize/3.0)/2) - (tempWidth/2));
        skin.setLayoutY((80/100.0)*Utils.canvasSize - 45);
        skin.handler(gsm);
        skin.setStyle("-fx-background-color: transparent;");


        ModeButton arcade = new ModeButton(new Image("Buttons/sign_arcade.png"), "Arcade");
        arcade.setLayoutX( ((((2*Utils.canvasSize)/3.) + (Utils.canvasSize/3.))/2) - (tempWidth/2));
        arcade.setLayoutY((80/100.0)*Utils.canvasSize);
        arcade.handler(gsm);

        ModeButton editor = new ModeButton(new Image("Buttons/sign_editor.png"), "Editor");
        editor.setLayoutX( (((2*Utils.canvasSize)/3.) + Utils.canvasSize)/2 - (tempWidth/2));
        editor.setLayoutY((80/100.0)*Utils.canvasSize);
        editor.handler(gsm);


        ModeButton options = new ModeButton(new Image("Buttons/sign_options.png"), "Options");
        options.setLayoutX(Utils.canvasSize - 2.4*(new Image("Buttons/sign_options.png").getWidth()));
        options.setLayoutY(-15);
        options.handler(gsm);

        layout.getChildren().addAll(options, easy, medium, hard, skin, editor, arcade);


        theScene = new Scene(layout, Utils.canvasSize, Utils.canvasSize);
    }

    public void init() {
    }

    public void nextStep() {

    }

    @Override
    public void keyInput(KeyEvent e) {

    }

    @Override
    public void render(GraphicsContext gc) {

    }
}
