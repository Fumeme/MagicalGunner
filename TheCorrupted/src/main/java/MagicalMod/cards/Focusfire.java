package MagicalMod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import MagicalMod.MagicalBase;
import MagicalMod.patches.AbstractCardEnum;
import MagicalMod.powers.Mana;


public class Focusfire extends AbstractCorrCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Strike Deal 7(9) damage.
     */

    // TEXT DECLARATION

    public static final String ID = MagicalBase.makeID("Focusfire");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG = MagicalBase.makePath(MagicalBase.DEFAULT_COMMON_ATTACK);

    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String[] EFFECTS = cardStrings.EXTENDED_DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = AbstractCardEnum.MAGICAL_COLOR;

    private static final int COST = 0;
    private static int DAMAGE = 2;
    private static final int up = 1;

    // /STAT DECLARATION/

    public Focusfire() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = DAMAGE;
        this.magicNumber = this.baseMagicNumber = 1;

        this.rawDescription = DESCRIPTION;

        this.initializeDescription();
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        AbstractDungeon.actionManager
                .addToBottom(new DamageAction(m,
                        new DamageInfo(p, this.damage, this.damageTypeForTurn),
                        AbstractGameAction.AttackEffect.FIRE));

        AbstractDungeon.actionManager
                .addToBottom(new DamageAction(m,
                        new DamageInfo(p, this.damage, this.damageTypeForTurn),
                        AbstractGameAction.AttackEffect.FIRE));

        if (magic(6)) {

            addToBot(new DrawCardAction(1));
        }


    }

    boolean magic(int min) {
        if (AbstractDungeon.player.hasPower(Mana.POWER_ID)) {

            return AbstractDungeon.player.getPower(Mana.POWER_ID).amount >= min;

        }
        return false;
    }

    @Override
    public void applyPowers() {

        int tmp = this.baseDamage;
        if (magic(3)) {
            this.baseDamage += this.magicNumber;
        }
        super.applyPowers();
        this.baseDamage = tmp;
        this.isDamageModified = this.baseDamage != this.damage;

        if (magic(3)) {

            this.rawDescription = DESCRIPTION + Focusfire.EFFECTS[1];
        } else {

            this.rawDescription = DESCRIPTION + Focusfire.EFFECTS[0];
        }

        this.initializeDescription();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int tmp = this.baseDamage;
        if (magic((short) 3)) {
            this.baseDamage += this.magicNumber;
        }
        super.calculateCardDamage(mo);
        this.baseDamage = tmp;
        this.isDamageModified = this.baseDamage != this.damage;
    }

    // Which card to return when making a copy of this card.
    @Override
    public AbstractCard makeCopy() {
        return new Focusfire();
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(up);
            this.upgradeMagicNumber(up);
            this.initializeDescription();
        }
    }
}