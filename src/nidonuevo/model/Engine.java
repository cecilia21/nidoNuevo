/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//IMPORTANTE!!
//La inicializacion del juego se hará mediante un archivo XML, esta función esta implementada en
//la función init, donde se inicializan los resusos, items disponibles, mapas, etc.  que  tendrá 
//el juego

//El grabado de este XML se hizo mediante la función saveGameToXML
//La lectura sera loadGameFromXML
package nidonuevo.model;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import nidonuevo.app.Display;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
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
        loadGameFromXML();
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

        SM.add(menu);

        
        
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
		saveGameToXML();
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



    
    public void saveToXML() {
        try {
            Thread.sleep(3000);//Para esperar a q se cargue todo, despues lo borraremos
        } catch (InterruptedException ex) {
            Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
        }
        Document document=DocumentHelper.createDocument();
        Element root=document.addElement("GameData");
        //Todo lo de player
        Element player=root.addElement("Player");
        player.addElement("name").addText(LMS.getPlayer().getName());
        player.addElement("happiness").addText(""+LMS.getPlayer().getHappiness());
        player.addElement("numberOfFriends").addText(""+LMS.getPlayer().getNumberOfFriends());
        player.addElement("level").addText(""+LMS.getPlayer().getLevel());
        player.addElement("numerOfTrophies").addText(""+LMS.getPlayer().getNumerOfTrophies());
        for(int i=0;i<LMS.getPlayer().getFriends().size();i++){
            Element friend=player.addElement("Friend")
                    .addAttribute("id",""+LMS.getPlayer().getFriends().get(i).getId());
        }
        Element inventory=player.addElement("Inventory");
        inventory.addElement("Capacity").addText(""+LMS.getPlayer().getInventory().getCapacity());
        inventory.addElement("Qunatity").addText(""+LMS.getPlayer().getInventory().getQuantity());        
        for(int i=0;i<LMS.getPlayer().getInventory().getItems().size();i++){
            Element item=inventory.addElement("Item")
                    .addAttribute("id",""+LMS.getPlayer().getInventory().getItems().get(i).getId());
            //Como vamos a manejar la cantidad de items?
        }
        //Mapa Actual
        Element cMap=root.addElement("CurrentMap");
        cMap.addElement("Map").addText(""+currentMap);
        
        try { 
            XMLWriter writer=new XMLWriter(new FileWriter("GameData.xml"));
            writer.write(document);
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
        }
  
        
    } 
    
    public void loadToXML() {
        SAXReader reader= new SAXReader();
        try {    
            Document document=reader.read("GameData.xml");
            Element root=document.getRootElement();
            Element player=root.element("Player");
            String name=player.element("name").getText();
            double happiness=Double.parseDouble(player.element("happiness").getText());
            //Falta sacar mas elementos......
        } catch (DocumentException ex) {
            Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadGameFromXML() {
       }

    private void saveGameToXML() {
       
    }
    

}
