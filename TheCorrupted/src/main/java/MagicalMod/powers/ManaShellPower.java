package MagicalMod.powers;

import MagicalMod.MagicalBase;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

//Gain 1 dex for the turn for each card played.

public class ManaShellPower extends TwoAmountPower{
	public AbstractCreature source;

	public static final String POWER_ID = MagicalBase.makeID("ManaShellPower");
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

	public ManaShellPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		this.type = PowerType.BUFF;
		this.isTurnBased = false;
		this.loadRegion("dexterity");
		this.source = source;
		canGoNegative = false;
		this.amount2 = 0;
		this.updateDescription();

	}

	@Override
	public void atStartOfTurnPostDraw() {
		this.updateDescription();
	}
	
	@Override
	public void atEndOfTurn(boolean isPlayer) {
		int block = 0;
		if (this.owner.hasPower(Mana.POWER_ID) && this.owner.getPower(Mana.POWER_ID).amount>0) {

			block = MathUtils.round((float)this.amount * (float)this.owner.getPower(Mana.POWER_ID).amount / 3.0f);

			System.out.println(block + " block is given by mana shell.");
			AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this.owner, this.owner, block));
		}
		this.updateDescription();
	}

	@Override
	public void stackPower(int stackAmount) {
		this.fontScale = 8.0F;
		this.amount += stackAmount;
		if (this.amount <= 0) {
			AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
		}
		this.updateDescription();
	}

	
	@Override
	public void updateDescription() {

		if (this.owner.hasPower(Mana.POWER_ID)) {
			int block = MathUtils.round((float)this.amount * (float)this.owner.getPower(Mana.POWER_ID).amount / 3.0f);

			this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + block + DESCRIPTIONS[2];
			
			this.amount2 = block;
			System.out.println(block + " is calculated for description.");
		} else {

			this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + 0 + DESCRIPTIONS[2];
			
			this.amount2 = 0;
		}
	}

	@Override
	public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
		
		this.updateDescription();
	}
	/*
   public void update(int slot)
	   {	this.updateDescription();  }
   */
   @Override
   public void onAfterUseCard(AbstractCard card, UseCardAction action) {  	this.updateDescription();  }
   @Override
   public void onAfterCardPlayed(AbstractCard usedCard) {	this.updateDescription();  }
   @Override
   public void onUseCard(AbstractCard card, UseCardAction action) {this.updateDescription();}
   @Override
   public void onPlayCard(AbstractCard card, AbstractMonster m) {this.updateDescription();}

}
