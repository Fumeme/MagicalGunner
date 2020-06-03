package MagicalMod.actions;

import MagicalMod.MagicalBase;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;


public class CoveringAction extends AbstractGameAction {
    private AbstractCard card;
    private AbstractPlayer p = AbstractDungeon.player;

    public CoveringAction() {
        this.actionType = ActionType.CARD_MANIPULATION;
    }

    public void update() {
        int ammonum = 0;

        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (c.hasTag(MagicalBase.Ammo)) {

                ammonum++;
            }
        }

        AbstractCard card;
        if (ammonum > 0) {
            do {

                card = this.p.discardPile.getRandomCard(AbstractDungeon.cardRandomRng);
            } while (!card.hasTag(MagicalBase.Ammo));


            this.p.hand.addToHand(card);
            this.p.discardPile.removeCard(card);
            this.p.hand.refreshHandLayout();
            card.lighten(false);
            card.unhover();

            card.applyPowers();

            this.p.hand.refreshHandLayout();
            tickDuration();
        }
        this.isDone = true;
    }

}