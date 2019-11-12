package Strategy;

import Utils.DIRECTION;

/**
 * Calcule l'angle entre le monstre et le bonus le plus proche, choisit le chemin possible le plus proche de l'angle.
 */
public class BonusStrat implements Strategy{

    public DIRECTION nextWay() {
        return DIRECTION.STOP;
    }
}

