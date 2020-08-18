package MagicalMod.cards.ammo;

import MagicalMod.MagicalBase;
import MagicalMod.cards.AbstractCorrCard;
import MagicalMod.cards.Focusfire;
import MagicalMod.patches.AbstractCardEnum;
import MagicalMod.powers.Mana;
import MagicalMod.powers.Sparkle;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Bullet extends AbstractCorrCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Strike Deal 7(9) damage.
     */

    // TEXT DECLARATION

    public static final String ID = MagicalBase.makeID("NormalBullet");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG = MagicalBase.makePath(MagicalBase.ReAmmo);

    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = AbstractCardEnum.MAGICAL_COLOR;

    private static final int COST = 0;
    private static final int DAMAGE = 2;

    // /STAT DECLARATION/

    public Bullet() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = DAMAGE;
this.baseMagicNumber = 1;
        tags.add(MagicalBase.Ammo);

    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        addToBot(new DamageAction(m,
                new DamageInfo(p, this.damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.FIRE));
        if (ammocheck()) {
            addToBot(new DamageAction(m,
                    new DamageInfo(p, this.damage, this.damageTypeForTurn),
                    AbstractGameAction.AttackEffect.FIRE));
        }


    }
    @Override
    public void applyPowers() {

        int tmp = this.baseDamage;
        if (magic(5)) {
            this.baseDamage += this.magicNumber;
        }
        super.applyPowers();
        this.baseDamage = tmp;
        this.isDamageModified = this.baseDamage != this.damage;
/**
        if (magic(5)) {

            this.rawDescription = DESCRIPTION + Focusfire.EFFECTS[1];
        } else {

            this.rawDescription = DESCRIPTION + Focusfire.EFFECTS[0];
        }
**/
        this.initializeDescription();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int tmp = this.baseDamage;
        if (magic((short) 5)) {
            this.baseDamage += this.magicNumber;
        }
        super.calculateCardDamage(mo);
        this.baseDamage = tmp;
        this.isDamageModified = this.baseDamage != this.damage;
    }
    boolean magic(int min) {
        if (AbstractDungeon.player.hasPower(Mana.POWER_ID)) {

            return AbstractDungeon.player.getPower(Mana.POWER_ID).amount >= min;

        }
        return false;
    }
    boolean ammocheck() {
        int hasammo = 0;
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.hasTag(MagicalBase.Ammo)) {
                hasammo +=1;
            }
        }
        return hasammo>1;
    }

    // Which card to return when making a copy of this card.
    @Override
    public AbstractCard makeCopy() {
        return new Bullet();
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
this.upgradeDamage(1);
this.upgradeMagicNumber(1);
            this.initializeDescription();
        }
    }
}