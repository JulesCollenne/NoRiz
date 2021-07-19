package ui;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import states.GameStateManager;
import utils.DIRECTION;
import utils.Utils;
import worldBuilder.World;


public class ButtonChooseSkin extends Button{

    private ImageView image;
    String skinName;

    public ButtonChooseSkin(String skinName){
        this.skinName = skinName;
        //this.image = new ImageView("skinNoriz/" + this.skinName + "_droite0.png");
        this.image = new ImageView(World.class.getResource("/skinNoriz/" + this.skinName + "_droite0.png").toString());
        this.getChildren().add(this.image);
        super.setGraphic(this.image);

        setMinSize(100,395);
        setMaxSize(100,395);
        setStyle("-fx-background-color: transparent");
    }

    public void handler(GameStateManager gsm) {
        this.setOnMousePressed(mouseEvent -> {gsm.noriz.makeAnimations(Utils.toInt(DIRECTION.LEFT),"_gauche", "skinNoriz/");
            gsm.noriz.makeAnimations(Utils.toInt(DIRECTION.RIGHT),"_droite", "skinNoriz/");
            gsm.noriz.makeAnimations(Utils.toInt(DIRECTION.DOWN),"_gauche", "skinNoriz/");
            gsm.noriz.makeAnimations(Utils.toInt(DIRECTION.UP),"_gauche", "skinNoriz/");;
        });
    }
}