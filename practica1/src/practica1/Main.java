package practica1;

import java.util.Scanner;

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
		// Crea el objeto de la clase Mundo a trav�s de su constructora.
		Mundo mundo = new Mundo();
		/* Crea un objeto de la clase Controlador a trav�s de su constructora y realiza la
		 * simulaci�n del juego llamando al m�todo realizaSimulacion() de la clase Controlador.
		 */
		Main.in = new Scanner(System.in);
		
		Controlador control = new Controlador(mundo, in); 
		control.realizaSimulacion();
		in.close();
	}
}