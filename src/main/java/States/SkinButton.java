package States;

import Utils.Utils;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

class SkinButton extends Button {
    private ImageView skin;
    private String label;
    public boolean mouseOver = false;

    AnimationTimer animationTimer;
    private int nbImgAnim = 2;
    private int animTime;
    private int lastAnim;
    private int animSpeed;
    private Image[][] image2 = new Image[1][2];

    SkinButton(Image image, String label){

        nbImgAnim = 2;
        animSpeed = 10;
        animTime = 0;

        skin = new ImageView(image);
        this.getChildren().add(skin);
        this.label = label;
        super.setGraphic(skin);
        setMinSize(85,170);
        setMaxSize(85,170);
        setStyle("-fx-background-color: white");

        makeAnimations();

        animationTimer = new AnimationTimer() {
            public void handle(long currentNanoTime) {

                //Faire une petite animation quand on survole le bouton

                skin = new ImageView(image2[0][animTime]);
                getChildren().add(skin);

                if(lastAnim == animSpeed) {
                    animTime = (animTime + 1) % 2;
                    lastAnim = 0;
                }
                lastAnim++;
            }
        };
        animationTimer.start();

    }

    /**
     * Puts the images at the right place in the variable "image"
     */
    private void makeAnimations(){
        for(int i = 0; i < nbImgAnim; i++)
            image2[0][i] = new Image("Player/nori_" + "droite" + i + ".png");
    }

    void handler(GameStateManager gsm) {
        this.setOnMousePressed(mouseEvent -> {
            gsm.changeState(7);
        });
        this.setOnMouseEntered(mouseEvent -> {
            mouseOver = true;
        });
        this.setOnMouseExited(mouseEvent -> {
            mouseOver = false;
        });
    }
}

