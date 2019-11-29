package ui;

import utils.Utils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class inGameUserInterface {

    private Image UITimer = new Image("UI/UITimer.png", 119, Utils.caseDimension * 2, true, false);
    private Image UIRiceTemp = new Image("UI/UIRiceTemp.png", 119, Utils.caseDimension * 2, true, false);
    private Image UIHeart = new Image("UI/UIHeart.png", 52.7, Utils.caseDimension + (Utils.caseDimension/2.0), true, false);
    private Image grainDeRiz = new Image("collectable/GrainDeRiz.png", 52.7, Utils.caseDimension + (Utils.caseDimension/2.0), true, false);

    public inGameUserInterface(){

    }

    private void renderUITimer(GraphicsContext gc, long timer){

        gc.drawImage(UITimer, Utils.canvasSize - 130, 0);

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
        gc.drawImage(UIRiceTemp, 11, 0);
        gc.drawImage(grainDeRiz, 12,8);

        gc.setFill(Color.WHITE);
        gc.fillText(nbRice+"", 60 , 18+((70/100.0)*Utils.caseDimension));

    }

    private void renderHeart(GraphicsContext gc, int nbLife){
        for(int i = 0; i<nbLife; i++){
            gc.drawImage(UIHeart, 150 + (i*70) , (50/100.0) * (Utils.caseDimension/2.0));
        }
    }

    private void renderScore(GraphicsContext gc, int score){

        gc.setFont(Font.font("Helvetica", 20));
        gc.fillText("Score: "+score, 150 + 5*70 + 5, Utils.caseDimension+ (Utils.caseDimension/3.0));
        gc.setFont(Font.font("Helvetica", 24));

    }

    public void render(GraphicsContext gc, int nbLife, int nbRice, long timer, int score){

        renderUITimer(gc, timer);
        renderNbRice(gc, nbRice);
        renderHeart(gc, nbLife);
        renderScore(gc, score);

    }

    public void render(GraphicsContext gc, int nbLife, int nbRice, long timer){

        renderUITimer(gc, timer);
        renderNbRice(gc, nbRice);
        renderHeart(gc, nbLife);

    }


}
