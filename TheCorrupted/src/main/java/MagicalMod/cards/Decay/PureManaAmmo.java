package MagicalMod.cards.Decay;

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
import com.megacrit.cardcrawl.powers.VulnerablePower;

import MagicalMod.MagicalBase;
import MagicalMod.actions.ManaBlightTriggerAction;
import MagicalMod.patches.AbstractCardEnum;
import MagicalMod.powers.Mana;
import MagicalMod.powers.ManaBlightPower;

public class PureManaAmmo extends AbstractCorrCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     * 
     * Strike Deal 7(9) damage.
     */

    // TEXT DECLARATION

    public static final String ID = MagicalBase.makeID("PureMana");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG = MagicalBase.makePath(MagicalBase.DEFAULT_COMMON_ATTACK);

    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String[] EFFECTS = cardStrings.EXTENDED_DESCRIPTION;

    // /TEXT DECLARATION/

    
    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = AbstractCardEnum.MAGICAL_COLOR;

    private static final int COST = 0;
    private static final int DAMAGE = 2;
    private static final int UPGRADE_PLUS_DMG = 1;
    private int AMOUNT = 2;
    private int bonus = 5;
    
    // /STAT DECLARATION/

    public PureManaAmmo() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = DAMAGE;
        this.baseMagicNumber = this.magicNumber = AMOUNT;
        tags.add(MagicalBase.Ammo);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	
        if(magic(1)) {
        	
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                    new Mana(p, p, -1), -1));
            
        }
    	
        AbstractDungeon.actionManager
                .addToBottom(new DamageAction(m,
                        new DamageInfo(p, this.damage, this.damageTypeForTurn),
                        AbstractGameAction.AttackEffect.FIRE));
        
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p,
                new ManaBlightPower(m, p, this.magicNumber), this.magicNumber));
        
        if(magic(3)) { AbstractDungeon.actionManager.addToBottom(new ManaBlightTriggerAction(m, p, 1,0));
        	
            AbstractDungeon.actionManager
            .addToBottom(new DamageAction(m,
                    new DamageInfo(p, this.magicNumber, this.damageTypeForTurn),
                    AbstractGameAction.AttackEffect.FIRE));
            
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p,
                    new VulnerablePower(m, this.magicNumber, false), this.magicNumber));
            
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p,
                    new ManaBlightPower(m, p, 1), 1));
            if(magic(6)) { AbstractDungeon.actionManager.addToBottom(new ManaBlightTriggerAction(m, p, 1,0));
            	
                AbstractDungeon.actionManager
                .addToBottom(new DamageAction(m,
                        new DamageInfo(p, this.magicNumber, this.damageTypeForTurn),
                        AbstractGameAction.AttackEffect.FIRE));
                
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p,
                        new VulnerablePower(m, this.magicNumber, false), this.magicNumber));
                
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p,
                        new ManaBlightPower(m, p, 1), 1));
                if(magic(9)) { AbstractDungeon.actionManager.addToBottom(new ManaBlightTriggerAction(m, p, 1,0));
                	
                    AbstractDungeon.actionManager
                    .addToBottom(new DamageAction(m,
                            new DamageInfo(p, this.magicNumber, this.damageTypeForTurn),
                            AbstractGameAction.AttackEffect.FIRE));
                    
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p,
                            new VulnerablePower(m, this.magicNumber, false), this.magicNumber));
                    
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p,
                            new ManaBlightPower(m, p, 1), 1));
                }
            }
        }


    }

    boolean magic (int min) {
    	if (AbstractDungeon.player.hasPower(Mana.POWER_ID)) {

    		 return AbstractDungeon.player.getPower(Mana.POWER_ID).amount >= min;
    		
    	}
    	return false;
    }

    @Override
    public void applyPowers(){
    	
    	int tmp = this.baseDamage;
    	if (magic((short) 1)){
    	    this.baseDamage += bonus;
    	}
    	super.applyPowers();
    	this.baseDamage = tmp;
    	this.isDamageModified = this.baseDamage != this.damage;
  /*      
    	if(magic((short) 3)) {
    		
    		this.rawDescription = DESCRIPTION + PureManaAmmo.EFFECTS[1];
    		
    	   	if(magic((short) 6)) {
        		
        		this.rawDescription += PureManaAmmo.EFFECTS[2];
        		
        	   	if(magic((short) 9)) {
        			
        			this.rawDescription += PureManaAmmo.EFFECTS[3];
        		}
        	}
    	   	
    	   	this.rawDescription += PureManaAmmo.EFFECTS[5];
    	}else {
    		
            this.rawDescription = DESCRIPTION + PureManaAmmo.EFFECTS[0];
    	}*/
   	
        this.initializeDescription();
    }
    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int tmp = this.baseDamage;
        if (magic((short) 1)){
            this.baseDamage += bonus;
        }
        super.calculateCardDamage(mo);
        this.baseDamage = tmp;
        this.isDamageModified = this.baseDamage != this.damage;
}
    // Which card to return when making a copy of this card.
    @Override
    public AbstractCard makeCopy() {
        return new PureManaAmmo();
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