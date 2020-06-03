 package MagicalMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import MagicalMod.powers.ManaShellPower;


 public class ManaShellAction extends AbstractGameAction
 {
	 private AbstractPlayer p;
	  private boolean freeToPlayOnce = false;
	  private boolean upgraded = false;
			private int energyOnUse = -1;
	
	 public ManaShellAction(AbstractPlayer p, boolean upgraded, boolean freeToPlayOnce, int energyOnUse) {
 this.p = AbstractDungeon.player;
 this.duration = Settings.ACTION_DUR_FAST;

     this.freeToPlayOnce = freeToPlayOnce;
    this.upgraded = upgraded;
    this.actionType = AbstractGameAction.ActionType.SPECIAL;
    this.energyOnUse = energyOnUse;
		 }

	
	 public void update()
	 {
		
		 int effect = EnergyPanel.totalCount;

		      
		      if (this.p.hasRelic("Chemical X")) {
			        effect += 2;
			        this.p.getRelic("Chemical X").flash();
		      }
		      
		      if (this.upgraded) {
			        effect++;
		      }
		      
		     if (effect > 0) {
		        	AbstractDungeon.actionManager.
		           	addToBottom(new 
		           			ApplyPowerAction(p, p, new ManaShellPower(p, p, effect), effect));
		  
			       if (!this.freeToPlayOnce) {
				         this.p.energy.use(EnergyPanel.totalCount);
		        }
		      }
		     this.isDone = true;
		
		
		 }
	 }