package states;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;


public class ButtonChooseSkin extends Button{

    private ImageView image;
    String skinName;

    ButtonChooseSkin(String skinName){
        this.skinName = skinName;
        this.image = new ImageView("Player/" + this.skinName + "_droite1.png");
        this.getChildren().add(this.image);
        super.setGraphic(this.image);

        setMinSize(100,395);
        setMaxSize(100,395);
        setStyle("-fx-background-color: transparent");
    }

    void handler(GameStateManager gsm) {
        this.setOnMousePressed(mouseEvent -> {
            System.out.println("Salut");
            //gsm.player.setSkin(this.skinName);
        });
    }
}