package practica1;

import java.util.Scanner;

/**
 * Esta clase contiene el int�rprete de los posibles comandos que se pueden ejecutar en consola.
 */
public class Controlador {
	
	/**
	 * El atributo in se utiliza para leer de teclado.
	 */
	private Scanner in;
	/**
	 * Creamos un objeto privado de la clase Mundo.
	 */
	private Mundo mundo;
	
	/**
	 * La constructora del Controlador inicializa los atributos.
	 * @param mundo Representa el mundo sobre el que ejecutar los comandos,.
	 * @param in Realiza las operaciones de lectura.
	 */
	public Controlador(Mundo mundo, Scanner in){
		this.in = in;
		this.mundo = mundo;
	}
	
	/**
	 * Consiste en un bucle en el que se pide un comando al usuario y se ejecuta dicho comando.
     * Adem�s si el comando es incorrecto se muestra un mensaje de error. El bucle termina cuando el usuario teclea el comando SALIR.
	 */
	public void realizaSimulacion() {
		boolean salir = false;	// Booleano para saber si se termina la simulaci�n.
		String comando = null;	//String para la c�dena que se introduce por teclado.
		int fila, columna;		// N�meros que se introducen por teclado.

 		mundo.inicializaSuperficie();  //Iniciamos la superficie con 6 c�lulas iniciales.
	 	while(!salir){
	 		System.out.println(mundo.toString());  //Comenzamos mostrando el tablero.
	 		System.out.print("Comando > ");
	 		comando = in.nextLine();
	 		//Creamos un array de String para hacer una selecci�n de lo que hemos introducido por teclado.
	 		String[] palabras = comando.split(" "); 
	 		
	 		if (palabras[0].equalsIgnoreCase("PASO")){
	 			mundo.evoluciona();
	 		}
	 		else if (palabras[0].equalsIgnoreCase("INICIAR")){
	 			mundo.vaciarSuperficie();
	 			mundo.inicializaSuperficie();
	 		}
	 		else if (palabras[0].equalsIgnoreCase("CREARCELULA")){
	 			fila = new Integer(palabras[1]);
	 			columna = new Integer(palabras[2]);
	 			if((fila < 0 || fila >= this.mundo.getMaxFilas()) ||  (columna < 0 || columna >= this.mundo.getMaxColumnas()))
	 				System.out.println("Error: Esa posici�n no se encuentra dentro de los limites de la superficie.");
	 			else if (mundo.crearCelula(fila, columna))
	 				System.out.println("Creamos nueva celula en la posici�n: (" + fila + "," + columna + ")");
	 			else
	 				System.out.println("Error: ya est� ocupada esa posici�n");
	 		}
	 		else if (palabras[0].equalsIgnoreCase("ELIMINARCELULA")){
	 			fila = new Integer(palabras[1]);
	 			columna = new Integer(palabras[2]);
	 			if((fila < 0 || fila >= this.mundo.getMaxFilas()) ||  (columna < 0 || columna >= this.mundo.getMaxColumnas()))
	 				System.out.println("Error: Esa posici�n no se encuentra dentro de los limites de la superficie.");
	 			else if (mundo.eliminarCelula(fila, columna))
	 				System.out.println("Eliminada la celula en la posici�n: (" + fila + "," + columna + ")");
	 			else
	 				System.out.println("Error: ya est� libre esa posici�n");
	 		}
	 		else if (palabras[0].equalsIgnoreCase("AYUDA")){
	 			mostrarAyuda();
	 		}
	 		else if (palabras[0].equalsIgnoreCase("VACIAR")){
	 			System.out.println("Vaciando la superficie....");
	 			mundo.vaciarSuperficie();
	 		}
	 		else if (palabras[0].equalsIgnoreCase("SALIR")){
	 			salir = true;
	 			System.out.println("Fin de la simulacion.....");
	 		}
	 		else
	 			System.out.println("Instruccion incorrecta");
	 	}
	 }

	/**
	 * M�todo privado que muestra por pantalla los posibles comandos.
	 */
	private void mostrarAyuda() {
		System.out.println(
			"POSIBLES COMANDOS:\n"
			+ "    PASO: realiza un paso en la simulacion\n"
			+ "    AYUDA: muestra esta ayuda\n"
			+ "    SALIR: cierra la aplicaci�n\n"
			+ "    INICIAR: inicia una nueva simulaci�n\n"
			+ "    VACIAR: crea un mundo vac�o\n"
			+ "    CREARCELULA F C: crea una nueva celula en la posici�n (f,c) si es posible\n"
			+ "    ELIMINARCELULA F C: elimina una celula de la posici�n (f,c) si es posible\n");
	}
}
