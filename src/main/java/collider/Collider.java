package collider;

import entity.Entity;
import utils.Utils;
import utils.WORLDITEM;
import worldBuilder.World;

/**
 * The collider manages the collisions
 */
public class Collider{

    private final World world;

    public Collider(World world) {
        this.world = world;
    }

    /**
     * p1 and p2 is a line, we check if it's in a wall
     * @param x1 P1
     * @param y1 P1
     * @param x2 P2
     * @param y2 P2
     * @return true if the line (P1, P2) is not in a wall
     */
    public boolean isPossible(int x1, int y1, int x2, int y2) {
        int[] coord1 = Utils.getSquare(x1,y1);
        int[] coord2 = Utils.getSquare(x2,y2);
        return world.map[coord1[0]][coord1[1]] != WORLDITEM.WALL && world.map[coord2[0]][coord2[1]] != WORLDITEM.WALL;
    }

    /**
     * Return true is the square is in a wall
     */
    public boolean collide(int[][] coords){
        for(int i = 0; i < 4; i++){
            if(isInWall(coords[i][0], coords[i][1]))
                return true;
        }
        return false;
    }

    private boolean isInWall(int x, int y){
        int[] coord = Utils.getSquare(x, y);
        return world.map[coord[0]][coord[1]] == WORLDITEM.WALL;
    }

    /**
     * This function is not working and should not be used anymore
     * @param x point
     * @param y point
     * @return true if x,y is in a wall
     */
    public boolean isPossible(int x, int y) {
        int[] coords = Utils.getSquare(x,y);
        return world.map[coords[0]][coords[1]] != WORLDITEM.WALL;
    }


    /**
     * @param e1 entity 1
     * @param e2 entity 2
     * @return true if e1 and e2 touch, false otherwise
     */
    public boolean isTouching(Entity e1, Entity e2){
        if (Utils.distance(e1.getCenterX(), e1.getCenterY(), e2.getCenterX(), e2.getCenterY()) <= e1.getHitCircle() / 3. + e2.getHitCircle() / 3.){
            System.out.println(e1.getName());
            System.out.println(e2.getName());
        }
        return Utils.distance(e1.getCenterX(),e1.getCenterY(),e2.getCenterX(),e2.getCenterY()) <= e1.getHitCircle()/3. + e2.getHitCircle()/3.;
    }

    public int[] closestRoad(int x, int y){

        int[] playerCoords = Utils.getSquare(x,y);

        int[] coords;

        double[][] distances = new double[Utils.mapWidth][Utils.mapHeight];

        for(int i = 0; i < Utils.mapWidth; i++){
            for(int j = 0; j < Utils.mapHeight; j++){
                if(world.map[i][j] != WORLDITEM.WALL && world.map[i][j] != WORLDITEM.UI)
                    distances[i][j] = Utils.distance(playerCoords[0], playerCoords[1], i, j);
                else
                    distances[i][j] = 999;
            }
        }

        coords = Utils.getMinIndex(distances);

        return coords;
    }
}
