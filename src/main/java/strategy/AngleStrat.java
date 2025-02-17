package strategy;

import entity.Monster;
import entity.Noriz;
import utils.DIRECTION;
import utils.Utils;

/**
 *
 * Calcule l'angle entre le monstre et le joueur, choisit le chemin possible le plus proche de l'angle.
 * 1.Soit x l'angle entre Player et le Monster
 * Et D,R,U,L les directions :
 *
 *     | cos(x)         | sin(x)
 *   D | -0.5 < A < 0.5 |  A < 0
 *   R | A > 0          | -0.5 < A < 0.5
 *   U | -0.5 < A < 0.5 |  A > 0
 *   L | A < 0          | -0.5 < A < 0.5
 *
 *   OU
 *                                                            OU
 *   D | cos(x) >= 0.5 && sin(x) <= -0.5 && -sin(x) > cos(x)  || cos(x) <= -0.5 && sin(x) <= -0.5 && -sin(x) > -cos(x)
 *   R | cos(x) >= 0.5 && sin(x) >= 0.5 && sin(x) <= cos(x)   || cos(x) >= 0.5 && sin(x) <= -0.5 && -sin(x) <= cos(x)
 *   U | cos(x) >= 0.5 && sin(x) >= 0.5 && sin(x) > cos(x)    || cos(x) <= -0.5 && sin(x) >= 0.5 && sin(x) > -cos(x)
 *   L | cos(x) <= -0.5 && sin(x) >= 0.5 && sin(x) <= -cos(x) || cos(x) <= -0.5 && sin(x) <= -0.5 && -sin(x) <= -cos(x)
 *
 *
 */
public class AngleStrat implements Strategy {

    private Noriz noriz;

    public AngleStrat(Noriz noriz){
        this.noriz = noriz;
    }

    public DIRECTION nextWay(Monster monster) {


        int vectorX = noriz.getCenterX() - monster.getCenterX();
        int vectorY = noriz.getCenterY() - monster.getCenterY();

        int x = monster.getX();
        int y = monster.getY();

        double angle = Math.atan2(vectorY,vectorX);
        double cos,sin;
        cos = Math.cos(angle);
        sin = Math.sin(angle);

        DIRECTION currentWay = monster.getFacing();

//        if(currentWay == DIRECTION.UP && !monster.collider.isPossible(x+1, y - Utils.caseHeight+1)){
//            if(monster.collider.isPossible(x - Utils.caseWidth+1, y+1))
//                return DIRECTION.LEFT;
//            else if(monster.collider.isPossible(x + Utils.caseWidth+1, y+1))
//                return DIRECTION.RIGHT;
//            else
//                return DIRECTION.DOWN;
//        }
//        else if(currentWay == DIRECTION.DOWN && !monster.collider.isPossible(x+1, y + Utils.caseHeight+1)){
//            if(monster.collider.isPossible(x - Utils.caseWidth+1, y+1))
//                return DIRECTION.LEFT;
//            else if(monster.collider.isPossible(x + Utils.caseWidth+1, y+1))
//                return DIRECTION.RIGHT;
//            else
//                return DIRECTION.UP;
//        }
//        else if(currentWay == DIRECTION.LEFT && !monster.collider.isPossible(x - Utils.caseWidth+1, y+1)){
//            if(monster.collider.isPossible(x+1, y - Utils.caseHeight+1))
//                return DIRECTION.UP;
//            else if(monster.collider.isPossible(x+1, y + Utils.caseHeight+1))
//                return DIRECTION.DOWN;
//            else
//                return DIRECTION.RIGHT;
//        }
//        else if(currentWay == DIRECTION.RIGHT && !monster.collider.isPossible(x + Utils.caseWidth+1, y+1)){
//            if(monster.collider.isPossible(x+1, y - Utils.caseHeight+1))
//                return DIRECTION.UP;
//            else if(monster.collider.isPossible(x+1, y + Utils.caseHeight+1))
//                return DIRECTION.DOWN;
//            else
//                return DIRECTION.LEFT;
//        }
        
        if(-Math.PI/4 < cos && cos < Math.PI/4) {
            if(sin < 0){
                return DIRECTION.UP;                                                    // Haut
            }
            else if(sin > 0){
                return DIRECTION.DOWN;                                                  // Bas
            }

        }
        else if(-Math.PI/4 < sin && sin < Math.PI/4){
            if(cos < 0) {
                return DIRECTION.LEFT;                                                  // Gauche
            }
            else if(cos > 0) {
                return DIRECTION.RIGHT;                                                 // Droite
            }
        }
        else if(cos >= Math.PI/4){
            if(sin >= Math.PI/4) {
                if (sin > cos) {
                    return DIRECTION.DOWN;                                              // Bas
                }
                else {
                    return DIRECTION.RIGHT;                                             // Droite
                }
            }
            else if(sin <= -Math.PI/4){
                sin = -sin;
                if (sin > cos) {
                    return DIRECTION.UP;                                                // Haut
                }
                else {
                    return DIRECTION.RIGHT;                                             // Droite
                }
            }
        }
        else if(cos <= -Math.PI/4){
            cos = -cos;
            if(sin >= Math.PI/4){
                if(sin > cos) {
                    return DIRECTION.DOWN;                                              // Bas
                }
                else {
                    return DIRECTION.LEFT;                                              // Gauche
                }
            }
            else if(sin <= -Math.PI/4){
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
