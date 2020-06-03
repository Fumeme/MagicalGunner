package MagicalMod.patches.relics.LoreDiary;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;

import MagicalMod.MagicalBase;
import basemod.abstracts.CustomReward;

public class LoreDiaryReward extends CustomReward {
    private static final Texture ICON = new Texture(Gdx.files.internal("CorruptedResources/images/relics/placeholder_relic.png"));

    public LoreDiaryReward() {
        super(ICON, "Lore Card Reward", LoreDiaryPatch.LoreDiaryReward);

    	ArrayList<AbstractCard> LoreList = new ArrayList<AbstractCard>();

    	
    	for (AbstractCard c : CardLibrary.getAllCards()) {
    	if(c.hasTag(MagicalBase.Lore)) LoreList.add(c.makeStatEquivalentCopy());
    	}
    	


        /*                                     */
        ArrayList<AbstractCard> LoreListSelect = new ArrayList<AbstractCard>();
      
   	 
        
        for(int i = 0; i < 2; i++) {
            //add card to list
        	
        	 LoreListSelect.add(LoreList.get(AbstractDungeon.cardRng.random(LoreList.size() -1)).makeStatEquivalentCopy());	
        }
        

        this.cards = LoreListSelect;

    }

    @Override
    public boolean claimReward() {


    		if(AbstractDungeon.screen == AbstractDungeon.CurrentScreen.COMBAT_REWARD) {

    			AbstractDungeon.cardRewardScreen.open(this.cards, this, "Pick A Lore Card.");

    			AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.COMBAT_REWARD;

    		}

    		return false;
    }
}