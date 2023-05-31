package worldBuilder;

import javafx.scene.image.Image;
import utils.WORLDITEM;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

import static utils.Utils.*;
import static utils.WORLDITEM.*;

public class worldRender {

    /*
     *   Entrée: une matrice représentant la carte
     *   Dessine la carte dans la fenêtre de jeu
     *        */
    public static void renderMap(GraphicsContext gc, WORLDITEM[][] map, boolean isEditor) {
        for(int posX = 0; posX< map.length; posX++) {
            for (int posY = 0; posY < map[posX].length; posY++) {
                //if(isStatic(map[posX][posY]))
                    renderItem(posX,posY, map[posX][posY], gc, isEditor);
            }
        }
    }

    public static void renderItems(GraphicsContext gc, WORLDITEM[][] map, boolean isEditor){
        for(int posX = 0; posX< map.length; posX++) {
            for (int posY = 0; posY < map[posX].length; posY++) {
                if(!isStatic(map[posX][posY]))
                    renderItem(posX,posY, map[posX][posY], gc, isEditor);
            }
        }
    }

    private static boolean isStatic(WORLDITEM item){
        if(item == WALL || item == ROAD || item == SPAWN_MONSTER || item == SPAWN_PLAYER || item == UI)
            return true;
        return false;
    }

    /*
     *   Entrée: posX et posY: position de l'item // l'item a dessiner
     *   Dessine l'item a la position demandée
     */
    public static void renderItem(int posX, int posY, WORLDITEM item, GraphicsContext gc, boolean isEditor) {

        switch (item) {
            case ROAD ->
                    gc.drawImage(World.road, posX * caseWidth, posY * caseHeight, caseWidth, caseHeight);
            case WALL ->
                    gc.drawImage(World.wall, posX * caseWidth, posY * caseHeight, caseWidth, caseHeight);
            case RICE -> {
                gc.drawImage(World.road, posX * caseWidth, posY * caseHeight, caseWidth, caseHeight);
                gc.drawImage(World.rice, posX * caseWidth + caseWidth / 3.0, posY * caseHeight + caseHeight / 3.0, caseWidth / 2., caseHeight / 2.);
            }
            case BONUS -> {
                gc.drawImage(World.road, posX * caseWidth, posY * caseHeight, caseWidth, caseHeight);
                gc.drawImage(World.bonus[(posY > (mapHeight / 2) ? 0 : 1)], posX * caseWidth + caseWidth / 3.0, posY * caseHeight + caseHeight / 3.0, caseWidth / 2., caseHeight / 2.);
            }
            case UI -> {
                gc.setFill(Color.DARKGRAY);
                gc.fillRect(posX * caseWidth, posY * caseHeight, caseWidth, caseHeight);
            }
            case SPAWN_MONSTER -> {
                if (isEditor) {
                    gc.setFill(Color.RED);
                    gc.fillRect(posX * caseWidth, posY * caseHeight, caseWidth, caseHeight);
                } else {
                    gc.drawImage(World.road, posX * caseWidth, posY * caseHeight, caseWidth, caseHeight);
                }
            }
            case SPAWN_PLAYER -> {
                if (isEditor) {
                    gc.setFill(Color.BLUE);
                    gc.fillRect(posX * caseWidth, posY * caseHeight, caseWidth, caseHeight);
                } else {
                    gc.drawImage(World.road, posX * caseWidth, posY * caseHeight, caseWidth, caseHeight);
                }
            }
        }
    }

    public static void renderSquare(GraphicsContext gc, WORLDITEM[][] map, int x, int y) {
        renderItem(x, y, map[x][y],gc,false);
    }
}
