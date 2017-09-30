package logica;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


/**
 * Es la superficie donde transcurre la evoluci�n de las c�lulas. La superficie la vamos a representar 
 * mediante una matriz de c�lulas, por lo tanto esta clase contendr� un atributo 
 * private Celula[][] superficie, junto con los atributos private int filas y private int columnas 
 * que determinan el tama�o de la superficie.
 */
public class Superficie {
	/** Matriz bidimensional de la clase Celula. */
	private Celula[][] superficie;
	/** Atributo privado que contiene las filas. */
	private int filas;
	/** Atributo privado que contiene las columnas. */
	private int columnas;

	/**
	 * Constructor de la clase Superficie.
	 * @param nf Pasamos como par�metro en n�mero de filas.
	 * @param nc Pasamos como par�metro en n�mero de columnas.
	 */
	public Superficie(int nf, int nc){
		this.filas = nf;
		this.columnas = nc;
		this.superficie = new Celula[filas][columnas];
		vaciarSuperficie();
	}
	
	/**
	 * Ponemos todas las posiciones de la superficies a null.
	 */
	public void vaciarSuperficie(){
		for(int i = 0; i < this.filas; i++)
			for(int j = 0; j < this.columnas; j++)
				this.superficie[i][j] = null;
	}
	
	/**
	 * @return Devolvemos una cadena de car�cteres que muestra por pantalla la superficie.
	 */
	public String toString() {
		String r = "";
		
		for (int i = 0; i < getFilas(); i++) {
			r = r + "| ";
			for (int j = 0; j < getColumnas(); j++) {
				if (casillaVacia(i, j))
					r = r + "  -  " ;
				else
					r = r + this.superficie[i][j].toString();
				r = r + " | "; // Separacion entre columnas
			}
			r = r + System.getProperty("line.separator"); // Salto de linea
		}
		
		return r;
	}
	
	/**
	 * @param fil Contiene la fila actual.
	 * @param col Contiene la columna actual.
	 * @param filAleatoria Contiene la nueva fila a la que se va a mover la c�lula.
	 * @param colAleatoria Contiene la nueva columna a la que se va a mover la c�lula.
	 */
	public void moverCelula(int fil, int col, int filAleatoria, int colAleatoria) {
		this.superficie[filAleatoria][colAleatoria] = this.superficie[fil][col];  //Copiamos la c�lula a su nueva posici�n.
		eliminarCelula(fil, col); //Eliminamos la antigua c�lula.
	}

	/**
	 * @param fil Fila actual.
	 * @param col Columna actual.
	 * @return Devuelve true si no hay una celula en una posicion determinada
	 */
	public boolean casillaVacia(int fil, int col) {
		return (this.superficie[fil][col] == null);
	}
	
	/**
	 * Crea una nueva c�lula simple en la posici�n que introducimos por par�metro.
	 * @param filas Fila actual.
	 * @param columnas Columna actual.
	 * @param celula Tipo de celula (simple o compleja) a insertar.
	 */
	public void insertarCelula(int filas, int columnas, Celula celula) {
		this.superficie[filas][columnas] = celula;
	}

	/**
	 * Elimina la c�lula de la posici�n que introducimos.
	 * @param fil Fila actual.
	 * @param col Columna actual.
	 */
	public void eliminarCelula(int fil, int col){
		this.superficie[fil][col] = null;
	}
	
	/**
	 * @return Devuelve el n�mero de filas.
	 */
	public int getFilas() {
		return this.filas;
	}
	
	/**
	 * @return Devuelve el n�mero de columnas.
	 */
	public int getColumnas() {
		return this.columnas;
	}
	/**
	 * 
	 * @param fila Indica la fila actual.
	 * @param columna Indica la columna actual.
	 * @return Devuelve la casilla a la que se va a mover la celula.
	 */
	public Casilla ejecutaMovimiento(int fila, int columna) {
		Casilla pos = this.superficie[fila][columna].ejecutaMovimiento(fila, columna, this);

		return pos;
	}
	
	/**
	 * 
	 * @param fila Indica la fila actual.
	 * @param columna Indica la columna actual.
	 * @return Devuelve true si es comestible, y false si no lo es.
	 */
	public boolean isEsComestible(int fila, int columna) {
		return this.superficie[fila][columna].esComestible();
	}

	/**
	 * Metodo que carga el contenido de la superficie.
	 * @param fichero fichero Fichero con el que estamos trabajando.
	 * @param s Scanner que le pasamos desde el controlador para poder seguir realizando la lectura.
	 */
	public void leerSuperficie(File fichero, Scanner s) {
		while (s.hasNextLine()) {
			int f = s.nextInt(); 		// Guardamos la linea en un Int
			int c = s.nextInt(); 		// Guardamos la linea en un Int
			
			String tipoCel = s.next();
			if (tipoCel.equalsIgnoreCase("simple"))
				insertarCelula(f, c, new CelulaSimple());
			else if (tipoCel.equalsIgnoreCase("compleja"))
				insertarCelula(f, c, new CelulaCompleja());
			
			superficie[f][c].leerCelula(fichero, s);
		}
	}

	/**
	 * M�todo para guardar el contenido de la partida en un fichero.
	 * @param fichero Fichero con el que estamos trabajando.
	 * @throws IOException Lanzamos una excepci�n por si ocurre algo problema.
	 */
	public void escribirSuperficie(FileWriter fichero) throws IOException {
		for (int i = 0; i < getFilas(); i++)
			for(int j = 0; j < getColumnas(); j++){
				if (!casillaVacia(i, j)) {
					fichero.write("\r\n" + i + " " + j + " ");
					//fichero.write(j + " ");
					if (isEsComestible(i, j))
						fichero.write("simple ");
					else
						fichero.write("compleja ");
					superficie[i][j].escribirSuperficie(fichero);
				}
			}
	}

}
