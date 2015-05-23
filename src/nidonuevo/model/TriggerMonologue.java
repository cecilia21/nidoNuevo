/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nidonuevo.model;

/**
 *
 * @author alulab14
 */
public class TriggerMonologue extends Trigger {
    //private int pX;
    //private int pY;
    //private int changeTo;
    
    public TriggerMonologue(int x,int y){
        this.x=x;
        this.y=y;
        this.active=true;
    }
    
    @Override
    public void execTrigger(LocalMap aThis) {
        
        if (this.active){
            //LLAMANDO A RAUL c:
        }
        
    }

}
