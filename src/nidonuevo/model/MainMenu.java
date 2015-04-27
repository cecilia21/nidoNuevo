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
import java.awt.image.BufferedImage;
import java.util.ArrayList;
/**
 *
 * @author pucp
 */
public class MainMenu extends State{
    protected ArrayList<String> options;
    protected ArrayList<Button> buttons;
    private final int space=100;
    private String title="Nido Nuevo, Amigos Nuevos!";
    private Font fntT;
    private final int fontSizeT=40;
    private BufferedImage background;
    private Engine eng;
    private final int x=275;    
    private final int y=250;
    private final int  widthB=250; //buttton width
    private final int  heightB=70; //button height

    private int selectY=y; //selector del menu
    public MainMenu(Engine eng){
        //aca se debe cargar el menu inicial
        //carga de botones
        buttons=new ArrayList<Button>();        
        options=new ArrayList<String>();
        fntT =new Font("Comic Sans MS",Font.BOLD,fontSizeT);
        options.add("START");        
        options.add("HELP");
        options.add("SALIR");
        buttons.add(new Button(options.get(0),x,y,widthB,heightB));
        buttons.add(new Button(options.get(1),x,y+space,widthB,heightB));
        buttons.add(new Button(options.get(2),x,y+2*space,widthB,heightB));
       this.eng=eng;
       background=ImageLoader.loadImage("/img/bgF.jpg");
        
        
    }
    public void render(Graphics g){
        //background
        
        g.drawImage(background,0,0,800,700,null);
        //titulo
//        g.setFont(fntT);
//        g.setColor(Color.black);
//        int cen=(int)(800-title.length()*((int)(fontSizeT)))/(2);
//        g.drawString(title,x+cen,y-100);
        //buttons
        for (int i=0;i<buttons.size();i++){
            buttons.get(i).render(g);
          
        }
        
   
        
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

    
}

