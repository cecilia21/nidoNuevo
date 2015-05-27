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
    private ArrayList<Image> listImages ;
    private int matches=0;
    private int[] fila ={136,258,380,502}; // pixel x 
    private int[] col={157,298,439};    // pixel y
    private Engine eng;
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
}
