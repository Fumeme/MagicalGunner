package MagicalMod.relics;

import java.util.ArrayList;
import java.util.Collections;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.relics.BetterOnSmithRelic;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.TreasureRoom;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;

import MagicalMod.MagicalBase;
import MagicalMod.patches.relics.LoreDiary.LoreDiaryReward;
import basemod.abstracts.CustomRelic;

public class LoreDiary extends CustomRelic implements BetterOnSmithRelic {
	/*
	 * https://github.com/daviscook477/BaseMod/wiki/Custom-Relics
	 * 
	 * At the start of each combat, gain 1 strenght (i.e. Varja)
	 */

	// ID, images, text.
	public static final String ID = MagicalBase.makeID("LoreDiary");
	public static final String IMG = MagicalBase.makePath(MagicalBase.PLACEHOLDER_RELIC);
	public static final String OUTLINE = MagicalBase.makePath(MagicalBase.PLACEHOLDER_RELIC_OUTLINE_2);

	private static ArrayList<AbstractCard> cardsToShow = new ArrayList<AbstractCard>();
	private AbstractPlayer p = AbstractDungeon.player;

	public LoreDiary() {
		super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.SPECIAL, LandingSound.FLAT);
	}

	@Override
	/*    */ public void justEnteredRoom(AbstractRoom room)
	/*    */ {
		/* 30 */ if ((room instanceof TreasureRoom)) {
			/* 31 */ flash();
			/* 32 */ this.pulse = true;
			/*    */ } else {
			/* 34 */ this.pulse = false;
			/*    */ }
		/*    */ }

	@Override
	/*    */ public void onChestOpen(boolean bossChest)
	/*    */ {
		/* 44 */ if (!bossChest) { /*    */

			flash();
			AbstractDungeon.getCurrRoom().rewards.add(new LoreDiaryReward());

		}
	}

	public void betterOnSmith(AbstractCard c) {

		final ArrayList<AbstractCard> upgradableCards = new ArrayList<AbstractCard>();

		if(c.hasTag(MagicalBase.Lore)) {
		
		for (final AbstractCard c1 : AbstractDungeon.player.masterDeck.group) {

			if (c1.canUpgrade() && !c1.hasTag(MagicalBase.Lore)) {

				upgradableCards.add(c1);

			}

		}

		Collections.shuffle(upgradableCards);

		if (!upgradableCards.isEmpty()) {

			cardsToShow.add(upgradableCards.get(0));
			upgradableCards.get(0).upgrade();

			AbstractDungeon.player.bottledCardUpgradeCheck(upgradableCards.get(0));

			cardEffects();

		}

	}}

	public static void cardEffects() {
		for (AbstractCard c : cardsToShow) {
			float x = MathUtils.random(0.4F, 0.9F) * Settings.WIDTH;
			float y = MathUtils.random(0.6F, 0.8F) * Settings.HEIGHT;
			AbstractDungeon.topLevelEffectsQueue.add(new ShowCardBrieflyEffect(c.makeStatEquivalentCopy(), x, y));
			AbstractDungeon.topLevelEffectsQueue.add(new UpgradeShineEffect(x, y));
		}
		cardsToShow.clear();
	}

	// Description
	@Override
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0];
	}

	// Which relic to return on making a copy of this relic.
	@Override
	public AbstractRelic makeCopy() {
		return new LoreDiary();
	}
}
