package logica;

public class CelulaSimple extends Celula {
	/**numero de pasos en los que la celula no se ha movido.*/
	private int pasosNoMovidos;
	/**numero de pasos dados (tanto si se ha movido como si no).*/
	private int pasosDados;

	/** Constante privada en la clase (para que no pueda ser modificada) que indica los parámetros con los que se inicia la célula. */
	private static final int MAX_PASOS_SIN_MOVER = 1;
	/** Constante privada en la clase (para que no pueda ser modificada) que indica los parámetros con los que se inicia la célula. */
	private static final int PASOS_REPRODUCCION = 2;
	
	/**
	 *  Constructor celula simple
	 */
	public CelulaSimple() {
		this.pasosNoMovidos = MAX_PASOS_SIN_MOVER;
		this.pasosDados = PASOS_REPRODUCCION;
	}
	@Override
	public Casilla ejecutaMovimiento(int f, int c, Superficie superficie) {
		int maxPos = 8, cont = 0, posLibreAleatoria, nf = -1, nc = -1;
		int[] incrFil = {-1,-1,-1, 0,0, 1,1,1};
		int[] incrCol = { -1,0,1, -1,1, -1,0,1};
		Casilla[] pos = new Casilla[maxPos]; // Array de posiciones libres (maximas posiciones libres = 8)
		Casilla nuevaPosicion = null; // Casilla a devolver
		
		// Calculo de un array de posiciones libres
		for (int k = 0; k < maxPos; k++) {
			nf = f + incrFil[k];
			nc = c + incrCol[k];
			if (correctas(nf, nc, superficie) && superficie.casillaVacia(nf, nc)) {  
				pos[cont] = new Casilla(nf, nc);
				cont++;
			}
		}		
		
		if (cont != 0) { // Si se puede mover
			posLibreAleatoria = (int) (Math.random() * cont);
			nuevaPosicion = new Casilla(pos[posLibreAleatoria].getX(), pos[posLibreAleatoria].getY());
			System.out.println("Movimiento de (" + f + "," + c + ") a (" + nuevaPosicion.getX() + "," + nuevaPosicion.getY() + ")");
			if (movimientoDeCelulaSimple(f, c, nuevaPosicion.getX(), nuevaPosicion.getY(), superficie))
				System.out.println("Nace nueva celula en (" + f + "," + c + ") cuyo padre ha sido (" + nuevaPosicion.getX() + "," + nuevaPosicion.getY() + ")");
		
		}
		else { // Si no se puede mover
			if (celulaInactiva(f, c, superficie))
				System.out.println("Muere la celula de la casilla (" + f + "," + c + ") por inactividad");
			else if (celulaInmovilizada(f, c, superficie))
				System.out.println("Muere la celula de la casilla (" + f + "," + c + ") por no poder reproducirse");
			else
				modificarCelula(f, c);
		}
			
		
		return nuevaPosicion;
	}
	
	/**
	 * Realiza el movimiento de una celula
	 * @param fil Contiene la fila donde se encuentra la celula
	 * @param col Contiene la columna donde se encuentra la celula
	 * @param filAleatoria Contiene la fila donde se movera aleatoriamente la celula
	 * @param colAleatoria Contiene la columna donde se movera aleatoriamente la celula
	 * @return Devuelve true si la celula se reproduce al moverse
	 */
	private boolean movimientoDeCelulaSimple(int fil, int col, int filAleatoria, int colAleatoria, Superficie superficie){
		boolean creaCelulaHija = false; //	Indicador para marcar la posicion en la matrizBooleana

		if (getPasosDados() == 0) {
			superficie.moverCelula(fil, col, filAleatoria, colAleatoria);
			superficie.insertarCelula(fil, col, new CelulaSimple()); // Celula hija
			// Se resetean los pasos dados
			setPasosDados(getPasosReproduccion());
			creaCelulaHija = true;
		}
		else {
			superficie.moverCelula(fil, col, filAleatoria, colAleatoria);
			// Decrementar el contador de pasosDados de la celula
			setPasosDados(getPasosDados()-1);
		}
		
		return creaCelulaHija;
	}
	
	/**
	 * Comprueba si una casilla es correcta
	 * @param fil Numero de fila a comprobar
	 * @param col Numero de columna a comprobar
	 * @return Devuelve si una casilla esta dentro de la superficie
	 */
	private boolean correctas(int f, int c, Superficie superficie) {
		boolean posicionCorrecta = false;
		int filas = superficie.getFilas(), columnas = superficie.getColumnas();
		
		if ((0 <= f && f < filas) && (0 <= c && c < columnas))
			posicionCorrecta = true;
		
		return posicionCorrecta;
	}
	@Override
	public boolean esComestible() {
		// Devuelve true al ser una celula simple
		return true;
	}
		
	
	/**
	 * Comprueba si una celula se encuentra inactiva
	 * @param fil Numero de fila de la celula
	 * @param col Numero de columna de la celula
	 * @return Devuelve si una celula debe morirse por inactividad
	 */
	private boolean celulaInactiva(int fil, int col, Superficie superficie) {
		boolean esInactiva = false;
		
		if (getPasosNoMovidos() == 0) {
			esInactiva = true;
			superficie.eliminarCelula(fil, col);
		}
		
		return esInactiva;
	}
	
	/**
	 * Comprueba si una celula se encuentra inmovilizada
	 * @param fil Numero de fila de la celula
	 * @param col Numero de columna de la celula
	 * @return Devuelve si una celula debe morirse por estar inmovilizada
	 */
	private boolean celulaInmovilizada(int fil, int col, Superficie superficie) {
		boolean estaInmovil = false;
		
		if (getPasosDados() == 0) {
			estaInmovil = true;
			superficie.eliminarCelula(fil, col);
		}
		
		return estaInmovil;
	}
	
	/**
	 * Modifica una celula decrementando sus valores correspondientes
	 * @param fil Numero de fila de la celula
	 * @param col Numero de columna de la celula
	 */
	private void modificarCelula(int fil, int col) {
		setPasosDados(getPasosDados()-1);
		setPasosNoMovidos(getPasosNoMovidos()-1);
	}

	/**
	 * @return Devuelve el número de pasos no movidos de la célula.
	 */
	private int getPasosNoMovidos() {
		return this.pasosNoMovidos;
	}
	/**
	 * @param pasosNoMovidos Modifica el número de pasos no movidos de la célula.
	 */
	private void setPasosNoMovidos(int pasosNoMovidos) {
		this.pasosNoMovidos = pasosNoMovidos;
	}

	/**
	 * @return Devuelve el número de pasos dados que contiene la célula.
	 */
	private int getPasosDados() {
		return this.pasosDados;
	}
	/**
	 * @param pasosDados Modifica el número de pasos dados de la célula.
	 */
	private void setPasosDados(int pasosDados) {
		this.pasosDados = pasosDados;
	}

	/**
	 * @return Devuelve el número que contiene la constante PASOS_REPRODUCCION.
	 */
	public int getPasosReproduccion() {
		return PASOS_REPRODUCCION;
	}

	 // Representacion de Celula Simple:	|1-2-X| <-
	 // Representacion de Celula Compleja:	| 2-* |
	/**
	 * @return Devuelve una cádena de caracteres que muestra por pantalla el contenido de una célula.
	 */
	public String toString() {
		String r = "";
		r = r + getPasosNoMovidos() + "-" + getPasosDados() + "-X";
		return r;
	}
}
