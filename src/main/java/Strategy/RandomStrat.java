package Strategy;
/**
 * Choisit une direction au hasard
 */
public class RandomStrat implements Strategy{
    public int nextWay() {
        return (int) (Math.random() * 4);
    }
}
