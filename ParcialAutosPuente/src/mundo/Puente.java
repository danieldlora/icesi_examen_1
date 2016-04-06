/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mundo;

import java.util.concurrent.Semaphore;

/**
 *
 * @author distribuidos
 */
public class Puente {
    
    public final static int CANTIDAD_MAXIMA = 10;
    
    
    int cantidadCarrosIzq;
    int cantidadCarrosDer;
    
    /**
     * Cantidad de carros que se encuentran sobre el puente
     */
    int cantidadPuente;
    
    /**
     * Sentido de que se transportan los carros
     * 
     * 1: Izquierda -> Los carros van del lado izquierdo al derecho
     * 2: Derecha -> Los carros van del lado derecho al izquierdo
     */
    int  sentido;
    
    /**
     * Existen 4 hilos de carros tipo izquierda, este semáforo se encargará de mantener el orden en que accesan a la cantidad de carros
     */
    Semaphore espacioDisponibleIzq;
    
    /**
     * Existen 4 hilos de carros tipo derecha, este semáforo se encargará de mantener el orden en que accesan a la cantidad de carros
     */
    Semaphore espacioDisponibleDer;
    
    /**
     * Controla el pase de carros que no se exceda de 10 y que pueda pasar cuando el sentido de que llega esté en verde
     */
    Semaphore mutex;
    
    public Puente(){
        this.cantidadCarrosDer = 0;
        this.cantidadCarrosIzq = 0;
        this.cantidadPuente = 0;
        
        espacioDisponibleIzq = new Semaphore(1,true);
        espacioDisponibleDer = new Semaphore(1, true);
        mutex = new Semaphore(1,true);
    }
    
    /**
     * Metodo para agregar la cantidad de carros del lado izquierdo, en caso tal que el sentido esté en verde(Semáforo en vida real)
     * puede pasar por el puente
     * @throws InterruptedException 
     */
    public void colocarCarroIzq() throws InterruptedException{
        espacioDisponibleIzq.acquire();
        cantidadCarrosIzq++;
        espacioDisponibleIzq.release();
        if(sentido==1){
            
            pasarCarrosIzq();
        }
        
       
    }
    
    /**
     * Metodo para agregar la cantidad de carros del lado derecho, en caso tal que el sentido esté en verde(Semáforo en vida real)
     * puede pasar por el puente
     * @throws InterruptedException 
     */
    public void colocarCarroDer() throws InterruptedException{
       
        espacioDisponibleDer.acquire();
        cantidadCarrosDer++;
        espacioDisponibleDer.release();        
        if(sentido==2){
           
            pasarCarrosDer();
        }
     
    }
    
    /**
     * Metodo para cambiar el sentido del puente
     * @param sentido
     * @throws InterruptedException 
     */
    public void cambiarSentido(int sentido) throws InterruptedException{
       
        if(sentido == 1){
            this.sentido = 2;
            pasarCarrosDer();
        }
        
        else {
            this.sentido = 1;
            pasarCarrosIzq();
        }
    }
    
    /**
     * Método que se encarga de permitir pasar los carros del sentido izquierdo al derecho
     * @throws InterruptedException 
     */
    public void pasarCarrosIzq() throws InterruptedException{
        mutex.acquire();
        if(cantidadCarrosIzq>= CANTIDAD_MAXIMA){
             System.out.println("pasan " + CANTIDAD_MAXIMA + " carros del sentido izquierdo al derecho " );
            cantidadCarrosIzq-=CANTIDAD_MAXIMA;
        }
        else{
            if(cantidadCarrosIzq != 0){
            System.out.println("pasan " + cantidadCarrosIzq + " carros del sentido izquierdo al derecho");
            cantidadCarrosIzq = 0;
            }
        }
        mutex.release();
    }
    
    
    /**
     * Método que se
     * @throws InterruptedException 
     */
    public void pasarCarrosDer() throws InterruptedException{
        mutex.acquire();
        if(cantidadCarrosDer>= CANTIDAD_MAXIMA){
             System.out.println("pasan " + CANTIDAD_MAXIMA + " carros del sentido derecho al izquierdo");
            cantidadCarrosDer-=CANTIDAD_MAXIMA;
        }
        else{
            if(cantidadCarrosDer != 0){
            System.out.println("pasan " + cantidadCarrosDer + " carros del sentido derecho al izquierdo");
            cantidadCarrosDer = 0;
            }
        }
        mutex.release();
    }
}
