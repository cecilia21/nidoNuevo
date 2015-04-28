/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nidonuevo.model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author alulab14
 */
public class Player extends Person implements Serializable{
    private int contDelay=5;
    private Engine eng;
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
    private Layer LC;


    private Inventory inventory=new Inventory();
    private ArrayList <Friend> friends=new ArrayList <Friend>();
    
    public Player(){
        positionX=0;    
        positionY=0;
        dir=2;//der=2 izq=1 arr=3 aba=0
        String path="/img/playerS.png";
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
    private int getT(int x){
        int cW=(int)(LC.getTotalX()*1.0/LC.getWidth());
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
                    
                    if (getDir()==3) {if (delay==0) {s++; delay=getContDelay();} else delay--;} else s=0;
                    this.setDir(3);
                    //collision
                    if (valid(getPositionX(),getPositionY()-getSpeed()))//valida si esta en marco
                    if (LC.getTiles()[getT(getPositionX())][getT(getPositionY()-getSpeed())]==1)//colision
                    yMove = -getSpeed();
                    
                    
                }
                    
                        
                        
		if(eng.getKeyManager().down){
                    if (getDir()==0) {if (delay==0) {s++; delay=getContDelay();} else delay--;} else s=0;
                    this.setDir(0);
                    if (valid(getPositionX(),getPositionY()+getSpeed()))
                    if (LC.getTiles()[getT(getPositionX())][getT(getPositionY()+getSpeed())]==1)
		    yMove = getSpeed();
                }
                        
                        
		if(eng.getKeyManager().left){
                    if (getDir()==1) {if (delay==0) {s++; delay=getContDelay();} else delay--;} else s=0;
                    this.setDir(1);
                    if (valid(getPositionX()-getSpeed(), getPositionY()))
                    if (LC.getTiles()[getT(getPositionX()-getSpeed())][getT(getPositionY())]==1)
                        xMove = -getSpeed();
                    
                }
			
                        
		if(eng.getKeyManager().right){
                    if (getDir()==2) {if (delay==0) {s++; delay=getContDelay();} else delay--;} else s=0;
                    this.setDir(2);
                    if (valid(getPositionX()+getSpeed(), getPositionY()))
                    if (LC.getTiles()[getT(getPositionX()+getSpeed())][getT(getPositionY())]==1)
                    xMove = getSpeed();
                }
                if (s==4) s=0;
                
                        
                        
	}
    public Player(Engine eng,int x,int y){
        LC=eng.getLc();
        this.eng=eng;
        name="GGwp"; //por cambiar, tiene que ser ingresao desde el meenu inicial
        positionX=x;
        positionY=y;
        path="/img/playerS.png";
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
        
	g.drawImage(getSprite()[this.getDir()*4+s], (int)(getPositionX()), (int)(getPositionY()), getWidth(), getHeight(), null);
	System.out.println(name);
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
    
    
    
}
