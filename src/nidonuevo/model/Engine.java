
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
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
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
import serverrmi.IServices;
public class Engine implements Runnable{
    private ArrayList<Person> idPersons=new ArrayList<Person>();
    private Loading loading;
    private int cantGuar=0;
    private String title;
    private int width, height;
    private Display display;
    private Boolean running=false;
    private Thread thread;
    private BufferStrategy bs;
    private Graphics g;
    private Boolean flagCanvas=false;
    private final Object GUI_INITIALIZATION_MONITOR = new Object();
    private boolean pauseThreadFlag = false;
    private int aux=0;
    //layer de collision
 //   private Layer lc;
    //Actual map
    private int currentMap=0;
    
    //Input
    public KeyManager keyManager;
    
    //States
    private StateMachine SM;
    public LocalMap LMS;
    public static Registry reg = null;
    public static IServices proxy = null;    
    	static {
		try {
			reg = LocateRegistry.getRegistry("192.168.205.230", 1099);
			//reg = LocateRegistry.getRegistry("10.101.40.104", 1099);
			proxy = (IServices)reg.lookup("MyRMIServer");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
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
    private void minic(){
        //debe ser cargado del xml
                    //Primero se crea los jugadores del minigame
                    ArrayList<Person> persons=new ArrayList<Person>();
                    persons.add(LMS.getPlayer());
                    //preguntas
                    ArrayList<String> messages=new ArrayList<String>();
                    messages.add("Capital de Rumania?");
                    messages.add("Presidente de Ecuador?");
                    messages.add("Primer elemento de la, tabla periodica?");
                    messages.add("Descubridor del electrón?");
                    //respuestas
                    ArrayList<String[]> answers=new ArrayList<String[]>();
                    String[] ans1={"Lima","Kajaskitan","Correcto"};
                    answers.add(ans1);
                    String[] ans2={"Humala","Niño Nieto","Diego Bustamante xD"};
                    answers.add(ans2);
                    String[] ans3={"H","N","Br"};
                    answers.add(ans3);
                    String[] ans4={"Rutterford","Einstein","Fischer"};
                    answers.add(ans4);
                    //repuestas correctas
                    ArrayList<Integer> correct1=new ArrayList<Integer>();
                    correct1.add(1);
                    correct1.add(2);
                    correct1.add(3);
                    correct1.add(1);
                    //puntos
                    ArrayList<Integer> points=new ArrayList<Integer>();
                    points.add(1);
                    points.add(5);
                    points.add(1);
                    points.add(5);
                    
                    MiniGame mini=new MiniGame(this,persons,messages,answers,correct1,points);
                   
                    LMS.getPlayer().getMiniGames().add(mini);
    }
    private void init(){
        
        
        loadGameFromXML();                    
        //VAMOS A GUARDAR LA CONFIGURACION INICIAL DEL JUEGO
       //minic();
        saveGameToXML();     
        loading.stop();
        //Utils.sleepFor(5000);
    }
    
    public void tecla(){
        keyManager.tick();
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
        if(keyManager.i){
            if(aux==0) aux++;
        }
        if(keyManager.iR){
            if(aux==1) aux++;
        }
        if(aux==2) {
            keyManager.i=false;
            keyManager.iR=false;
            aux=0;
            if(!flagCanvas) flagCanvas=true;
            else flagCanvas=false;
            System.out.println("presionando iiiiiiiiiiiiiiiiiiii");

        }

        setBs(display.getCanvas().getBufferStrategy());
        if(getBs() == null){
                display.getCanvas().createBufferStrategy(3);
                return;
        }
        g = getBs().getDrawGraphics();
        //Clear Screen
        g.clearRect(0, 0, getWidth(), getHeight());
        //Draw Here!
        if(flagCanvas) renderInventory();
        else {if(!SM.getState().empty())
                getSM().render(g); 
        }	

        //End Drawing!
        if (LMS.isChange()) Utils.imgB(g, 0, 0, this.getWidth(), this.getHeight(), LMS.getBright());

        getBs().show();

        g.dispose();
                
        LMS.getPlayer().setCurrentMap(LMS.getMapAct());
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
    public void renderInventory(){
        Image img2 = new ImageIcon("src/img/board.jpg").getImage();
        g.drawImage(img2, 0, 0, display.getCanvas());
        ArrayList <Item> inv= LMS.getPlayer().getInventory().getItems();
        for (int i=0;i<inv.size();i++) {
            Image img = new ImageIcon(inv.get(i).getImage()).getImage();
            g.drawImage(img,120+i*100, 150, display.getCanvas());
            g.setColor(Color.red);
            g.setFont(new Font("Comic Sans MS",Font.BOLD,20));
                           
            g.drawString(""+inv.get(i).getStock(), 145+i*100, 220); 
                            
        }
        g.setFont(new Font("Comic Sans MS",Font.BOLD,30));
        g.drawString("Inventory game", 300, 50);           
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
    public Display getDisplay(){
        return display;
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
        cMap.addElement("Map").addText(""+getCurrentMap());
        
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
            Document document=reader.read("juego.xml");
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
                
                //TRIGGERS
                
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
                    if(0==trigger.element("type").getText().compareTo("TriggerMini")){
                        Iterator u=trigger.elementIterator("par");
                        Element parametro=(Element)u.next();
                        int par1=Integer.parseInt(parametro.getText()); parametro=(Element)u.next();
                        int par2=Integer.parseInt(parametro.getText()); parametro=(Element)u.next();
                        int par3=Integer.parseInt(parametro.getText());
                        map1.getTriggers().add(new TriggerMini(par1,par2,par3));
                    }
                    if(0==trigger.element("type").getText().compareTo("TriggerMonologue")){
                        Iterator u=trigger.elementIterator("par");
                        Element parametro=(Element)u.next();
                        int par1=Integer.parseInt(parametro.getText()); parametro=(Element)u.next();
                        int par2=Integer.parseInt(parametro.getText()); parametro=(Element)u.next();
                        int par3=Integer.parseInt(parametro.getText());
                        map1.getTriggers().add(new TriggerMonologue(par1,par2));
                    }
                    
                }
                //GOALS
                
                Element goals=map.element("Goals");
                for (Iterator j=goals.elementIterator("Goal");j.hasNext();){
                    Element goal=(Element)j.next();
                    
                    //int id, boolean active,String desc,int tipo
                    int idGoal=Integer.parseInt(goal.element("id").getText());
                    boolean active=(goal.element("active").getText().compareTo("true")==0);
                    String desc=goal.element("description").getText();
                    int tipo=Integer.parseInt(goal.element("type").getText());
                    map1.getGoals().add(new Goal(idGoal,active,desc,tipo));
                    
                }
              //  map1.getGoals().add(new Goal(1,true,"Meta conversar con NPC 1",2));
               
                
                LMS.getMaps().add(map1);
              
          
            }
            LMS.getPlayer().setLC(LMS.getMaps().get(0).getLC());
            //Player
            Element player=root.element("Player"); 
            LMS.getPlayer().setId(0);
            
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
                    String image=item.element("image").getText();
                    LMS.getPlayer().getInventory().getItems().add(new Item(id,name,stock,description,image));
                }
                          
           idPersons.add(LMS.getPlayer());
           getSM().add(LMS);
           //prueba del menu
           MainMenu menu=new MainMenu(this);

           SM.add(menu);
           
           
            //MINIJUEGOS
         Element miniGames=root.element("MiniGames");
         
         for (Iterator i=miniGames.elementIterator("MiniGame");i.hasNext();){
                    Element miniGame=(Element)i.next();
                    int idMini=Integer.parseInt(miniGame.element("id").getText());
                 
                    
                    
                    //jugadores
                    ArrayList<Person> persons1=new ArrayList<Person>();
                    
                    Element persons=miniGame.element("Persons");;
                    for (Iterator k=persons.elementIterator("id");k.hasNext();){
                        Element person=(Element)k.next();
                        int u=Integer.parseInt(person.getText());
                        persons1.add(idPersons.get(u));
                    }
                    //mensajes
                    ArrayList<String> messages1=new ArrayList<String>();
                    Element messages=miniGame.element("Messages");
                   for (Iterator k=messages.elementIterator("message");k.hasNext();){
                       Element message=(Element)k.next();
                       messages1.add(message.getText());
                    }
                   
                   //respuestas
                   ArrayList<String[]> answers1=new ArrayList<String[]>();
                   Element answers=miniGame.element("Answers");
                   for (Iterator k=answers.elementIterator("answer");k.hasNext();){
                        
                        Element answer=(Element)k.next();
                        
                        String[] auxS=answer.getText().split("-");
                        answers1.add(auxS);
                    }
                   //respuestas correctas
                   ArrayList<Integer> correct1=new ArrayList<Integer>();
                   Element correctP=miniGame.element("Correct1");
                   for (Iterator k=correctP.elementIterator("correct");k.hasNext();){
                        Element correct=(Element)k.next();
                        correct1.add(Integer.parseInt(correct.getText()));
                    }
                  //puntos
                   ArrayList<Integer> points1=new ArrayList<Integer>();
                   Element points=miniGame.element("Points");
                   for (Iterator k=points.elementIterator("point");k.hasNext();){
                        Element point=(Element)k.next();
                        points1.add(Integer.parseInt(point.getText()));
                    }
                    MiniGame mini=new MiniGame(this,persons1,messages1,answers1,correct1,points1);
                    LMS.getPlayer().getMiniGames().add(mini);
                    
                }
               //FIN
           
        } catch (DocumentException ex) {
            Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
        }   
        
        
        
    }
    //GUARDA EL INICIO DEL JUEGO
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
            
