package ui;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import states.GameStateManager;
import worldBuilder.World;

public class SkinButton extends Button {
    private ImageView skin;
    private String label;

    AnimationTimer animationTimer;
    private int nbImgAnim = 2;
    private int animTime;
    private int lastAnim;
    private int animSpeed;
    private Image[][] image2 = new Image[1][2];

    public SkinButton(Image image, String label){

        nbImgAnim = 2;
        animSpeed = 10;
        animTime = 0;

        skin = new ImageView(image);
        this.getChildren().add(skin);
        this.label = label;
        super.setGraphic(skin);
        setMinSize(85,170);
        setMaxSize(85,170);
        setStyle("-fx-background-color: transparent");
        makeAnimations();

        setPrefSize(20,20);
        animationTimer = new AnimationTimer() {
            public void handle(long currentNanoTime) {

                //Faire une petite animation quand on survole le bouton

                skin = new ImageView(image2[0][animTime]);
                //TODO bien l'afficher
                skin.setLayoutX(20);
                skin.setLayoutY(60);
                //getChildren().add(skin);
                setGraphic(skin);

                if(lastAnim == animSpeed) {
                    animTime = (animTime + 1) % 2;
                    lastAnim = 0;
                }
                lastAnim++;
            }
        };
    }

    /**
     * Puts the images at the right place in the variable "image"
     */
    private void makeAnimations(){
        for(int i = 0; i < nbImgAnim; i++)
            image2[0][i] = new Image(World.class.getResource("/Player/nori_" + "droite" + i + ".png").toString());
    }

    public void handler(GameStateManager gsm) {
        this.setOnMousePressed(mouseEvent -> {
            gsm.changeState(7);
        });
        this.setOnMouseEntered(mouseEvent -> {
            animationTimer.start();
        });
        this.setOnMouseExited(mouseEvent -> {
            animationTimer.stop();
        });
    }
}

