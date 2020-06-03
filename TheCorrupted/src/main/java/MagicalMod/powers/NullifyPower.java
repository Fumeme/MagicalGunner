package MagicalMod.powers;

import MagicalMod.MagicalBase;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.*;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
//Gain 1 dex for the turn for each card played.

public class NullifyPower extends AbstractPower implements OnReceivePowerPower
{
    public AbstractCreature source;

    public static final String POWER_ID = MagicalBase.makeID("NullifyPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public NullifyPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.type = PowerType.DEBUFF;
        this.isTurnBased = false;
        this.loadRegion("focus");
        this.source = source;

    }


    // At the end of the turn, Remove gained dexterity.
    @Override
    public boolean onReceivePower(AbstractPower power, AbstractCreature target, AbstractCreature source)
    {

        if (power.type == PowerType.BUFF) {

            flashWithoutSound();
if (owner.getPower(NullifyPower.POWER_ID).amount >0) {
	AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(owner, owner,
	        new NullifyPower(owner, owner, -1), -1));
	
}else {
            AbstractDungeon.actionManager.addToTop(new com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction(this.owner, this.owner, NullifyPower.POWER_ID));
            return false;
}
        }

        return true;
    }

    @Override
    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
        if (this.amount <= 0) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
        }
        }
        
    // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
    @Override
    public void updateDescription() {
    	this.description = DESCRIPTIONS[0];
    }

}
