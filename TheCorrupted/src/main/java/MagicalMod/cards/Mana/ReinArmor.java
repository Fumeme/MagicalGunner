package MagicalMod.cards.Mana;

import MagicalMod.cards.AbstractCorrCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;

import MagicalMod.MagicalBase;
import MagicalMod.patches.AbstractCardEnum;
import MagicalMod.powers.Mana;

public class ReinArmor extends AbstractCorrCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     * 
     * Defend Gain 5 (8) block.
     */


    // TEXT DECLARATION 

    public static final String ID = MagicalBase.makeID("ReinArmor");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG = MagicalBase.makePath(MagicalBase.ReinArmor);

    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String[] EFFECTS = cardStrings.EXTENDED_DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION 	

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = AbstractCardEnum.MAGICAL_COLOR;

    private static final int COST = 1;
    private static final int BLOCK = 7;
    private static final int MAGIC = 1;
    private static final int UPGRADE_MAGIC = 1;


    // /STAT DECLARATION/


    public ReinArmor() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);        
        this.baseBlock = BLOCK;
        this.magicNumber = this.baseMagicNumber = MAGIC;
        
        this.rawDescription = DESCRIPTION + ReinArmor.EFFECTS[0];
        
        this.initializeDescription();
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new GainBlockAction(p, p, this.block));
        
        if(magic((short) 3)) {
        	AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new Mana(p,p, -1),-1));
        	
        	AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new PlatedArmorPower(p,this.magicNumber+1),this.magicNumber+1));
      
        }else {
        
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new PlatedArmorPower(p,this.magicNumber),this.magicNumber));
    }
    }

    boolean magic (short min) {
    	if (AbstractDungeon.player.hasPower(Mana.POWER_ID)) {

    		 return AbstractDungeon.player.getPower(Mana.POWER_ID).amount >= min;
    		
    	}
    	return false;
    }

    public void applyPowers()
    {
        super.applyPowers();
        
    	if(magic((short) 3)) {
    		
    		this.rawDescription = DESCRIPTION + ReinArmor.EFFECTS[1];
    	}else {
    		
            this.rawDescription = DESCRIPTION + ReinArmor.EFFECTS[0];
    	}
    	
        this.initializeDescription();

    }
    
    // Which card to return when making a copy of this card.
    @Override
    public AbstractCard makeCopy() {
        return new ReinArmor();
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_MAGIC);
            this.rawDescription = DESCRIPTION + ReinArmor.EFFECTS[0];
            this.initializeDescription();
        }
    }
}