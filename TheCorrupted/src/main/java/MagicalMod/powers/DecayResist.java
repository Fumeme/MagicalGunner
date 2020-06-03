package MagicalMod.powers;

import MagicalMod.MagicalBase;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

//Gain 1 dex for the turn for each card played.

public class DecayResist extends TwoAmountPower {
    public AbstractCreature source;

    public static final String POWER_ID = MagicalBase.makeID("DecayResist");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


    public DecayResist(final AbstractCreature owner, final AbstractCreature source, final int turns, final int resist) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = turns;
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
        this.loadRegion("brutality");
        this.source = source;
        canGoNegative = false;
        this.updateDescription();
        this.priority = 11;
        this.amount2 = resist;

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
        this.description = DESCRIPTIONS[0] + this.amount2 + DESCRIPTIONS[1] + this.amount;
        if (this.amount > 1){
            this.description += DESCRIPTIONS[3];
        }else {
            this.description += DESCRIPTIONS[2];
        }
    }
}
