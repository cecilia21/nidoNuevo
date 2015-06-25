/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nidonuevo.model;

import java.awt.Graphics;
import java.awt.image.RescaleOp;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TOSHIBA
 */
public class TriggerMini extends Trigger{
    private MainMenu menu;
    private int changeTo;
    private Engine eng;
    
    public TriggerMini(Engine eng,int x,int y,int changeTo){
        this.eng=eng;
        this.x=x;
        this.y=y;
        this.changeTo=changeTo;
        this.active=true;
      
        
    }

  
    @Override
    public void execTrigger(LocalMap aThis,int i) {
        
        if (this.active){
 
            int currmap=aThis.getPlayer().getCurrentMap();
//            if(eng.modoEspectador){ 
//                try {
//                sleep(2000);
//                currmap=aThis.getPlayer().getAmigos().get(eng.jugEsp-1).map;
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(TriggerMini.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }               
            //aThis.setChange(true);
            int tt=aThis.getMaps().get(currmap).getTriggers().size();
            int tm=aThis.getMaps().get(currmap).getTriggersMini().size();
            System.out.println("Si");  
            aThis.getPlayer().getCorrectos().set(i-tt+tm, true);

//            aThis.getPlayer().correct=true;
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
