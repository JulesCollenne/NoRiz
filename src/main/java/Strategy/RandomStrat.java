package Strategy;
/**
 * Choisit une direction au hasard
 */
public class RandomStrat implements Strategy{
    public int nextWay() {
        int facing = (int) (Math.random() * 4);
        return facing;
    }
}
