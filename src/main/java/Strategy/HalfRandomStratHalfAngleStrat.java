package Strategy;

import Entity.Monster;
import Entity.Player;
import Utils.DIRECTION;
import Utils.Utils;

/**
 *
 */

public class HalfRandomStratHalfAngleStrat implements Strategy {
    private Player player;
    private static double taux = 0.7; //

    public HalfRandomStratHalfAngleStrat(Player player) {
        this.player = player;
    }

    public DIRECTION nextWay(Monster monster) {
        double random = Math.random();

        DIRECTION currentWay = monster.getFacing();
        DIRECTION nextWay = currentWay;

        int x = monster.getX();
        int y = monster.getY();

        if(random <= taux){ //RandomStrat
            int rand;
            boolean impossible = true;

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
        else{ //AngleStrat
            int vectorX = player.getX() - x;
            int vectorY = player.getY() - y;

            double angle = Math.atan2(vectorY,vectorX);
            double cos,sin;
            cos = Math.cos(angle);
            sin = Math.sin(angle);

            if(currentWay == DIRECTION.UP && !monster.collider.isPossible(x+1, y - Utils.caseDimension+1)){
                if(monster.collider.isPossible(x - Utils.caseDimension+1, y+1))
                    return DIRECTION.LEFT;
                else if(monster.collider.isPossible(x + Utils.caseDimension+1, y+1))
                    return DIRECTION.RIGHT;
                else
                    return DIRECTION.DOWN;
            }
            else if(currentWay == DIRECTION.DOWN && !monster.collider.isPossible(x+1, y + Utils.caseDimension+1)){
                if(monster.collider.isPossible(x - Utils.caseDimension+1, y+1))
                    return DIRECTION.LEFT;
                else if(monster.collider.isPossible(x + Utils.caseDimension+1, y+1))
                    return DIRECTION.RIGHT;
                else
                    return DIRECTION.UP;
            }
            else if(currentWay == DIRECTION.LEFT && !monster.collider.isPossible(x - Utils.caseDimension+1, y+1)){
                if(monster.collider.isPossible(x+1, y - Utils.caseDimension+1))
                    return DIRECTION.UP;
                else if(monster.collider.isPossible(x+1, y + Utils.caseDimension+1))
                    return DIRECTION.DOWN;
                else
                    return DIRECTION.RIGHT;
            }
            else if(currentWay == DIRECTION.RIGHT && !monster.collider.isPossible(x + Utils.caseDimension+1, y+1)){
                if(monster.collider.isPossible(x+1, y - Utils.caseDimension+1))
                    return DIRECTION.UP;
                else if(monster.collider.isPossible(x+1, y + Utils.caseDimension+1))
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
