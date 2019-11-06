package Collider;

import Entity.Entity;
import States.GameStateManager;
import Utils.Utils;

public class Collider {

    GameStateManager gsm;

    public Collider(GameStateManager gsm) {
        this.gsm = gsm;
    }

    /**
     *
     * @return Si un mouvement est possible ( pas dans un mur )
     */
    public boolean isPossible(int x, int y){

        int[] coord = Utils.getSquare(x,y);

        return gsm.map[coord[0]][coord[1]] != 1;
    }

    public boolean isTouching(Entity e1, Entity e2){
        if()
    }

}
