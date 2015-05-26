/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nidonuevo.app;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import nidonuevo.model.KeyManager;


/**
 *
 * @author Cecilia
 */
public class DialogueCanvas extends Canvas{
        ArrayList<String> conversacion;
        Graphics g;
        private int altura=30;
        private int i=0;
        private int cotI;
        private int cotF;
        
        public DialogueCanvas(ArrayList<String> letras){
//            setPreferredSize(new Dimension(100, 600));
//            setMaximumSize(new Dimension(800, 600));
//            setMinimumSize(new Dimension(800, 600));
//            setFocusable(false);
            setBackground(Color.green);
            
            conversacion=letras;          
        }

        public void paint(Graphics g){
//            Image img2 = new ImageIcon("src/img/cloud.png").getImage();
//            g.drawImage(img2, 10, 10,null);
            if(i>conversacion.size())
                return;
            g.clearRect(0, 0, 600, 800);
            Font fuente=new Font("Monospaced", Font.BOLD, 20);
            g.setFont(fuente);
            g.setColor(Color.BLACK);
            altura=30;
            cotI=0;
            cotF=62;
            for(int k=0;k<2;k++){//se muestra en dos lineas todo
                String linea=new String();
                try{
                    linea=conversacion.get(i).substring(cotI, cotF);
                        while(linea.charAt(cotF-1)!=' '){
                            cotF--;
                        }
                    linea=conversacion.get(i).substring(cotI, cotF);
                    g.drawString(" "+linea,0,altura);
                    altura+=30;//lo q se suma a la altura para que se muestre en la siguiente linea
                }catch(Exception e){
                    linea=conversacion.get(i).substring(cotI, conversacion.get(i).length());
                    g.drawString(" "+linea,0,altura);//falta acomodar la altura para que se vea
                     i++;
                    break;
                }
                cotI=cotF;
                cotF+=cotF;
            }      
        }
        public boolean isFinish(){
            return (i>=conversacion.size());
        }
}