package ui;

import states.GameStateManager;
import utils.Utils;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static utils.Utils.*;

import static utils.Utils.DIF.*;

public class ModeButton extends Button {
    private ImageView image;
    private String label;

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
        this.setOnMousePressed(mouseEvent -> {
            switch(label) {
                case "Facile":
                    gsm.difficulty = EASY;
                    gsm.changeState(CINEMATIQUE);
                    break;
                case "Medium":
                    gsm.difficulty = MEDIUM;
                    gsm.changeState(CINEMATIQUE);
                    break;
                case "Hard":
                    gsm.difficulty = HARD;
                    gsm.changeState(CINEMATIQUE);
                    break;
                case "Rejouer":
                    gsm.changeState(STORY);
                    break;
                case "Reprendre":
                    gsm.reprendreJeu();
                    break;
                case "Menu":
                    gsm.sm.backGround.stop();
                    gsm.difficulty = EASY;
                    //gsm.sm.backGround.stop();
                    gsm.isEditorTest = false;
                    gsm.noriz.init();
                    gsm.initMonsters();
                    gsm.changeState(START);
                    break;
                case "Editor":
                    gsm.changeState(EDITOR);
                    break;
                case "Options":
                    gsm.changeState(OPTIONS);
                    break;
                case "Arcade":
                    gsm.difficulty = Utils.DIF.ARCADE;
                    gsm.changeState(Utils.ARCADE);
                    break;
                case "Multi":
                    gsm.changeState(MULTIMENU);
                    break;
                case "Niveau_Suivant":
                    if(gsm.difficulty == EASY){
                        gsm.difficulty = MEDIUM;
                        gsm.changeState(CINEMATIQUE);
                    }
                    else if(gsm.difficulty == MEDIUM){
                        gsm.difficulty = HARD;
                        gsm.changeState(CINEMATIQUE);
                    }
                    else if(gsm.difficulty == HARD){
                        gsm.difficulty = EASY;
                        gsm.isEditorTest = false;
                        gsm.changeState(START);
                    }
                    break;
            }
        });
        this.setOnMouseEntered((EventHandler<javafx.event.Event>) mouseEvent -> {
            //Faire une petite animation quand on survole le bouton
        });
    }
}

