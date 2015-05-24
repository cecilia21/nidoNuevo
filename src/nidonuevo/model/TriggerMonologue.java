/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nidonuevo.model;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import nidonuevo.app.Display;
import javax.swing.JFrame;

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
        System.out.println("ejucutar monolgo");
        if (this.active){
            aThis.getEng().getDisplay().setOnMonPanel();
        }
        
    }
 
    public int getChangeTo(){
        return 1;
    }     

}
