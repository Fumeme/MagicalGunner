package MagicalMod.events;

import MagicalMod.MagicalBase;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.colorless.Apotheosis;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.OddlySmoothStone;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import MagicalMod.MagicalBase;

public class IdentityCrisisEvent extends AbstractEvent {


    public static final String ID = MagicalBase.makeID("IdentityCrisisEvent");
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);

    private static final String NAME = eventStrings.NAME;
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;
    public static final String IMG = MagicalBase.makeID("IdentityCrisisEvent.png");

    private int screenNum = 0; // The initial screen we will see when encountering the event - screen 0;

    private float HEALTH_LOSS_PERCENTAGE = 0.03F; // 3%
    private float HEALTH_LOSS_PERCENTAGE_LOW_ASCENSION = 0.05F; // 5%

    private int healthdamage; //The actual number of how much Max HP we're going to lose.

    public IdentityCrisisEvent() {
        super();

this.body = DESCRIPTIONS[0];
        // The first dialogue options available to us.
        roomEventText.addDialogOption(OPTIONS[0], new OddlySmoothStone()); // rock get
        roomEventText.addDialogOption(OPTIONS[1]); // no rock
        roomEventText.addDialogOption(OPTIONS[2], new Apotheosis()); // Acceptance - Gain Apotheosis
    }

    @Override
    protected void buttonEffect(int i) { // This is the event:
        switch (screenNum) {
            case 0: // While you are on screen number 0 (The starting screen)
                switch (i) {
                    case 0: // If you press button the first button (Button at index 0), in this case: Inspiration.
                        this.roomEventText.updateBodyText(DESCRIPTIONS[1]); // Update the text of the event
                        this.roomEventText.updateDialogOption(0, OPTIONS[4]); // 1. Change the first button to the [Leave] button
                        this.roomEventText.clearRemainingOptions(); // 2. and remove all others
                        screenNum = 1; // Screen set the screen number to 1. Once we exit the switch (i) statement,
                        // we'll still continue the switch (screenNum) statement. It'll find screen 1 and do it's actions
                        // (in our case, that's the final screen, but you can chain as many as you want like that)

                        AbstractRelic relicToAdd = RelicLibrary.getRelic(OddlySmoothStone.ID);
                        // Get a random starting relic

                        AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2), relicToAdd);


                        break; // Onto screen 1 we go.
                    case 1: // If you press button the second button (Button at index 1), in this case: Deinal

                        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.MED, false);


                        this.roomEventText.updateBodyText(DESCRIPTIONS[2]);
                        this.roomEventText.updateDialogOption(0, OPTIONS[5]);
                        this.roomEventText.clearRemainingOptions();
                        screenNum = 1;

                        break; // Onto screen 1 we go.
                    case 2: // If you press button the third button (Button at index 2), in this case: Acceptance

                        AbstractCard c = new Apotheosis().makeCopy();
                        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(c, (float) (Settings.WIDTH / 2), (float) (Settings.HEIGHT / 2)));

                        this.roomEventText.updateBodyText(DESCRIPTIONS[3]);
                        this.roomEventText.updateDialogOption(0, OPTIONS[4]);
                        this.roomEventText.clearRemainingOptions();
                        screenNum = 1;
                        break;
                }
                break;




            case 1: // Welcome to screenNum = 1;
                switch (i) {
                    case 0: // If you press the first (and this should be the only) button,
                        openMap(); // You'll open the map and end the event.
                        break;
                }
                break;
        }


    }


}
