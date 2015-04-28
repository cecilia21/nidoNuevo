/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nidonuevo.model;

import java.io.Serializable;

/**
 *
 * @author TOSHIBA
 */
public class Friend extends Person implements Serializable{
    private String province;
    private int homePositionX;
    private int homePositionY;
    private int unlockLevel;
    

    /**
     * @return the province
     */
    public String getProvince() {
        return province;
    }

    /**
     * @param province the province to set
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * @return the homePositionX
     */
    public int getHomePositionX() {
        return homePositionX;
    }

    /**
     * @param homePositionX the homePositionX to set
     */
    public void setHomePositionX(int homePositionX) {
        this.homePositionX = homePositionX;
    }

    /**
     * @return the homePositionY
     */
    public int getHomePositionY() {
        return homePositionY;
    }

    /**
     * @param homePositionY the homePositionY to set
     */
    public void setHomePositionY(int homePositionY) {
        this.homePositionY = homePositionY;
    }

    /**
     * @return the unlockLevel
     */
    public int getUnlockLevel() {
        return unlockLevel;
    }

    /**
     * @param unlockLevel the unlockLevel to set
     */
    public void setUnlockLevel(int unlockLevel) {
        this.unlockLevel = unlockLevel;
    }
    public void move(){
        
    }
    public void talk(){ 
    }
    public void giveItem(){
        
    }
    public void addItem(){
        
    }   
    public void consumeItem(){
        
    }
    
}
