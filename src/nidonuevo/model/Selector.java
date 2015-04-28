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
    private int opt; //option default
    private String path;
    private int x,y,w,h;
    private int stepY;
    private int contDelay=10;
    private int delay=contDelay;
    private int max_opts;
    public Selector(int x,int y,int w,int h,int stepY,int max){
        this.max_opts=max;
        this.opt=1;
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;
        this.stepY=stepY;
        path="/img/selector.png";
        
        sprite = ImageLoader.loadImage(path);
            
        
        
   
        
        
        
    }
    public void down(){
        
        if(getOpt()<max_opts) if(delay==0) {y+=stepY; delay=contDelay; opt++;} else delay--;
        
    }
    public void up(){
     
        if(getOpt()>1) if(delay==0) {y-=stepY; delay=contDelay; opt--;} else delay--;
        
    }
    public void render(Graphics g){
        

        g.drawImage(sprite,x,y,w,h, null);

    }

    /**
     * @return the opt
     */
    public int getOpt() {
        return opt;
    }
}
