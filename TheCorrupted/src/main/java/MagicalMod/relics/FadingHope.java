package MagicalMod.relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GenericStrengthUpPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import MagicalMod.MagicalBase;
import basemod.abstracts.CustomRelic;

public class FadingHope extends CustomRelic {
    /*
     * https://github.com/daviscook477/BaseMod/wiki/Custom-Relics
     * 
     * At the start of each combat, gain 1 strenght (i.e. Varja)
     */
    
    // ID, images, text.
    public static final String ID = MagicalBase.makeID("PlaceholderRelic2");
    public static final String IMG = MagicalBase.makePath(MagicalBase.PLACEHOLDER_RELIC_2);
    public static final String OUTLINE = MagicalBase.makePath(MagicalBase.PLACEHOLDER_RELIC_OUTLINE_2);

    public FadingHope() {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.BOSS, LandingSound.HEAVY);
    }

 // Gain 1 energy on eqip.

    @Override

    public void onEquip() {

        AbstractDungeon.player.energy.energyMaster += 2;

    }



    // Lose 1 energy on unequip.

    @Override

    public void onUnequip() {

        AbstractDungeon.player.energy.energyMaster -= 2;

    }
    
    // Gain 1 Strenght on on equip.
    @Override
    public void atBattleStartPreDraw() {
        this.flash();
        AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        
        
        for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(mo, AbstractDungeon.player, new GenericStrengthUpPower(mo, "Gaining Strength", 1), 1));
        }
        
    }
    

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    // Which relic to return on making a copy of this relic.
    @Override
    public AbstractRelic makeCopy() {
        return new FadingHope();
    }
}
