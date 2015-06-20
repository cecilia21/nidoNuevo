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
    public ThreadSend(Engine eng){
        super();
        proxy=eng.proxy;
        jug=eng.LMS.getPlayer();
    }
    public void run(){
        while (true){
            int posX=jug.positionX;
            int posY=jug.positionY;
            int map=jug.getCurrentMap();
            try {
                proxy.giveData(posX, posY, map);
            } catch (RemoteException ex) {
                Logger.getLogger(ThreadSend.class.getName()).log(Level.SEVERE, null, ex);
            }
        }		
    }    
}
