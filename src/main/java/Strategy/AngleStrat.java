package Strategy;


import States.GameStateManager;

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

    private final int DOWN = 0;
    private final int UP = 1;
    private final int LEFT = 2;
    private final int RIGHT = 3;

    private GameStateManager gsm;
    private int x,y;

    public AngleStrat(GameStateManager gsm){
        this.gsm = gsm;
    }

    public int nextWay() {
        int facing = -1;
        y = gsm.player.getY() - gsm.monsters[0].getY();
        x = gsm.player.getX() - gsm.monsters[0].getX();

        double angle = Math.atan2(y,x);
        double cos,sin;
        cos = Math.cos(angle);
        sin = Math.sin(angle);

        if(-0.5 < cos && cos < 0.5) {
            if (sin < 0)
                return UP;                                                 // Bas
            else if(sin > 0)
                return DOWN;                                                 // Haut
        }
        else if(-0.5 < sin && sin < 0.5){
            if(cos < 0)
                return LEFT;                                                 // Gauche
            else if(cos > 0)
                return RIGHT;                                                 // Droite
        }
        else if(cos >= 0.5){
            if(sin >= 0.5) {
                if (sin > cos)
                    return DOWN;                                                 // Haut
                else
                    return RIGHT;                                                 // Droite
            }
            else if(sin <= -0.5){
                sin = -sin;
                if (sin > cos)
                    return UP;                                                 // Bas
                else
                    return RIGHT;                                                 // Droite
            }
        }
        else if(cos <= -0.5){
            cos = -cos;
            if(sin >= 0.5){
                if(sin > cos)
                    return DOWN;                                                 // Haut
                else
                    return LEFT;                                                 // Gauche
            }
            else if(sin <= -0.5){
                sin = -sin;
                if(sin > cos)
                    return UP;                                                 // Bas
                else
                    return LEFT;                                                 // Gauche
            }
        }
        return facing;
    }
}
