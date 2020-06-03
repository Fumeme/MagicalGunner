package MagicalMod.cards.statuses;

import MagicalMod.cards.AbstractCorrCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import MagicalMod.MagicalBase;
import MagicalMod.powers.Inefficiency;
import MagicalMod.powers.Mana;

public class Overexertion extends AbstractCorrCard {

	/*
	 * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
	 * 
	 * Strike Deal 7(9) damage.
	 */

	// TEXT DECLARATION

	public static final String ID = MagicalBase.makeID("Overexertion");
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String IMG = MagicalBase.makePath(MagicalBase.InfernalForm);

	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;

	// /TEXT DECLARATION/

	// STAT DECLARATION

	private static final CardRarity RARITY = CardRarity.SPECIAL;
	private static final CardTarget TARGET = CardTarget.NONE;
	private static final CardType TYPE = CardType.STATUS;
	public static final CardColor COLOR = AbstractCard.CardColor.COLORLESS;

	// /STAT DECLARATION/

	public Overexertion() {
		super(ID, NAME, IMG, -2, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
	}

	/*    */ public void triggerWhenDrawn()
	/*    */ {
		/* 41 */ flash();
		/*    */ AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player,
				AbstractDungeon.player, new Mana(AbstractDungeon.player, AbstractDungeon.player, 1), 1));

		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
				new Inefficiency(AbstractDungeon.player, AbstractDungeon.player, 2), 2));

		AbstractDungeon.actionManager.addToBottom(
				new DamageAction(AbstractDungeon.player, new DamageInfo(AbstractDungeon.player, 2, DamageType.THORNS),
						AbstractGameAction.AttackEffect.BLUNT_HEAVY));

		/* 61 */ if ((AbstractDungeon.player.hasPower("Evolve")) && (!AbstractDungeon.player.hasPower("No Draw")))
		/*    */ {
			/* 63 */ AbstractDungeon.player.getPower("Evolve").flash();
			/* 64 */ AbstractDungeon.actionManager.addToBottom(
					new DrawCardAction(AbstractDungeon.player, AbstractDungeon.player.getPower("Evolve").amount));
			/*    */ }
		/*    */ }

	// Actions the card should do.
	@Override
	/*    */ public void use(AbstractPlayer p, AbstractMonster m)
	/*    */ {
		/* 55 */ if (p.hasRelic("Medical Kit")) {
			/* 56 */ useMedicalKit(p);
			/*    */ } else {
			/* 58 */ AbstractDungeon.actionManager.addToBottom(new UseCardAction(this));
			/*    */ }
		/*    */ }

	// Which card to return when making a copy of this card.
	@Override
	public AbstractCard makeCopy() {
		return new Overexertion();
	}

	@Override
	public void upgrade() {
	}

}