/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nidonuevo.model;

import java.io.Serializable;

/**
 *
 * @author alulab14
 */
public class Item implements Serializable{
    protected int id;
    protected String name;
    protected int stock;
    protected String description;
    private String image;
    
    public Item(int id,String name, int stock, String description,String image){
        this.id=id;
        this.name=name;
        this.stock=stock;
        this.description=description;
        this.image=image;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }
    
    
    
}
