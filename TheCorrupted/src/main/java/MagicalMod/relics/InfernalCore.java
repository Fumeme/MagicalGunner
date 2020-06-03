package MagicalMod.relics;

import MagicalMod.powers.ManaGeneration;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import MagicalMod.MagicalBase;
import basemod.abstracts.CustomRelic;

public class InfernalCore extends CustomRelic {
    
    /*
     * https://github.com/daviscook477/BaseMod/wiki/Custom-Relics
     * 
     * Gain 1 energy.
     */

    // ID, images, text.
    public static final String ID = MagicalBase.makeID("InfernalCore");
    public static final String IMG = MagicalBase.makePath(MagicalBase.PLACEHOLDER_RELIC);
    public static final String OUTLINE = MagicalBase.makePath(MagicalBase.PLACEHOLDER_RELIC_OUTLINE);

    public InfernalCore() {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.STARTER, LandingSound.MAGICAL);
    }
    
@Override
    public void atBattleStart() {
    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ManaGeneration(AbstractDungeon.player, AbstractDungeon.player, 2), 1));

}


    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    // Which relic to return on making a copy of this relic.
    @Override
    public AbstractRelic makeCopy() {
        return new InfernalCore();
    }
}
