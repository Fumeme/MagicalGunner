package MagicalMod.cards.Mana;

import MagicalMod.cards.AbstractCorrCard;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import MagicalMod.MagicalBase;
import MagicalMod.patches.AbstractCardEnum;
import MagicalMod.powers.Mana;

public class ManaBlade extends AbstractCorrCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     * 
     * Strike Deal 7(9) damage.
     */

    // TEXT DECLARATION

    public static final String ID = MagicalBase.makeID("ManaBlade");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG = MagicalBase.makePath(MagicalBase.DEFAULT_COMMON_ATTACK);

    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    // /TEXT DECLARATION/

    
    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = AbstractCardEnum.MAGICAL_COLOR;

    private static final int COST = 1;
    private static final int MAX = 13;
    int MIN = 6;

    // /STAT DECLARATION/

    public ManaBlade() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = MAX;
        this.baseMagicNumber = MIN;
        this.BaseSecondMagicNumber = MIN;

    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	
    	/* 
    	 * this.damage is max damage done, magicNumber is Min damage, both affected by dmg modifiers
    	 *
    	 */

    	
        AbstractDungeon.actionManager
                .addToBottom(new DamageAction(m,
                        new DamageInfo(p, this.SecondMagicNumber, this.damageTypeForTurn),
                        AbstractGameAction.AttackEffect.SHIELD));
    }

    // Which card to return when making a copy of this card.
    @Override
    public AbstractCard makeCopy() {
        return new ManaBlade();
    }


    @Override
    public void applyPowers() {
        int CurrMin = baseMagicNumber;
        int CurrMax = baseDamage;
        super.applyPowers();

        baseDamage = CurrMin;
        super.applyPowers(); // takes baseDamage and applies things like Strength or Pen Nib to set damage

        magicNumber = damage; // magic number holds the first condition's modified damage, so !M! will work
        isMagicNumberModified = magicNumber != baseMagicNumber;

        // repeat so damage holds the second condition's damage
        baseDamage = CurrMax;
       // super.applyPowers();
         ManaCheck();
        super.applyPowers();
        ManaCheck();

        this.initializeDescription();


    }
    @Override
    public void calculateCardDamage(final AbstractMonster mo) {

        int CurrMin = magicNumber;
        int CurrMax = baseDamage;
        super.applyPowers();

        baseDamage = CurrMin;
        super.applyPowers();
        super.calculateCardDamage(mo); // takes baseDamage and applies things like Strength or Pen Nib to set damage

        magicNumber = damage; // magic number holds the first condition's modified damage, so !M! will work
        isMagicNumberModified = magicNumber != baseMagicNumber;

        // repeat so damage holds the second condition's damage
        baseDamage = CurrMax;
        ManaCheck();
        super.applyPowers();
        super.calculateCardDamage(mo); // takes baseDamage and applies things like Strength or Pen Nib to set damage


        ManaCheck();
        super.calculateCardDamage(mo);
        ManaCheck();

        this.initializeDescription();

    }


    public void ManaCheck(){
        AbstractPlayer p = AbstractDungeon.player;
        SecondMagicNumber = 0;

        if (p.hasPower(Mana.POWER_ID)) {
            int am = MathUtils.floor(p.getPower(Mana.POWER_ID).amount);

            if (am >= this.damage) {

                this.SecondMagicNumber = this.damage;
            } else if (am <= this.magicNumber) {

                this.SecondMagicNumber = this.magicNumber;
            } else {

                this.SecondMagicNumber = p.getPower(Mana.POWER_ID).amount;
            }
        }else {this.SecondMagicNumber = this.magicNumber;}
        isSecondMagicNumberModified = true;

    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(2);
            this.upgradeDamage(4);
            this.initializeDescription();
        }
    }
}