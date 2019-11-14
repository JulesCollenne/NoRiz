package Strategy;


import Entity.Monster;
import Entity.Player;
import States.GameStateManager;
import Utils.DIRECTION;

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

    private Player player;

    public AngleStrat(Player player){
        this.player = player;
    }

    public DIRECTION nextWay(Monster monster) {

        int x = player.getX() - monster.getX();
        int y = player.getY() - monster.getY();

        double angle = Math.atan2(y,x);
        double cos,sin;
        cos = Math.cos(angle);
        sin = Math.sin(angle);

        if(-0.5 < cos && cos < 0.5) {
            if (sin < 0)
                return DIRECTION.UP;                                         // Bas
            else if(sin > 0)
                return DIRECTION.DOWN;                                                 // Haut
        }
        else if(-0.5 < sin && sin < 0.5){
            if(cos < 0)
                return DIRECTION.LEFT;                                                 // Gauche
            else if(cos > 0)
                return DIRECTION.RIGHT;                                                 // Droite
        }
        else if(cos >= 0.5){
            if(sin >= 0.5) {
                if (sin > cos)
                    return DIRECTION.DOWN;                                                 // Haut
                else
                    return DIRECTION.RIGHT;                                                 // Droite
            }
            else if(sin <= -0.5){
                sin = -sin;
                if (sin > cos)
                    return DIRECTION.UP;                                                 // Bas
                else
                    return DIRECTION.RIGHT;                                                 // Droite
            }
        }
        else if(cos <= -0.5){
            cos = -cos;
            if(sin >= 0.5){
                if(sin > cos)
                    return DIRECTION.DOWN;                                                 // Haut
                else
                    return DIRECTION.LEFT;                                                 // Gauche
            }
            else if(sin <= -0.5){
                sin = -sin;
                if(sin > cos)
                    return DIRECTION.UP;                                                 // Bas
                else
                    return DIRECTION.LEFT;                                                 // Gauche
            }
        }
        return DIRECTION.STOP;
    }
}

