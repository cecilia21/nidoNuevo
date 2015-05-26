/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nidonuevo.model;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import nidonuevo.app.Display;
import javax.swing.JFrame;


/**
 *
 * @author alulab14
 */
public class TriggerMonologue extends Trigger {
    //private int pX;
    //private int pY;
    //private int changeTo;
    private int aux=0;
    
    public TriggerMonologue(int x,int y){
        this.x=x;
        this.y=y;
        this.active=true;
        
    }
    
    
    @Override
    public void execTrigger(LocalMap aThis) {
        if (this.active){
            //Esto se leera del XML, solo es para probar
            ArrayList<String> letras= new ArrayList<String> ();
            letras.add("Hola, como estas? Me llamo, Vania, te gustaria jugar conmigo y mi demas amigos");/////////Limite
            letras.add("En el mundo de los animales vivía una liebre muy orgullosa y vanidosa");
            letras.add(", que no cesaba de pregonar que ella era la más veloz y se burlaba de ello ante la lentitud de la tortuga");
            letras.add("Un día, a la tortuga se le ocurrió hacerle una inusual apuesta a la liebre:");
            letras.add(" Estoy segura de poder ganarte una carrera");
            letras.add("¿A mí? Preguntó asombrada la liebre.");
            letras.add("Sí, sí, a ti, dijo la tortuga. Pongamos nuestras apuestas y veamos quién gana la carrera.");
            letras.add("La liebre, muy ingreída, aceptó la apuesta.");
            letras.add("Así que todos los animales se reunieron para presenciar la carrera. El búho señaló los puntos de partida");
            letras.add("y de llegada, y sin más preámbulos comenzó la carrera en medio de la incredulidad de los asistentes.");

            aThis.getEng().getDisplay().setOnMonPanel();
            aThis.getEng().getDisplay().setOnDialogos(letras);//Se llama al establecer canvas
            
            while(!aThis.getEng().keyManager.s){
                        aThis.getEng().keyManager.tick();
                        if(aThis.getEng().keyManager.z)
                            if(aux==0) aux++;
                        if(aThis.getEng().keyManager.zR)
                            if(aux==1) aux++;
                        if(aux==2){
                            aThis.getEng().keyManager.z=false;
                            aThis.getEng().keyManager.zR=false;
                            aux=0;
                              aThis.getEng().getDisplay().getCanvasLetras().repaint();
                        }
                        if (aThis.getEng().getDisplay().getCanvasLetras().isFinish()&&aThis.getEng().keyManager.z)
                                break;
            }
            this.active = false;
            aThis.getEng().getDisplay().setOffMonPanel();
            aThis.getEng().getDisplay().removePanel();
        }
        
    }
 
    public int getChangeTo(){
        return 1;
    }     

}
