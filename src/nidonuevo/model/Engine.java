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
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import nidonuevo.app.Display;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
public class Engine implements Runnable{
    private Loading loading;
    private int cantGuar=0;
    private String title;
    private int width, height;
    private Display display;
    private Boolean running=false;
    private Thread thread;
    private BufferStrategy bs;
    private Graphics g;
    
    //layer de collision
 //   private Layer lc;
    //Actual map
    private int currentMap=0;
    
    //Input
    private KeyManager keyManager;
    
    //States
    private StateMachine SM;
    private LocalMap LMS;
    
    public Engine(String title,int width,int height){
        
        
        
        display=new Display(title, width, height);
        
        loading=new Loading(display,bs,g);
        loading.setPriority(loading.MAX_PRIORITY);
        loading.start();
        
        keyManager = new KeyManager();
        display.getFrame().addKeyListener(keyManager);
        setSM(new StateMachine());
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
        //VAMOS A GUARDAR LA CONFIGURACION INICIAL DEL JUEGO
        saveGameToXML();     
        loading.stop();
        //Utils.sleepFor(5000);
    }
    private void tick(){
        keyManager.tick();
        if (getSM().getOrdenPop()) 
            getSM().pop();
        if (!SM.getState().empty()){
            getSM().tick();
        }
    }
    private void render(){
        setBs(display.getCanvas().getBufferStrategy());
		if(getBs() == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = getBs().getDrawGraphics();
		//Clear Screen
		g.clearRect(0, 0, getWidth(), getHeight());
		//Draw Here!
		
		if(!SM.getState().empty())
                        getSM().render(g);
			
		
		//End Drawing!
                if (LMS.isChange()) Utils.imgB(g, 0, 0, this.getWidth(), this.getHeight(), LMS.getBright());
		getBs().show();
                
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
				if (LMS.isChange()==false) tick();
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
        saveToXML();
    }



    
    public void saveToXML() {
        
//        try {
//            Thread.sleep(3000);//Para esperar a q se cargue todo, despues lo borraremos
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
//        }
        Document document=DocumentHelper.createDocument();
        Element root=document.addElement("GameData");
        //PLAYER
        Element player=root.addElement("Player");
        player.addElement("name").addText(LMS.getPlayer().getName());
        player.addElement("happiness").addText(""+LMS.getPlayer().getHappiness());
        player.addElement("numberOfFriends").addText(""+LMS.getPlayer().getNumberOfFriends());
        player.addElement("level").addText(""+LMS.getPlayer().getLevel());
        player.addElement("numerOfTrophies").addText(""+LMS.getPlayer().getNumberOfTrophies());
        ////FRIENDS
        Element friends=player.addElement("Friends");
        for(int i=0;i<LMS.getPlayer().getFriends().size();i++){
            Element friend=friends.addElement("Friend")
                    .addAttribute("id",""+LMS.getPlayer().getFriends().get(i).getId());
        }
        ////INVENTORY
        Element inventory=player.addElement("Inventory");
        inventory.addElement("Capacity").addText(""+LMS.getPlayer().getInventory().getCapacity());
        inventory.addElement("Quantity").addText(""+LMS.getPlayer().getInventory().getQuantity());  
        //////ITEMS
        Element items=inventory.addElement("Items");
        for(int i=0;i<LMS.getPlayer().getInventory().getItems().size();i++){
            Element item=items.addElement("Item")
                    .addAttribute("id",""+LMS.getPlayer().getInventory().getItems().get(i).getId());
            item.addElement("stock").addText(""+LMS.getPlayer().getInventory().getItems().get(i).getStock());    
        }
        //Mapa Actual
        Element cMap=root.addElement("CurrentMap");
        cMap.addElement("Map").addText(""+currentMap);
        
        try { 
            OutputFormat format = OutputFormat.createPrettyPrint();
             format.setIndent(true);
            XMLWriter writer=new XMLWriter(new FileWriter("GameData.xml"),format);
            writer.write(document);
            writer.setIndentLevel(2);
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
            //Player
            Element player=root.element("Player");
            String name=player.element("name").getText();
            double happiness=Double.parseDouble(player.element("happiness").getText());
            int numberOfFriends=Integer.parseInt(player.element("numberOfFriends").getText());
            int level=Integer.parseInt(player.element("level").getText());
            int numberOfTrophies=Integer.parseInt(player.element("numerOfTrophies").getText());
            //Friends
            Element friends=player.element("Friends");
            int idFriends[]=new int[20],cantF=0;
            for(Iterator i=friends.elementIterator("product");i.hasNext();){
                Element friend=(Element)i.next();
                int id=Integer.parseInt(friend.attribute("id").getText());
                idFriends[cantF++]=id;
            }
            //Inventory
            Element inventory=player.element("Inventory");
            int capacity=Integer.parseInt(inventory.element("Capacity").getText());
            int quantity=Integer.parseInt(inventory.element("Quantity").getText());
            //Items
            Element items=inventory.element("Items");
            int idItems[]=new int[20],stocks[]=new int[20],cantI=0;
            for(Iterator i=items.elementIterator("Item");i.hasNext();){
                Element item=(Element)i.next();
                int id=Integer.parseInt(item.attribute("id").getText());
                int stock=Integer.parseInt(item.element("stock").getText());
                idItems[cantI]=id;idItems[cantI]=stock;
                cantI++;
            }
            //Current Map
            Element currentMap=root.element("CurrentMap");
            int mapId=Integer.parseInt(currentMap.element("Map").getText());
            
        } catch (DocumentException ex) {
            Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadGameFromXML() {
        SAXReader reader= new SAXReader();
        try {    
            Document document=reader.read("GameInit.xml");
            Element root=document.getRootElement();
            //General
            Element general=root.element("General");
            title=general.element("title").getText();
            setWidth(Integer.parseInt(general.element("width").getText()));
            setHeight(Integer.parseInt(general.element("height").getText()));
            
            
            
            
            //Maps
            LMS=new LocalMap(this);
            Element maps=root.element("Maps");
            for(Iterator i=maps.elementIterator("Map");i.hasNext();){
                
                Element map=(Element)i.next();
                
                int id=Integer.parseInt(map.attribute("id").getText());
                int numberLayers=Integer.parseInt(map.element("NumberLayers").getText());
                String[] paths=new String[numberLayers];
                String[] dirImg=new String[numberLayers];
                Element source=map.element("Source");
                int j1=0;
                Iterator k=source.elementIterator("Img");
                for (Iterator j=source.elementIterator("Path");j.hasNext();j1++){
                    Element path=(Element)j.next();
                    Element dir=(Element)k.next();
                    paths[j1]=path.getText();
                    dirImg[j1]=dir.getText();
                }
                Map map1=new Map(this,numberLayers,paths,dirImg);
                //setLc(map1.getLC());//arreglar las colisiones, mas mapas
                
                
                
                Element triggers=map.element("Triggers");
                for (Iterator j=triggers.elementIterator("Trigger");j.hasNext();){
                    Element trigger=(Element)j.next();
                    if(0==trigger.element("type").getText().compareTo("TriggerChangeMap")){
                        Iterator u=trigger.elementIterator("par");
                        Element parametro=(Element)u.next();
                        int par1=Integer.parseInt(parametro.getText()); parametro=(Element)u.next();
                        int par2=Integer.parseInt(parametro.getText()); parametro=(Element)u.next();
                        int par3=Integer.parseInt(parametro.getText()); parametro=(Element)u.next();
                        int par4=Integer.parseInt(parametro.getText()); parametro=(Element)u.next();
                        int par5=Integer.parseInt(parametro.getText()); 
                        map1.getTriggers().add(new TriggerChangeMap(par1,par2,par3,par4,par5));
                    }
                    if(0==trigger.element("type").getText().compareTo("TriggerMap")){
                        Iterator u=trigger.elementIterator("par");
                        Element parametro=(Element)u.next();
                        int par1=Integer.parseInt(parametro.getText()); parametro=(Element)u.next();
                        int par2=Integer.parseInt(parametro.getText()); parametro=(Element)u.next();
                        int par3=Integer.parseInt(parametro.getText()); parametro=(Element)u.next();
                        int par4=Integer.parseInt(parametro.getText()); parametro=(Element)u.next();
                        int par5=Integer.parseInt(parametro.getText()); 
                        map1.getTriggers().add(new TriggerMap(par1,par2,par3,par4,par5));
                    }
                }
                LMS.getMaps().add(map1);
              
          
            }
            LMS.getPlayer().setLC(LMS.getMaps().get(0).getLC());
            //Player
            Element player=root.element("Player"); 
            
            LMS.getPlayer().setPositionX(Integer.parseInt(player.element("positionX").getText()));
            LMS.getPlayer().setPositionY(Integer.parseInt(player.element("positionY").getText()));
            LMS.getPlayer().setDir(Integer.parseInt(player.element("dir").getText()));
            LMS.getPlayer().setPath(player.element("path").getText());
            LMS.getPlayer().setContDelay(Integer.parseInt(player.element("contDelay").getText()));
            LMS.getPlayer().setWidth(Integer.parseInt(player.element("width").getText()));
            LMS.getPlayer().setHeight(Integer.parseInt(player.element("height").getText()));
            LMS.getPlayer().settW(Integer.parseInt(player.element("tW").getText()));
            LMS.getPlayer().settH(Integer.parseInt(player.element("tH").getText()));
            LMS.getPlayer().setSpeed(Integer.parseInt(player.element("speed").getText()));
            LMS.getPlayer().setHappiness(Double.parseDouble(player.element("happiness").getText()));
            LMS.getPlayer().setNumberOfFriends(Integer.parseInt(player.element("numberOfFriends").getText()));
            LMS.getPlayer().setNumberOfTrophies(Integer.parseInt(player.element("numberOfTrophies").getText()));
            Element inventory=player.element("Inventory");        
                for(Iterator i=inventory.elementIterator("Item");i.hasNext();){
                    Element item=(Element)i.next();
                    int id=Integer.parseInt(item.attribute("id").getText());
                    String name=item.element("name").getText();
                    int stock=Integer.parseInt(item.element("stock").getText());
                    String description=item.element("description").getText();
                    LMS.getPlayer().getInventory().getItems().add(new Item(id,name,stock,description));
                }
                          
               
           getSM().add(LMS);
           //prueba del menu
           MainMenu menu=new MainMenu(this);

           SM.add(menu);

        } catch (DocumentException ex) {
            Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
        }   
        
        
        
    }

    private void saveGameToXML() {
       Document document=DocumentHelper.createDocument();
       Element root=document.addElement("Game");
       //General
        Element general=root.addElement("General");
        general.addElement("title").addText(title);
        general.addElement("width").addText(""+getWidth());
        general.addElement("height").addText(""+getHeight());
       //Mapas
        Element maps=root.addElement("Maps");
        for(int i=0;i<LMS.getMaps().size();i++){
            
            Element map=maps.addElement("Map").addAttribute("id", ""+i);
            map.addElement("NumberLayers").addText(""+LMS.getMaps().get(i).getLayers().size());
            Element source=map.addElement("Source");
            for(int j =0; j<LMS.getMaps().get(i).getLayers().size();j++){
                source.addElement("Path").addAttribute("id", ""+j).addText(LMS.getMaps().get(i).getPaths()[j]);
                source.addElement("Img").addAttribute("id", ""+j).addText(LMS.getMaps().get(i).getDirImg()[j]);
                //falta width,gehith, layer. mapa, etc, terminar mapash
            }
            
            
            Element triggers=map.addElement("Triggers");
                for (int j=0;j<LMS.getMaps().get(i).getTriggers().size();j++){
                    Element trigger=triggers.addElement("Trigger");
                    if (LMS.getMaps().get(i).getTriggers().get(j) instanceof TriggerChangeMap){
                        TriggerChangeMap aux=(TriggerChangeMap)LMS.getMaps().get(i).getTriggers().get(j);
                        trigger.addElement("type").addText("TriggerChangeMap");
                        trigger.addElement("par").addText(""+aux.x);
                        trigger.addElement("par").addText(""+aux.y);
                        trigger.addElement("par").addText(""+aux.getChangeTo());
                        trigger.addElement("par").addText(""+aux.getpX());
                        trigger.addElement("par").addText(""+aux.getpY());
                    }
                    if (LMS.getMaps().get(i).getTriggers().get(j) instanceof TriggerMap){
                        TriggerMap aux=(TriggerMap)LMS.getMaps().get(i).getTriggers().get(j);
                        trigger.addElement("type").addText("TriggerMap");
                        trigger.addElement("par").addText(""+aux.x);
                        trigger.addElement("par").addText(""+aux.y);
                        trigger.addElement("par").addText(""+aux.getChangeTo());
                        trigger.addElement("par").addText(""+aux.getpX());
                        trigger.addElement("par").addText(""+aux.getpY());
                    }
                }
            
        }
        //Player
        Element player=root.addElement("Player"); 
        player.addElement("positionX").addText(""+LMS.getPlayer().getPositionX());
        player.addElement("positionY").addText(""+LMS.getPlayer().getPositionY());
        player.addElement("dir").addText(""+LMS.getPlayer().getDir());
        player.addElement("path").addText(""+LMS.getPlayer().getPath());
        player.addElement("contDelay").addText(""+LMS.getPlayer().getContDelay());
        player.addElement("width").addText(""+LMS.getPlayer().getWidth());
        player.addElement("height").addText(""+LMS.getPlayer().getHeight());
        player.addElement("tW").addText(""+LMS.getPlayer().gettW());
        player.addElement("tH").addText(""+LMS.getPlayer().gettH());
        player.addElement("speed").addText(""+LMS.getPlayer().getSpeed());
        player.addElement("happiness").addText(""+LMS.getPlayer().getHappiness());
        player.addElement("numberOfFriends").addText(""+LMS.getPlayer().getNumberOfFriends());
        player.addElement("numberOfTrophies").addText(""+LMS.getPlayer().getNumberOfTrophies());
        Element inventory=player.addElement("Inventory");        
            for(int i =0;i<LMS.getPlayer().getInventory().getItems().size();i++){
                Element item=inventory.addElement("Item").addAttribute("id",""+LMS.getPlayer().getInventory().getItems().get(i).getId());
                item.addElement("name").addText(""+LMS.getPlayer().getInventory().getItems().get(i).getName());
                item.addElement("stock").addText(""+LMS.getPlayer().getInventory().getItems().get(i).getStock());
                item.addElement("description").addText(""+LMS.getPlayer().getInventory().getItems().get(i).getDescription());
            }
            
        
        
        
        
        
      //WRITER
        try { 
            OutputFormat format = OutputFormat.createPrettyPrint();
             format.setIndent(true);
            XMLWriter writer=new XMLWriter(new FileWriter("GameInit.xml"),format);
            writer.write(document);
            writer.setIndentLevel(2);
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void saveToBin(String aux){
        
//        try {
//            Thread.sleep(3000);//Para esperar a q se cargue todo, despues lo borraremos
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
//        }        
        
        try {
//            String aux="GameData"+cantGuar+".bin";
            aux=aux+".bin";
            FileOutputStream fout=new FileOutputStream(aux);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(LMS.getPlayer());
            fout.close();
            cantGuar++;
        }    
        catch (FileNotFoundException ex) {
            Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex) {
            Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadToBin(String path){
        try { 
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);  
            Player play1=new Player ();
            play1=(Player)ois.readObject();
            LMS.getPlayer().copyPlayer(play1);
            fis.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
        }catch (IOException ex) {
            Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @return the bs
     */
    public BufferStrategy getBs() {
        return bs;
    }

    /**
     * @param bs the bs to set
     */
    public void setBs(BufferStrategy bs) {
        this.bs = bs;
    }

}

