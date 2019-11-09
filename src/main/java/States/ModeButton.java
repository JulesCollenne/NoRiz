package States;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ModeButton extends Button {
    ImageView image;
    String label;

    public ModeButton(Image image, String label){
        this.image = new ImageView(image);
        this.getChildren().add(this.image);
        this.label = label;
        super.setGraphic(this.image);
        setMinSize(170,85);
        setMaxSize(170,85);
        setStyle("-fx-background-color: transparent");
    }

    public void handler(GameStateManager gsm) {
        this.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //System.out.println("Mode " + label);
                switch(label) {
                    case "Facile":
                        gsm.difficulty = 0;
                        gsm.changeState(1);
                        break;
                    case "Medium":
                        gsm.difficulty = 1;
                        gsm.changeState(1);
                        break;
                    case "Hard":
                        gsm.difficulty = 2;
                        gsm.changeState(1);
                        break;
                }
            }
        });
        this.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //Faire une petite animation quand on survole le bouton
            }
        });
    }
}
