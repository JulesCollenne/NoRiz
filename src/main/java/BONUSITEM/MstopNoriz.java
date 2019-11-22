package BONUSITEM;

import Entity.Monster;
import Entity.Player;
import Utils.TYPEBONUS;

public class MstopNoriz implements CollectableItem{

    int x, y;

    TYPEBONUS type = TYPEBONUS.MALUSFREEZE;

    public MstopNoriz(){

    }

    @Override
    public void effect(Player p) {
        p.frozen = 500;
    }

    @Override
    public void effect(Monster[] monsters) {

    }
}
