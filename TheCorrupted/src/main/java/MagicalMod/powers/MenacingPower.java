package MagicalMod.powers;

import MagicalMod.MagicalBase;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

//Gain 1 dex for the turn for each card played.

public class MenacingPower extends TwoAmountPower {
    public AbstractCreature source;

    public static final String POWER_ID = MagicalBase.makeID("MenacingPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final String IMG = MagicalBase.makePath(MagicalBase.MenacingPower);


    public MenacingPower(final AbstractCreature owner, final AbstractCreature source, final int turns, int apply) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = turns;
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
        this.img = new Texture(IMG);
        this.source = source;
        canGoNegative = false;
        this.amount2 = apply;
        this.updateDescription();

    }

    @Override
    public void atStartOfTurnPostDraw() {
        updateDescription();

        for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {

            if (((mo.intent.equals(AbstractMonster.Intent.ATTACK)) || (mo.intent.equals(AbstractMonster.Intent.ATTACK_BUFF)) ||
                    (mo.intent.equals(AbstractMonster.Intent.ATTACK_DEBUFF)) || (mo.intent.equals(AbstractMonster.Intent.ATTACK_DEFEND)))) {


                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, owner, new VulnerablePower(mo, amount2, false), amount2));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, owner, new WeakPower(mo, amount2, false), amount2));
            }
        }

        AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(owner, owner, this, 1));
    }
    @Override
    public void atStartOfTurn() {
        updateDescription();
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
        this.description = DESCRIPTIONS[0] + this.amount2 + DESCRIPTIONS[1];
    }
}

