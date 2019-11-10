package Strategy;

import States.GameStateManager;
import Utils.DIRECTION;
import Utils.Utils;

/**
 * Choisit une direction au hasard lorsqu'il passe sur une intersection
 */


public class RandomStrat implements Strategy {

    private GameStateManager gsm;
    private int x, y;

    public RandomStrat(GameStateManager gsm) {
        this.gsm = gsm;
    }

    ;

    public DIRECTION nextWay(DIRECTION currentWay) {

        y = gsm.monsters[1].getY();
        x = gsm.monsters[1].getX();


        int rand;
        boolean impossible = true;
        DIRECTION nextWay = currentWay;
        System.out.println("pos: "+x+", "+y);

        if (x % Utils.caseDimension != 0 || y % Utils.caseDimension != 0) { //tant qu'il n'est pas a une intersection il garde son chemin
            System.out.println("je ne change pas");
            return currentWay;
        }
        else {

            System.out.println("\n\n\nJe suis a l'intersection "+x+", "+y+"\n\n\n");

            while (impossible) {
                rand = (int) (Math.random() * 4);

                switch (rand) {

                    case 0:
                        if (currentWay != DIRECTION.UP && gsm.collider.isPossible(x, y + Utils.caseDimension)) {  // il ne peut pas faire demi tour/choisir une direction vers un mur
                            System.out.println("nextWay: " + DIRECTION.DOWN);
                            nextWay = DIRECTION.DOWN;
                            impossible = false;
                        }
                        break;

                    case 1:
                        if (currentWay != DIRECTION.DOWN && gsm.collider.isPossible(x, y - Utils.caseDimension)) {  // il ne peut pas faire demi tour/choisir une direction vers un mur
                            System.out.println("nextWay: " + DIRECTION.UP);
                            nextWay = DIRECTION.UP;
                            impossible = false;
                        }
                        break;

                    case 2:
                        if (currentWay != DIRECTION.RIGHT && gsm.collider.isPossible(x - Utils.caseDimension, y)) {  // il ne peut pas faire demi tour/choisir une direction vers un mur
                            System.out.println("nextWay: " + DIRECTION.LEFT);
                            nextWay = DIRECTION.LEFT;
                            impossible = false;
                        }
                        break;

                    case 3:
                        if (currentWay != DIRECTION.LEFT && gsm.collider.isPossible(x + Utils.caseDimension, y)) {  // il ne peut pas faire demi tour/choisir une direction vers un mur
                            System.out.println("nextWay: " + DIRECTION.RIGHT);
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
}


