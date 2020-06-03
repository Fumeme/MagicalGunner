package MagicalMod.cards.Mana;

import MagicalMod.MagicalBase;
import MagicalMod.cards.AbstractCorrCard;
import MagicalMod.patches.AbstractCardEnum;
import MagicalMod.powers.LeechPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class CosmicNova extends AbstractCorrCard {

	/*
	 * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
	 *
	 * for each loop x2" "Apply 1 Vulnerable to all enemies, 2(3) times.
	 */

	// TEXT DECLARATION

	public static final String ID = MagicalBase.makeID("CosmicNova");
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

	public CosmicNova() {
		super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
		this.baseMagicNumber = 3;
		this.baseDamage = 13;
		this.isMultiDamage = true;
		this.exhaust = true;
	}

	// Actions the card should do.
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {


			AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HEAVY));

			for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {

				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new LeechPower(mo, p, this.magicNumber), this.magicNumber));

			}

	}


	// Which card to return when making a copy of this card.
	@Override
	public AbstractCard makeCopy() {
		return new CosmicNova();
	}

	// Upgraded stats.
	@Override
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
this.upgradeDamage(5);
            this.initializeDescription();
		}
	}
}