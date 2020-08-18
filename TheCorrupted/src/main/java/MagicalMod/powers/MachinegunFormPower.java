package MagicalMod.powers;

import MagicalMod.MagicalBase;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

//Gain 1 dex for the turn for each card played.

public class MachinegunFormPower extends TwoAmountPower {
    public AbstractCreature source;

    public static final String POWER_ID = MagicalBase.makeID("MGForm");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public MachinegunFormPower(final AbstractCreature owner, final AbstractCreature source, final int dmg) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = 0;
        this.amount2 = dmg;
        this.updateDescription();
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
        this.loadRegion("focus");
        this.source = source;


    }

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {

    if (card.hasTag(MagicalBase.Ammo)){
        this.flash();
        this.amount++;
    }
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {


        


        return damage;
    }
    @Override
    public void atEndOfTurn(boolean isPlayer) {
        this.flashWithoutSound();
        this.amount = 0;
    }

    @Override
    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
        if (this.amount <= 0) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
        }
        }
        
    // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
    @Override
    public void updateDescription() {
    	this.description = DESCRIPTIONS[0];
    }

}
