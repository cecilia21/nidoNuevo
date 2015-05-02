/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nidonuevo.model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import nidonuevo.app.Display;

/**
 *
 * @author goldfenrir
 */
public class Loading extends Thread{
    private int i=0;
    private final int max=59;
    private boolean running=true;
     private Display display;
    private BufferStrategy bs;
    private Graphics g;
    private BufferedImage[] img=new BufferedImage[61];
    private JLabel label;
    public Loading(){
        
    }
    public Loading(Display display,BufferStrategy bs,Graphics g){
        for ( i=0;i<=max;i++){
           String temp="/output/tmp-"+i+".gif"; 
           img[i]=ImageLoader.loadImage(temp);
        }
        
       
        i=0;
        this.display=display;
        display.visible();
        this.bs=bs;
        this.g=g;
        
    }
    @Override
    public void run() {
       
        int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
        
		
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1){
				
				render();
                                
				ticks++;
				delta--;
			}
			
			if(timer >= 1000000000){
				System.out.println("Ticks and Frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
                
		
		

    }

    private void render() {
      bs=display.getCanvas().getBufferStrategy();
		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//Clear Screen
		g.clearRect(0, 0, display.getFrame().getWidth(), display.getFrame().getHeight());
		//Draw Here!
                
		g.drawImage(img[i],0, 0, display.getFrame().getWidth(), display.getFrame().getHeight(),null);
		i++; 
                if (i==max) i=0;	
		
		//End Drawing!
                
		bs.show();
                
		g.dispose();
    }

}
