package strategy;

import entity.Monster;
import utils.DIRECTION;

public interface Strategy {

    DIRECTION nextWay(Monster monster);
}

