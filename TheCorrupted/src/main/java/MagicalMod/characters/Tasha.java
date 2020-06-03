package MagicalMod.characters;

import java.util.ArrayList;

import basemod.abstracts.CustomPlayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import MagicalMod.MagicalBase;
import MagicalMod.cards.*;
import MagicalMod.patches.AbstractCardEnum;
import MagicalMod.relics.InfernalCore;
import basemod.animations.SpriterAnimation;
//import kobting.friendlyminions.characters.AbstractPlayerWithMinions;
//import kobting.friendlyminions.characters.CustomCharSelectInfo;

public class Tasha extends CustomPlayer {
    public static final Logger logger = LogManager.getLogger(MagicalBase.class.getName());

    // =============== BASE STATS =================

    public static final int ENERGY_PER_TURN = 3;
    public static final int STARTING_HP = 80;
    public static final int MAX_HP = 80;
    public static final int STARTING_GOLD = 105;
    public static final int CARD_DRAW = 5;
    public static final int ORB_SLOTS = 0;
  //  public static final int MinionMax = 3;

    // =============== /BASE STATS/ =================

    
    // =============== TEXTURES OF BIG ENERGY ORB ===============

    public static final String[] orbTextures = {
            "CorruptedResources/images/char/defaultCharacter/orb/layer1.png",
            "CorruptedResources/images/char/defaultCharacter/orb/layer2.png",
            "CorruptedResources/images/char/defaultCharacter/orb/layer3.png",
            "CorruptedResources/images/char/defaultCharacter/orb/layer4.png",
            "CorruptedResources/images/char/defaultCharacter/orb/layer5.png",
            "CorruptedResources/images/char/defaultCharacter/orb/layer6.png",
            "CorruptedResources/images/char/defaultCharacter/orb/layer1d.png",
            "CorruptedResources/images/char/defaultCharacter/orb/layer2d.png",
            "CorruptedResources/images/char/defaultCharacter/orb/layer3d.png",
            "CorruptedResources/images/char/defaultCharacter/orb/layer4d.png",
            "CorruptedResources/images/char/defaultCharacter/orb/layer5d.png", };
    
    // =============== /TEXTURES OF BIG ENERGY ORB/ ===============

    
    // =============== CHARACTER CLASS START =================

    public Tasha(String name, PlayerClass setClass) {
        super(name, setClass, orbTextures,
                "CorruptedResources/images/char/defaultCharacter/orb/vfx.png", null,
                new SpriterAnimation(
                        "CorruptedResources/images/char/defaultCharacter/Spriter/theDefaultAnimation.scml"));
        
    
//(String name, PlayerClass playerClass, String[] orbTextures, String orbVfxPath, float[] layerSpeeds, AbstractAnimation animation)
        
        // =============== TEXTURES, ENERGY, LOADOUT =================  

        initializeClass(null, // required call to load textures and setup energy/loadout
                MagicalBase.makePath(MagicalBase.THE_DEFAULT_SHOULDER_1), // campfire pose
                MagicalBase.makePath(MagicalBase.THE_DEFAULT_SHOULDER_2), // another campfire pose
                MagicalBase.makePath(MagicalBase.THE_DEFAULT_CORPSE), // dead corpse
                getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN)); // energy manager

        // =============== /TEXTURES, ENERGY, LOADOUT/ =================

        
        // =============== ANIMATIONS =================  

        this.loadAnimation(
                MagicalBase.makePath(MagicalBase.THE_DEFAULT_SKELETON_ATLAS),
                MagicalBase.makePath(MagicalBase.THE_DEFAULT_SKELETON_JSON),
                1.0f);
        AnimationState.TrackEntry e = this.state.setAnimation(0, "animation", true);
        e.setTime(e.getEndTime() * MathUtils.random());

        // =============== /ANIMATIONS/ =================

        
        // =============== TEXT BUBBLE LOCATION =================

        this.dialogX = (this.drawX + 0.0F * Settings.scale); // set location for text bubbles
        this.dialogY = (this.drawY + 220.0F * Settings.scale); // you can just copy these values

