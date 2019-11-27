package BONUSITEM;

import Entity.Monster;
import Entity.Player;

public interface CollectableItem {

    void effect(Player p, Monster[] monsters);

}
