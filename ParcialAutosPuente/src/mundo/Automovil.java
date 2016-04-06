/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mundo;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author distribuidos
 */
public class Automovil extends Thread {
    
    public final static int IZQ = 1;
    public final static int DER = 2;
    
    private int tipo;
    
    private Puente puente;
    
    public Automovil(Puente puente,int tipo){
        this.puente = puente;
        this.tipo = tipo;
    }
    
    public void run(){
        
        while (true){
            
            int tiempoEspera = (int) (Math.random() * 10);
            if(tipo == 1){
                System.out.println("Llega un carro en el lado izquierdo, tiempo espera " + tiempoEspera + " segundos" );
                try {
                	
                    puente.colocarCarroIzq();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Automovil.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            else {
                System.out.println("Llega un carro en el lado derecho, tiempo espera " + tiempoEspera + " segundos" );
                try {
                	
                    puente.colocarCarroDer();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Automovil.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            
            try{
            	sleep(tiempoEspera*1000);
            }catch(Exception e){
            	
            }
        }
    }
    
}
