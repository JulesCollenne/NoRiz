package BONUSITEM;

import Entity.Monster;
import Entity.Player;

/**
 * This bonus allow Nori to eat the enemies
 */
public class BcanEatMonsters implements CollectableItem {

    public BcanEatMonsters(){
    }

    @Override
    public void effect(Player p,Monster[] monsters ) {
        p.setInvulnerable(500);
    }

}
