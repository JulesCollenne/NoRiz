package BONUSITEM;

import Entity.Player;
import Utils.TYPEBONUS;

public class stopMonsters implements BonusItem {

    int x, y;
    TYPEBONUS type =  TYPEBONUS.BONUS;

    public stopMonsters(int x, int y) {
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
