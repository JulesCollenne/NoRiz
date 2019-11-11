package Strategy;

import States.GameStateManager;
import Utils.DIRECTION;
import Utils.Utils;

/**
 * Choisit une nouvelle direction au hasard:
 * La nouvelle direction ne peut être ni a contre sens (il ne peut pas faire demi tour), ni vers un mur
 *
 * TODO: Peut etre ajouté un temps de pause a chaque intersection parce qu'il a tendance a ne pas les prendre car il passe trop vite
 */


public class RandomStrat implements Strategy {

    private GameStateManager gsm;
    private int x, y;

    public RandomStrat(GameStateManager gsm) {
        this.gsm = gsm;
    }

    public DIRECTION nextWay(DIRECTION currentWay) {

        y = gsm.monsters[1].getY();
        x = gsm.monsters[1].getX();

        int rand;
        boolean impossible = true;
        DIRECTION nextWay = currentWay;

        while (impossible) {
            rand = (int) (Math.random() * 4);

            switch (rand) {

                case 0:
                    if (currentWay != DIRECTION.UP && gsm.collider.isPossible(x+1, y + Utils.caseDimension+1)) {  // il ne peut pas faire demi tour/choisir une direction vers un mur
                        nextWay = DIRECTION.DOWN;
                        impossible = false;
                    }
                    break;

                case 1:
                    if (currentWay != DIRECTION.DOWN && gsm.collider.isPossible(x+1, y - Utils.caseDimension+1)) {  // il ne peut pas faire demi tour/choisir une direction vers un mur
                        nextWay = DIRECTION.UP;
                        impossible = false;
                    }
                    break;

                case 2:
                    if (currentWay != DIRECTION.RIGHT && gsm.collider.isPossible(x - Utils.caseDimension+1, y+1)) {  // il ne peut pas faire demi tour/choisir une direction vers un mur
                        nextWay = DIRECTION.LEFT;
                        impossible = false;
                    }
                    break;

                case 3:
                    if (currentWay != DIRECTION.LEFT && gsm.collider.isPossible(x + Utils.caseDimension+1, y+1)) {  // il ne peut pas faire demi tour/choisir une direction vers un mur
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




