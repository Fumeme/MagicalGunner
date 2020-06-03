/*    */ package MagicalMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */
/*    */
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
/*    */
/*    */ import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import MagicalMod.powers.Mana;

/*    */
/*    */ public class FocusAction extends AbstractGameAction
/*    */ {
	/* 14 */ private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("RecycleAction");
	/* 15 */ public static final String[] TEXT = uiStrings.TEXT;
	/*    */ private AbstractPlayer p;

	/*    */
	/*    */ public FocusAction(int amount) {
		/* 19 */ this.actionType = com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType.CARD_MANIPULATION;
		/* 20 */ this.p = AbstractDungeon.player;
		/* 21 */ this.duration = Settings.ACTION_DUR_FAST;
		this.amount = amount;
		/*    */ }

	/*    */
	/*    */ public void update()
	/*    */ {
		/* 26 */ if (this.duration == Settings.ACTION_DUR_FAST) {
			/* 27 */ if (this.p.hand.isEmpty()) {
				/* 28 */ this.isDone = true;
				/* 29 */ return;
			}
			/* 30 */ if (this.p.hand.size() == 1) {
				/* 31 */ if (this.p.hand.getBottomCard().costForTurn == -1) {

					AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p,
							new Mana(p, p, EnergyPanel.getCurrentEnergy()), EnergyPanel.getCurrentEnergy()));

					/* 33 */ } else if (this.p.hand.getBottomCard().costForTurn > 0) {

					AbstractDungeon.actionManager.addToTop(
							new ApplyPowerAction(p, p, new Mana(p, p, (this.p.hand.getBottomCard().costForTurn)),
									(this.p.hand.getBottomCard().costForTurn)));
					/*    */ }
				/* 36 */ this.p.hand.moveToExhaustPile(this.p.hand.getBottomCard());
				/* 37 */ tickDuration();
				/* 38 */ return;
				/*    */ }
			if (this.amount > 1) {
				AbstractDungeon.handCardSelectScreen.open(TEXT[0],2, true, true, false,false, true);
			} else {

				AbstractDungeon.handCardSelectScreen.open(TEXT[0],1, true, true, false,false, true);
			}
			/* 41 */ tickDuration();
			/* 42 */ return;
			/*    */ }
		/*    */
		/*    */
		/*    */
		/* 47 */ if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
			/* 48 */ for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
				/* 49 */ if (c.costForTurn == -1) {

					AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p,
							new Mana(p, p, EnergyPanel.getCurrentEnergy()), EnergyPanel.getCurrentEnergy()));
					/* 51 */ } else if (c.costForTurn > 0) {

					AbstractDungeon.actionManager
							.addToTop(new ApplyPowerAction(p, p, new Mana(p, p, c.costForTurn), c.costForTurn));
					/*    */ }
				/* 54 */ this.p.hand.moveToExhaustPile(c);
				/*    */ }
			/* 56 */ AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
			/* 57 */ AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
			/*    */ }
		/*    */
		/* 60 */ tickDuration();
		/*    */ }
	/*    */ }

/*
 * Location: C:\Program Files
 * (x86)\Steam\steamapps\common\SlayTheSpire\desktop-1.0.jar!\com\megacrit\
 * cardcrawl\actions\defect\RecycleAction.class Java compiler version: 8 (52.0)
 * JD-Core Version: 0.7.1
 */