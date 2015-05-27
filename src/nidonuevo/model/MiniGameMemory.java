/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nidonuevo.model;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author alulab14
 */
public class MiniGameMemory extends State {
    private Image img = new ImageIcon("src/img/memory.jpg").getImage();
    private String manoCursor= "/img/mano.png";
    private ArrayList<Image> listImages = new ArrayList<Image>() ;
    private int matches=0;
    private int[] fila ={136,258,380,502}; // pixel x 
    private int[] col={157,298,439};    // pixel y
    private Engine eng;
    private Selector sel= new Selector(185,203,33,26,145,123,510,560,manoCursor);
    private int auxEnter=0;
    private int[] flags={0,0,0,0,0,0,0,0,0,0,0,0};
    private Boolean initTurno=true;
    private Boolean match=true;
    private int ultFlag=0;
    private int antultFlag=0;
    
    public MiniGameMemory(Engine eng){
       this.eng=eng;
       inicializa();
    }
    @Override
    public boolean ordenPop() {
       return (matches==6);
    }
    public void render(Graphics g){    
        
        g.drawImage(img, 0, 0, null);
        sel.render(g);
        rules();

        for(int i=0;i<12;i++)
            if(flags[i]==1) g.drawImage(listImages.get(i%6), fila[i%4], col[getCol(i)], null);
        sel.render(g);
        //g.drawImage(manocursor, fila[0]+44, col[0]+47, null);
    }
    public void inicializa(){
        Image img1 = new ImageIcon("src/img/d1.png").getImage();
        listImages.add(img1);
        Image img2 = new ImageIcon("src/img/d2.png").getImage();
        listImages.add(img2);
        Image img3 = new ImageIcon("src/img/d3.png").getImage();
        listImages.add(img3);
        Image img4 = new ImageIcon("src/img/d4.png").getImage();
        listImages.add(img4);
        Image img5 = new ImageIcon("src/img/d5.png").getImage();
        listImages.add(img5);        
        Image img6 = new ImageIcon("src/img/d6.png").getImage();
        listImages.add(img6);        
    }
    public void tick(){
        if (eng.getKeyManager().down)
            sel.down();
        if (eng.getKeyManager().up)
            sel.up();
        if (eng.getKeyManager().left)
            sel.left();
        if (eng.getKeyManager().right)
            sel.right();
    }
    public void rules(){
        
         if(eng.getKeyManager().enter){
            if(auxEnter==0) auxEnter++;
        }
        if(eng.getKeyManager().enterR){
            if(auxEnter==1) auxEnter++;
        }   
        if(auxEnter==2){
            eng.getKeyManager().enter=false;
            eng.getKeyManager().enterR=false;
            auxEnter=0;
            int y=sel.getOpt();// obtengo opcion
            int x=sel.getOptX(); // Lo maximo ese selector xD
            int pos=getPos(x,y);
            if(pos>=0){
            flags[pos]=1;
            if(initTurno){             
                initTurno=false;
                if(match==false) {
                    flags[ultFlag]=0;
                    flags[antultFlag]=0;
                } // despintamos si no hizo match en la anterior 
                antultFlag=pos;   
                
            }
            else{// verificamos si hizo match xD
                if(((pos-antultFlag)==6) ||((antultFlag-pos)==6)){ // hizo match xD
                    matches++;
                    match=true;
                }
                else match=false;
                initTurno=true;
                ultFlag=pos; 
            }
            }
        }
    }
    public int getPos(int x, int y){ // falta mejorar esta funcion para ahorrar lineas xD gg
        if(y==1) return x-y;
        if(y==2)return x+y+1;
        if(y==3) return x+y+4;
        return -1;
    }
    public int getCol(int i){
        if(i<4) return 0;
        if(i<8) return 1;
        return 2;
    }
}
