package Utils;

import static Utils.WORLDITEM.*;

public class Utils {

    public static final int mapSize = 25; // labyrinthe de size*size case
    public static final int caseDimension = 32; // chaque case du labyrinthe fait dimension*dimension pixel
    public static final int canvasSize = mapSize * caseDimension;
    public static final int roundDuration = 120; // en seconde

    public static final int START  = 0;
    public static final int PLAY  = 1;
    public static final int PAUSE  = 2;
    public static final int GAMEOVER = 3;
    public static final int EDITOR = 4;
    public static final int OPTIONS = 5;

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
            case SPAWN_PLAYER:
                return 5;
            case SPAWN_MONSTER:
                return 6;
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
            case 5:
                return SPAWN_PLAYER;
            case 6:
                return SPAWN_MONSTER;
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
}

