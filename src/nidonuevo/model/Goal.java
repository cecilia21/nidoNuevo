/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nidonuevo.model;

/**
 *
 * @author alulab14
 */
public class Goal {
   private int id;
   private boolean active;
   private String description;
   private int tipo; //1 para minigame y 2 para NPC
   public Goal(){
       
   }
   public Goal(int id, boolean active,String desc,int tipo){
       this.id=id;
       this.active=active;
       description=desc;
       this.tipo=tipo;
   }

   
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    
    public String getDescription() {
        return description;
    }

    
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the tipo
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
   
   
}
