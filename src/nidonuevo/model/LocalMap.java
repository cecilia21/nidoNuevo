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
    private boolean ordenarPop=false;
    private Map map;
    private ArrayList<Map> maps=new ArrayList<Map>();
    private Player player;
    private ArrayList<Friend> friends;
    private int mapAct;
    private Engine eng;
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
        return ordenarPop;
    }
    public void onExit(){
        
    }       
    private int triggerActive(){
        for(int i=0;i<maps.get(getMapAct()).getTriggers().size();i++){
            if (player.getT(player.positionX)==maps.get(getMapAct()).getTriggers().get(i).getX() 
                    &&player.getT(player.positionY)==maps.get(getMapAct()).getTriggers().get(i).getY()){
                return i;
            }
        }
        return -1;
    }
    public void tick(){
        
        getPlayer().tick();
        int i=triggerActive();
        if ((i)>=0){
            maps.get(getMapAct()).getTriggers().get(i).execTrigger(this);
            Layer aux=maps.get(getMapAct()).getLC();
            player.setLC(aux);
        }
        
        maps.get(getMapAct()).tick();
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
