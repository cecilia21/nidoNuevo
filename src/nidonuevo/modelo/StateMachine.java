/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nidonuevo.modelo;

import java.util.Stack;

public class StateMachine {
    
    private Stack<String> stackSM=new Stack<String>();
    
    public void udpate(){ //falta el argumento elapsedTime, cual es el tipo?

    }
    
    public void render(){
        
    }
    
    public void add(State state){
        
    }
    
    public void pull(){
        
    }

    public Stack<String> getState() {
        return stackSM;
    }

    public void setState(Stack<String> stackSM) {
        this.stackSM = stackSM;
    }
    
    
}
