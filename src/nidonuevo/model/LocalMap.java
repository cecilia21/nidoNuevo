/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nidonuevo.model;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
/**
 *
 * @author pucp
 */
public class LocalMap extends State{
    private final double  step=0.01;
    private double bright=1;
    private int iter=1;
    private boolean ordenarPop=false;
   // private Map map;
    private ArrayList<Map> maps=new ArrayList<Map>();
    private Player player;
    private ArrayList<Friend> friends;
    private int mapAct;
    private Engine eng;
    private boolean change=false;
    public LocalMap(Engine eng){
        //faltaria el super
        //se inicializa los mapas
        //comenzando con mapa 1

        //aqui se crea el player, se inicializa
        this.eng=eng;
        player = new Player(eng, 0, 0);
	mapAct=eng.getCurrentMap();
    }
    public void render(Graphics g){
        
        if (isChange()) onChangeEnter(g);
        maps.get(getMapAct()).render(g);
        eng.setCurrentMap(getMapAct());
        getPlayer().render(g);
        if (isChange()) onChangeExit(g);
        
        
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
            if(maps.get(getMapAct()).getTriggers().get(i) instanceof TriggerChangeMap)
            if (getPlayer().getT(getPlayer().positionX)==maps.get(getMapAct()).getTriggers().get(i).getX() 
                    &&getPlayer().getT(getPlayer().positionY)==maps.get(getMapAct()).getTriggers().get(i).getY()){
                return i;
            }
            
            if(maps.get(getMapAct()).getTriggers().get(i) instanceof TriggerMap){
                TriggerMap trig = (TriggerMap)maps.get(getMapAct()).getTriggers().get(i);
                if(getPlayer().getT(getPlayer().positionX)==trig.getX()
                    &&getPlayer().getT(getPlayer().positionY)==trig.getY())
                    return i;
            }
            
            if(maps.get(getMapAct()).getTriggers().get(i) instanceof TriggerMini)
            if (getPlayer().getT(getPlayer().positionX)==maps.get(getMapAct()).getTriggers().get(i).getX() 
                    &&getPlayer().getT(getPlayer().positionY)==maps.get(getMapAct()).getTriggers().get(i).getY()){
                if (maps.get(getMapAct()).getTriggers().get(i).isActive())
                return i;
            }else{
                player.correct=false;
            }
            
            if(maps.get(getMapAct()).getTriggers().get(i) instanceof TriggerMonologue){
                TriggerMonologue trig = (TriggerMonologue)maps.get(getMapAct()).getTriggers().get(i);
                if(getPlayer().getT(getPlayer().positionX)==trig.getX()
                    &&getPlayer().getT(getPlayer().positionY)==trig.getY() && trig.active)
                    return i;
            }
            
        }
        return -1;
    }
    public void tick(){
        
        getPlayer().tick();
        int i=triggerActive();
        if ((i)>=0){
            Trigger auxT=maps.get(getMapAct()).getTriggers().get(i);
            if(auxT instanceof TriggerMini){
                maps.get(getMapAct()).getTriggers().get(i).execTrigger(this);
            }else{
                maps.get(getMapAct()).getTriggers().get(i).execTrigger(this);
                Layer aux=maps.get(getMapAct()).getLC();
                getPlayer().setLC(aux);
            }
        }        
        maps.get(getMapAct()).tick();
        //faltaria tick de amigos
    }
    public ArrayList<Map> getMaps(){
        return maps;
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

    /**
     * @return the bright
     */
    public double getBright() {
        return bright;
    }

    /**
     * @param bright the bright to set
     */
    public void setBright(double bright) {
        this.bright = bright;
    }

    private void onChangeEnter(Graphics g) {
        
       bright=iter*step;
       iter++;
       
    }
    private void onChangeExit(Graphics g) {
       if (iter==1/step){
           change=false;
           bright=1;
           iter=1;
       }
       
    }

    /**
     * @return the change
     */
    public boolean isChange() {
        return change;
    }

    /**
     * @param change the change to set
     */
    public void setChange(boolean change) {
        this.change = change;
    }
    
    public Engine getEng(){
        return eng;
    }
   
}
