/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nidonuevo.model;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import serverrmi.IServices;

/**
 *
 * @author alulab14
 */
public class ThreadSend extends Thread {
    public Player jug= null;
    public IServices proxy = null; 
    public Engine eng=null;
    public serverrmi.IServices.Player jugP=null ;
    public ThreadSend(Engine eng){
        super();
        proxy=eng.proxy;
        jug=eng.LMS.getPlayer();
        jugP=new serverrmi.IServices.Player(jug.getName(),jug.getPositionX(),jug.getPositionY(),jug.getCurrentMap());
        this.eng=eng;
    }
    public void run(){
        while (true){
            jug=eng.LMS.getPlayer();
            jugP.posX=jug.positionX;
            jugP.posY=jug.positionY;
            jugP.map=jug.getCurrentMap();
            jugP.name=jug.getName();
            try {
                proxy.giveData(jugP);
            } catch (RemoteException ex) {
                Logger.getLogger(ThreadSend.class.getName()).log(Level.SEVERE, null, ex);
            }
        }		
    }    
}
