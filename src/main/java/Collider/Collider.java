package Collider;

import Entity.Entity;
import Utils.Utils;
import Utils.WORLDITEM;
import WorldBuilder.World;

/**
 * The collider manages the collisions
 */
public class Collider{

    private World world;

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
     * This function is not working and should not be used anymore
     * @param x point
     * @param y point
     * @return true if x,y is in a wall
     */
    public boolean isPossible(int x, int y) {
        int coords[] = Utils.getSquare(x,y);
        return world.map[coords[0]][coords[1]] != WORLDITEM.WALL;
    }


    /**
     * @param e1 entity 1
     * @param e2 entity 2
     * @return true if e1 and e2 touch, false otherwise
     */
    public boolean isTouching(Entity e1, Entity e2){
        return Utils.distance(e1.getCenterX(),e1.getCenterY(),e2.getCenterX(),e2.getCenterY()) <= e1.getSize()/3 + e2.getSize()/3;
    }

}
