package Strategy;

import Entity.Monster;
import Utils.DIRECTION;

public interface Strategy {

    DIRECTION nextWay(Monster monster);
}

