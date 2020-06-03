package MagicalMod.actions;

import MagicalMod.MagicalBase;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;
import java.util.Iterator;


public class PreloadAction extends AbstractGameAction {
    private AbstractCard card;
    int cards;

    public PreloadAction(int DmgUp, int cardnum) {
        this.amount = DmgUp;
        this.cards = cardnum;
    }


    public void update() {

        AbstractCard c;
        for (int i = 0; i < this.cards; i++) {
            c = returnRandomAmmo().makeCopy();
            addToBot(new MakeTempCardInHandAction(c, true));


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



    public static AbstractCard returnRandomAmmo() {

        CardGroup common = AbstractDungeon.srcCommonCardPool;
        CardGroup uncommon = AbstractDungeon.srcUncommonCardPool;
        CardGroup rare = AbstractDungeon.srcRareCardPool;

        ArrayList<AbstractCard> list = new ArrayList();
        Iterator var2 = common.group.iterator();

        AbstractCard c;
        while(var2.hasNext()) {
            c = (AbstractCard)var2.next();
            if (c.hasTag(MagicalBase.Ammo)) {
                list.add(c);
            }
        }

        var2 = uncommon.group.iterator();

        while(var2.hasNext()) {
            c = (AbstractCard)var2.next();
            if (c.hasTag(MagicalBase.Ammo)) {
                list.add(c);
            }
        }

        var2 = rare.group.iterator();

        while(var2.hasNext()) {
            c = (AbstractCard)var2.next();
            if (c.hasTag(MagicalBase.Ammo)) {
                list.add(c);
            }
        }

        return (AbstractCard)list.get(AbstractDungeon.cardRandomRng.random(list.size() - 1));
    }
}