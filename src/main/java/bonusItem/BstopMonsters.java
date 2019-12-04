package bonusItem;

import entity.Monster;
import entity.Noriz;
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
    public void effect(Noriz noriz) {

    }

    @Override
    public void effect(Monster[] monsters) {
        for (Monster monster : monsters) {
            monster.frozen = 500;
        }
    }

    @Override
    public void effect(Noriz noriz, Monster[] monsters) {

    }

    @Override
    public TypeEffectBonus getTypeEffectBonus() {
        return typeEffectBonus;
    }
}
