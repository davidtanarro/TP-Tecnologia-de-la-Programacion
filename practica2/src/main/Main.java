package main;

import java.util.Scanner;

import control.Controlador;
import logica.Mundo;

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
		// Crea el objeto de la clase Mundo a través de su constructora.
		Mundo mundo = new Mundo();
		// Crea un objeto de la clase Scanner a través de su constructora y se lo asigna al atributo del Main
		Main.in = new Scanner(System.in);
		// Crea un objeto de la clase Controlador a través de su constructora
		Controlador control = new Controlador(mundo, in); 
		// Realiza la simulación del juego llamando al método realizaSimulacion() de la clase Controlador.
		control.realizaSimulacion();
		in.close();
	}
}