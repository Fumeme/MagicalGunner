package MagicalMod.actions;

import MagicalMod.MagicalBase;
import MagicalMod.powers.Mana;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;


public class SmokerAction extends AbstractGameAction {
    private DamageInfo info;
private  boolean isUpgraded;
    private AbstractCreature target;
int up;
int base;
    public SmokerAction(AbstractCreature target, int base, int up, boolean upgraded) {
        this.target = target;
        this.isUpgraded = upgraded;
        this.base = base;
        this.up = up;
    }



    public void update() {
        if (magic((short) 4)) {

            this.base +=2;
            if (magic((short) 7)) {

                this.base +=2;

                if (magic((short) 10)) {

                    this.base +=2;
                }
            }
        }
            if (isUpgraded && magic((short)2)) {
                addToTop(new GainBlockAction(this.target, this.base + this.up));
            } else {
                addToTop(new GainBlockAction(this.target, this.base));
            }

        this.isDone = true;
    }

    boolean magic(short min) {
        if (AbstractDungeon.player.hasPower(Mana.POWER_ID)) {

            return AbstractDungeon.player.getPower(Mana.POWER_ID).amount >= min;

        }
        return false;
    }
}