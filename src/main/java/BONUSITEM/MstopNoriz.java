package BONUSITEM;

import Entity.Monster;
import Entity.Player;

/**
 * This malus freeze Nori for some time
 */
public class MstopNoriz implements CollectableItem{

    public MstopNoriz(){

    }

    @Override
    public void effect(Player p, Monster[] monsters) {
        p.frozen = 300;
    }
}
