package BONUSITEM;

import Entity.Player;
import Utils.TYPEBONUS;

public class BstopMonsters implements BonusItem {

    int x, y;
    TYPEBONUS type =  TYPEBONUS.BONUSFREEZE;

    public BstopMonsters(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void effect(Player p) {

    }

    @Override
    public void effect() {

    }
}
