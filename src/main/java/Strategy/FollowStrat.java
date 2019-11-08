package Strategy;

import Utils.DIRECTION;

/**
 * Suit le joueur à la trace
 */

public class FollowStrat implements Strategy {

    public DIRECTION nextWay() {
        return DIRECTION.STOP;
    }
}
