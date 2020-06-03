package MagicalMod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;

import MagicalMod.MagicalBase;

//Gain 1 dex for the turn for each card played.

public class ToxicWallPower extends AbstractPower {
    public AbstractCreature source;

    public static final String POWER_ID = MagicalBase.makeID("ToxicWallPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final String IMG = MagicalBase.makePath(MagicalBase.ToxicWall);

    public ToxicWallPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
        this.loadRegion("flameBarrier");
        this.source = source;

    }

    // On use card, apply (amount) of dexterity. (Go to the actual power card for the ammount.)
    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != null && info.owner != this.owner) {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(info.owner, AbstractDungeon.player,
                    new PoisonPower(info.owner, this.owner, this.amount), this.amount));
            
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                    new Decay(AbstractDungeon.player, AbstractDungeon.player, 1), 1));
        }

        return damageAmount;
    }
    /*    */   @Override
    /*    */   public void atStartOfTurn()
    /*    */   {
    /* 60 */     AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, ToxicWallPower.POWER_ID));
    /*    */   }
    /*    */   
    /*    */ @Override
    /*    */   public void updateDescription()
    /*    */   {
    /* 66 */     this.description = (DESCRIPTIONS[0]);
    /*    */   }
    /*    */ }