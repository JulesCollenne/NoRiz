package utils;

import static utils.DIRECTION.*;
import static utils.WORLDITEM.*;

public class Utils {

    public enum DIF {EASY, MEDIUM, HARD, ARCADE}

    public static final int mapWidth = 25; // labyrinthe de size*size case
    public static final int mapHeight = 25; // labyrinthe de size*size case
//    public static final int caseWidth = 16*4; // chaque case du labyrinthe fait dimension*dimension pixel
//    public static final int caseHeight = 9*4; // chaque case du labyrinthe fait dimension*dimension pixel
    public static final int caseWidth = 30; // chaque case du labyrinthe fait dimension*dimension pixel
    public static final int caseHeight = 30; // chaque case du labyrinthe fait dimension*dimension pixel

    public static final int canvasWidth = mapWidth * caseWidth;
    public static final int canvasHeight = mapHeight * caseHeight;
    static final int roundDuration = 500; // en seconde

    public static final boolean fullscreen = false;

    public static final float playerSpeed = canvasWidth/800F;
    public static final float monsterSpeed = canvasWidth/800F;

    public static final int UISize = 2;

    public static final int START  = 0;
    public static final int STORY = 1;
    public static final int PAUSE  = 2;
    public static final int GAMEOVER = 3;
    public static final int EDITOR = 4;
    public static final int OPTIONS = 5;
    public static final int ARCADE = 6;
    public static final int SKIN = 7;
    public static final int CINEMATIQUE = 8;
    public static final int WIN = 9;
    public static final int EDITORPLAY = 10;
    public static final int MULTIMENU = 11;
    public static int nbStates = 12;
    
    public static int worldItemToInt(WORLDITEM worlditem){
        return switch (worlditem) {
            case ROAD -> 0;
            case WALL -> 1;
            case RICE -> 2;
            case BONUS -> 3;
            case UI -> 4;
            case SPAWN_PLAYER -> 5;
            case SPAWN_MONSTER -> 6;
        };
    }

    public static WORLDITEM intToWorldItem(int item){
        return switch (item) {
            case 0 -> ROAD;
            case 1 -> WALL;
            case 2 -> RICE;
            case 3 -> BONUS;
            case 4 -> UI;
            case 5 -> SPAWN_PLAYER;
            case 6 -> SPAWN_MONSTER;
            default -> ROAD;
        };
    }

    /**
     *
     * @param x coordonnée en pixel
     * @param y coordonnée en pixel
     * @return un tableau contenant x,y en coordonnée de la matrice
     */
    public static int[] getSquare(int x, int y){
        int[] coord = new int[2];

        coord[0] = x/caseWidth % mapWidth;
        coord[1] = y/caseHeight;
        if(coord[1] >= mapHeight)
            coord[1] = UISize;

        if(coord[0] < 0)
            coord[0] = mapWidth-1;
        if(coord[1] < UISize)
            coord[1] = mapHeight-1;

        return coord;
    }

    public static DIRECTION inverse(DIRECTION dir){
        return switch (dir) {
            case DOWN -> UP;
            case UP -> DOWN;
            case RIGHT -> LEFT;
            case LEFT -> RIGHT;
            default -> null;
        };
    }

    /**
     *
     * @param x coordonnée en pixel
     * @param y coordonnée en pixel
     * @return un tableau contenant x,y en coordonnée de la matrice
     */
    public static int[] getSquareMouse(int x, int y){
        int[] coord = new int[2];

        coord[0] = x/caseWidth;
        coord[1] = y/caseHeight;

        return coord;
    }

    /**
     *
     * @param x1 x1
     * @param y1 y1
     * @param x2 x2
     * @param y2 y2
     * @return distance between the two points
     */
    public static double distance(int x1, int y1, int x2, int y2){
        return Math.sqrt(Math.pow(x2 - x1,2) + Math.pow(y2 - y1,2));
    }

    public static int toInt(DIRECTION dir){
        return switch (dir) {
            case DOWN -> 0;
            case UP -> 1;
            case LEFT -> 2;
            case RIGHT -> 3;
            default -> 0;
        };
    }

    /**
     *
     * @param map the map we want to copy
     * @return the copy of the map
     */
    public static WORLDITEM[][] copyMap (WORLDITEM[][] map){
        WORLDITEM[][] tempMap = new WORLDITEM[map.length][map[0].length];
        for(int i = 0; i<map.length; i++)
            System.arraycopy(map[i], 0, tempMap[i], 0, map[0].length);
        return tempMap;
    }

    public static int[] getMinIndex(double[][] distances) {
        int[] coords = new int[2];

        double min = 999;

        for(int i=0; i < distances.length; i++) {
            for (int j = 0; j < distances[i].length; j++) {
                if(distances[i][j] < min){
                    min = distances[i][j];
                    coords[0] = i;
                    coords[1] = j;
                }
            }
        }
        return coords;
    }

    public static int[] getCanvasCoords(int[] coords){
        int[] newCoords = new int[2];

        newCoords[0] = coords[0] * Utils.caseWidth + Utils.caseHeight/2;
        newCoords[1] = coords[1] * Utils.caseWidth + Utils.caseHeight/2;

        return newCoords;
    }
}

