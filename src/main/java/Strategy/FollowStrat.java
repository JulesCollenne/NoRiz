package Strategy;

import Entity.Monster;
import Utils.DIRECTION;

/**
 * Suit le joueur à la trace
 */

public class FollowStrat implements Strategy {

    public DIRECTION nextWay(Monster monster) {
        return DIRECTION.STOP;
    }
}

