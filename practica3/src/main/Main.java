package main;

import java.util.Scanner;

import control.Controlador;
// import logica.Mundo;

/**
 * 
 * @author JAVIER ORTIZ INIESTA Y DAVID TANARRO DE LAS HERAS.
 *
 */
public class Main {
	/**
	 * El atributo in se utiliza para leer de teclado.
	 */
	private static Scanner in;
	
	/**
	 * @param args Sirve para ejecutar comandos desde la linea de comandos de java.
	 */
	public static void main(String[] args) {
		// Crea un objeto de la clase Scanner a trav�s de su constructora y se lo asigna al atributo del Main
		in = new Scanner(System.in);
		// Crea un objeto de la clase Controlador a trav�s de su constructora
		Controlador control = new Controlador(in); 
		// Realiza la simulaci�n del juego llamando al m�todo realizaSimulacion() de la clase Controlador.
		control.realizaSimulacion();
		in.close();
	}
}