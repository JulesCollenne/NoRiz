package Strategy;

import Utils.DIRECTION;

/**
 * Choisit une direction au hasard
 */
public class RandomStrat implements Strategy{
    public DIRECTION nextWay() {

        int rand;
        rand = (int) (Math.random() * 2);
        if(rand < 1)
            return DIRECTION.STOP; // objectif: Return currentDirection, pour éviter qu'il change de direction tout le temps en gros (a améliorer, peut etre un certain temps minimal entre chaque changement ?)

        rand = (int) (Math.random() * 4);

        switch (rand){

            case 0: return DIRECTION.DOWN;

            case 1: return DIRECTION.UP;

            case 2: return DIRECTION.LEFT;

            case 3: return DIRECTION.RIGHT;

            default: return DIRECTION.STOP;

        }

    }
}
