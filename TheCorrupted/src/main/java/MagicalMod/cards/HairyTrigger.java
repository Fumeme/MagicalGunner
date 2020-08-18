package MagicalMod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import MagicalMod.MagicalBase;
import MagicalMod.patches.AbstractCardEnum;

public class HairyTrigger extends AbstractCorrCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     * 
     * Strike Deal 7(9) damage.
     */

    // TEXT DECLARATION

    public static final String ID = MagicalBase.makeID("HairyTrigger");
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

    private static final int COST = 0;
    private static final int DAMAGE = 2;


    // /STAT DECLARATION/

    public HairyTrigger() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = DAMAGE;
        magicNumber = this.baseMagicNumber = 1;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	for(short i = 0; i < magicNumber; i++) {
    	
        AbstractDungeon.actionManager
                .addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
                        new DamageInfo(p, this.damage, this.damageTypeForTurn),
                        AbstractGameAction.AttackEffect.POISON));
        
    	}  
    }

    // Which card to return when making a copy of this card.
    @Override
    public AbstractCard makeCopy() {
        return new HairyTrigger();
    }

    @Override
    public boolean canUpgrade() {
        return true;
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        
            this.upgradeName();
            this.name = (NAME + "+" + timesUpgraded);
            timesUpgraded++;

        this.upgradeMagicNumber(1);
        this.initializeDescription();
    }
}