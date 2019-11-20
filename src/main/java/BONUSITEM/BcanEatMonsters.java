package BONUSITEM;

import Entity.Player;
import Utils.TYPEBONUS;

public class BcanEatMonsters implements BonusItem {

    int x, y;

    TYPEBONUS type = TYPEBONUS.BONUSEAT;

    public BcanEatMonsters(int x, int y){
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
