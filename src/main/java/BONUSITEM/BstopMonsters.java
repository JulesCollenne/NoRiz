package BONUSITEM;

import Entity.Monster;
import Entity.Player;

/**
 * This bonus freeze the enemies
 */
public class BstopMonsters implements CollectableItem {

    public BstopMonsters() {
    }

    @Override
    public void effect(Player p, Monster[] monsters) {
        for (Monster monster : monsters) {
            monster.frozen = 500;
        }
    }
}
