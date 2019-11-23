package States;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

class SkinButton extends Button {
    private ImageView skin;
    private String label;

    private Image currentImage = new Image("Player/nori_droite0.png");
    private int cmp = 0;

    SkinButton(Image image, String label){
        skin = new ImageView(image);
        this.getChildren().add(skin);
        this.label = label;
        super.setGraphic(skin);
        setMinSize(85,170);
        setMaxSize(85,170);
        setStyle("-fx-background-color: white");
    }

    void handler(GameStateManager gsm) {
        this.setOnMousePressed(mouseEvent -> {
            gsm.changeState(7);
        });
        this.setOnMouseEntered(mouseEvent -> {
            //Faire une petite animation quand on survole le bouton
            cmp = (cmp + 1) % 2;
            currentImage = new Image("Player/nori_droite"+cmp+".png");
            skin = new ImageView(currentImage);
        });
    }
}

