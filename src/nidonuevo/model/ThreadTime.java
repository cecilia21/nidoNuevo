/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nidonuevo.model;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Raul
 */
public class ThreadTime extends Thread{
    private Engine eng;
    private int time=400;
    private boolean corre=true;
    public ThreadTime(Engine eng){
        this.eng=eng;
        corre=true;
    }
    public void run(){
        corre=true;
        while (corre){
            setTime(getTime() - 1);
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadTime.class.getName()).log(Level.SEVERE, null, ex);
            }
        }		
    }   

    /**
     * @return the time
     */
    public int getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     * @return the corre
     */
    public boolean isCorre() {
        return corre;
    }

    /**
     * @param corre the corre to set
     */
    public void setCorre(boolean corre) {
        this.corre = corre;
    }
   
}
