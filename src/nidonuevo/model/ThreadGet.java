/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nidonuevo.model;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import serverrmi.IServices;

/**
 *
 * @author alulab14
 */
public class ThreadGet extends Thread {
    //public Player jug= null;
    public ArrayList<Integer> dat= new ArrayList<Integer>() ;
    public IServices proxy = null;  
    public ThreadGet(Engine eng){
        super();
        proxy=eng.proxy;
        //jug=eng.LMS.getPlayer();
    }
    public void run(){
        while (true){

            try {
                dat=proxy.receiveData();
                System.out.println(dat.get(0));
                System.out.println(dat.get(1));
                System.out.println(dat.get(2));
            } catch (RemoteException ex) {
                Logger.getLogger(ThreadSend.class.getName()).log(Level.SEVERE, null, ex);
            }
        }		
    }    
}