package practica1;

/**
 * El mundo contiene a la superficie y por lo tanto tiene el atributo private Superficie superficie.
 */
public class Mundo {
	/**
	 * Atributo privado de la clase Superficie.
	 */
	private Superficie superficie;
	
	/**
	 * Constante para iniciar la superificie con seis células.
	 */
	public static final int NUM_CELULAS_INICIALES = 60;
	
	/**
	 * Constantes para las filas que tiene la superficie.
	 */
	public static final int MAX_FILAS = 30;
	
	/**
	 * Constantes para las filas que tiene la superficie.
	 */
	public static final int MAX_COLUMNAS = 40;
	
	/**
	 * Inicia la superficie con 6 células elegidas de manera aleatoria.
	 */
	public void inicializaSuperficie() {
		int nFila, nColumna, i = 0;
		int maxCelulas = MAX_FILAS * MAX_COLUMNAS;
		
		//Condición para no salirnos del tablero.
		while (i < NUM_CELULAS_INICIALES && i < maxCelulas) {
			nFila = (int) (Math.random() * this.superficie.getFilas());        //Escogemos dos números aleatorios.
			nColumna = (int) (Math.random() * this.superficie.getColumnas());  //
			// Si esta libre la posicion => inserto celula e incremento el contador
			// Si no está libre no incrementamos el contador y buscamos aleatoriamente otra posición.
			if (this.superficie.casillaVacia(nFila, nColumna)) {
				this.superficie.insertarCelula(nFila, nColumna);
				i++;
			}
		}
	}
	
	/**
	 * La constructora por defecto de esta clase inicializa la superficie colocando un número 
	 * de células (dado por una constante) en posiciones aleatorias de la superficie.
	 */
	public Mundo(){
		this.superficie = new Superficie(MAX_FILAS, MAX_COLUMNAS);
	}
	
	/**
	 * Inicializa a false una matriz de booleanos.
	 * @param matrizBooleana contiene una matriz de booleanos
	 */
	private void inicializaMatriz(boolean[][] matrizBooleana){
		for(int i = 0; i < superficie.getFilas(); i++)
			for(int j = 0; j < superficie.getColumnas(); j++)
				matrizBooleana[i][j] = false;
	}
	
	/**
	 * El metodo evoluciona recorre la superficie y dependiendo de las reglas
	 * de la vida, va pidiendola a esta que realice los pasos pertinentes.
	 */
	/* Teniendo en cuenta que este método es el que determina como se mueven las
	 * celulas, las coloca, comprueba si mueren o se reproducen, etc..
	 * Por otro lado cuando el mundo evoluciona, hay que controlar que una
	 * célula no se mueva dos veces en el mismo paso de evolución.
	 */
	public void evoluciona(){
		int maxPos = 8, cont, posLibreAleatoria, nf = -1, nc = -1;
		boolean[][] matrizBooleana=new boolean[MAX_FILAS][MAX_COLUMNAS];
		int[] incrFil = {-1,-1,-1, 0,0, 1,1,1};
		int[] incrCol = { -1,0,1, -1,1, -1,0,1};
		Posicion[] pos = new Posicion[maxPos];
		
		inicializaMatriz(matrizBooleana); // Cada vez que se llame a evoluciona se inicializará¡
		for (int i = 0; i < superficie.getFilas(); i++)
			for (int j = 0; j < superficie.getColumnas(); j++)
				if (!matrizBooleana[i][j]) {
					if (!superficie.casillaVacia(i, j)) {
						cont = 0;
						for (int k = 0; k < maxPos; k++) {
							nf = i + incrFil[k];
							nc = j + incrCol[k];
							if (correctas(nf, nc) && this.superficie.casillaVacia(nf, nc)) {  
								pos[cont] = new Posicion(nf, nc);
								cont++;
							}
						}
						if (cont != 0){
							posLibreAleatoria = (int) (Math.random() * cont);
							matrizBooleana[pos[posLibreAleatoria].getX()][pos[posLibreAleatoria].getY()] = true; // Se marca en la matriz la nueva posicion de la celula
							System.out.println("Movimiento de (" + i + "," + j + ") a (" + pos[posLibreAleatoria].getX() + "," + pos[posLibreAleatoria].getY() + ")");
							if (movimientoDeCelula(i, j, pos[posLibreAleatoria].getX(), pos[posLibreAleatoria].getY())) {
								matrizBooleana[i][j] = true; // Se marca en la matriz la posicion de una nueva celula
								System.out.println("Nace nueva celula en (" + i + "," + j + ") cuyo padre ha sido (" + pos[posLibreAleatoria].getX() + "," + pos[posLibreAleatoria].getY() + ")");
							}
						}
						else {
							if (celulaInactiva(i, j))
								System.out.println("Muere la celula de la casilla (" + i + "," + j + ") por inactividad");
							else if (celulaInmovilizada(i, j))
								System.out.println("Muere la celula de la casilla (" + i + "," + j + ") por no poder reproducirse");
							else
								modificarCelula(i, j);
						}
					}
				}
	}
	
