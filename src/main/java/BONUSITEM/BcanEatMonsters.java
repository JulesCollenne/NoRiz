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
    public void effect(Player p,Monster[] monsters ) {
        p.setInvulnerable(500);
    }

}
