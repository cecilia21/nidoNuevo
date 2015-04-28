/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nidonuevo.model;
import java.awt.FileDialog;
import java.awt.Frame;
/**
 *
 * @author TOSHIBA
 */
public class loadGame extends FileDialog{

    public loadGame(Frame parent) {
        
        super(parent,"g",LOAD);
        initComponents();
        setLocation(430,300);
    }
    private void initComponents() {
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        pack();
    }
    private void closeDialog(java.awt.event.WindowEvent evt) {                             
        setVisible(false);
        dispose();
    }       
    
}
