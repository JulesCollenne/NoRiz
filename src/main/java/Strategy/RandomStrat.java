package Strategy;

import Entity.Monster;
import States.GameStateManager;
import Utils.DIRECTION;
import Utils.Utils;

/**
 * Choisit une nouvelle direction au hasard:
 * La nouvelle direction ne peut être ni a contre sens (il ne peut pas faire demi tour), ni vers un mur
 *
 * TODO: Peut etre ajouter un temps de pause a chaque intersection parce qu'il a tendance a ne pas les prendre car il passe trop vite
 */


public class RandomStrat implements Strategy {

    public RandomStrat(int nbMonster) {

    }

    public DIRECTION nextWay(Monster monster) {

        int x = monster.getX();
        int y = monster.getY();

        int rand;
        boolean impossible = true;
        DIRECTION currentWay = monster.getFacing();
        DIRECTION nextWay = currentWay;

        while (impossible) {
            rand = (int) (Math.random() * 4);

            switch (rand) {

                case 0:
                    if (currentWay != DIRECTION.UP && monster.collider.isPossible(x+1, y + Utils.caseDimension+1)) {  // il ne peut pas faire demi tour/choisir une direction vers un mur
                        nextWay = DIRECTION.DOWN;
                        impossible = false;
                    }
                    break;

                case 1:
                    if (currentWay != DIRECTION.DOWN && monster.collider.isPossible(x+1, y - Utils.caseDimension+1)) {  // il ne peut pas faire demi tour/choisir une direction vers un mur
                        nextWay = DIRECTION.UP;
                        impossible = false;
                    }
                    break;

                case 2:
                    if (currentWay != DIRECTION.RIGHT && monster.collider.isPossible(x - Utils.caseDimension+1, y+1)) {  // il ne peut pas faire demi tour/choisir une direction vers un mur
                        nextWay = DIRECTION.LEFT;
                        impossible = false;
                    }
                    break;

                case 3:
                    if (currentWay != DIRECTION.LEFT && monster.collider.isPossible(x + Utils.caseDimension+1, y+1)) {  // il ne peut pas faire demi tour/choisir une direction vers un mur
                        nextWay = DIRECTION.RIGHT;
                        impossible = false;
                    }
                    break;

                default:
                    nextWay = DIRECTION.STOP;
                    break;

            }
        }
        return nextWay;
    }
}




