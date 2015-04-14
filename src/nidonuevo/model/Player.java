/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nidonuevo.model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author alulab14
 */
public class Player {
    private int contDelay=5;
    private Engine eng;
    private int id;
    private String name;
    private int gender;
    private double happiness;
    private int numberOfFriends;
    private int positionX=0;
    private int positionY=0;
    private int level;
    private int xMove;
    private int yMove;
    private int dir=2;//der=2 izq=1 arr=3 aba=0
    private int s=0; //0->3
    private int delay=contDelay;
    private int numerOfTrophies;
    private int speed=5;
    private int tW=200,tH=200;
    private  int width = 50, height = 50;
    private BufferedImage[] sprite;
    private Layer LC;

    private int pointingDirection; // es -1 si mira hacia la izq, +1 hacia la derecha
    // 2 hacia arriba y -2 hacia abajo
    private Inventory inventory;
    private ArrayList <Friend> friends;
    
    public Player(){
        
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
    private void getInput(){
		xMove = 0;
		yMove = 0;
		//der=2 izq=1 arr=3 aba=0
                
		if(eng.getKeyManager().up){
                    //delay para actualizar sprite
                    //s es un sprite movimiento de una direccion
                    
                    if (dir==3) {if (delay==0) {s++; delay=contDelay;} else delay--;} else s=0;
                    this.dir=3;
                    //collision
//                    if (LC.getTiles()[yMove-speed][1]==1)
                    yMove = -speed;
                }
                    
                        
                        
		if(eng.getKeyManager().down){
                    if (dir==0) {if (delay==0) {s++; delay=contDelay;} else delay--;} else s=0;
                    this.dir=0;
		    yMove = speed;
                }
                        
                        
		if(eng.getKeyManager().left){
                    if (dir==1) {if (delay==0) {s++; delay=contDelay;} else delay--;} else s=0;
                    this.dir=1;
                        xMove = -speed;
                    
                }
			
                        
		if(eng.getKeyManager().right){
                    if (dir==2) {if (delay==0) {s++; delay=contDelay;} else delay--;} else s=0;
                    this.dir=2;
                    xMove = speed;
                }
                if (s==4) s=0;
                
                        
                        
	}
    public Player(Engine eng,int x,int y){
        LC=eng.getLc();
        this.eng=eng;
        name="GGwp"; //por cambiar, tiene que ser ingresao desde el meenu inicial
        positionX=x;
        positionY=y;
        String path="/img/playerS.png";
        Sprite sheet = new Sprite(ImageLoader.loadImage(path));
		
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
        
	g.drawImage(sprite[this.dir*4+s], (int)(positionX), (int)(positionY), width, height, null);
	
    }
    public void move(){
        int newX=positionX+xMove;
        int newY=positionY+yMove;
        if(this.getPositionY()==newY)
            this.setPointingDirection(newX-this.getPositionX());
        else
            this.setPointingDirection(newY-(this.getPositionY()+1));
        this.setPositionX(newX);
        this.setPositionY(newY);
        //aca como cambio el muñequito que se debe mostrar? le mando al 
        //render de sprite? o como? :c
        //no nada, aca se actualiza el render es despues
        
    }
    
    public void increaseLevel(){ 
        
    }
    
    public void increaseNumberOfTrophies(){
        
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the gender
     */
    public int getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(int gender) {
        this.gender = gender;
    }

    /**
     * @return the numberOfFriends
     */
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
     * @return the positionX
     */
    public int getPositionX() {
        return positionX;
    }

    /**
     * @param positionX the positionX to set
     */
    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    /**
     * @return the positionY
     */
    public int getPositionY() {
        return positionY;
    }

    /**
     * @param positionY the positionY to set
     */
    public void setPositionY(int positionY) {
        this.positionY = positionY;
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
     * @return the numerOfTrophies
     */
    public int getNumerOfTrophies() {
        return numerOfTrophies;
    }

    /**
     * @param numerOfTrophies the numerOfTrophies to set
     */
    public void setNumerOfTrophies(int numerOfTrophies) {
        this.numerOfTrophies = numerOfTrophies;
    }

    public double getHappiness() {
        return happiness;
    }

    public void setHappiness(double happiness) {
        this.happiness = happiness;
    }

//    public BufferedImage getSprite() {
//        return sprite;
//    }
//
//    public void setSprite(BufferedImage sprite) {
//        this.sprite = sprite;
//    }

    public int getPointingDirection() {
        return pointingDirection;
    }

    public void setPointingDirection(int pointingDirection) {
        this.pointingDirection = pointingDirection;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
    
    
}
