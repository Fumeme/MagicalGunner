package MagicalMod.powers;


import MagicalMod.MagicalBase;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.*;

//Gain 1 dex for the turn for each card played.

public class ShortTermPower extends AbstractPower {
    public AbstractCreature source;

    public static final String POWER_ID = MagicalBase.makeID("ShortTermPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public ShortTermPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.type = PowerType.DEBUFF;
        this.isTurnBased = true;
        this.loadRegion("flex");
        this.source = source;

    }


    // At the start of the turn, Remove this debuff.
    @Override
    public void atStartOfTurn() {

    	
    	flash();
    	AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
    	
    }
    @Override
    public void atEndOfTurn(boolean isPlayer)
     {
    	if(owner.hasPower(Mana.POWER_ID)) {
    	flash();
    	
    	if(magic(this.amount)) {
    		AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.ApplyPowerAction(this.owner, this.owner, new Mana(this.owner, this.owner, -this.amount), -this.amount));
    	}else {
    		
    		AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.ApplyPowerAction(this.owner, this.owner, new Mana(this.owner, this.owner, -(AbstractDungeon.player.getPower(Mana.POWER_ID).amount)), -(AbstractDungeon.player.getPower(Mana.POWER_ID).amount)));
    	}
    	}
 
  }
    
    boolean magic (int min) {
    	if (AbstractDungeon.player.hasPower(Mana.POWER_ID)) {

    		 return AbstractDungeon.player.getPower(Mana.POWER_ID).amount >= min;
    		
    	}
    	return false;
    }
    // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
    @Override
    public void updateDescription() {
    	this.description = DESCRIPTIONS[0] + this.amount;
    }

}
