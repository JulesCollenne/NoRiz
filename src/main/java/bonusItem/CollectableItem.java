package bonusItem;

import entity.Monster;
import entity.Player;
import utils.TypeEffectBonus;

public interface CollectableItem {

    void effect(Player player);
    void effect(Monster[] monsters);
    void effect(Player player, Monster[] monsters);
    TypeEffectBonus getTypeEffectBonus();
}
