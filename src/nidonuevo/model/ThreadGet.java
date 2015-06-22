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
    public Player jug= null;
    public ArrayList<serverrmi.IServices.Player> dat= new ArrayList<serverrmi.IServices.Player>() ;
    public IServices proxy = null;  
    public ThreadGet(Engine eng){
        super();
        proxy=eng.proxy;
        jug=eng.LMS.getPlayer();
    }
    public void run(){
        while (true){

            try {
                dat=proxy.receiveData();
                for(int i=0;i<dat.size();i++){
                    if(dat.get(i).name.compareTo(jug.getName())!=0){
                        System.out.print(dat.get(i).name);
                        System.out.print("-");
                        System.out.print(dat.get(i).posX);
                        System.out.print("-");
                        System.out.print(dat.get(i).posY);
                        System.out.print("-");
                        System.out.println(dat.get(i).map);
                        jug.setOtherPlayer(dat.get(i));
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(ThreadSend.class.getName()).log(Level.SEVERE, null, ex);
            }
        }		
    }    
}