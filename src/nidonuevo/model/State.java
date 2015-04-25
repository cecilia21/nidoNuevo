/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nidonuevo.model;

import java.awt.Graphics;


public abstract class State {

    public void render(Graphics g){
        
    }
    public abstract boolean ordenPop();
    public void tick(){
        
    }
    public void onEnter(){
        
    }
    public void onExit(){
        
    }        
    
}
