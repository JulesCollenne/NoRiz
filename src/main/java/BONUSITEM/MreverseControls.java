package BONUSITEM;

import Entity.Monster;
import Entity.Player;
import Utils.TypeEffectBonus;

public class MreverseControls implements CollectableItem {

    TypeEffectBonus typeEffectBonus;

    public MreverseControls( TypeEffectBonus typeEffectBonus){
        this.typeEffectBonus = typeEffectBonus;
    }

    @Override
    public void effect(Player player) {
        player.setReversed(500);
    }

    @Override
    public void effect(Monster[] monsters) {

    }

    @Override
    public TypeEffectBonus getTypeEffectBonus() {
        return typeEffectBonus;
    }
}
