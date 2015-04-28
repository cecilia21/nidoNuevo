/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nidonuevo.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Inventory implements Serializable{
    private int quantity;
    private int capacity;
    private ArrayList <Item> items=new ArrayList <Item>();
    
    public void addItem(int itemId){
        
    }
    
    public void consumeItem(int itemId){
        
    }
    
    public void increaseQuantity(){
        
    }
    
    public void decreaseQuantity(){
        
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
    
    
}
