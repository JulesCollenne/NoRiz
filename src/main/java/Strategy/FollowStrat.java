package Strategy;

/**
 * Suit le joueur à la trace
 */

public class FollowStrat implements Strategy {

    private final int DOWN = 0;
    private final int UP = 1;
    private final int LEFT = 2;
    private final int RIGHT = 3;

    public int nextWay() {
        return 0;
    }
}
