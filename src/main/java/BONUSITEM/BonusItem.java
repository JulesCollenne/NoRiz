package BONUSITEM;

import Entity.Monster;
import Entity.Player;

public interface BonusItem {

    public void effect(Player p);
    public void effect(Monster[] monsters);

}
