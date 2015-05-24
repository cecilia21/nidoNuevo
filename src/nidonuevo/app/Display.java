/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nidonuevo.app;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Panel;

/**
 *
 * @author TOSHIBA
 */
public class Display {
    private JFrame frame;
    private String title;
    private int width, height;
    private Canvas canvas;
    private Panel panel1;
    
    public Display(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height =height;
        createDisplay();
    }
    public void visible(){
        frame.setVisible(true);
    }

    private void createDisplay(){
     
        frame = new JFrame(title);
        frame.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        //frame.setSize(width,height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        //frame.setLocationRelativeTo(null);
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/ico_NN.jpg"));
        frame.setIconImage(icon);
        panel1 = new java.awt.Panel();
        panel1.setBackground(new java.awt.Color(102, 255, 0));
        java.awt.Button button1 = new java.awt.Button();
        button1.setLabel("button1");

        panel1.setBackground(new java.awt.Color(102, 255, 0));
        panel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        button1.setLabel("button1");
        panel1.add(button1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, -1, -1));
        panel1.setVisible(false);
        frame.getContentPane().add(panel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 380, 110));
        
        canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);
		canvas.setBackground(Color.black);
		frame.getContentPane().add(canvas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 800, 700));
		frame.pack();
    }
    public JFrame getFrame(){
        return frame;
    }
    public Canvas getCanvas(){
		return canvas;
	}
public void setFrame(JFrame fr){
    frame = fr;
    frame.pack();
}

public void setOnMonPanel(){
    panel1.setVisible(true);
}
        public void setOffMonPanel(){
            panel1.setVisible(false);
        }

}
