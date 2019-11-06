package Utils;

public class Utils {
    /**
     *
     * @param x coordonnée en pixel
     * @param y coordonnée en pixel
     * @return un tableau contenant x,y en coordonnée de la matrice
     */
    public static int[] getSquare(int x, int y){
        int[] coord = new int[2];

        coord[0] = x/50;
        coord[1] = y/50;

        return coord;
    }

    public double distance(int x1, int y1, int x2, int y2){
        return Math.sqrt(Math.pow(x2 - x1,2) + Math.pow(y2 - y1,2));
    }
}
