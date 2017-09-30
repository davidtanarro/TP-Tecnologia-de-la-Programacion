package logica;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Representa una c�lula del mundo.
 */
public interface Celula {

	/**
	 * Realiza el movimiento de una c�lula.
	 * @param f Fila actual de la c�lula.
	 * @param c Columna actual de la c�lula.
	 * @param superficie Contiene la superficie actual.
	 * @return Devuelve la casilla a la que se va a mover la c�lula.
	 */
	public abstract Casilla ejecutaMovimiento(int f, int c, Superficie superficie);
	
	/**
	 * @return Devuelve si la c�lula es comestible.
	 */
	public abstract boolean esComestible();

	/**
	 * Metodo que carga el contenido de una c�lula.
	 * @param fichero fichero Fichero con el que estamos trabajando.
	 * @param s Scanner que le pasamos desde el controlador para poder seguir realizando la lectura.
	 */
	public abstract void leerCelula(File fichero, Scanner s);

	/**
	 * M�todo para guardar el contenido de la partida en un fichero.
	 * @param fichero Fichero con el que estamos trabajando.
	 * @throws IOException Lanzamos una excepci�n por si ocurre algo problema.
	 */
	public abstract void escribirSuperficie(FileWriter fichero) throws IOException;

	
	
}