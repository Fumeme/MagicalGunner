package MagicalMod.cards.ammo;

import MagicalMod.cards.AbstractCorrCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import MagicalMod.MagicalBase;
import MagicalMod.actions.ManaBlightTriggerAction;
import MagicalMod.patches.AbstractCardEnum;
import MagicalMod.powers.Mana;
import MagicalMod.powers.ManaBlightPower;

public class ManaSparkAmmo extends AbstractCorrCard {

	/*
	 * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
	 * 
	 * Strike Deal 7(9) damage.
	 */

	// TEXT DECLARATION

	public static final String ID = MagicalBase.makeID("ManaSpark");
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String IMG = MagicalBase.makePath(MagicalBase.DEFAULT_COMMON_ATTACK);

	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String[] EFFECTS = cardStrings.EXTENDED_DESCRIPTION;

	// /TEXT DECLARATION/

	// STAT DECLARATION

	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.ENEMY;
	private static final CardType TYPE = CardType.ATTACK;
	public static final CardColor COLOR = AbstractCardEnum.MAGICAL_COLOR;

	private static final int COST = 0;
	private static final int DAMAGE = 0;
	private static final int UPGRADE_PLUS_DMG = 1;
	private int AMOUNT = 2;

	// /STAT DECLARATION/

	public ManaSparkAmmo() {
		super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
		this.baseDamage = DAMAGE;
		 this.magicNumber = this.baseMagicNumber = AMOUNT;
		tags.add(MagicalBase.Ammo);
		this.rawDescription = DESCRIPTION + EFFECTS[0];
		this.initializeDescription();
	}

	// Actions the card should do.
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {

		if (magic(1)) {

			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Mana(p, p, -1), -1));
			AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
					new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));

			if (m.hasPower(ManaBlightPower.POWER_ID) && m.getPower(ManaBlightPower.POWER_ID).amount > 0) {

				AbstractDungeon.actionManager.addToBottom(new ManaBlightTriggerAction(m, p, 1, 0));
			} else {

				AbstractDungeon.actionManager.addToBottom(
						new ApplyPowerAction(m, p, new ManaBlightPower(m, p, this.magicNumber), this.magicNumber));
			}

			if (magic(5)) {
				AbstractDungeon.actionManager.addToBottom(new ManaBlightTriggerAction(m, p, 1, 0));

				AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
						new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));

				if (magic(6)) {
					AbstractDungeon.actionManager.addToBottom(new ManaBlightTriggerAction(m, p, 1, 0));

					AbstractDungeon.actionManager
							.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn),
									AbstractGameAction.AttackEffect.FIRE));

					if (magic(7)) {
						AbstractDungeon.actionManager.addToBottom(new ManaBlightTriggerAction(m, p, 1, 0));

						AbstractDungeon.actionManager
								.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn),
										AbstractGameAction.AttackEffect.FIRE));

						if (magic(8)) {
							AbstractDungeon.actionManager.addToBottom(new ManaBlightTriggerAction(m, p, 1, 0));

							AbstractDungeon.actionManager.addToBottom(
									new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn),
											AbstractGameAction.AttackEffect.FIRE));

							if (magic(10)) {
								AbstractDungeon.actionManager.addToBottom(new ManaBlightTriggerAction(m, p, 1, 0));

								AbstractDungeon.actionManager
										.addToBottom(new ApplyPowerAction(m, p, new ManaBlightPower(m, p, 3), 3));
							}

						}
					}
				}
			}
		}
	}

	boolean magic(int min) {
		if (AbstractDungeon.player.hasPower(Mana.POWER_ID)) {

			return AbstractDungeon.player.getPower(Mana.POWER_ID).amount >= min;

		}
		return false;
	}

	//

	@Override
	public void applyPowers() {

		int tmp = this.baseDamage;
		super.applyPowers();
		this.baseDamage = tmp;
		this.isDamageModified = this.baseDamage != this.damage;

		if (magic((short) 5)) {

			this.rawDescription = DESCRIPTION + ManaSparkAmmo.EFFECTS[2];

			if (magic((short) 6)) {

				this.rawDescription += DESCRIPTION + ManaSparkAmmo.EFFECTS[4];

				if (magic((short) 7)) {

					this.rawDescription += DESCRIPTION + ManaSparkAmmo.EFFECTS[6];

					if (magic((short) 8)) {

						this.rawDescription += DESCRIPTION + ManaSparkAmmo.EFFECTS[8];
					} else {
						this.rawDescription += DESCRIPTION + ManaSparkAmmo.EFFECTS[7];
					}
				} else {
					this.rawDescription += DESCRIPTION + ManaSparkAmmo.EFFECTS[5];
				}
			} else {
				this.rawDescription += DESCRIPTION + ManaSparkAmmo.EFFECTS[3];
			}
			
			this.rawDescription += DESCRIPTION + ManaSparkAmmo.EFFECTS[10];
		} else {
			this.rawDescription = DESCRIPTION + ManaSparkAmmo.EFFECTS[1];
			this.rawDescription += DESCRIPTION + ManaSparkAmmo.EFFECTS[9];
		}
		if (magic((short) 10)) {

			this.rawDescription = DESCRIPTION + ManaSparkAmmo.EFFECTS[12];
		} else {
			this.rawDescription = DESCRIPTION + ManaSparkAmmo.EFFECTS[11];
		}
		this.initializeDescription();
	}

	@Override
	public void calculateCardDamage(AbstractMonster mo) {
		int tmp = this.baseDamage;
		super.calculateCardDamage(mo);
		this.baseDamage = tmp;
		this.isDamageModified = this.baseDamage != this.damage;
	}
	//

	// Which card to return when making a copy of this card.
	@Override
	public AbstractCard makeCopy() {
		return new ManaSparkAmmo();
	}

	// Upgraded stats.
	@Override
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeDamage(UPGRADE_PLUS_DMG);
			this.upgradeMagicNumber(UPGRADE_PLUS_DMG);
			this.initializeDescription();
		}
	}
}