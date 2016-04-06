/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mundo;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author distribuidos
 */
public class Sentido extends Thread {

	private Puente puente;
	private int sentido;

	public Sentido(Puente puente) {
		this.puente = puente;
		this.sentido = 1;
	}

	public void run() {
		while (true) {

			int tiempoEspera = (int) (Math.random() * 10);
			
				
				
				if (sentido == Automovil.IZQ) {
					System.out.println("cambiando a sentido Derecho en " + tiempoEspera + " segundos");
					try {
						sleep(tiempoEspera * 1000);
						System.out.println("cambiando sentido derecho");
						puente.cambiarSentido(sentido);
						sentido = Automovil.DER;
					} catch (Exception e) {
						
					}
				}

				else {
					System.out.println("cambiando a sentido izquierdo en "  + tiempoEspera + " segundos");
					try {
						sleep(tiempoEspera * 1000);
						System.out.println("cambiando sentido izquierdo");
						puente.cambiarSentido(sentido);
						sentido = Automovil.IZQ;
					} catch (Exception e ) {
						
					}
				}
			}

		}
	}

