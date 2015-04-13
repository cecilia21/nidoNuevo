/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nidonuevo.model;
import java.awt.Image;
/**
 *
 * @author pucp
 */
public class WorldMap {
    protected Image background;
    private int mapAct;
    
    public void render(){
        
    }
    public void update(){
        
    }
    public void onEnter(){
        
    }
    public void onExit(){
        
    }    

    /**
     * @return the mapAct
     */
    public int getMapAct() {
        return mapAct;
    }

    /**
     * @param mapAct the mapAct to set
     */
    public void setMapAct(int mapAct) {
        this.mapAct = mapAct;
    }
}
