package ui;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import states.GameStateManager;
import utils.DIRECTION;
import utils.Utils;


public class ButtonChooseSkin extends Button{

    private ImageView image;
    String skinName;

    public ButtonChooseSkin(String skinName){
        this.skinName = skinName;
        this.image = new ImageView("" + this.skinName + "_droite0.png");
        this.getChildren().add(this.image);
        super.setGraphic(this.image);

        setMinSize(100,395);
        setMaxSize(100,395);
        setStyle("-fx-background-color: transparent");
    }

    public void handler(GameStateManager gsm) {
        this.setOnMousePressed(mouseEvent -> {gsm.noriz.makeAnimations(Utils.toInt(DIRECTION.LEFT),"_gauche", "");
            gsm.noriz.makeAnimations(Utils.toInt(DIRECTION.RIGHT),"_droite", "");
            gsm.noriz.makeAnimations(Utils.toInt(DIRECTION.DOWN),"_gauche", "");
            gsm.noriz.makeAnimations(Utils.toInt(DIRECTION.UP),"_gauche", "");;
        });
    }
}