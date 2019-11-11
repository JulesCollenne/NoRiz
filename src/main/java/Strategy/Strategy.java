package Strategy;

import Utils.DIRECTION;

public interface Strategy {

    DIRECTION nextWay(DIRECTION currentWay);
}

