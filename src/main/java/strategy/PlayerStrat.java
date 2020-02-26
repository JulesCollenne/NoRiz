package strategy;

import entity.Monster;
import utils.DIRECTION;

public class PlayerStrat implements Strategy {
    @Override
    public DIRECTION nextWay(Monster monster) {
        return monster.getNextFacing();
    }
}
