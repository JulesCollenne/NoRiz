package Strategy;

/**
 * Calcule l'angle entre le monstre et le bonus le plus proche, choisit le chemin possible le plus proche de l'angle.
 */
public class BonusStrat implements Strategy{

    private final int DOWN = 0;
    private final int UP = 1;
    private final int LEFT = 2;
    private final int RIGHT = 3;

    public int nextWay() {
        return 0;
    }
}
