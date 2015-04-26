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
 * @author pucp
 */
public class LocalMap extends State{
    private Map map;
    private ArrayList<Map> maps=new ArrayList<Map>();
    private Player player;
    private ArrayList<Friend> friends;
    private int mapAct;
    
    public LocalMap(Engine eng){
        //faltaria el super
        //se inicializa los mapas
        //comenzando con mapa 1

        //aqui se crea el player, se inicializa
        
        player = new Player(eng, 0, 0);
	mapAct=eng.getCurrentMap();
    }
    public void render(Graphics g){
        maps.get(getMapAct()).render(g);
        getPlayer().render(g);
    }
    public void update(){
        
    }
    public void onEnter(){
        
    }
    public boolean ordenPop(){
        return false;
    }
    public void onExit(){
        
    }       
    public void tick(){
        maps.get(getMapAct()).tick();
        getPlayer().tick();
        //faltaria tick de amigos
    }
    public ArrayList<Map> getMaps(){
        return maps;
    }

    /**
     * @return the map
     */
    public Map getMap() {
        return map;
    }

    /**
     * @param map the map to set
     */
    public void setMap(Map map) {
        this.map = map;
    }

    /**
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @param player the player to set
     */
    public void setPlayer(Player player) {
        this.player = player;
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
