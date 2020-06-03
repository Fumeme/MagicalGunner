/*    */ package MagicalMod.powers;

import MagicalMod.MagicalBase;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.rooms.AbstractRoom;

/*    */ public class ManaGeneration extends AbstractPower
/*    */ {
	public static final String POWER_ID = MagicalBase.makeID("ManaGeneration");
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	/*    */
	/*    */ public ManaGeneration(AbstractCreature owner, AbstractCreature source, int DecayAmt)
	/*    */ {
		/* 23 */ this.name = NAME;
		/* 25 */ this.owner = owner;
		/* 27 */ this.amount = DecayAmt;
		canGoNegative = false;
		this.ID = POWER_ID;
		this.updateDescription();
		/* 34 */ this.loadRegion("focus");
		/* 35 */ this.type = AbstractPower.PowerType.BUFF;
		/*    */
		/* 37 */ this.isTurnBased = true;
		this.priority = 10;
		/*    */ }


	/*    */ @Override
	/*    */ public void stackPower(int stackAmount)
	/*    */ {
		/* 56 */ super.stackPower(stackAmount);
		/*    */
		/* 58 */
		/* 59 */ if (this.amount <= 0) {
			AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
		}
		/*    */ }

	/*    */ @Override
	/*    */ public void atEndOfTurn(boolean isPlayer)
	/*    */ {
		/* 65 */ if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT)
		{flashWithoutSound();
		
			if(this.amount>0) {

				AbstractDungeon.actionManager.addToBottom(
						new ApplyPowerAction(this.owner, this.owner, new Mana(this.owner, this.owner, 1), 1));
				AbstractDungeon.actionManager.addToBottom(
						new ApplyPowerAction(this.owner, this.owner, new ManaGeneration(this.owner, this.owner, -1), -1));
			}else {
				
				AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
			}
			}
			/*    */ }
    @Override
    public void updateDescription() {
    	this.description = DESCRIPTIONS[0];
    }
		/*    */ }

/*
 * Location: C:\Program Files
 * (x86)\Steam\steamapps\common\SlayTheSpire\desktop-1.0.jar!\com\megacrit\
 * cardcrawl\powers\PoisonPower.class Java compiler version: 8 (52.0) JD-Core
 * Version: 0.7.1
 */