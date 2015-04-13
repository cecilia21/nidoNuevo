/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nidonuevo.model;
import java.util.Stack;
import java.util.ArrayList;
/**
 *
 * @author pucp
 */
public class InGameMenu {
    protected Stack<SubMenu> subMenus;
    private int posY;
    protected ArrayList<String> options;
    public void render(){
        
    }
    public void update(){
        
    }
    public void onEnter(){
        
    }
    public void onExit(){
        
    }        

    /**
     * @return the posY
     */
    public int getPosY() {
        return posY;
    }

    /**
     * @param posY the posY to set
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }
        
}
