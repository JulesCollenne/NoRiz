package States;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class SkinButton extends Button {
    ImageView skin;
    String label;

    Image currentImage = new Image("Player/nori_droite0.png");
    int cmp = 0;

    public SkinButton(Image image, String label){
        skin = new ImageView(image);
        this.getChildren().add(skin);
        this.label = label;
        super.setGraphic(skin);
        setMinSize(85,170);
        setMaxSize(85,170);
        setStyle("-fx-background-color: white");
    }

    public void handler(GameStateManager gsm) {
        this.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("Mode " + label);
                //gsm.changeState(1);
            }
        });
        this.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //Faire une petite animation quand on survole le bouton
                cmp = cmp % 1;
                currentImage = new Image("Player/nori_droite"+cmp+".png");
                skin = new ImageView(currentImage);
            }
        });
    }
}

