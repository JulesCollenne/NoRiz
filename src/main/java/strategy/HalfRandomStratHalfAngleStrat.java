package strategy;

import entity.Monster;
import entity.Noriz;
import utils.DIRECTION;
import utils.Utils;

/**
 *
 */

public class HalfRandomStratHalfAngleStrat implements Strategy {
    private Noriz noriz;
    private static double taux = 0.7; //

    public HalfRandomStratHalfAngleStrat(Noriz noriz) {
        this.noriz = noriz;
    }

    public DIRECTION nextWay(Monster monster) {
        double random = Math.random();

        DIRECTION currentWay = monster.getFacing();
        DIRECTION nextWay = currentWay;

        int x = monster.getX();
        int y = monster.getY();

        int coords[];
        DIRECTION facingTmp = monster.getFacing();

        if(random <= taux){ //RandomStrat
            int rand;
            boolean impossible = true;

            int nbEssai = 0;
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
                nbEssai++;
                if(nbEssai == 10){
                    nextWay = Utils.inverse(currentWay);
                    impossible = false;
                }
            }
            return nextWay;
        }
        else{ //AngleStrat
            int vectorX = noriz.getX() - x;
            int vectorY = noriz.getY() - y;

            double angle = Math.atan2(vectorY,vectorX);
            double cos,sin;
            cos = Math.cos(angle);
            sin = Math.sin(angle);

            if(currentWay == DIRECTION.UP && !monster.collider.isPossible(x+1, y - Utils.caseHeight+1)){
                if(monster.collider.isPossible(x - Utils.caseWidth+1, y+1))
                    return DIRECTION.LEFT;
                else if(monster.collider.isPossible(x + Utils.caseWidth+1, y+1))
                    return DIRECTION.RIGHT;
                else
                    return DIRECTION.DOWN;
            }
            else if(currentWay == DIRECTION.DOWN && !monster.collider.isPossible(x+1, y + Utils.caseHeight+1)){
                if(monster.collider.isPossible(x - Utils.caseWidth+1, y+1))
                    return DIRECTION.LEFT;
                else if(monster.collider.isPossible(x + Utils.caseWidth+1, y+1))
                    return DIRECTION.RIGHT;
                else
                    return DIRECTION.UP;
            }
            else if(currentWay == DIRECTION.LEFT && !monster.collider.isPossible(x - Utils.caseWidth+1, y+1)){
                if(monster.collider.isPossible(x+1, y - Utils.caseHeight+1))
                    return DIRECTION.UP;
                else if(monster.collider.isPossible(x+1, y + Utils.caseHeight+1))
                    return DIRECTION.DOWN;
                else
                    return DIRECTION.RIGHT;
            }
            else if(currentWay == DIRECTION.RIGHT && !monster.collider.isPossible(x + Utils.caseWidth+1, y+1)){
                if(monster.collider.isPossible(x+1, y - Utils.caseHeight+1))
                    return DIRECTION.UP;
                else if(monster.collider.isPossible(x+1, y + Utils.caseHeight+1))
                    return DIRECTION.DOWN;
                else
                    return DIRECTION.LEFT;
            }
            if(-0.5 < cos && cos < 0.5) {
                if(sin < 0){
                    return DIRECTION.UP;                                                    // Haut
                }
                else if(sin > 0){
                    return DIRECTION.DOWN;                                                  // Bas
                }

            }
            else if(-0.5 < sin && sin < 0.5){
                if(cos < 0) {
                    return DIRECTION.LEFT;                                                  // Gauche
                }
                else if(cos > 0) {
                    return DIRECTION.RIGHT;                                                 // Droite
                }
            }
            else if(cos >= 0.5){
                if(sin >= 0.5) {
                    if (sin > cos) {
                        return DIRECTION.DOWN;                                              // Bas
                    }
                    else {
                        return DIRECTION.RIGHT;                                             // Droite
                    }
                }
                else if(sin <= -0.5){
                    sin = -sin;
                    if (sin > cos) {
                        return DIRECTION.UP;                                                // Haut
                    }
                    else {
                        return DIRECTION.RIGHT;                                             // Droite
                    }
                }
            }
            else if(cos <= -0.5){
                cos = -cos;
                if(sin >= 0.5){
                    if(sin > cos) {
                        return DIRECTION.DOWN;                                              // Bas
                    }
                    else {
                        return DIRECTION.LEFT;                                              // Gauche
                    }
                }
                else if(sin <= -0.5){
                    sin = -sin;
                    if(sin > cos) {
                        return DIRECTION.UP;                                                 // Haut
                    }
                    else {
                        return DIRECTION.LEFT;                                               // Gauche
                    }
                }
            }
            return DIRECTION.STOP;
        }
    }
}
