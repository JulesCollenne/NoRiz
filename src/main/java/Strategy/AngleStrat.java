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
 *
 */
public class AngleStrat implements Strategy {

    GameStateManager gsm;
    private int x,y;

    public AngleStrat(GameStateManager gsm){
        this.gsm = gsm;
    }

    public int nextWay() {
        int facing = -1;
        y = gsm.player.getY() - gsm.monsters[0].getY();
        x = gsm.player.getX() - gsm.monsters[0].getX();

        //System.out.println("X : " + x + " Y : " + y);

        double angle = Math.atan2(y,x);
        double cos,sin;
        cos = Math.cos(angle);
        sin = Math.sin(angle);

        if(-0.5 < cos && cos < 0.5) {
            if (sin < 0)
                return 0;                                                 // Bas
            else if(sin > 0)
                return 1;                                                 // Haut
        }
        else if(-0.5 < sin && sin < 0.5){
            if(cos < 0)
                return 2;                                                 // Gauche
            else if(cos > 0)
                return 3;                                                 // Droite
        }
        else if(cos >= 0.5){
            if(sin >= 0.5) {
                if (sin > cos)
                    return 1;                                                 // Haut
                else
                    return 3;                                                 // Droite
            }
            else if(sin <= -0.5){
                sin = -sin;
                if (sin > cos)
                    return 0;                                                 // Bas
                else
                    return 3;                                                 // Droite
            }
        }
        else if(cos <= -0.5){
            cos = -cos;
            if(sin >= 0.5){
                if(sin > cos)
                    return 1;                                                 // Haut
                else
                    return 2;                                                 // Gauche
            }
            else if(sin <= -0.5){
                sin = -sin;
                if(sin > cos)
                    return 0;                                                 // Bas
                else
                    return 2;                                                 // Gauche
            }
        }
        return facing;
    }
}
