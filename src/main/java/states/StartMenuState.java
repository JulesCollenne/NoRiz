package states;

import ui.ModeButton;
import ui.SkinButton;
import utils.Utils;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class StartMenuState extends GameState{

    //boolean firstRender = true;

    private static Image backGroundImage = new Image("noriz01.jpg");

    StartMenuState(GameStateManager gsm) {
        super(gsm);
        init();
        createAnimTimer();
    }

    private void createAnimTimer() {
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {

            }
        };
    }

    private void createScene() {
        Pane layout = new Pane();

        BackgroundSize backgroundSize = new BackgroundSize(Utils.canvasSize, Utils.canvasSize, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(backGroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        layout.setBackground(new Background(backgroundImage));

        double tempWidth = new Image("sign_menu.png").getWidth();

        ModeButton easy = new ModeButton(new Image("sign_facile.png"), "Facile");
        easy.setLayoutX(((Utils.canvasSize/3.0)/2) - (tempWidth/2));
        easy.setLayoutY((60/100.0)*Utils.canvasSize);
        easy.handler(gsm);


        ModeButton medium;
        if(gsm.storyProgress >= 1) {
            Image mediumIm = new Image("sign_moyen.png");
            medium = new ModeButton(mediumIm, "Medium");
            medium.setLayoutX(((((2 * Utils.canvasSize) / 3.) + (Utils.canvasSize / 3.)) / 2) - (tempWidth / 2));
            medium.setLayoutY((60 / 100.0) * Utils.canvasSize);
            medium.handler(gsm);
        }
        else{
            Image mediumIm = new Image("sign_moyen_lock.png");
            medium = new ModeButton(mediumIm, "Medium");
            medium.setLayoutX(((((2 * Utils.canvasSize) / 3.) + (Utils.canvasSize / 3.)) / 2) - (tempWidth / 2));
            medium.setLayoutY((60 / 100.0) * Utils.canvasSize);
        }

        ModeButton hard;
        if(gsm.storyProgress >= 2) {
            hard = new ModeButton(new Image("sign_difficile.png"), "Hard");
            hard.setLayoutX((((2 * Utils.canvasSize) / 3.) + Utils.canvasSize) / 2 - (tempWidth / 2));
            hard.setLayoutY((60 / 100.0) * Utils.canvasSize);
            hard.handler(gsm);
        }
        else{
            hard = new ModeButton(new Image("sign_difficile_lock.png"), "Hard");
            hard.setLayoutX((((2 * Utils.canvasSize) / 3.) + Utils.canvasSize) / 2 - (tempWidth / 2));
            hard.setLayoutY((60 / 100.0) * Utils.canvasSize);
        }

        SkinButton skin = new SkinButton(new Image("nori_droite0.png"), "Skin");
        skin.setLayoutX(((Utils.canvasSize/3.0)/2) - (tempWidth/2));
        skin.setLayoutY((80/100.0)*Utils.canvasSize - 45);
        skin.handler(gsm);
        //skin.setStyle("-fx-background-color: transparent;");


        ModeButton arcade = new ModeButton(new Image("sign_arcade.png"), "Arcade");
        arcade.setLayoutX(((((2*Utils.canvasSize)/3.) + (Utils.canvasSize/3.))/2) - (tempWidth/2));
        arcade.setLayoutY((80/100.0)*Utils.canvasSize);
        arcade.handler(gsm);

        Text bestScore = new Text();
        bestScore.setLayoutX(arcade.getLayoutX());
        bestScore.setLayoutY(arcade.getLayoutY()+120);
        bestScore.setText("Meilleur score: "+gsm.bestScore);
        bestScore.setFill(Color.WHITE);
        bestScore.setFont(new Font(20));

        ModeButton editor = new ModeButton(new Image("sign_editeur.png"), "Editor");
        editor.setLayoutX( (((2*Utils.canvasSize)/3.) + Utils.canvasSize)/2 - (tempWidth/2));
        editor.setLayoutY((80/100.0)*Utils.canvasSize);
        editor.handler(gsm);


        ModeButton options = new ModeButton(new Image("sign_options.png"), "Options");
        options.setLayoutX(Utils.canvasSize - 2.4*(new Image("sign_options.png").getWidth()));
        options.setLayoutY(-15);
        options.handler(gsm);

        layout.getChildren().addAll(options, easy, medium, hard, skin, editor, arcade, bestScore);


        theScene = new Scene(layout, Utils.canvasSize, Utils.canvasSize);
    }

    public void init() {
        //System.out.println(gsm.bestScore);
        createScene();
        //gsm.sm.backGround.play();
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
