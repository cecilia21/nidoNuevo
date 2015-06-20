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
public class ThreadGet extends Thread {
    //public Player jug= null;
    public IServices proxy = null;  
    public ThreadGet(Engine eng){
        super();
        proxy=eng.proxy;
        //jug=eng.LMS.getPlayer();
    }
    public void run(){
        while (true){
            Integer posX=0;
            Integer posY=0;
            Integer map=0;
            try {
                proxy.receiveData(posX, posY, map);
                System.out.println(posX);
                System.out.println(posY);
                System.out.println(map);
            } catch (RemoteException ex) {
                Logger.getLogger(ThreadSend.class.getName()).log(Level.SEVERE, null, ex);
            }
        }		
    }    
}