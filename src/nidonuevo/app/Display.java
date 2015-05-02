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
/**
 *
 * @author TOSHIBA
 */
public class Display {
    private JFrame frame;
    private String title;
    private int width, height;
    private Canvas canvas;
    
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

        frame.setSize(width,height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/ico_NN.jpg"));
        frame.setIconImage(icon);
        
        canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);
		canvas.setBackground(Color.black);
		frame.add(canvas);
		frame.pack();
    }
    public JFrame getFrame(){
        return frame;
    }
    public Canvas getCanvas(){
		return canvas;
	}
	

}
