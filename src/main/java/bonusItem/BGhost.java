package bonusItem;

import entity.Monster;
import entity.Noriz;
import utils.TypeEffectBonus;

public class BGhost implements CollectableItem {

    TypeEffectBonus typeEffectBonus;

    public BGhost(TypeEffectBonus typeEffectBonus) {
        this.typeEffectBonus = typeEffectBonus;
    }

    @Override
    public void effect(Noriz noriz) {
        noriz.setTimeGhosted(500);
    }

    @Override
    public void effect(Monster[] monsters) {

    }

    @Override
    public void effect(Noriz noriz, Monster[] monsters) {

    }

    @Override
    public TypeEffectBonus getTypeEffectBonus() {
        return typeEffectBonus;
    }
}
