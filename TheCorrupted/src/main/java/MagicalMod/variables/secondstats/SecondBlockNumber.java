package MagicalMod.variables.secondstats;

import MagicalMod.cards.AbstractCorrCard;
import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;

import static MagicalMod.MagicalBase.makeID;

public class SecondBlockNumber extends DynamicVariable {

    //For in-depth comments, check the other variable(CustomVariable). It's nearly identical.

    @Override
    public String key() {
        return makeID("B2");
        // This is what you put between "!!" in your card strings to actually display the number.
        // You can name this anything (no spaces), but please pre-phase it with your mod name as otherwise mod conflicts can occur.
        // Remember, we're using makeID so it automatically puts "DiamondMod:" (or, your id) before the name.
    }

    @Override
    public boolean isModified(AbstractCard card) {
        return ((AbstractCorrCard) card).isSecondBlockModified;

    }

    @Override
    public int value(AbstractCard card) {
        return ((AbstractCorrCard) card). SecondBlock;
    }

    @Override
    public int baseValue(AbstractCard card) {
        return ((AbstractCorrCard) card).BaseSecondBlock;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return ((AbstractCorrCard) card).upgradedSecondBlock;
    }
}