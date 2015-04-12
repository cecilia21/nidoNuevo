/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nidonuevo.modelo;

import java.awt.Image;
import java.util.ArrayList;

public class Sprite {
    
    private ArrayList<Image> images=new ArrayList<Image>();
    
    public void render(){
        
    }

    public ArrayList<Image> getImages() {
        return images;
    }

    public void setImages(ArrayList<Image> images) {
        this.images = images;
    }
    
}
