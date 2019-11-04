package Strategy;

/**
 * NOTE : TODO : pour l'instant il y a que anglestrat, il faut faire toutes les strats
 *
 * Calcule l'angle entre le monstre et le joueur, choisi le chemin possible le plus proche de l'angle.
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
    public int nextWay() {
        return 0;
    }
}
