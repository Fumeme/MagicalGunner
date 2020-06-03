package MagicalMod.cards.ammo;

import MagicalMod.MagicalBase;
import MagicalMod.cards.AbstractCorrCard;
import MagicalMod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Iterator;

public class Stockpile extends AbstractCorrCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Strike Deal 7(9) damage.
     */

    // TEXT DECLARATION

    public static final String ID = MagicalBase.makeID("Stockpile");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG = MagicalBase.makePath(MagicalBase.DEFAULT_COMMON_ATTACK);

    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = AbstractCardEnum.MAGICAL_COLOR;

    private static final int COST = 2;
int NumofAmmo = 3;
    // /STAT DECLARATION/

    public Stockpile() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);

        this.baseMagicNumber = this.magicNumber = NumofAmmo;
        this.exhaust = true;

    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard c;
        for (int i = 0; i < this.magicNumber; i++) {
            c = returnRandomAmmo().makeCopy();
            addToBot( new MakeTempCardInHandAction(c, true));
        }
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

    // Which card to return when making a copy of this card.
    @Override
    public AbstractCard makeCopy() {
        return new Stockpile();
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
            this.initializeDescription();
        }
    }
}