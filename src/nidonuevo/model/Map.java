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
    int id;
    private ArrayList<Layer> layers=new ArrayList<Layer>();
    private int width, height;
    private int spawnX, spawnY;
    private String[] paths;
    private String[] dirImg;
    
    public Map(Engine eng,int cantLayer,String[] paths,String[] dirImg){
        this.paths=paths;
        this.dirImg=dirImg;
        for (int i=0;i<cantLayer;i++){           
            layers.add(new Layer(paths[i],dirImg[i]));
        }
        
    }
    public void tick(){
        
        
    }
    public Layer getLC(){
        return getLayers().get(getLayers().size()-1);
    }
    public void render(Graphics g){
        for (int i=0;i<getLayers().size();i++){
            getLayers().get(i).render(g);
        }
    }

    /**
     * @return the layers
     */
    public ArrayList<Layer> getLayers() {
        return layers;
    }

    /**
     * @return the spawnX
     */
    public int getSpawnX() {
        return spawnX;
    }

    /**
     * @param spawnX the spawnX to set
     */
    public void setSpawnX(int spawnX) {
        this.spawnX = spawnX;
    }

    /**
     * @return the paths
     */
    public String[] getPaths() {
        return paths;
    }

    /**
     * @return the dirImg
     */
    public String[] getDirImg() {
        return dirImg;
    }
    
}
