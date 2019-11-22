package BONUSITEM;

import Entity.Monster;
import Entity.Player;
import Utils.TYPEBONUS;

/**
 * This bonus freeze the enemies
 */
public class BstopMonsters implements CollectableItem {

    int x, y;
    TYPEBONUS type =  TYPEBONUS.BONUSFREEZE;

    public BstopMonsters() {
    }

    @Override
    public void effect(Player p) {

    }

    @Override
    public void effect(Monster[] monsters) {
        for(int i =0; i< monsters.length; i++){
            monsters[i].frozen = 500;
        }
    }
}
