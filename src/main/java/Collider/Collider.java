package Collider;

import Entity.Entity;
import States.GameStateManager;
import UI.inGameUserInterface;
import Utils.*;

public class Collider{

    private GameStateManager gsm;

    public Collider(GameStateManager gsm) {
        this.gsm = gsm;
    }

    /**
     *
     * @return true Si un mouvement est possible ( pas dans un mur )
     */
    public boolean isPossible(int x, int y){
        int[] coord = Utils.getSquare(x,y);
        return gsm.world.map[coord[0]][coord[1]] != WORLDITEM.WALL;
    }


    /**
     *
     * @param e1 entity 1
     * @param e2 entity 2
     * @return true if they touch, false otherwise
     */
    private boolean isTouching(Entity e1, Entity e2){
        return Utils.distance(e1.getCenterX(),e1.getCenterY(),e2.getCenterX(),e2.getCenterY()) <= e1.getSize()/3 + e2.getSize()/3;
    }

    public boolean isImpossible(int x1, int y1, int x2, int y2) {
        int[] coord1 = Utils.getSquare(x1,y1);
        int[] coord2 = Utils.getSquare(x2,y2);
        return (gsm.world.map[coord1[0]][coord1[1]] == WORLDITEM.WALL) || (gsm.world.map[coord2[0]][coord2[1]] == WORLDITEM.WALL);
    }

    public void nextStep(){
        for (Entity monster : gsm.monsters) {
            if(isTouching(gsm.player, monster)){
                gsm.player.gotHit();
            }
        }
    }

    public void takeRice(int x,int y) {
        int[] coords = Utils.getSquare(x,y);
        if(gsm.world.map[coords[0]][coords[1]] == WORLDITEM.RICE) {
            gsm.world.map[coords[0]][coords[1]] = WORLDITEM.ROAD;
            inGameUserInterface.currentNbRice -= 1;
        }
    }
}
