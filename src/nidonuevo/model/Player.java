/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nidonuevo.model;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author alulab14
 */
public class Player extends Person implements Serializable{
    private int auxR=0;
    public boolean correct;
    private int contDelay=5;
    private transient Engine eng;
    private double happiness;
    private int numberOfFriends;
    private int level;
    private int xMove;
    private int yMove;
    private int s=0; //0->3
    private int delay=getContDelay();
    private int numberOfTrophies;
    private int speed=6;
    private int tW=200,tH=200;
    private  int width = 50, height = 50;
    private transient Layer LC;
    private transient ArrayList<MiniGame> miniGames=new ArrayList<MiniGame>();
    private int idMinigame;
    private int auxEnter=0;
    private int currentMap;

    private Inventory inventory=new Inventory();
    private ArrayList <Friend> friends=new ArrayList <Friend>();
    
    public Player(){
        positionX=0;    
        positionY=0;
        dir=2;//der=2 izq=1 arr=3 aba=0
        String path="/img/playerS2.png";
        Sprite sheet = new Sprite(ImageLoader.loadImage(path));
		
	sprite=new BufferedImage[16];	
        for(int py = 0;py < 4;py++){
			for(int px = 0;px < 4;px++){
				sprite[px+4*py]=sheet.crop(px*width, py*height,width, height);
			}
		}
    }
    
    public void tick(){
        
        getInput();
        move();
    }
    public int getT(int x){
        int cW=(int)(getLC().getTotalX()*1.0/getLC().getWidth());
        return (x/cW)+1;
    }
    private boolean valid(int x, int y){
        return (x>=0 && y>=0 && x<750 && y<650);
    }
    private void getInput(){
		xMove = 0;
		yMove = 0;
		//der=2 izq=1 arr=3 aba=0
                
                if(eng.getKeyManager().up){
                    //delay para actualizar sprite
                    //s es un sprite movimiento de una direccion
                    
                    if (getDir()==3) {if (delay==0) {setS(getS() + 1); delay=getContDelay();} else delay--;} else setS(0);
                    this.setDir(3);
                    //collision
                    if (valid(getPositionX(),getPositionY()-getSpeed()))//valida si esta en marco
                    if (getLC().getTiles()[getT(getPositionX())][getT(getPositionY()-getSpeed())]==1)//colision
                    yMove = -getSpeed();
                    
                    
                }
                    
                        
                        
		if(eng.getKeyManager().down){
                    if (getDir()==0) {if (delay==0) {setS(getS() + 1); delay=getContDelay();} else delay--;} else setS(0);
                    this.setDir(0);
                    if (valid(getPositionX(),getPositionY()+getSpeed()))
                    if (getLC().getTiles()[getT(getPositionX())][getT(getPositionY()+getSpeed())]==1)
		    yMove = getSpeed();
                }
                        
                        
		if(eng.getKeyManager().left){
                    if (getDir()==1) {if (delay==0) {setS(getS() + 1); delay=getContDelay();} else delay--;} else setS(0);
                    this.setDir(1);
                    if (valid(getPositionX()-getSpeed(), getPositionY()))
                    if (getLC().getTiles()[getT(getPositionX()-getSpeed())][getT(getPositionY())]==1)
                        xMove = -getSpeed();
                    
                }
			
                        
		if(eng.getKeyManager().right){
                    if (getDir()==2) {if (delay==0) {setS(getS() + 1); delay=getContDelay();} else delay--;} else setS(0);
                    this.setDir(2);
                    if (valid(getPositionX()+getSpeed(), getPositionY()))
                    if (getLC().getTiles()[getT(getPositionX()+getSpeed())][getT(getPositionY())]==1)
                    xMove = getSpeed();
                }
                if (eng.getKeyManager().m){
                    if (auxR==0) auxR++;
                }
                if (eng.getKeyManager().mR){
                    if (auxR==1) auxR++;
                }
                if(auxR==2){
                    eng.getKeyManager().mR=false;
                    eng.getKeyManager().m=false;
                    auxR=0;
                    InGameMenu inGameM=new InGameMenu(eng);
                    eng.getSM().add(inGameM);
                }
                if (eng.getKeyManager().s && correct){
                    
                    eng.getSM().add(miniGames.get(idMinigame));
                    correct=false;
                     
                     
                }
                if(eng.getKeyManager().enter){
                    //System.out.println("Solo se presiona enter");
                    //Cuando tu mismo vuelves a presionar enter se para la secuencia
                }
                
                
                
                if (getS()==4) setS(0);
                
                        
                        
	}
    public Player(Engine eng,int x,int y){
       // LC=eng.getLc();
        this.eng=eng;
        name="GGwp"; //por cambiar, tiene que ser ingresao desde el meenu inicial
        positionX=x;
        positionY=y;
        path="/img/playerS2.png";
        Sprite sheet = new Sprite(ImageLoader.loadImage(getPath()));
		
	sprite=new BufferedImage[16];	
        for(int py = 0;py < 4;py++){
			for(int px = 0;px < 4;px++){
				sprite[px+4*py]=sheet.crop(px*width, py*height,width, height);
			}
		}
        
    }
    
    
    public void increaseHappiness(){
        
    }
    