            //TRIGGERS
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
                    }else if (LMS.getMaps().get(i).getTriggers().get(j) instanceof TriggerMap){
                        TriggerMap aux=(TriggerMap)LMS.getMaps().get(i).getTriggers().get(j);
                        trigger.addElement("type").addText("TriggerMap");
                        trigger.addElement("par").addText(""+aux.x);
                        trigger.addElement("par").addText(""+aux.y);
                        trigger.addElement("par").addText(""+aux.getChangeTo());
                        trigger.addElement("par").addText(""+aux.getpX());
                        trigger.addElement("par").addText(""+aux.getpY());
                    }else if(LMS.getMaps().get(i).getTriggers().get(j) instanceof TriggerMini){
                        TriggerMini aux=(TriggerMini)LMS.getMaps().get(i).getTriggers().get(j);
                        trigger.addElement("type").addText("TriggerMini");
                        trigger.addElement("par").addText(""+aux.x);
                        trigger.addElement("par").addText(""+aux.y);
                        trigger.addElement("par").addText(""+aux.getChangeTo());
                        
                    }else if(LMS.getMaps().get(i).getTriggers().get(j) instanceof TriggerMonologue){
                        TriggerMonologue aux=(TriggerMonologue)LMS.getMaps().get(i).getTriggers().get(j);
                        trigger.addElement("type").addText("TriggerMonologue");
                        trigger.addElement("par").addText(""+aux.x);
                        trigger.addElement("par").addText(""+aux.y);
                        trigger.addElement("par").addText(""+aux.getChangeTo());
                        
                    }
                    
                }
            //GOALS
            Element goals=map.addElement("Goals");
                for (int j=0;j<LMS.getMaps().get(i).getGoals().size();j++){
                    
                    Element goal=goals.addElement("Goal");
                    Goal aux=LMS.getMaps().get(i).getGoals().get(j);
                    //int id, boolean active,String desc,int tipo
                    goal.addElement("id").addText(""+aux.getId());
                    goal.addElement("active").addText(""+aux.isActive());
                    goal.addElement("description").addText(""+aux.getDescription());
                    goal.addElement("type").addText(""+aux.getTipo());
              
                  
                    
                    
                }
                
            //MINIGAME
            
            
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
                item.addElement("image").addText(""+LMS.getPlayer().getInventory().getItems().get(i).getImage());
            }
            
            
         //MINIJUEGOS
         Element miniGames=root.addElement("MiniGames");
         for (int j=0;j<LMS.getPlayer().getMiniGames().size();j++){
                    
                    Element miniGame=miniGames.addElement("MiniGame");
                    miniGame.addElement("id").addText(""+j);
                    MiniGame aux=LMS.getPlayer().getMiniGames().get(j);
                    //jugadores
                    Element persons=miniGame.addElement("Persons");;
                    for (int k=0;k<aux.getPersons().size();k++){
                        
                        persons.addElement("id").addText(""+aux.getPersons().get(k).id);
                    }
                    //mensajes
                    Element messages=miniGame.addElement("Messages");
                   for (int k=0;k<aux.getMessages().size();k++){
                        
                        messages.addElement("message").addText(""+aux.getMessages().get(k));
                    }
                   //respuestas
                   Element answers=miniGame.addElement("Answers");
                   for (int k=0;k<aux.getAnswers().size();k++){
                        
                        
                        String auxS="";
                        for (int j1=0;j1<aux.getAnswers().get(k).length;j1++){
                            if (j1==0){
                               auxS=aux.getAnswers().get(k)[j1]; 
                            }else{
                               auxS=auxS+"-"+aux.getAnswers().get(k)[j1]; 
                            }
                            
                        }
                        answers.addElement("answer").addText(auxS);
                    }
                   //respuestas correctas
                   Element correct=miniGame.addElement("Correct1");
                   for (int k=0;k<aux.getCorrect().size();k++){
                        
                        correct.addElement("correct").addText(""+aux.getCorrect().get(k));
                    }
                  //puntos
                   Element points=miniGame.addElement("Points");
                   for (int k=0;k<aux.getPoints().size();k++){
                        
                        points.addElement("point").addText(""+aux.getPoints().get(k));
                    }
                    
                    
                }
               //FIN
        
        
        
        
        
      //WRITER
        try { 
            OutputFormat format = OutputFormat.createPrettyPrint();
             format.setIndent(true);
            XMLWriter writer=new XMLWriter(new FileWriter("juego.xml"),format);
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
            play1=(Player)(ois.readObject());
            LMS.getPlayer().copyPlayer(play1);
            LMS.setMapAct(play1.getCurrentMap());
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

    /**
     * @param currentMap the currentMap to set
     */
    public void setCurrentMap(int currentMap) {
        this.currentMap = currentMap;
    }

}

