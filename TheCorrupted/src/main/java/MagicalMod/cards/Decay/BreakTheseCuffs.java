package MagicalMod.cards.Decay;

import MagicalMod.cards.AbstractCorrCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;

import MagicalMod.MagicalBase;
import MagicalMod.patches.AbstractCardEnum;
import MagicalMod.powers.Decay;

public class BreakTheseCuffs extends AbstractCorrCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     * 
     * Strike Deal 7(9) damage.
     */

    // TEXT DECLARATION

    public static final String ID = MagicalBase.makeID("BreakTheseCuffs");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG = MagicalBase.makePath(MagicalBase.DEFAULT_COMMON_ATTACK);

    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    // /TEXT DECLARATION/

    
    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = AbstractCardEnum.MAGICAL_COLOR;

    private static final int COST = 1;
    private short DecayGain;


    // /STAT DECLARATION/

    public BreakTheseCuffs() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
 
    	if(p.hasPower(VulnerablePower.POWER_ID)) {  
    		this.DecayGain += p.getPower(VulnerablePower.POWER_ID).amount;
    		
    		AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, VulnerablePower.POWER_ID));
    	}
        
        
    	if(p.hasPower(WeakPower.POWER_ID)) {  
    		this.DecayGain += p.getPower(WeakPower.POWER_ID).amount;
    		
    		AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, WeakPower.POWER_ID));
    	}
        

    	if(p.hasPower(FrailPower.POWER_ID)) {  
    		this.DecayGain += p.getPower(FrailPower.POWER_ID).amount;
    		
    		AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, FrailPower.POWER_ID));
    	}
    	
    	if(this.DecayGain>0) {
    	AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                new Decay(p, p, this.DecayGain), this.DecayGain));
    	}
    	
    }

    // Which card to return when making a copy of this card.
    @Override
    public AbstractCard makeCopy() {
        return new BreakTheseCuffs();
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.exhaust = false;
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
}