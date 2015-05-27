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
    private Image img1;
    private Selector sel= new Selector(185,203,33,26,145,123,510,560,manoCursor);
    private int auxEnter=0;
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
            int y=sel.getOpt();
            int x=sel.getOptX();
            if((x==185) &&(y==203)){
                g.drawImage(img1, fila[0], col[0], null);
            }
        }
        //g.drawImage(manocursor, fila[0]+44, col[0]+47, null);
    }
    public void inicializa(){
        img1 = new ImageIcon("src/img/d1.png").getImage();
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
}
