package MagicalMod.variables.secondstats;

import MagicalMod.cards.AbstractCorrCard;
import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class SecondMagicNumber extends DynamicVariable {

    //For in-depth comments, check the other variable(CustomVariable). It's nearly identical.

    @Override
    public String key() {
        return("2ndM");
        // This is what you put between "!!" in your card strings to actually display the number.
        // You can name this anything (no spaces), but please pre-phase it with your mod name as otherwise mod conflicts can occur.
        // Remember, we're using makeID so it automatically puts "DiamondMod:" (or, your id) before the name.
    }

    @Override
    public boolean isModified(AbstractCard card) {
        return ((AbstractCorrCard) card).isSecondMagicNumberModified;

    }

    @Override
    public int value(AbstractCard card) {
        return ((AbstractCorrCard) card). SecondMagicNumber;
    }

    @Override
    public int baseValue(AbstractCard card) {
        return ((AbstractCorrCard) card).BaseSecondMagicNumber;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return ((AbstractCorrCard) card).upgradedSecondMagicNumber;
    }
}