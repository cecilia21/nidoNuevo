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
import javax.swing.JOptionPane;
/**
 *
 * @author pucp
 */
public class MainMenu extends State {
    
    protected ArrayList<String> options;
    protected ArrayList<Button> buttons;
    private Selector sel;
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
        options.add("LOAD");
        options.add("HELP");
        options.add("SALIR");
        sel=new Selector(x-widthB,y,widthB,heightB,space,0,2,0,"/img/selector.png");
        buttons.add(new Button(options.get(0),x,y,widthB,heightB));
        buttons.add(new Button(options.get(1),x,y+space,widthB,heightB));
        buttons.add(new Button(options.get(2),x,y+2*space,widthB,heightB));
        buttons.add(new Button(options.get(3),x,y+3*space,widthB,heightB));
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
        sel.render(g);
        
   
        
    }
    public boolean ordenPop(){
        //arreglar
        if (eng.getKeyManager().enter){
            if (sel.getOpt()==1){
                getName dialog = new getName(new java.awt.Frame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0); //quitar elboton de cerrar
                    }
                });
               
                    dialog.setVisible(true);
                if (dialog.isClick_ok()){
                    eng.setPlayerName(dialog.name);
                    eng.getKeyManager().eme=false;
                    return true;
                }
            }
            if (sel.getOpt()==2){
                
                
                loadGame lgDialog = new loadGame(new java.awt.Frame(),true);
                lgDialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0); //quitar elboton de cerrar
                    }
                });
                lgDialog.setVisible(true);
                if (lgDialog.isClick_ok()){
                    eng.loadToBin(lgDialog.sel());
                    eng.getKeyManager().eme=false;
                    return true; //eng.getSM().pop();
                }    
//                
                
            }
            
            if (sel.getOpt()==3){
                help dg = new help(new java.awt.Frame(),true);
                dg.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0); //quitar elboton de cerrar
                    }
                });
                dg.setVisible(true);
                if (dg.isClick_ok()){
                    
                    eng.getKeyManager().eme=false;
                    return false;
                }
            }
            if (sel.getOpt()==4){
                System.exit(0);
            }
            
            
        }
        if (eng.getKeyManager().q){
            System.exit(1);
        }
        return false;
    }
    public void tick(){
        if (eng.getKeyManager().down){
            sel.down();
        }
        if(eng.getKeyManager().up){
            sel.up();
        }
    }
    public void onEnter(){
        
    }
    public void onExit(){
        
    }        

    /**
     * @return the posY
     */

    
}

