/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nidonuevo.model;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
/**
 *
 * @author pucp
 */
public class MainMenu extends State{
    protected ArrayList<String> options;
    private int posY;
    private final int space=70;
    
    public MainMenu(){
        //aca se debe cargar el menu inicial
        options=new ArrayList<String>(0);
        options.add("START");
        options.add("SALIR");
        
        
        
    }
    public void render(Graphics g){
        Font fnt0 =new Font("arial",Font.BOLD,50);
        g.setFont(fnt0);
        g.setColor(Color.black);
        for (int i=0;i<options.size();i++){
          g.drawString(options.get(i),100,space*(i+1));  
        }
        
    }
    public void tick(){
        
    }
    public void onEnter(){
        
    }
    public void onExit(){
        
    }        

    /**
     * @return the posY
     */
    public int getPosY() {
        return posY;
    }

    /**
     * @param posY the posY to set
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }
    
}
