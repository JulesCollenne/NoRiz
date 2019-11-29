package bonusItem;

import entity.Monster;
import entity.Player;
import utils.TypeEffectBonus;

/**
 * This bonus freeze the enemies
 */
public class BstopMonsters implements CollectableItem {

    TypeEffectBonus typeEffectBonus;

    public BstopMonsters( TypeEffectBonus typeEffectBonus) {
        this.typeEffectBonus = typeEffectBonus;
    }

    @Override
    public void effect(Player player) {

    }

    @Override
    public void effect(Monster[] monsters) {
        for (Monster monster : monsters) {
            monster.frozen = 500;
        }
    }

    @Override
    public TypeEffectBonus getTypeEffectBonus() {
        return typeEffectBonus;
    }
}
