/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nidonuevo.model;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
/**
 *
 * @author TOSHIBA
 */
public class Selector {
    
    protected BufferedImage sprite;
    private int optY; //option default
    private int optX;
    private String path;
    private int x,y,w,h;
    private int stepY;
    private int stepX;
    private int contDelay=10;
    private int delay=contDelay;
    private int max_optsY;
    private int max_optsX;
    public Selector(int x,int y,int w,int h,int stepY,int stepX,int maxY,int maxX,String path){
        this.max_optsY=maxY;
        this.max_optsX=maxX;
        this.optY=1;
        this.optX=1;
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;
        this.stepY=stepY;
        this.stepX=stepX;
        //this.path="/img/selector.png";
        this.path=path;
        sprite = ImageLoader.loadImage(path);
            
        
        
   
        
        
        
    }
    public void down(){
        
        if(getOpt()<max_optsY) if(delay==0) {y+=stepY; delay=contDelay; optY++;} else delay--;
        
    }
    public void right(){
     
        if(getOptX()<max_optsX) if(delay==0) {x+=stepX; delay=contDelay; optX++;} else delay--;
        
    }
    public void up(){
     
        if(getOpt()>1) if(delay==0) {y-=stepY; delay=contDelay; optY--;} else delay--;
        
    }
    
    public void left(){
     
        if(getOptX()>1) if(delay==0) {x-=stepX; delay=contDelay; optX--;} else delay--;
        
    }
    
    public void render(Graphics g){
        

        g.drawImage(sprite,x,y,w,h, null);
        print();

    }

    /**
     * @return the opt
     */
    public int getOpt() {
        return optY;
    }

    public int getOptX() {
        return optX;
    }
    public void print(){
        System.out.println("Y: "+optY+" , X:"+optX);
    }
}
