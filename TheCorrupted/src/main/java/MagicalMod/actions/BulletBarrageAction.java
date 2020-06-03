package MagicalMod.actions;

import MagicalMod.MagicalBase;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;


public class BulletBarrageAction extends AbstractGameAction {
    private DamageInfo info;
private  boolean isUpgraded;
    private AbstractCreature target;
    private AbstractCard card;

    public BulletBarrageAction(AbstractCreature m, DamageInfo info, boolean upgraded, AbstractCard card) {
        this.info = info;
        this.target = m;
        this.isUpgraded = upgraded;
        this.card = card;
    }



    public void update() {

        for (int i = 0; i < AbstractDungeon.player.hand.size(); i++) {

            if (AbstractDungeon.player.hand.group.get(i).hasTag(MagicalBase.Ammo))
                if (this.isUpgraded) {
                    AbstractDungeon.actionManager.addToTop((AbstractGameAction) new DamageAction(this.target, this.info, AttackEffect.FIRE, true));
                }else{
                    AbstractDungeon.actionManager.addToTop((AbstractGameAction) new AttackDamageRandomEnemyAction(card, AttackEffect.FIRE));

                }
        }
        this.isDone = true;
}

}