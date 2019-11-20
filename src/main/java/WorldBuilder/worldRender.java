package WorldBuilder;

import Utils.WORLDITEM;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import static Utils.Utils.caseDimension;
import Utils.WORLDITEM.*;

public class worldRender {

    /*
     *   Entrée: une matrice représentant la carte
     *   Dessine la carte dans la fenêtre de jeu
     *        */
    public static void renderMap(GraphicsContext gc, WORLDITEM[][] map) {
        //gc.drawImage(backGroundImage,0,0,Utils.canvasSize, Utils.canvasSize);
        for(int posX = 0; posX< map.length; posX++) {
            for (int posY = 0; posY < map[posX].length; posY++) {
                renderItem(posX,posY, map[posX][posY], gc);
            }
        }
    }

    /*
     *   Entrée: posX et posY: position de l'item // l'item a dessiner
     *   Dessine l'item a la position demandée
     */
    public static void renderItem(int posX, int posY, WORLDITEM item, GraphicsContext gc) {

        switch(item){

            case ROAD:
                gc.drawImage(World.road, posX*caseDimension, posY*caseDimension, caseDimension, caseDimension);
                break;

            case WALL:
                gc.drawImage(World.wall, posX*caseDimension, posY*caseDimension, caseDimension, caseDimension);
                break;

            case RICE:
                gc.drawImage(World.road, posX*caseDimension, posY*caseDimension, caseDimension, caseDimension);
                gc.drawImage(World.rice, posX*caseDimension + caseDimension/3.0, posY*caseDimension + caseDimension/3.0, caseDimension/2.,caseDimension/2.);
                break;

            case BONUS:
                gc.drawImage(World.road, posX*caseDimension, posY*caseDimension, caseDimension, caseDimension);
                gc.setFill(Color.GREEN);
                gc.fillRect(posX*caseDimension, posY*caseDimension, caseDimension,caseDimension);
                break;

            case UI:
                gc.setFill(Color.DARKGRAY);
                gc.fillRect(posX*caseDimension, posY*caseDimension, caseDimension,caseDimension);
                break;

        }
    }

}
