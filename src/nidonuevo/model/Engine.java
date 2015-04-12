/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nidonuevo.model;
import nidonuevo.app.Display;
public class Engine implements Runnable{
    private String title;
    private int width, height;
    private Display display;
    private Boolean running=false;
    private Thread thread;
    //Input
    private KeyManager keyManager;
    public Engine(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height =height;  
        keyManager = new KeyManager();
        
    }   
    public void start(){
        this.run();
    }
    private void init(){
        display=new Display(title,width,height);
        display.getFrame().addKeyListener(keyManager); //enlaza el key listener con el frame
    }
    public void getInput(){
        
    }
    
    public void setOutput(){
        
    }
    
    public void validate(){
        
    }
    public void run(){
		
		init();
		
		
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
}
