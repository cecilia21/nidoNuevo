/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nidonuevo.model;

import java.awt.Graphics;
import java.awt.image.RescaleOp;

/**
 *
 * @author TOSHIBA
 */
public class TriggerMini extends Trigger{
    private MainMenu menu;
    private int changeTo;
    
    public TriggerMini(int x,int y,int changeTo){
        this.x=x;
        this.y=y;
        this.changeTo=changeTo;
        this.active=true;
      
        
    }

  
    @Override
    public void execTrigger(LocalMap aThis) {
        
        if (this.active){
            //aThis.setChange(true);
            System.out.println("Si");  
            aThis.getPlayer().correct=true;
            this.active=false;
            //aThis.getPlayer().positionX=800;
            //aThis.getPlayer().positionY=800;
          //  aThis.getEng().getSM().add(mini);
            
          
             

           
        }
        
    }

    /**
     * @return the pX


    /**
     * @return the changeTo
     */
    public int getChangeTo() {
        return changeTo;
    }

    /**
     * @param changeTo the changeTo to set
     */
    public void setChangeTo(int changeTo) {
        this.changeTo = changeTo;
    }
    
}