        // =============== /TEXT BUBBLE LOCATION/ =================

    }

    // =============== /CHARACTER CLASS END/ =================

    
    // Starting description and loadout
    /**
    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo("The Corrupted",
                "use your mana properly to maximize your card value or NL " + "Second line of description text. ",
                STARTING_HP, MAX_HP, ORB_SLOTS, STARTING_GOLD, CARD_DRAW, this, getStartingRelics(),
                getStartingDeck(), false);
    }
**/

    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo("The Magical Gunner", "Optimize your mana for big brain plays.",
                STARTING_HP, MAX_HP, ORB_SLOTS, STARTING_GOLD, CARD_DRAW, this, getStartingRelics(),
                getStartingDeck(), false);
    }
    
    
    // Starting Deck
    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();

        logger.info("Begin loading started Deck strings");

        retVal.add(DefaultCommonAttack.ID);
        retVal.add(DefaultCommonAttack.ID);
        retVal.add(DefaultCommonAttack.ID);
        retVal.add(DefaultCommonAttack.ID);

        retVal.add(DefaultCommonSkill.ID);
        retVal.add(DefaultCommonSkill.ID);

        retVal.add(Focusfire.ID);
        retVal.add(Channel.ID);
        retVal.add(Channel.ID);
        retVal.add(Channel.ID);
        
        return retVal;
    }

    // Starting Relics	
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();

        retVal.add(InfernalCore.ID);

        UnlockTracker.markRelicAsSeen(InfernalCore.ID);

        /*
        retVal.add(LoreDiary.ID);

        UnlockTracker.markRelicAsSeen(LoreDiary.ID);


         */
        return retVal;
    }

    // Character select screen effect
    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("ATTACK_DAGGER_1", 1.25f); // Sound Effect
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT,
                true); // Screen Effect
    }

    // Character select on-button-press sound effect
    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "ATTACK_DAGGER_1";
    }

    // Should return how much HP your maximum HP reduces by when starting a run at
    // ascension 14 or higher. (ironclad loses 5, defect and silent lose 4 hp respectively)
    @Override
    public int getAscensionMaxHPLoss() {
        return 0;
    }

    // Should return the card color enum to be associated with your character.
    @Override
    public AbstractCard.CardColor getCardColor() {
        return AbstractCardEnum.MAGICAL_COLOR;
    }

    // Should return a color object to be used to color the trail of moving cards
    @Override
    public Color getCardTrailColor() {
        return MagicalBase.DEFAULT_GRAY;
    }

    // Should return a BitmapFont object that you can use to customize how your
    // energy is displayed from within the energy orb.
    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }

    // Should return class name as it appears in run history screen.
    @Override
    public String getLocalizedCharacterName() {
        return "The Magical Gunner";
    }

    //Which starting card should specific events give you?
    @Override
    public AbstractCard getStartCardForEvent() {
        return new DefaultCommonAttack();
    }

    // The class name as it appears next to your player name in game	
    @Override
    public String getTitle(AbstractPlayer.PlayerClass playerClass) {
        return "the Corrupted";
    }

    // Should return a new instance of your character, sending this.name as its name parameter.
    @Override
    public AbstractPlayer newInstance() {
        return new Tasha(this.name, this.chosenClass);
    }

    // Should return a Color object to be used to color the miniature card images in run history.
    @Override
    public Color getCardRenderColor() {
        return MagicalBase.DEFAULT_GRAY;
    }

    // Should return a Color object to be used as screen tint effect when your
    // character attacks the heart.
    @Override
    public Color getSlashAttackColor() {
        return MagicalBase.DEFAULT_GRAY;
    }

    // Should return an AttackEffect array of any size greater than 0. These effects
    // will be played in sequence as your character's finishing combo on the heart.
    // Attack effects are the same as used in damage action and the like.
    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[] {
                AbstractGameAction.AttackEffect.FIRE };
    }

    // Should return a string containing what text is shown when your character is
    // about to attack the heart. For example, the defect is "NL You charge your
    // core to its maximum..."
    @Override
    public String getSpireHeartText() {
        return "You touch the heart.";
    }

    // The vampire events refer to the base game characters as "brother", "sister",
    // and "broken one" respectively.This method should return a String containing
    // the full text that will be displayed as the first screen of the vampires event.
    @Override
    public String getVampireText() {
        return "Navigating an unlit street, you come across several hooded figures in the midst of some dark ritual. As you approach, they turn to you in eerie unison. The tallest among them bares fanged teeth and extends a long, pale hand towards you. NL ~\"Join~ ~us~ ~basic~ ~one,~ ~and~ ~feel~ ~the~ ~warmth~ ~of~ ~the~ ~Spire.\"~";
    }
/*
	@Override
	public CustomCharSelectInfo getInfo() {
		return (CustomCharSelectInfo) getLoadout ();
	}
*/

}
