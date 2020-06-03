package MagicalMod.cards.Decay;

import MagicalMod.cards.AbstractCorrCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.WeakPower;

import MagicalMod.MagicalBase;
import MagicalMod.patches.AbstractCardEnum;
import MagicalMod.powers.Decay;

public class EndlessTorment extends AbstractCorrCard {

	/*
	 * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
	 * 
	 * for each loop x2" "Apply 1 Vulnerable to all enemies, 2(3) times.
	 */

	// TEXT DECLARATION

	public static final String ID = MagicalBase.makeID("EndlessTorment");
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String IMG = MagicalBase.makePath(MagicalBase.DEFAULT_COMMON_ATTACK);

	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

	// /TEXT DECLARATION/

	// STAT DECLARATION

	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
	private static final CardType TYPE = CardType.ATTACK;
	public static final CardColor COLOR = AbstractCardEnum.MAGICAL_COLOR;

	private static final int COST = 1;

	private int AMOUNT = 3;

	// /STAT DECLARATION/

	public EndlessTorment() {
		super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
		 this.magicNumber = this.baseMagicNumber = AMOUNT;
		this.baseDamage = 2;
		this.isMultiDamage = true;
	}

	// Actions the card should do.
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {

		for (short i = 0; i < this.magicNumber; i++) {

			AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HEAVY));

			for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {

				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new Decay(mo, p, 2), 2));
				
				if (Corrupt(6)) {

					AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new PoisonPower(mo, p, 5), 5));

					if (!this.upgraded) {
						AbstractDungeon.actionManager
								.addToBottom(new ApplyPowerAction(mo, p, new WeakPower(mo, 1, false), 1));
					} else {
						AbstractDungeon.actionManager
								.addToBottom(new ApplyPowerAction(mo, p, new WeakPower(mo, 2, false), 2));
					}
				}


			}


		}

	}

	boolean Corrupt(int i) {
		if (AbstractDungeon.player.hasPower(Decay.POWER_ID)) {

			return AbstractDungeon.player.getPower(Decay.POWER_ID).amount >= i;

		}
		return false;
	}

	// Which card to return when making a copy of this card.
	@Override
	public AbstractCard makeCopy() {
		return new EndlessTorment();
	}

	// Upgraded stats.
	@Override
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeMagicNumber(1);
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
		}
	}
}