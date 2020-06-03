package MagicalMod.powers;

import MagicalMod.MagicalBase;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.unique.PoisonLoseHpAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;

//Gain 1 dex for the turn for each card played.

public class InfernalFormPower extends AbstractPower {
    public AbstractCreature source;

    public static final String POWER_ID = MagicalBase.makeID("InfernalFormPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public InfernalFormPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
        this.loadRegion("focus");
        this.source = source;

    }

    
     public float atDamageReceive(float damage, DamageInfo.DamageType type)
      {
  
    return damage;
  }

    // At the end of the turn, Remove gained dexterity.
     @Override
     public void atStartOfTurn() {
    	 
    	 if (owner.hasPower(PoisonPower.POWER_ID)) {
    		 
    		 for (short i = 0; i< this.amount; i++) {
    		 
    		 AbstractDungeon.actionManager.addToBottom(new PoisonLoseHpAction(this.owner, this.owner, this.owner.getPower(PoisonPower.POWER_ID).amount, com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect.POISON));
    		 }
    		 }
     }
     
     
    @Override
    public void atEndOfTurn(final boolean isPlayer) {
    	
  
    	if(owner.hasPower(Decay.POWER_ID)) {
    		
    		
    		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner,
                    new Mana(owner, owner, this.amount), this.amount));
    			
    		
    		
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
        
    // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
    @Override
    public void updateDescription() {
    	this.description = DESCRIPTIONS[0];
    }

}
