package MagicalMod.powers;

import MagicalMod.MagicalBase;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnLoseBlockPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

//Gain 1 dex for the turn for each card played.

public class witheringBarrierPower extends AbstractPower implements OnLoseBlockPower {
    public AbstractCreature source;

    public static final String POWER_ID = MagicalBase.makeID("witheringBarrierPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public witheringBarrierPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
        this.loadRegion("dexterity");
        this.source = source;
        canGoNegative = false;
        this.updateDescription();
    }


    // At the end of the turn, Remove gained dexterity.
    @Override
    public void atEndOfTurn(final boolean isPlayer) {

    	if(this.amount <= 0) {
    		AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
    	}else {
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(this.owner, this.owner, new Decay(this.owner, this.owner, 2), 2));
        }
    }
    @Override
    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
        if (this.amount <= 0) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
        }
        }
        
    @Override
    public int onLoseBlock(DamageInfo damageInfo, int i) {
    	if( damageInfo.type != DamageInfo.DamageType.THORNS) {
        if (i > owner.currentBlock){
            int d = owner.currentBlock;
            int r = this.amount * d;
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(damageInfo.owner, AbstractDungeon.player, new Decay(damageInfo.owner, this.owner, r), r, true));

        }
        else {
            int r = this.amount * i;
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(damageInfo.owner, AbstractDungeon.player, new Decay(damageInfo.owner, this.owner, r), r, true));
        }}
        return i;
     }
    
    // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
    @Override
    public void updateDescription() {
    	this.description = DESCRIPTIONS[0];
    }

}
