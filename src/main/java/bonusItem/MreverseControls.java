package bonusItem;

import entity.Monster;
import entity.Noriz;
import utils.TypeEffectBonus;

public class MreverseControls implements CollectableItem {

    TypeEffectBonus typeEffectBonus;

    public MreverseControls( TypeEffectBonus typeEffectBonus){
        this.typeEffectBonus = typeEffectBonus;
    }

    @Override
    public void effect(Noriz noriz) {
        noriz.setReversed(500);
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
