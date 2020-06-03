/*    */ package MagicalMod.actions;

import MagicalMod.powers.MenacingPower;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

/*    */

/*    */
/*    */ public class GainMenacingAction extends AbstractGameAction
/*    */ {
int apply = 0;
int Turns = 0;
	/*    */
	/*    */ public GainMenacingAction(AbstractCreature target, AbstractCreature source, int Apply, int Turns)
	/*    */ {
		/* 26 */ this.actionType = ActionType.POWER;
		/* 27 */ this.duration = 0.2f;
		this.target = target;
		this.source = source;
		this.apply = Apply;
		this.Turns = Turns;
		/*    */ }

	/*    */
	/*    */ public void update()
	/*    */ {
		System.out.println("starting action");

        if(target.hasPower(MenacingPower.POWER_ID)) {
        	System.out.println("checking if " + target + " has menacing.");
        	
            if (target.getPower(MenacingPower.POWER_ID) instanceof TwoAmountPower) {
            	System.out.println("adding " + this.apply + "to " + target + "'s Menacing counter.");

            	target.getPower(MenacingPower.POWER_ID).flash();
                ((TwoAmountPower)target.getPower(MenacingPower.POWER_ID)).amount2 += this.apply;
                target.getPower(MenacingPower.POWER_ID).amount += this.Turns;
              }
            }else{

			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, source, new MenacingPower(target, source, this.Turns, this.apply), this.Turns));

		}

		isDone = true;
		/*    */ }
	 public Color getColor()
	  {
	    return Color.GOLD;
	  }
}