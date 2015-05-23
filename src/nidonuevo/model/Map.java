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
    private float bright=1f;
    private String[] paths;
    private String[] dirImg;
    private ArrayList<Trigger> triggers=new ArrayList<Trigger>();
    private ArrayList<Goal> goals=new ArrayList<Goal>();
    public Map(Engine eng,int cantLayer,String[] paths,String[] dirImg){
//        triggers.add(new TriggerChangeMap(16, 5, 1,410,618));
//        triggers.add(new TriggerChangeMap(11, 17, 0,626,216));
        this.paths=paths;
        this.dirImg=dirImg;
        for (int i=0;i<cantLayer;i++){           
            layers.add(new Layer(paths[i],dirImg[i]));
        }
        
    }
    public void tick(){
        //para ver si se activa algun trigger
        
    }
    public Layer getLC(){
        return getLayers().get(getLayers().size()-1);
    }
    public void render(Graphics g){
        for (int i=0;i<getLayers().size();i++){
            getLayers().get(i).render(g,bright);
        }
    }

    /**
     * @return the layers
     */
    public ArrayList<Layer> getLayers() {
        return layers;
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

    /**
     * @return the triggers
     */
    public ArrayList<Trigger> getTriggers() {
        return triggers;
    }

    /**
     * @param triggers the triggers to set
     */
    public void setTriggers(ArrayList<Trigger> triggers) {
        this.triggers = triggers;
    }

    /**
     * @return the bright
     */
    public float getBright() {
        return bright;
    }

    /**
     * @param bright the bright to set
     */
    public void setBright(float bright) {
        this.bright = bright;
    }

    /**
     * @return the goals
     */
    public ArrayList<Goal> getGoals() {
        return goals;
    }

    /**
     * @param goals the goals to set
     */
    public void setGoals(ArrayList<Goal> goals) {
        this.goals = goals;
    }
    
}
