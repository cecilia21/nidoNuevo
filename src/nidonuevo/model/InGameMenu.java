/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nidonuevo.model;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Stack;
import java.util.ArrayList;
/**
 *
 * @author pucp
 */
public class InGameMenu extends State {
    
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

    private int selectY=y; 
    
    public InGameMenu(Engine eng){
        
        buttons=new ArrayList<Button>();        
        options=new ArrayList<String>();
        fntT =new Font("Comic Sans MS",Font.BOLD,fontSizeT);
        options.add("SAVE");        
        options.add("SALIR");
        sel=new Selector(x-widthB,y,widthB,heightB,space,0,2,0,"/img/selector.png");
        buttons.add(new Button(options.get(0),x,y,widthB,heightB));
        buttons.add(new Button(options.get(1),x,y+space,widthB,heightB));
        this.eng=eng;
        background=ImageLoader.loadImage("/img/bgF.jpg");
        
    }
    
    public boolean ordenPop(){
        //arreglar
        if (eng.getKeyManager().enter){
            if (sel.getOpt()==1){
                setFile dialog = new setFile(new java.awt.Frame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0); //quitar elboton de cerrar
//                    }
//                });
               
                    dialog.setVisible(true);
                if (dialog.isClick_ok()){
                    eng.saveToBin(dialog.name);
                    eng.getKeyManager().eme=false;
                    System.out.println("1");
                    return true; //eng.getSM().pop();
                }
                
                
                
               
                
            }
            if (sel.getOpt()==2){
                System.out.println("2");
                return true; //eng.getSM().pop();
            }
            
        }
        if (eng.getKeyManager().q){
            System.exit(1);
        }
        return false;
    }

     public void render(Graphics g){
        
        g.drawImage(background,0,0,800,700,null);
        for (int i=0;i<buttons.size();i++){
            buttons.get(i).render(g);
          
        }
        sel.render(g);   
        
    }
     
    public void tick(){
        if (eng.getKeyManager().down){
            sel.down();
        }
        if(eng.getKeyManager().up){
            sel.up();
        }
    }
    public void update(){
        
    }
    public void onEnter(){
        
    }
    public void onExit(){
        
    }        


        
}
