package control;

import java.util.Scanner;

import comandos.Comando;
import comandos.ParserComando;
import logica.Mundo;

/**
 * Esta clase contiene el intérprete de los posibles comandos que se pueden
 * ejecutar en consola.
 */
public class Controlador {
	/** El atributo in se utiliza para leer de teclado. */
	private Scanner in;
	/** Creamos un objeto privado de la clase Mundo. */
	private Mundo mundo;

	/**
	 * La constructora del Controlador inicializa los atributos.
	 * @param mundo Representa el mundo sobre el que ejecutar los comandos,.
	 * @param in Realiza las operaciones de lectura.
	 */
	public Controlador(Mundo mundo, Scanner in) {
		this.in = in;
		this.mundo = mundo;
	}

	/**
	 * Consiste en un bucle en el que se pide un comando al usuario y se ejecuta
	 * dicho comando. Además si el comando es incorrecto se muestra un mensaje
	 * de error. El bucle termina cuando el usuario teclea el comando SALIR.
	 */
	public void realizaSimulacion() {
		// Iniciamos la superficie con las células iniciales.
		this.mundo.inicializaSuperficie();
		while (!esSimulacionTerminada(this.mundo)) {
			System.out.println(this.mundo.toString());
			System.out.print("Comando > ");
			String entrada = in.nextLine(); // Lee la linea entera de teclado
			String[] palabras = entrada.split(" ");
			try {
				// parseaComando devuelve un comando
				Comando comando = ParserComando.parseaComando(palabras);

				if (comando != null)
					comando.ejecuta(this.mundo);
				else
					System.out.println("Instruccion incorrecta");
			}
			catch (NumberFormatException e) {
				System.out.println("Se ha introducido una cadena de carácteres en vez de dos enteros.");
			}
		}
	}

	/**
	 * Consulta en mundo si se ha terminado la simulacion
	 * @param mundo Mundo actual
	 * @return Devuelve true si se acaba la partida, false en caso contrario
	 */
	public boolean esSimulacionTerminada(Mundo mundo) {
		return mundo.isSimulacionTerminada();
	}
}
