/*    */ package MagicalMod.actions;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
/*    */
/*    */
/*    */ import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
/*    */ 
/*    */ public class OverheadAction extends com.megacrit.cardcrawl.actions.AbstractGameAction
/*    */ {
/*    */   private DamageInfo info;
/*    */   private static final float DURATION = 0.1F;
/*    */   
/*    */   public OverheadAction(AbstractCreature target, DamageInfo info)
/*    */   {
/* 23 */     this.info = info;
/* 24 */     setValues(target, info);
/* 26 */     this.actionType = ActionType.DAMAGE;
/* 27 */     this.duration = 0.1F;
/*    */   }
/*    */   
/*    */   public void update()
/*    */   {
/* 32 */     if ((this.duration == 0.1F) && 
/* 33 */       (this.target != null)) {
/* 34 */       AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/*    */       
/* 36 */       this.target.damage(this.info);
/*    */       
/* 38 */      if ((((AbstractMonster)this.target).isDying) || (this.target.currentHealth <= 0)) {
	

/* 40 */         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.getMonsters().getRandomMonster(true), AbstractDungeon.player,
        new VulnerablePower(AbstractDungeon.getMonsters().getRandomMonster(true), 1, false), 1));

AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.getMonsters().getRandomMonster(true), AbstractDungeon.player,
        new StrengthPower(AbstractDungeon.getMonsters().getRandomMonster(true), -1), -1));
/*    */         
/*    */ 
/*    */       }
/*    */       
/* 48 */       if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
/* 49 */         AbstractDungeon.actionManager.clearPostCombatActions();
/*    */       }
/*    */     }
/*    */     
/*    */ 
/* 54 */     tickDuration();
/*    */   }
/*    */ }