/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nidonuevo.model;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
/**
 *
 * @author pucp
 */
public class MainMenu extends State{
    protected ArrayList<String> options;
    private int posY;
    private final int space=100;
    private Engine eng;
    private final int x=350;    
    private final int y=250;
    private final int  widthB=150; //buttton width
    private final int  heightB=50; //button height
    private Rectangle playB=new Rectangle(x,y,widthB,heightB);
    private Rectangle helpB=new Rectangle(x,y+space,widthB,heightB);
    private Rectangle quitB=new Rectangle(x,y+2*space,widthB,heightB);
    public MainMenu(Engine eng){
        //aca se debe cargar el menu inicial
        options=new ArrayList<String>(0);
        options.add("START");
        options.add("HELP");
        options.add("SALIR");
       this.eng=eng;
        
        
    }
    public void render(Graphics g){
        Graphics2D g2d=(Graphics2D) g; 
        Font fnt0 =new Font("arial",Font.BOLD,50);
        g.setFont(fnt0);
        g.setColor(Color.black);
        for (int i=0;i<options.size();i++){
          g.drawString(options.get(i),x,y+space/2+space*(i));  
          
        }
        g2d.draw(playB);
        g2d.draw(helpB);
        g2d.draw(quitB);
    }
    public boolean ordenPop(){
        //arreglar
        if (eng.getKeyManager().eme){
            eng.getKeyManager().eme=false;
            return true;
        }
        if (eng.getKeyManager().q){
            System.exit(1);
        }
        return false;
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

