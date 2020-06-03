package MagicalMod.actions;

import MagicalMod.cards.ammo.RecycledAmmo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.ArrayList;


public class RecycleAction extends AbstractGameAction {

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("RecycleAction");
    public static final String[] TEXT = uiStrings.TEXT;
    boolean isUpgraded = false;
    AbstractPlayer p = AbstractDungeon.player;
    private ArrayList<AbstractCard> isNotAttack = new ArrayList<>();


    public RecycleAction(boolean up) {


        this.isUpgraded = up;

        this.actionType = ActionType.CARD_MANIPULATION;

        if (Settings.FAST_MODE) {

            this.duration = Settings.ACTION_DUR_XFAST;

        } else {

            this.duration = Settings.ACTION_DUR_FASTER;

        }

    }


    public void update() {

        for (AbstractCard c : this.p.hand.group) {


            if (!c.type.equals(AbstractCard.CardType.ATTACK)) {

                this.isNotAttack.add(c);
            }
        }

        if (!isUpgraded) {
            if (this.isNotAttack.size() == this.p.hand.group.size()) {
                this.isDone = true;
                return;
            }

            this.p.hand.group.removeAll(this.isNotAttack);
            AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false, false, false, false);

            for (AbstractCard c : this.isNotAttack) {
                this.p.hand.addToTop(c);
            }
            this.p.hand.refreshHandLayout();

            if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
                for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {

                    this.p.hand.moveToExhaustPile(c);
                    addToBot(new MakeTempCardInHandAction(new RecycledAmmo()));
                }
            }
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();


            this.p.hand.refreshHandLayout();

            this.isDone = true;
            tickDuration();
        }
    }

}