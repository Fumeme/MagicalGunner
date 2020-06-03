package MagicalMod.actions;

import MagicalMod.MagicalBase;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;


public class BulletSharpenAction extends AbstractGameAction {
    private AbstractCard card;

    public BulletSharpenAction(int DmgUp) {
        this.amount = DmgUp;
    }


    public void update() {

        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.hasTag(MagicalBase.Ammo)) {
                c.baseDamage += this.amount;
                if (c.hasTag(MagicalBase.magdam)) {
                    c.baseMagicNumber += this.amount;
                }
                c.applyPowers();
            }
        }

        this.isDone = true;
    }

}