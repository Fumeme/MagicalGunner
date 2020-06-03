/*    */ package MagicalMod.actions;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
import MagicalMod.powers.ManaBlightPower;

/*    */
/*    */ public class ManaBlightTriggerAction extends AbstractGameAction
/*    */ {
int apply = 0;
int remove = 0;
	/*    */
	/*    */ public ManaBlightTriggerAction(AbstractCreature target, AbstractCreature source, int addTwo, int addOne)
	/*    */ {
		/* 26 */ this.actionType = ActionType.POWER;
		/* 27 */ this.duration = 0.2f;
		this.target = target;
		this.source = source;
		this.apply = addTwo;
		this.remove = addOne;
		/*    */ }

	/*    */
	/*    */ public void update()
	/*    */ {
		System.out.println("starting action");

        if(target.hasPower(ManaBlightPower.POWER_ID)) {
        	System.out.println("checking if " + target + " has mana blight.");
        	
            if (target.getPower(ManaBlightPower.POWER_ID) instanceof TwoAmountPower) {
            	System.out.println("adding " + this.apply + "to " + target + "'s Mana Blight counter.");
            	
            	target.getPower(ManaBlightPower.POWER_ID).flash();
                ((TwoAmountPower)target.getPower(ManaBlightPower.POWER_ID)).amount2 += this.apply;
                target.getPower(ManaBlightPower.POWER_ID).amount += this.remove;
              }
            }

		isDone = true;
		/*    */ }
	 public Color getColor()
	  {
	    return Color.SKY;
	  }
}