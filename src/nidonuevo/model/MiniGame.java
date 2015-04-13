/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nidonuevo.model;

/**
 *
 * @author pucp
 */
public class MiniGame {
    private int id;
    private Object game;
    
    public void render(){
        
    }
    public void update(){
        
    }
    public void onEnter(){
        
    }
    public void onExit(){
        
    }        

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the game
     */
    public Object getGame() {
        return game;
    }

    /**
     * @param game the game to set
     */
    public void setGame(Object game) {
        this.game = game;
    }
    
}
