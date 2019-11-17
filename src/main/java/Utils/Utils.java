package Utils;

import static Utils.DIRECTION.DOWN;
import static Utils.WORLDITEM.*;

public class Utils {

    public static final int mapSize = 25; // labyrinthe de size*size case
    public static final int caseDimension = 30; // chaque case du labyrinthe fait dimension*dimension pixel
    public static final int canvasSize = mapSize * caseDimension;
    public static final int roundDuration = 120; // en seconde


    public enum STATE {START, PLAY, PAUSE}


    public static int worldItemToInt(WORLDITEM worlditem){
        switch(worlditem){
            case ROAD:
                return 0;
            case WALL:
                return 1;
            case RICE:
                return 2;
            case BONUS:
                return 3;
            case UI:
                return 4;
        }
        return -1;
    }

    public static WORLDITEM intToWorldItem(int item){
        switch(item){
            case 0:
                return ROAD;
            case 1:
                return WALL;
            case 2:
                return RICE;
            case 3:
                return BONUS;
            case 4:
                return UI;
        }
        return ROAD;
    }

    /**
     *
     * @param x coordonnée en pixel
     * @param y coordonnée en pixel
     * @return un tableau contenant x,y en coordonnée de la matrice
     */
    public static int[] getSquare(int x, int y){
        int[] coord = new int[2];

        coord[0] = x/caseDimension;
        coord[1] = y/caseDimension;

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
        switch (dir) {
            case DOWN:
                return 0;
            case UP:
                return 1;
            case LEFT:
                return 2;
            case RIGHT:
                return 3;
        }
        return 0;
    }
}

