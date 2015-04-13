/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nidonuevo.model;
import java.util.ArrayList;
/**
 *
 * @author pucp
 */
public class LocalMap {
    private Map map;
    private ArrayList<Map> maps=new ArrayList<Map>();
    private Player player;
    protected ArrayList<Friend> friends;
    protected int mapAct;
    
    public LocalMap(Engine eng){
        //faltaria el super
        player = new Player(eng, 100, 100);
	mapAct=eng.getCurrentMap();
    }
    public void render(){
        
    }
    public void update(){
        
    }
    public void onEnter(){
        
    }
    public void onExit(){
        
    }       
    public void tick(){
        maps.get(mapAct).tick();
        player.tick();
        //faltaria tick de amigos
    }
}
