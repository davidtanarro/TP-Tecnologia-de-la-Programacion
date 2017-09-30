package practica1;

// import java.util.Math;

/**
 * Es la superficie donde transcurre la evolución de las células. La superficie la vamos a representar 
 * mediante una matriz de células, por lo tanto esta clase contendrá un atributo 
 * private Celula[][] superficie, junto con los atributos private int filas y private int columnas 
 * que determinan el tamaño de la superficie.
 */
public class Superficie {
	/**
	 * Matriz bidimensional de la clase Celula.
	 */
	private Celula[][] superficie;
	
	/**
	 * Atributo privado que contiene las filas.
	 */
	private int filas;

	/**
	 * Atributo privado que contiene las columnas.
	 */
	private int columnas;

	/**
	 * Constructor de la clase Superficie.
	 * @param nf Pasamos como parámetro en número de filas.
	 * @param nc Pasamos como parámetro en número de columnas.
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
				superficie[i][j] = null;
	}
	
	
	
	/**
	 * @return Devolvemos una cadena de carácteres que muestra por pantalla la superficie.
	 */
	public String toString() {
		String r = "";
		
		for (int i = 0; i < getFilas(); i++) {
			for (int j = 0; j < getColumnas(); j++) {
				if (casillaVacia(i, j))
					r = r + " - " ;
				else
					r = r + superficie[i][j].toString();
				r = r + "  "; // Separacion entre columnas
			}
			r = r + System.getProperty("line.separator"); // Salto de linea
		}
		
		return r;
	}
	
	/**
	 * @param fil Contiene la fila actual.
	 * @param col Contiene la columna actual.
	 * @param filAleatoria Contiene la nueva fila a la que se va a mover la célula.
	 * @param colAleatoria Contiene la nueva columna a la que se va a mover la célula.
	 */
	public void moverCelula(int fil, int col, int filAleatoria, int colAleatoria) {
		superficie[filAleatoria][colAleatoria] = superficie[fil][col];  //Copiamos la célula a su nueva posición.
		eliminarCelula(fil, col); //Eliminamos la antigua célula.
	}

	/**
	 * @param fil Fila actual.
	 * @param col Columna actual.
	 * @return Devuelve true si no hay una celula en una posicion determinada
	 */
	public boolean casillaVacia(int fil, int col) {
		return (superficie[fil][col] == null);
	}
	
	/**
	 * Crea una nueva célula en la posición que introducimos por parámetro.
	 * @param filas Fila actual.
	 * @param columnas Columna actual.
	 */
	public void insertarCelula(int filas, int columnas) {
		superficie[filas][columnas] = new Celula();
	}
	
	/**
	 * Elimina la célula de la posición que introducimos.
	 * @param fil Fila actual.
	 * @param col Columna actual.
	 */
	public void eliminarCelula(int fil, int col){
		superficie[fil][col] = null;
	}
	
	/**
	 * @return Devuelve el número de filas.
	 */
	public int getFilas() {
		return filas;
	}
	
	/**
	 * @param filas Modifica el número de filas.
	 */
	public void setFilas(int filas) {
		this.filas = filas;
	}

	/**
	 * @return Devuelve el número de columnas.
	 */
	public int getColumnas() {
		return columnas;
	}
	
	/**
	 * @param columnas Modifica el número de columnas.
	 */
	public void setColumnas(int columnas) {
		this.columnas = columnas;
	}

	/**
	 * @param f Contiene la fila actual.
	 * @param c Contiene la columna actual.
	 * @return Devuelve los pasos no movidos.
	 */
	public int getPasosNoMovidos(int f, int c) {
		return this.superficie[f][c].getPasosNoMovidos();
	}
	
	/**
	 * Modifica el número de pasos no movidos.
	 * @param f Contiene la fila actual.
	 * @param c Contiene la columna actual.
	 * @param nuevosPasosNoMovidos Contiene el nuevo número de pasos no movidos (ya que restamos 1).
	 */
	public void setPasosNoMovidos(int f, int c, int nuevosPasosNoMovidos) {
		superficie[f][c].setPasosNoMovidos(nuevosPasosNoMovidos);
	}

	/**
	 * @param f Contiene la fila actual.
	 * @param c Contiene la columna actual.
	 * @return Devuelve el número de pasos dados.
	 */
	public int getPasosDados(int f, int c) {
		return this.superficie[f][c].getPasosDados();
	}
	
	/**
	 * Modifica el número de pasos dados.
	 * @param f Contiene la fila actual.
	 * @param c Contiene la columna actual.
	 * @param nuevosPasosDados Contiene el nuevo número de pasos dados (ya que restamos 1).
	 */
	public void setPasosDados(int f, int c, int nuevosPasosDados) {
		superficie[f][c].setPasosDados(nuevosPasosDados);
	}
	
	/**
	 * @param f Contiene la fila actual.
	 * @param c Contiene la columna actual.
	 * @return Devuelve el número de pasos sin mover (constante).
	 */
	public int getMaxPasosSinMover(int f, int c) {
		return superficie[f][c].getMaxPasosSinMover();
	}
	
	/**
	 * @param f Contiene la fila actual.
	 * @param c Contiene la columna actual.
	 * @return Devuelve el número de pasos de reproducción (constante).
	 */
	public int getPasosReproduccion(int f, int c) {
		return superficie[f][c].getPasosReproduccion();
	}
}
