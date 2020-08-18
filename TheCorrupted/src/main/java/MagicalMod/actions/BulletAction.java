package MagicalMod.actions;

import MagicalMod.MagicalBase;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;


public class BulletAction extends AbstractGameAction {
    private AbstractCard card;
    

    public BulletAction(int DmgUp) {
        this.amount = DmgUp;
    }


    public void update() {


        this.isDone = true;
    }

}