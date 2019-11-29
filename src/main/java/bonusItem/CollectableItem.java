package bonusItem;

import entity.Monster;
import entity.Player;
import utils.TypeEffectBonus;

public interface CollectableItem {

    void effect(Player player);
    void effect(Monster[] monsters);
    TypeEffectBonus getTypeEffectBonus();
}
