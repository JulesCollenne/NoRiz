package BONUSITEM;

import Entity.Monster;
import Entity.Player;
import Utils.TYPEBONUS;

/**
 * This malus freeze Nori for some time
 */
public class MstopNoriz implements CollectableItem{

    int x, y;

    TYPEBONUS type = TYPEBONUS.MALUSFREEZE;

    public MstopNoriz(){

    }

    @Override
    public void effect(Player p) {
        p.frozen = 300;
    }

    @Override
    public void effect(Monster[] monsters) {

    }
}