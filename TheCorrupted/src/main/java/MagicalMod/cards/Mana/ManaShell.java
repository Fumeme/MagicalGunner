package MagicalMod.cards.Mana;

import MagicalMod.cards.AbstractCorrCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import MagicalMod.MagicalBase;
import MagicalMod.actions.ManaShellAction;
import MagicalMod.patches.AbstractCardEnum;

public class ManaShell extends AbstractCorrCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     * 
     * Hold Place Gain 1(2) Keywords(s).
     */


    // TEXT DECLARATION 

    public static final String ID = MagicalBase.makeID("ManaShell");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG = MagicalBase.makePath(MagicalBase.DEFAULT_COMMON_SKILL);

    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION 	

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = AbstractCardEnum.MAGICAL_COLOR;

    private static final int COST = -1;
    private static final int MAGIC = 1;

    // /STAT DECLARATION/


    public ManaShell() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = MAGIC;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {    	
    	   if (this.energyOnUse < EnergyPanel.totalCount) {
    		   this.energyOnUse = EnergyPanel.totalCount;
    		     }
    	   
       	AbstractDungeon.actionManager.
       	addToBottom(new 
       			ManaShellAction(p, this.upgraded, this.freeToPlayOnce, this.energyOnUse));
}


    // Which card to return when making a copy of this card.
    @Override
    public AbstractCard makeCopy() {
        return new ManaShell();
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
			this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
            
        }
    }
}