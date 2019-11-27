package BONUSITEM;

import Entity.Monster;
import Entity.Player;
import Utils.TypeEffectBonus;

public interface CollectableItem {

    void effect(Player player);
    void effect(Monster[] monsters);
    TypeEffectBonus getTypeEffectBonus();
}
