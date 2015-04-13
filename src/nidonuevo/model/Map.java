/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nidonuevo.model;
import java.awt.Graphics;



import java.util.ArrayList;
/**
 *
 * @author TOSHIBA
 */
public class Map {
    private ArrayList<Layer> layers=new ArrayList<Layer>();
    private int width, height;
    private int spawnX, spawnY;
    
    public Map(Engine eng){
        
        
    }
    public void tick(){
        
    }
    public void render(Graphics g){
        for (int i=0;i<layers.size();i++){
            layers.get(i).render(g);
        }
    }
    
}
