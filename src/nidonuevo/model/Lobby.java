/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nidonuevo.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import serverrmi.IServices;

/**
 *
 * @author alulab14
 */
public class Lobby extends State{
    private int auxEnter=0;
    private BufferedImage background;
    private Engine eng;
    public static Registry reg = null;
    public static IServices proxy = null;
    private boolean flagDialogo = true;
    private boolean [] listosList;
    static {
            try {
                    reg = LocateRegistry.getRegistry("192.168.207.206", 1099);
                    //reg = LocateRegistry.getRegistry("127.0.0.1", 1099);
                    proxy = (IServices)reg.lookup("MyRMIServer");
            } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }
    }
    
    public Lobby(Engine eng){
        this.eng = eng;
        background=ImageLoader.loadImage("/img/pizarraMul.png");
        /*serverrmi.IServices.Player pp= new serverrmi.IServices.Player( eng.LMS.getPlayer().getName(),p.getPositionX(), p.getPositionY(),
                                                    p.getCurrentMap(),p.getDir(),p.getS());
                    try {
                        proxy.conexionPlayer(pp);
                    } catch (RemoteException ex) {
                        Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    ThreadSend hilo= new ThreadSend(eng);
                    hilo.start();
                    ThreadGet hilo2= new ThreadGet(eng);
                    hilo2.start(); */
    }
    
    public boolean mostrarDialogoNombre(){
        getName dialog = new getName(new java.awt.Frame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0); //quitar elboton de cerrar
                    }
                });
               
                    dialog.setVisible(true);
                if (dialog.isClick_ok()){
                    eng.setPlayerName(dialog.name);
                    
                    eng.getKeyManager().eme=false;
                    Player p=eng.LMS.getPlayer();
                    p.setCurrentMap(0);
                    System.out.println(p.getName());
                    serverrmi.IServices.Player pp= new serverrmi.IServices.Player( p.getName(),p.getPositionX(), p.getPositionY(),
                                                    p.getCurrentMap(),p.getDir(),p.getS());
                    try {
                        if(!proxy.conexionPlayer(pp)){
                            JOptionPane.showMessageDialog(null, "juego ya iniciado");
                            eng.getSM().pop();
                        }
                        else{
                            ThreadSend hilo= new ThreadSend(eng, this);
                            hilo.start();
                            ThreadGet hilo2= new ThreadGet(eng, this);
                            hilo2.start(); 
                            return false;
                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
                    }
                                              
                    
                }
                return true;
    }

    @Override
    public boolean ordenPop() {
        if(eng.getKeyManager().enter){
            if(auxEnter==0) auxEnter++;
        }
        if(eng.getKeyManager().enterR){
            if(auxEnter==1) auxEnter++;
        }        
        if(auxEnter==2){
            auxEnter=0;
            eng.getKeyManager().enterR=false;
            eng.getKeyManager().enter=false;
            try {
                //debe iniciar el juego u.u
                //proxy.iniciarJuego();
                System.out.println("enter");
                proxy.agregarListo();
                
            } catch (RemoteException ex) {
                Logger.getLogger(Lobby.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try{
        if(proxy.todosListos()){
                    //agregar local map
                    LocalMap lmap = eng.LMS;
                    eng.getSM().pop();
                    eng.getSM().add(lmap);
                    eng.hiloTime.start();
                    
        }
        } catch (RemoteException ex){
            ex.printStackTrace();
        }
            
        return false;
    }
    
    public void render(Graphics g){
        
        if(flagDialogo){
            flagDialogo = mostrarDialogoNombre();
            //System.out.println("gg");
        }
            g.drawImage(background,0,0,800,700,null);
        try {
            ArrayList<serverrmi.IServices.Player> plist = proxy.receiveData();
            
            for(int i = 0; i< plist.size();i++){
                g.setFont(new Font("Comic Sans MS",Font.BOLD,50));
                g.setColor(Color.WHITE);
                g.drawString(plist.get(i).name, 100, i*100+80);
                
            }
        } catch (RemoteException ex) {
            Logger.getLogger(Lobby.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
//    public void setPauseGame(boolean pause){
//        try {
//                proxy.setpauseGame(pause);
//            } catch (RemoteException ex) {
//                Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
//            }
//    }
//    
//    public boolean getPauseState(){
//        boolean pause=false;
//        try {
//            pause=proxy.getPauseState();
//            return pause;
//        } catch (RemoteException ex) {
//            Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return false;
//    }
    
    public void tick(){
        if(eng.getKeyManager().enter){
            System.out.println("presiono enter :D");
        }
    }
}
