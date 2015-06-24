/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nidonuevo.model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author TOSHIBA
 */
public class Friend extends Person implements Serializable{
    private String province;
    private int homePositionX;
    private int homePositionY;
    private int unlockLevel;
    public int map;
    private String pregunta;
    private ArrayList<String> alternativas;
    private int sol;
    public Image img;
    private boolean dibujable=true;
    /**
     * @return the province
     */
    
    public Friend(int positionX,int positionY,Image image, int map,
                        String preg,ArrayList<String> alt,int sol){
        super();
        this.positionX=positionX;
        this.positionY=positionY;
        this.img=image;
        this.map=map;
        pregunta=preg;
        alternativas=alt;
        this.sol=sol;
    }
    
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

    /**
     * @return the dibujable
     */
    public boolean isDibujable() {
        return dibujable;
    }

    /**
     * @param dibujable the dibujable to set
     */
    public void setDibujable(boolean dibujable) {
        this.dibujable = dibujable;
    }
    
}
