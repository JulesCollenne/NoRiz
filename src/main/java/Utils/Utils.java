package Utils;

public class Utils {

    public static final int mapSize = 25; // labyrinthe de size*size case
    public static final int caseDimension = 40; // chaque case du labyrinthe fait dimension*dimension pixel
    public static final int canvasSize = mapSize * caseDimension;

    public enum STATE {START, PLAY, PAUSE}


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
}
