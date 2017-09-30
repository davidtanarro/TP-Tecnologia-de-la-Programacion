package logica;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * El mundo contiene a la superficie y por lo tanto tiene el atributo private Superficie superficie.
 */
public abstract class Mundo {
	
	/** Constantes para las filas que tiene la superficie. */
	protected int filas;
	
	/** Constantes para las filas que tiene la superficie. */
	protected int columnas;
	
	/** Atributo privado de la clase Superficie. */
	protected Superficie superficie;

	/**
	 * Inicia la superficie con células elegidas de manera aleatoria.
	 */
	public abstract void inicializaSuperficie();
	
	/**
	 * Crea una nueva celula simple si esta vacia la casilla
	 * @param f Numero de fila
	 * @param c Numero de columna
	 * @return Devuelve si se ha podido crear una nueva celula dada una posicion
	 */
	public boolean crearCelula(int f, int c, Celula celula){
		boolean ok = true;
		
		if (this.superficie.casillaVacia(f, c))
			this.superficie.insertarCelula(f, c, celula);
		else
			ok = false;
		
		return ok;
	}
	
	
	/**
	 * Elimina una celula si no esta vacia la casilla
	 * @param f Numero de fila de la celula
	 * @param c Numero de columna de la celula
	 * @return Devuelve si se ha podido eliminar una celula dada una posicion
	 */
	public boolean eliminarCelula(int f, int c){
		boolean ok = true;
		
		if (!this.superficie.casillaVacia(f, c))
			this.superficie.eliminarCelula(f, c);
		else
			ok = false;
		
		return ok;
	}
	
	/**
	 * Vacia toda la superficie de celulas
	 */
	public void vaciarSuperficie(){
		this.superficie.vaciarSuperficie();
	}
	
	/**
	 * Inicializa a false una matriz de booleanos.
	 * @param matrizBooleana contiene una matriz de booleanos
	 */
	private void inicializaMatriz(boolean[][] matrizBooleana){
		for(int i = 0; i < this.superficie.getFilas(); i++)
			for(int j = 0; j < this.superficie.getColumnas(); j++)
				matrizBooleana[i][j] = false;
	}
	
	/**
	 * El metodo evoluciona recorre la superficie y dependiendo de las reglas
	 * de la vida, va pidiendola a esta que realice los pasos pertinentes.
	 */
	public void evoluciona(){
		boolean[][] matrizBooleana = new boolean[getMaxFilas()][getMaxColumnas()];
		Casilla pos;
		
		inicializaMatriz(matrizBooleana); // Cada vez que se llame a evoluciona se inicializará¡
		for (int i = 0; i < this.superficie.getFilas(); i++)
			for (int j = 0; j < this.superficie.getColumnas(); j++)
				if (!matrizBooleana[i][j]){
					if (!this.superficie.casillaVacia(i, j)) {
						pos = this.superficie.ejecutaMovimiento(i, j);
						if (pos != null)
							matrizBooleana[pos.getX()][pos.getY()] = true;
					}
				
				}
	}
	
	/**
	 * 
	 * @return Devuelve el número de columnas.
	 */
	public int getMaxColumnas() {
		return this.superficie.getColumnas();
	}

	/**
	 * 
	 * @return Devuelve el número de filas.
	 */
	public int getMaxFilas() {
		return this.superficie.getFilas();
	}

	/**
	 * @return Devolvemos una cadena de carácteres para poder mostrar por pantalla la superficie
	 */
	public String toString(){
		return this.superficie.toString();
	}

	/**
	 * Metodo que carga el contenido del mundo.
	 * @param fichero fichero Fichero con el que estamos trabajando.
	 * @param s Scanner que le pasamos desde el controlador para poder seguir realizando la lectura.
	 */
	public void leerMundo(File fichero, Scanner s) {
		setFilas(Integer.parseInt(s.nextLine()));		// Guardamos la linea en un Int
		setColumnas(Integer.parseInt(s.nextLine()));	// Guardamos la linea en un Int
		this.superficie = new Superficie(this.filas, this.columnas);
		superficie.leerSuperficie(fichero, s);
	}
	
	/**
	 * Método para modificar el número de filas.
	 * @param filas Indica el número de filas.
	 */
	public void setFilas(int filas) {
		this.filas = filas;
	}
	
	/**
	 * @return Devuelve el número de filas.
	 */
	public int getFilas() {
		return filas;
	}

	/**
	 * Método para modificar el número de columnas.
	 * @param columnas Indica el número de columnas.
	 */
	public void setColumnas(int columnas) {
		this.columnas = columnas;
	}
	
	/**
	 * @return Devuelve el número de columnas.
	 */
	public int getColumnas() {
		return columnas;
	}

	/**
	 * Método para guardar el contenido de la partida en un fichero.
	 * @param fichero Fichero con el que estamos trabajando.
	 * @throws IOException Lanzamos una excepción por si ocurre algo problema.
	 */
	public void escribirMundo(FileWriter fichero) throws IOException {
		fichero.write(getFilas() + "\r\n");
		fichero.write(getColumnas() + "");
		
		superficie.escribirSuperficie(fichero);
	}
}
