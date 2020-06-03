package MagicalMod.cards.Mana;

import MagicalMod.cards.AbstractCorrCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
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

public class AimForTheHead extends AbstractCorrCard {

	/*
	 * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
	 * 
	 * Strike Deal 7(9) damage.
	 */

	// TEXT DECLARATION

	public static final String ID = MagicalBase.makeID("AimForTheHead");
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String IMG = MagicalBase.makePath(MagicalBase.TransMind);

	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

	// /TEXT DECLARATION/

	// STAT DECLARATION

	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.ENEMY;
	private static final CardType TYPE = CardType.ATTACK;
	public static final CardColor COLOR = AbstractCardEnum.MAGICAL_COLOR;

	private static final int COST = 1;
	private static final int DAMAGE = 5;
	private static final int UPGRADE_PLUS_DMG = 2;
	private double mulup = 0;
	private double dmgAlmost;
	// /STAT DECLARATION/

	public AimForTheHead() {
		super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
		this.damage = this.baseDamage = DAMAGE;
	}

	// Actions the card should do.
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {

		this.dmgAlmost = this.damage;

		System.out.println("this.dmgAlmost is:" + this.dmgAlmost);
		System.out.println("this.damage is:" + this.damage);
		System.out.println("this.mulup is:" + this.mulup);

		if (p.hasPower(Mana.POWER_ID) && p.getPower(Mana.POWER_ID).amount >= 4) {

			this.mulup = 0.35;
			AbstractDungeon.actionManager.addToBottom(new ManaBlightTriggerAction(m, p, 1,0));

		} else {
			this.mulup = 0.25;
		}

		System.out.println("this.dmgAlmost is:" + this.dmgAlmost);
		System.out.println("this.damage is:" + this.damage);
		System.out.println("this.mulup is:" + this.mulup);

if(p.hasPower(Mana.POWER_ID)) {
	if(!this.upgraded) {
		this.mulup += p.getPower(Mana.POWER_ID).amount*0.06;
		}else this.mulup += p.getPower(Mana.POWER_ID).amount*0.07;
}

		if (m.currentBlock <= 1) {

			if (!this.upgraded) {
				this.mulup += 1.5;

				this.dmgAlmost *= (this.mulup);

			} else {
				this.mulup += 1.6;

				this.dmgAlmost *= (this.mulup);


			}
		}

		System.out.println("---------------");
		System.out.println("this.dmgAlmost is:" + this.dmgAlmost);
		System.out.println("this.damage is:" + this.damage);
		System.out.println("this.mulup is:" +   this.mulup);

		Math.ceil(this.dmgAlmost);

		this.damage = (int) this.dmgAlmost;

		System.out.println("this.dmgAlmost is:" + this.dmgAlmost);
		System.out.println("this.damage is:" + this.damage);
		System.out.println("this.mulup is:" +   this.mulup);


		AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
				new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
		}


	// Which card to return when making a copy of this card.
	@Override
	public AbstractCard makeCopy() {
		return new AimForTheHead();
	}

	// Upgraded stats.
	@Override
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeDamage(UPGRADE_PLUS_DMG);
			this.rawDescription = UPGRADE_DESCRIPTION;
			this.initializeDescription();
		}
	}
}