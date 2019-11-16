package UI;

import States.GameStateManager;
import Utils.Utils;
import WorldBuilder.matrixWorld;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class inGameUserInterface {

    private GameStateManager gsm;

    public inGameUserInterface(GameStateManager gsm){

        this.gsm = gsm;

    }

    private void renderUITimer(GraphicsContext gc, long timer){


        Image UITimer = new Image("UI/UITimer.png", 119, Utils.caseDimension * 2, true, false);
        gc.drawImage(UITimer, Utils.canvasSize - 130, 1);


        gc.setFill(Color.WHITE);
        if(timer >= 100) {
            gc.fillText(timer + "", Utils.canvasSize - (80), 18 + ((70 / 100.0) * Utils.caseDimension));
        }
        else if (timer >= 10) {
            gc.fillText(timer + "", Utils.canvasSize - (71), 18 + ((70 / 100.0) * Utils.caseDimension));
        }
        else{
            gc.setFill(Color.RED);
            gc.fillText(timer + "", Utils.canvasSize - (65), 18 + ((70 / 100.0) * Utils.caseDimension));
        }

    }

    private void renderNbRice(GraphicsContext gc, int nbRice){

        Image UITimer = new Image("UI/UIRiceTemp.png", 119, Utils.caseDimension * 2, true, false);

        gc.drawImage(UITimer, 11, 1);

        gc.setFill(Color.WHITE);
        gc.fillText(nbRice+"", 60 , 18+((70/100.0)*Utils.caseDimension));

    }

    private void renderHeart(GraphicsContext gc, int nbLife){

        for(int i = 0; i<nbLife; i++){

            Image UIHeart = new Image("UI/UIHeart.png", 52.7, Utils.caseDimension + (Utils.caseDimension/2.0), true, false);
            gc.drawImage(UIHeart, 200 + (i*70) , (50/100.0) * (Utils.caseDimension/2.0));

        }
    }

    public void render(GraphicsContext gc, int nbLife, int nbRice, long timer){

        renderUITimer(gc, timer);
        renderNbRice(gc, nbRice);
        renderHeart(gc, nbLife);

    }


}