    public void increaseNumberOfFriends(Friend friend){
        
    }
    public void render(Graphics g){
        //der=2 izq=1 arr=3 aba=0
        
	g.drawImage(getSprite()[this.getDir()*4+getS()], (int)(getPositionX()), (int)(getPositionY()), getWidth(), getHeight(), null);
        System.out.println("Pixel X: "+getPositionX()+", Pixel Y:"+getPositionY());        
        System.out.println("Title X: "+getT(getPositionX())+", Title Y: "+getT(getPositionY()));
        System.out.println("Aux: "+auxR);
        if((getT(getPositionX())==8) && (getT(getPositionY())==14)&&(eng.getCurrentMap()==0)){
                    Image img2 = new ImageIcon("src/img/cloud.png").getImage();
                     g.drawImage(img2, 335, 435,null);
                     g.setFont(new Font("Comic Sans MS",Font.BOLD,10));
                    if(eng.getKeyManager().enter){
                        if(auxEnter==0) auxEnter++;
                    }
                    if(eng.getKeyManager().enterR){
                        if(auxEnter==1) auxEnter++;
                    }
                    if(auxEnter==2){
                        eng.getKeyManager().enter=false;
                        eng.getKeyManager().enterR=false;
                        auxEnter=0;
                        MiniGameMemory memory= new MiniGameMemory(eng);
                        eng.getSM().add(memory);
                    }
                    g.drawString("Hola soy cecilia", 345, 470);                      
        }
	//System.out.println(name);
    }
    public void move(){
        int newX=getPositionX()+xMove;
        int newY=getPositionY()+yMove;

        this.setPositionX(newX);
        this.setPositionY(newY);

    }
    
    public void increaseLevel(){ 
        
    }
    
    public void increaseNumberOfTrophies(){
        
    }
   public int getNumberOfFriends() {
        return numberOfFriends;
    }

    /**
     * @param numberOfFriends the numberOfFriends to set
     */
    public void setNumberOfFriends(int numberOfFriends) {
        this.numberOfFriends = numberOfFriends;
    }

    /**
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * @return the numberOfTrophies
     */
    public int getNumberOfTrophies() {
        return numberOfTrophies;
    }

    /**
     * @param numberOfTrophies the numberOfTrophies to set
     */
    public void setNumberOfTrophies(int numberOfTrophies) {
        this.numberOfTrophies = numberOfTrophies;
    }

    public double getHappiness() {
        return happiness;
    }

    public void setHappiness(double happiness) {
        this.happiness = happiness;
    }



    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public ArrayList <Friend> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList <Friend> friends) {
        this.friends = friends;
    }

    /**
     * @return the contDelay
     */
    public int getContDelay() {
        return contDelay;
    }

    /**
     * @param contDelay the contDelay to set
     */
    public void setContDelay(int contDelay) {
        this.contDelay = contDelay;
    }

    /**
     * @return the speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * @return the tW
     */
    public int gettW() {
        return tW;
    }

    /**
     * @param tW the tW to set
     */
    public void settW(int tW) {
        this.tW = tW;
    }

    /**
     * @return the tH
     */
    public int gettH() {
        return tH;
    }

    /**
     * @param tH the tH to set
     */
    public void settH(int tH) {
        this.tH = tH;
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
    
    public void copyPlayer(Player play1){
        
        positionX=play1.getPositionX();
        positionY=play1.getPositionY();
        
        contDelay=play1.getContDelay();
        happiness=play1.getHappiness();
        numberOfFriends=play1.getNumberOfFriends();
        level=play1.getLevel();
        s=play1.getS();
        delay=play1.getContDelay();
        numberOfTrophies=play1.getNumberOfTrophies();
       
        id=play1.getId();
        name=play1.getName();
        gender=play1.getGender();
        dir=play1.getDir();    

        friends=play1.getFriends();
        inventory=play1.getInventory();

    }
    public int getS() {
        return s;
    }

    public void setS(int s) {
        this.s = s;
    }

    /**
     * @return the LC
     */
    public Layer getLC() {
        return LC;
    }

    /**
     * @param LC the LC to set
     */
    public void setLC(Layer LC) {
        this.LC = LC;
    }

    /**
     * @return the miniGames
     */
    public ArrayList<MiniGame> getMiniGames() {
        return miniGames;
    }

    /**
     * @param miniGames the miniGames to set
     */
    public void setMiniGames(ArrayList<MiniGame> miniGames) {
        this.miniGames = miniGames;
    }

    /**
     * @return the idMinigame
     */
    public int getIdMinigame() {
        return idMinigame;
    }

    /**
     * @param idMinigame the idMinigame to set
     */
    public void setIdMinigame(int idMinigame) {
        this.idMinigame = idMinigame;
    }

    /**
     * @return the currentMap
     */
    public int getCurrentMap() {
        return currentMap;
    }

    /**
     * @param currentMap the currentMap to set
     */
    public void setCurrentMap(int currentMap) {
        this.currentMap = currentMap;
    }
        
}
