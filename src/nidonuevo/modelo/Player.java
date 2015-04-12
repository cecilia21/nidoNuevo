/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nidonuevo.modelo;

/**
 *
 * @author alulab14
 */
public class Player {
    private int id;
    private String name;
    private int gender;
    private double happiness;
    private int numberOfFriends;
    private int positionX;
    private int positionY;
    private int level;
    private int numerOfTrophies;
    private Sprite sprite;
    private int pointingDirection;
    private Inventory inventory;
    
    public Player(){
        
    }
    
    
    public void increaseHappiness(){
        
    }
    
    public void increaseNumberOfFriends(){
        
    }
    
    public void move(){
        
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

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

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