	/**
	 * Realiza el movimiento de una celula
	 * @param fil Contiene la fila donde se encuentra la celula
	 * @param col Contiene la columna donde se encuentra la celula
	 * @param filAleatoria Contiene la fila donde se movera aleatoriamente la celula
	 * @param colAleatoria Contiene la columna donde se movera aleatoriamente la celula
	 * @return Devuelve true si la celula se reproduce al moverse
	 */
	private boolean movimientoDeCelula(int fil, int col, int filAleatoria, int colAleatoria){
		boolean creaCelula = false; //	Indicador para marcar la posicion en la matrizBooleana
		
		if (superficie.getPasosDados(fil, col) == 0) {
			superficie.moverCelula(fil, col, filAleatoria, colAleatoria);
			superficie.insertarCelula(fil, col); // Celula hija
			// Se resetean los pasos dados
			superficie.setPasosDados(filAleatoria, colAleatoria, superficie.getPasosReproduccion(filAleatoria, colAleatoria));
			creaCelula = true;
		}
		else {
			superficie.moverCelula(fil, col, filAleatoria, colAleatoria);
			// Decrementar el contador de pasosDados de la celula
			superficie.setPasosDados(filAleatoria, colAleatoria, superficie.getPasosDados(filAleatoria, colAleatoria)-1);
		}
		
		return creaCelula;
	}
	
	/**
	 * Comprueba si una posicion es correcta
	 * @param fil Numero de fila a comprobar
	 * @param col Numero de columna a comprobar
	 * @return Devuelve si una posicion esta dentro de la superficie
	 */
	private boolean correctas(int fil, int col) {
		boolean posicionCorrecta = false;
		int filas = superficie.getFilas(), columnas = superficie.getColumnas();
		
		if ((0 <= fil && fil < filas) && (0 <= col && col < columnas))
			posicionCorrecta = true;
		
		return posicionCorrecta;
	}
	
	/**
	 * Comprueba si una celula se encuentra inactiva
	 * @param fil Numero de fila de la celula
	 * @param col Numero de columna de la celula
	 * @return Devuelve si una celula debe morirse por inactividad
	 */
	private boolean celulaInactiva(int fil, int col) {
		boolean esInactiva = false;
		
		if (this.superficie.getPasosNoMovidos(fil,col) == 0) {
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
	private boolean celulaInmovilizada(int fil, int col) {
		boolean estaInmovil = false;
		
		if (superficie.getPasosDados(fil, col) == 0) {
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
		superficie.setPasosDados(fil, col, superficie.getPasosDados(fil, col)-1);
		superficie.setPasosNoMovidos(fil, col, superficie.getPasosNoMovidos(fil, col)-1);
	}
	
	/**
	 * Crea una nueva celula si esta vacia la casilla
	 * @param f Numero de fila
	 * @param c Numero de columna
	 * @return Devuelve si se ha podido crear una nueva celula dada una posicion
	 */
	public boolean crearCelula(int f, int c){
		boolean ok = true;
		
		if (superficie.casillaVacia(f, c))
			superficie.insertarCelula(f, c);
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
		
		if (!superficie.casillaVacia(f, c))
			superficie.eliminarCelula(f, c);
		else
			ok = false;
		
		return ok;
	}
	
	/**
	 * Vacia toda la superficie de celulas
	 */
	public void vaciarSuperficie(){
		superficie.vaciarSuperficie();
	}
	
	/**
	 * Inicia la superficie con las celulas correspondientes
	 */
	/*public void iniciarSuperficie(){
		this.inicializaSuperficie();
	}*/
	
	/**
	 * @return Devolvemos una cadena de carácteres para poder mostrar por pantalla la superficie
	 */
	public String toString(){
		return superficie.toString();
	}

	public int getMaxColumnas() {
		return this.superficie.getColumnas();
	}

	public int getMaxFilas() {
	return this.superficie.getFilas();
	}	
}
