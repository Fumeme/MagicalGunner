/*    */ package MagicalMod.actions;
/*    */ import java.util.UUID;

/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ 
/*    */ public class ModifyMagicAction extends com.megacrit.cardcrawl.actions.AbstractGameAction
/*    */ {
/*    */   UUID uuid;
/*    */   
/*    */   public ModifyMagicAction(UUID targetUUID, int amount)
/*    */   {
/* 13 */     setValues(this.target, this.source, amount);
/* 14 */     this.actionType = ActionType.CARD_MANIPULATION;
/* 15 */     this.uuid = targetUUID;
/*    */   }
/*    */   
/*    */   public void update()
/*    */   {
/* 20 */     for (AbstractCard c : com.megacrit.cardcrawl.helpers.GetAllInBattleInstances.get(this.uuid)) {
/* 21 */       c.baseMagicNumber += this.amount;
/* 22 */       if (c.baseMagicNumber < 0) {
/* 23 */         c.baseMagicNumber = 0;
/*    */       }
/*    */     }
/* 26 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\ModifyDamageAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */