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
public class MiniGame extends State {
    private int aux =0;
    private ArrayList<String> messages=new ArrayList<String>();
    protected ArrayList<String> options;
    protected ArrayList<Button> buttons;
    private Selector sel;
    private final int spaceY=100;
    private final int spaceX=300;
    private String title="Nido Nuevo, Amigos Nuevos!";
    private Font fntT;
    private final int fontSizeT=40;
    private BufferedImage background;
    private Engine eng;
    private final int x=120;    
    private final int y=500;
    private final int  widthB=250; //buttton width
    private final int  heightB=70; //button height
    private Font fnt0;
    private int selectY=y; 
    private int turno =0;
    private ArrayList<Person> persons;
    
    public MiniGame(Engine eng,ArrayList<Person> persons){
        this.persons=persons;
        fnt0 = new Font("Monotype Corsiva",Font.BOLD,50);
        buttons=new ArrayList<Button>();        
        options=new ArrayList<String>();
        fntT =new Font("Comic Sans MS",Font.BOLD,fontSizeT);
        options.add("ATACK");        
        options.add("KILL");
        options.add("ESPEC");        
        options.add("SALIR");
        sel=new Selector(x-widthB,y,widthB,heightB,spaceY,spaceX,2,2,"/img/selector_1.png");
        buttons.add(new Button(options.get(0),x,y,widthB,heightB));
        buttons.add(new Button(options.get(1),x,y+spaceY,widthB,heightB));
        buttons.add(new Button(options.get(2),x+spaceX,y,widthB,heightB));
        buttons.add(new Button(options.get(3),x+spaceX,y+spaceY,widthB,heightB));
        
        this.eng=eng;
        background=ImageLoader.loadImage("/img/bg_battle.png");
        
    }
    
    public boolean ordenPop(){
        //arreglar
        if (eng.getKeyManager().enter){
            
//            if (sel.getOpt()==2 && sel.getOptX()==2 ){
//                System.out.println("2");
//                return true; //eng.getSM().pop();
//            }
            if (sel.getOpt()==2 && sel.getOptX()==2 ){
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
        g.setFont(fnt0);
       if (!messages.isEmpty()) g.drawString(messages.get(0),350,200);   
        
    }
     private void nextTurn(){
         if (turno<persons.size()-1)
         turno++;
         else
         turno=0;
     }
    public void tick(){
       
        if (eng.getKeyManager().enter){
            if (aux==0) aux++;
        }
        if (eng.getKeyManager().enterR){
            if (aux==1) aux++;
        }
        if (aux==2){
            eng.getKeyManager().enterR=false;
            eng.getKeyManager().enter=false;
            if (sel.getOpt()==1 && sel.getOptX()==1){
                aux=0;
                
                nextTurn();
                messages.clear();
                messages.add("Ataque "+turno);

            }

            
        }
        
        if (eng.getKeyManager().down){
            sel.down();
           // sel.print();
        }
        if(eng.getKeyManager().up){
            sel.up();
           // sel.print();
        }
        if(eng.getKeyManager().left){
            sel.left();
           // sel.print();
        }
        if(eng.getKeyManager().right){
            sel.right();
           // sel.print();
        }
    }
    public void update(){
        
    }
    public void onEnter(){
        
    }
    public void onExit(){
        
    }        


        
}
