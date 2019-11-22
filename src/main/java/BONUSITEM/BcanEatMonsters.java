package BONUSITEM;

import Entity.Monster;
import Entity.Player;
import Utils.TYPEBONUS;

/**
 * This bonus allow Nori to eat the enemies
 */
public class BcanEatMonsters implements CollectableItem {

    int x, y;

    TYPEBONUS type = TYPEBONUS.BONUSEAT;

    public BcanEatMonsters(){
    }

    @Override
    public void effect(Player p) {
        p.setInvulnerable(500);
    }

    @Override
    public void effect(Monster[] monsters) {
        //System.out.println("FDFDFDF");
    }
}
