package Strategy;

import Entity.Monster;
import Utils.DIRECTION;

/**
 * Choisit une nouvelle direction au hasard:
 * La nouvelle direction ne peut être ni a contre sens (il ne peut pas faire demi tour), ni vers un mur
 *
 */

//TODO peut etre utiliser les nouvelles collisions

public class RandomStrat implements Strategy {

    public DIRECTION nextWay(Monster monster) {

        int rand;
        boolean impossible = true;
        DIRECTION currentWay = monster.getFacing();
        DIRECTION nextWay = currentWay;

        int coords[];
        DIRECTION facingTmp = monster.getFacing();

        /*switch(facingTmp){
            case UP:
                break;
            case DOWN: break;

            case RIGHT: break;

            case LEFT: break;
            default:break;
        }*/

        while (impossible) {
            rand = (int) (Math.random() * 4);
            switch (rand) {
                case 0:
                    monster.setFacing(DIRECTION.DOWN);
                    coords = monster.getCollideCoords();
                    if (currentWay != DIRECTION.UP && monster.collider.isPossible(coords[0], coords[1] + 1, coords[2], coords[3]+1)) {  // il ne peut pas faire demi tour/choisir une direction vers un mur
                        nextWay = DIRECTION.DOWN;
                        impossible = false;
                    }
                    monster.setFacing(facingTmp);
                    break;

                case 1:
                    monster.setFacing(DIRECTION.UP);
                    coords = monster.getCollideCoords();
                    if (currentWay != DIRECTION.DOWN && monster.collider.isPossible(coords[0], coords[1] - 1, coords[2], coords[3] - 1)) {  // il ne peut pas faire demi tour/choisir une direction vers un mur
                        nextWay = DIRECTION.UP;
                        impossible = false;
                    }
                    break;

                case 2:
                    monster.setFacing(DIRECTION.LEFT);
                    coords = monster.getCollideCoords();
                    if (currentWay != DIRECTION.RIGHT && monster.collider.isPossible(coords[0] - 1, coords[1], coords[2] - 1, coords[3])) {  // il ne peut pas faire demi tour/choisir une direction vers un mur
                        nextWay = DIRECTION.LEFT;
                        impossible = false;
                    }
                    break;

                case 3:
                    monster.setFacing(DIRECTION.RIGHT);
                    coords = monster.getCollideCoords();
                    if (currentWay != DIRECTION.LEFT && monster.collider.isPossible(coords[0] + 1, coords[1], coords[2] + 1, coords[3])) {  // il ne peut pas faire demi tour/choisir une direction vers un mur
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




