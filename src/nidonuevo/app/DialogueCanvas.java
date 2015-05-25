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
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Raul
 */
public class DialogueCanvas extends Canvas{
    
        ArrayList<String> conversacion;
        Graphics g;
        private int altura=500;
        private int i=0;
        private int cotI=0;
        private int cotF=60;
        public DialogueCanvas(ArrayList<String> letras){
//            setPreferredSize(new Dimension(100, 600));
//            setMaximumSize(new Dimension(800, 600));
//            setMinimumSize(new Dimension(800, 600));
//            setFocusable(false);
            setBackground(Color.WHITE);
            conversacion=letras;
            addMouseMotionListener(new MouseMotionAdapter() {//Lo hize para probar, cada vez que arrastras el 
                                                            // mouse se pinta la siguiente linea del letras[i],solo se
                                                            //cambia por lectura del teclado        
                @Override
            public void mouseDragged(MouseEvent e){
                repaint();
            }
        });
            repaint();
        }

        public void paint(Graphics g){
            if(i>=(conversacion.size()-1))
                return;
            g.clearRect(0, 0, 600, 800);
            Font fuente=new Font("Monospaced", Font.BOLD, 18);
            g.setFont(fuente);
            g.setColor(Color.black);
            altura=500;
            cotI=0;
            cotF=60;
            for(int k=0;k<2;k++){//se muestra en dos lineas todo
                String linea=new String();
                try{
                    linea=conversacion.get(i).substring(cotI, cotF);
                    g.drawString(linea,400,altura);
                    altura+=30;//lo q se suma a la altura para que se muestre en la siguiente linea
                }catch(Exception e){
                    linea=conversacion.get(i).substring(cotI, conversacion.get(i).length());
                    g.drawString(linea,50,altura);//falta acomodar la altura para que se vea
                     i++;
                    break;
                }
                cotI=cotF;
                cotF=cotF+60;
            }      
        }
}
