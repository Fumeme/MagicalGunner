package MagicalMod.powers;

import MagicalMod.MagicalBase;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.*;

//Gain 1 dex for the turn for each card played.

public class LongTermDebuff extends AbstractPower implements NonStackablePower {
    public AbstractCreature source;

    public static final String POWER_ID = MagicalBase.makeID("LongTermDebuff");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
int currM;
	

    public LongTermDebuff(final AbstractCreature owner, final AbstractCreature source, final int amount, final int M) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
        this.loadRegion("flex");
        this.source = source;
        this.currM = M;
    }

    @Override
    public void atStartOfTurn() {

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction( owner, owner , new LongTermDebuff(owner, owner, -1, currM)));

        if(this.amount <1) {

            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));

            AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(owner, owner,
                    new LongTermBuff(owner, owner, 3, this.currM ), 3));
    	}else {
    	
    	AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner,
                new Mana(owner, owner, -(this.currM)/2), -(this.currM/2)));
    	}
    }
    // At the end of the turn, Remove gained dexterity.
    @Override
    public void atEndOfTurn(final boolean isPlayer) {
    	


    }
    @Override
    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
        if (this.amount <= 0) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));

            AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(owner, owner,
                    new LongTermBuff(owner, owner, 3, this.currM ), 3));

        }
        }
    // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
    @Override
    public void updateDescription() {
    	this.description = DESCRIPTIONS[0];
    }

}
