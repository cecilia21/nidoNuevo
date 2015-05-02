/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nidonuevo.app;

/**
 *
 * @author alulab14
 */
import java.io.File;
import nidonuevo.model.Engine;
import nidonuevo.model.Loading;

public class NidoNuevo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //archivo de inicialización XML, el path en la variable pathXML
        String pathXML="/files/xml/initNidoNuevo.xml";
        //Engine engine();
        
        Engine engine=new Engine("Nido Nuevo",800,700);        
        engine.start();
        //cinematic de introduccion
        //HE PUESTO EL SAVE DENTRODE NOMBRE PARA PROBARLO, DEBERIA IR DENTRO DE
        //UNA OPCION DE GUARDAR PARTIDA
        // public void setPlayerName(String name){ AHI LO PUSE, DIEGO
    }
    
}
