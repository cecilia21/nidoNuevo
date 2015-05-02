/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nidonuevo.model;

import java.awt.Graphics;

/**
 *
 * @author TOSHIBA
 */
class  Trigger {
    protected int x;
    protected int y;
    protected boolean active;
    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    void execTrigger(LocalMap aThis){
        
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }




    
}
