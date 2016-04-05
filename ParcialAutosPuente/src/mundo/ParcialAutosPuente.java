/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mundo;

/**
 *
 * @author distribuidos
 */
public class ParcialAutosPuente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Puente puente = new Puente();
        Automovil derecha = new Automovil(puente, Automovil.DER); 
        Automovil derecha1 = new Automovil(puente, Automovil.DER);
        Automovil derecha2 = new Automovil(puente, Automovil.DER);
        Automovil derecha3 = new Automovil(puente, Automovil.DER);
        
        Automovil izquierda = new Automovil(puente, Automovil.IZQ);
        Automovil izquierda1 = new Automovil(puente, Automovil.IZQ);
        Automovil izquierda2 = new Automovil(puente, Automovil.IZQ);
        Automovil izquierda3 = new Automovil(puente, Automovil.IZQ);
        
        
        Sentido sentido = new Sentido(puente);
        
        sentido.start();
        
        
        derecha.start();
        derecha1.start();
        derecha2.start();
        derecha3.start();
        
        
        izquierda.start();
        izquierda1.start();
        izquierda2.start();
        izquierda3.start();
    }
    
}
