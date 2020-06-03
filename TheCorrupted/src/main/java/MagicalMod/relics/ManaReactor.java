package MagicalMod.relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import MagicalMod.MagicalBase;
import MagicalMod.powers.Mana;
import basemod.abstracts.CustomRelic;

public class ManaReactor extends CustomRelic {
    
    /*
     * https://github.com/daviscook477/BaseMod/wiki/Custom-Relics
     * 
     * Gain 1 energy.
     */

    // ID, images, text.
    public static final String ID = MagicalBase.makeID("ManaReactor");
    public static final String IMG = MagicalBase.makePath(MagicalBase.PLACEHOLDER_RELIC);
    public static final String OUTLINE = MagicalBase.makePath(MagicalBase.PLACEHOLDER_RELIC_OUTLINE);

    public ManaReactor() {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.COMMON, LandingSound.MAGICAL);
    }

    // Flash at the start of Battle.
    @Override
    public void atBattleStartPreDraw() {
        flash();
    }

    @Override
    /*    */   public int onAttacked(DamageInfo info, int damageAmount)
    /*    */   {
    /* 24 */     if ((info.owner != null) && (info.type != DamageInfo.DamageType.HP_LOSS) && (info.type != DamageInfo.DamageType.THORNS) && (damageAmount >= 1))
    /*    */     {
    /* 26 */       flash();
    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
            new Mana(AbstractDungeon.player, AbstractDungeon.player, 1), 1));
    /*    */     }
    /* 30 */     return damageAmount;
    /*    */   }
    

    
    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    // Which relic to return on making a copy of this relic.
    @Override
    public AbstractRelic makeCopy() {
        return new ManaReactor();
    }
}
