package MagicalMod.cards;

import basemod.abstracts.CustomCard;

public abstract class AbstractCorrCard extends CustomCard {

    // Custom Abstract Cards can be a bit confusing. While this is a simple base for simply adding a second magic number,
    // if you're new to modding I suggest you skip this file until you know what unique things that aren't provided
    // by default, that you need in your own cards.

    // In this example, we use a custom Abstract Card in order to define a new magic number. From here on out, we can
    // simply use that in our cards, so long as we put "extends AbstractDynamicCard" instead of "extends AbstractCorrCard" at the start.
    // In simple terms, it's for things that we don't want to define again and again in every single card we make.

    public int SecondMagicNumber;        // Just like magic number, or any number for that matter, we want our regular, modifiable stat
    public int BaseSecondMagicNumber;    // And our base stat - the number in it's base state. It will reset to that by default.
    public boolean upgradedSecondMagicNumber; // A boolean to check whether the number has been upgraded or not.
    public boolean isSecondMagicNumberModified; // A boolean to check whether the number has been modified or not, for coloring purposes. (red/green)

    public int BaseSecondBlock;
    public int SecondBlock;
    public boolean upgradedSecondBlock;
    public boolean isSecondBlockModified;

    public int BaseSecondDamage;
    public int SecondDamage;
    public boolean upgradedSecondDamage;
    public boolean isSecondDamageModified;
    public String img;

    public AbstractCorrCard(final String id,
                            final String name,
                            final String img,
                            final int cost,
                            final String rawDescription,
                            final CardType type,
                            final CardColor color,
                            final CardRarity rarity,
                            final CardTarget target) {


        super(id, name,

                img,

                cost, rawDescription, type, color, rarity, target);

this.img = img;

        // Set all the things to their default values.
        isCostModified = false;
        isCostModifiedForTurn = false;
        isDamageModified = false;
        isBlockModified = false;
        isMagicNumberModified = false;
        isSecondMagicNumberModified = false;
        isSecondBlockModified = false;
        isSecondDamageModified = false;
    }

    public void displayUpgrades() { // Display the upgrade - when you click a card to upgrade it
        super.displayUpgrades();

        if (upgradedSecondMagicNumber) { // If we set upgradedSecondMagicNumber = true in our card.
            SecondMagicNumber = BaseSecondMagicNumber; // Show how the number changes, as out of combat, the base number of a card is shown.
            isSecondMagicNumberModified = true; // Modified = true, color it green to highlight that the number is being changed.
        }

        if (upgradedSecondBlock) { // If we set upgradedSecondMagicNumber = true in our card.
            SecondBlock = BaseSecondBlock; // Show how the number changes, as out of combat, the base number of a card is shown.
            isSecondBlockModified = true; // Modified = true, color it green to highlight that the number is being changed.
        }

        if (upgradedSecondDamage) { // If we set upgradedSecondMagicNumber = true in our card.
            SecondDamage = BaseSecondDamage; // Show how the number changes, as out of combat, the base number of a card is shown.
            isSecondDamageModified = true; // Modified = true, color it green to highlight that the number is being changed.
        }

    }

    public void UpgradeSecondMagicNumber(int amount) { // If we're upgrading (read: changing) the number. Note "upgrade" and NOT "upgraded" - 2 different things. One is a boolean, and then this one is what you will usually use - change the integer by how much you want to upgrade.
        BaseSecondMagicNumber += amount; // Upgrade the number by the amount you provide in your card.
        SecondMagicNumber = BaseSecondMagicNumber; // Set the number to be equal to the base value.
        upgradedSecondMagicNumber = true; // Upgraded = true - which does what the above method does.
    }

    public void UpgradeSecondBlock(int amount) { // If we're upgrading (read: changing) the number. Note "upgrade" and NOT "upgraded" - 2 different things. One is a boolean, and then this one is what you will usually use - change the integer by how much you want to upgrade.
        BaseSecondBlock += amount; // Upgrade the number by the amount you provide in your card.
        SecondBlock = BaseSecondBlock; // Set the number to be equal to the base value.
        upgradedSecondBlock = true; // Upgraded = true - which does what the above method does.
    }

    public void UpgradeSecondDamage(int amount) { // If we're upgrading (read: changing) the number. Note "upgrade" and NOT "upgraded" - 2 different things. One is a boolean, and then this one is what you will usually use - change the integer by how much you want to upgrade.
        BaseSecondDamage += amount; // Upgrade the number by the amount you provide in your card.
        SecondDamage = BaseSecondDamage; // Set the number to be equal to the base value.
        upgradedSecondDamage = true; // Upgraded = true - which does what the above method does.
    }


}