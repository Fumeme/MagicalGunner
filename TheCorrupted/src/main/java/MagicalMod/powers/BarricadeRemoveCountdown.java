package MagicalMod.powers;

import MagicalMod.MagicalBase;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.red.Barricade;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.BarricadePower;

//Gain 1 dex for the turn for each card played.

public class BarricadeRemoveCountdown extends AbstractPower{
    public AbstractCreature source;

    public static final String POWER_ID = MagicalBase.makeID("tempBarricade");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


    public BarricadeRemoveCountdown(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
        this.loadRegion("Barricade");
        this.source = source;
        canGoNegative = false;

    }

@Override
public void onInitialApplication(){

    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner,
            new BarricadePower(this.owner)));
}
    
    @Override
    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
        if (this.amount <= 0) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, Barricade.ID));
        }
        } 
        
    // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
    @Override
    public void updateDescription() {
    	if(amount > 1){ this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];}else{
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        this.updateDescription();

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner,
                new BarricadeRemoveCountdown(this.owner, this.owner, -1), -1));

    }
}
