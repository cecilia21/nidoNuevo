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
public class Button {
    private final int quanSp=3;
    protected BufferedImage[] sprite;
    private String[] paths;
    private int x,y,w,h;
    private Font fnt0;
    private String title;
    private final int fontSize=50; //leer xml
     //cantidad de sprites
    public Button(String title,int x,int y,int w,int h){
        //new font
        
        
        
        
        this.title=title;
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;
        sprite=new BufferedImage[quanSp];
        paths=new String[quanSp];
        paths[0]="/img/buttons/buttonF.png"; //leer desde xml
        paths[1]="/img/buttons/button2.png";
        paths[2]="/img/buttons/button1.png";
        for(int i=0;i<quanSp;i++){
            sprite[i] = ImageLoader.loadImage(paths[i]);
            
        }
        
        fnt0 =new Font("Monotype Corsiva",Font.BOLD,fontSize);
        
        
        
    }
    public void render(Graphics g){
        
        g.setFont(fnt0);
        g.setColor(Color.black);
        g.drawImage(sprite[0],x,y,w,h, null);
        int cen=(int)(sprite[0].getWidth()-title.length()*((int)(fontSize*0.65)))/(2);
        g.drawString(title,x+cen,y+fontSize); 
    }
}
