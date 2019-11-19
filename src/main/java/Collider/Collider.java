package Collider;

import Entity.Entity;
import UI.inGameUserInterface;
import Utils.*;
import WorldBuilder.World;

public class Collider{

    World world;

    public Collider(World world) {
        this.world = world;
    }

    public boolean isPossible(int x1, int y1, int x2, int y2) {
        int[] coord1 = Utils.getSquare(x1,y1);
        int[] coord2 = Utils.getSquare(x2,y2);
        return world.map[coord1[0]][coord1[1]] != WORLDITEM.WALL && world.map[coord2[0]][coord2[1]] != WORLDITEM.WALL;
    }

    public boolean isPossible(int x, int y) {
        int coords[] = Utils.getSquare(x,y);
        return world.map[coords[0]][coords[1]] != WORLDITEM.WALL;
    }

    /**
     *
     * @param e1 entity 1
     * @param e2 entity 2
     * @return true if they touch, false otherwise
     */
    public boolean isTouching(Entity e1, Entity e2){
        return Utils.distance(e1.getCenterX(),e1.getCenterY(),e2.getCenterX(),e2.getCenterY()) <= e1.getSize()/3 + e2.getSize()/3;
    }

    public boolean takeItemBonus(int x,int y) {
        int[] coords = Utils.getSquare(x,y);
        if(world.map[coords[0]][coords[1]] == WORLDITEM.BONUS) {
            world.map[coords[0]][coords[1]] = WORLDITEM.ROAD;
            return true;
        }
        return false;
    }
}
