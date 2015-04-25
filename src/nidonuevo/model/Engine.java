/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nidonuevo.model;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.File;
import nidonuevo.app.Display;
public class Engine implements Runnable{
    private String title;
    private int width, height;
    private Display display;
    private Boolean running=false;
    private Thread thread;
    private BufferStrategy bs;
    private Graphics g;
    //layer de collision
    private Layer lc;
    //Actual map
    private int currentMap=0;
    
    //Input
    private KeyManager keyManager;
    
    //States
    private StateMachine SM;
    private LocalMap LMS;
    
    public Engine(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height =height;  
        keyManager = new KeyManager();
        
    }   
    public void start(){
        if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start(); //run();
    }
    private void init(){
        display=new Display(title,width,height);
        display.getFrame().addKeyListener(keyManager); //enlaza el key listener con el frame
        SM=new StateMachine();
        
        String[] paths=new String[2];
//        String s=new File("a.txt").getAbsolutePath();
        paths[0]="src/img/l1.txt";
        
        paths[1]="src/img/lc1.txt";
        String[] dirImg=new String[2];
        dirImg[0]="/img/l1.png";
        dirImg[1]="/img/lc1.png";
        Map map=new Map(this,2,paths,dirImg);//eng, cant layer, paths2, iamgeleyer
        lc=map.getLC();
        LMS=new LocalMap(this);
        LMS.getMaps().add(map);
        
        //creando
        
        SM.add(LMS);
        //prueba del menu
        MainMenu menu=new MainMenu();
        SM.add(menu);
        
    }
    private void tick(){
        keyManager.tick();
        if (!SM.getState().empty()){
            SM.tick();
        }
    }
    private void render(){
        bs = display.getCanvas().getBufferStrategy();
		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//Clear Screen
		g.clearRect(0, 0, width, height);
		//Draw Here!
		
		if(!SM.getState().empty())
                        SM.render(g);
			
		
		//End Drawing!
		bs.show();
		g.dispose();
    }
    public void getInput(){
        
    }
    
    public void setOutput(){
        
    }
    
    public void validate(){
        
    }
    public void run(){
		
		init();
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
				tick();
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
		
		stop();
		
	}
    
    public synchronized void stop(){
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
    public int getCurrentMap(){
        return currentMap;
        
    }
    public KeyManager getKeyManager(){
        return this.keyManager;
    }

    /**
     * @return the lc
     */
    public Layer getLc() {
        return lc;
    }
}
