package bonusItem;

import entity.Monster;
import entity.Noriz;
import utils.TypeEffectBonus;

public interface CollectableItem {

    void effect(Noriz noriz);
    void effect(Monster[] monsters);
    void effect(Noriz noriz, Monster[] monsters);
    TypeEffectBonus getTypeEffectBonus();
}
