package bonusItem;

import entity.Monster;
import entity.Noriz;
import utils.TypeEffectBonus;

public class BSmall implements CollectableItem {

    TypeEffectBonus typeEffectBonus;

    public BSmall(TypeEffectBonus typeEffectBonus) {
        this.typeEffectBonus = typeEffectBonus;
    }

    @Override
    public void effect(Noriz noriz) {
        noriz.setIsSmall(500);
        noriz.setSize(noriz.getSize()/2);
        noriz.speed++;
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
