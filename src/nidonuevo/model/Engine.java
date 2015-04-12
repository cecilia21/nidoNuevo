/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nidonuevo.model;
import nidonuevo.app.Display;
public class Engine {
    private String title;
    private int width, height;
    
    public Engine(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height =height;        
    }   
    public void start(){
        Display display=new Display(title,width,height);
    }
    public void getInput(){
        
    }
    
    public void setOutput(){
        
    }
    
    public void validate(){
        
    }
    
    
}
