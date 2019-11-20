package States;

import Utils.myGameData;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static States.GameStateManager.DIF.*;

class ModeButton extends Button {
    private ImageView image;
    private String label;

    ModeButton(Image image, String label){
        this.image = new ImageView(image);
        this.getChildren().add(this.image);
        this.label = label;
        super.setGraphic(this.image);
        setMinSize(170,85);
        setMaxSize(170,85);
        setStyle("-fx-background-color: transparent");
    }

    void handler(GameStateManager gsm) {
        this.setOnMousePressed(mouseEvent -> {
            switch(label) {
                case "Facile":
                    gsm.difficulty = EASY;
                    gsm.changeState(1);
                    break;
                case "Medium":
                    gsm.difficulty = MEDIUM;
                    gsm.changeState(1);
                    break;
                case "Hard":
                    gsm.difficulty = HARD;
                    gsm.changeState(1);
                    break;
                case "Rejouer":
                    gsm.changeState(1);
                    break;
                case "Reprendre":
                    gsm.reprendreJeu();
                    break;
                case "Menu":
                    gsm.difficulty = EASY;
                    //gsm.sm.backGround.stop();
                    gsm.changeState(0);
                    break;
                case "Editor":
                    gsm.changeState(4);
                    break;
            }
        });
        this.setOnMouseEntered((EventHandler) mouseEvent -> {
            //Faire une petite animation quand on survole le bouton
        });
    }
}

