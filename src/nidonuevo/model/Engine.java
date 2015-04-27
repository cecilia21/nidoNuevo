/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//IMPORTANTE!!
//La inicializacion del juego se hará mediante un archivo XML, esta función esta implementada en
//la función init, donde se inicializan los resusos, items disponibles, mapas, etc.  que  tendrá 
//el juego

//El grabado de este XML se hizo mediante la función saveToXML
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
        loadFromXML();
        display=new Display(title,width,height);
        display.getFrame().addKeyListener(keyManager); //enlaza el key listener con el frame
        setSM(new StateMachine());
        
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
        
        getSM().add(LMS);
        //prueba del menu
        MainMenu menu=new MainMenu(this);
        getSM().add(menu);
   
        
        
    }
    private void tick(){
        keyManager.tick();
        if (getSM().getOrdenPop()) getSM().pop();
        if (!SM.getState().empty()){
            getSM().tick();
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
                        getSM().render(g);
			
		
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
		saveToXML();
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

    /**
     * @return the SM
     */
    public StateMachine getSM() {
        return SM;
    }

    /**
     * @param SM the SM to set
     */
    public void setSM(StateMachine SM) {
        this.SM = SM;
    }
    public void setPlayerName(String name){
        ((LocalMap)(SM.getState().get(0))).getPlayer().setName(name);
    }

    public void loadFromXML() {

    }
    public void saveToXML(){
        
    }
}
