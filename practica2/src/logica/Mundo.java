package logica;

/**
 * El mundo contiene a la superficie y por lo tanto tiene el atributo private Superficie superficie.
 */
public class Mundo {
	/** Atributo privado que controla la finalizacion de la simulacion en Mundo*/
	private boolean simulacionTerminada;
	/** Atributo privado de la clase Superficie. */
	private Superficie superficie;

	/** Constante para iniciar la superificie con un numero de células simples. */
	private static final int NUM_CELULAS_SIMPLES_INICIALES = 19;
	/** Constante para iniciar la superificie con un numero de células complejas. */
	private static final int NUM_CELULAS_COMPLEJAS_INICIALES = 2;
	/** Constantes para las filas que tiene la superficie. */
	private static final int MAX_FILAS = 3;
	/** Constantes para las filas que tiene la superficie. */
	private static final int MAX_COLUMNAS = 7;
	
	/**
	 * La constructora por defecto de esta clase inicializa la superficie colocando un número 
	 * de células (dado por una constante) en posiciones aleatorias de la superficie.
	 */
	public Mundo(){
		this.superficie = new Superficie(MAX_FILAS, MAX_COLUMNAS);
		this.simulacionTerminada = false;
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
	 * Crea una nueva celula simple si esta vacia la casilla
	 * @param f Numero de fila
	 * @param c Numero de columna
	 * @return Devuelve si se ha podido crear una nueva celula dada una posicion
	 */
	public boolean crearCelula(int f, int c, Celula celula){
		boolean ok = true;
		
		if (this.superficie.casillaVacia(f, c))
			this.superficie.insertarCelula(f, c,celula);
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
	 * Inicia la superficie con células elegidas de manera aleatoria.
	 */
	public void inicializaSuperficie() {
		int maxCelulas = getMaxFilas() * getMaxColumnas();
		
		if (maxCelulas >= getNumCelulasSimplesIniciales() + getNumCelulasComplejasIniciales()) {
			insertadoDeCelulasIniciales(true, getNumCelulasSimplesIniciales());
			insertadoDeCelulasIniciales(false, getNumCelulasComplejasIniciales());
		}
		else
			System.out.println("ERROR: Hay más células iniciales que posiciones en el tablero.");
	}

	// Si boolean tipoCelula es true => Inserta Celulas Simples
	// Si boolean tipoCelula es false => Inserta Celulas Complejas
	/**
	 * Inserta un numero de celulas iniciales.
	 * @param tipoCelula Indica si la celula es simple o compleja
	 * @param numCelulasIniciales Indica el número de celulas iniciales a insertar
	 */
	private void insertadoDeCelulasIniciales(boolean tipoCelula, int numCelulasIniciales) {
		int nFila, nColumna, i = 0;
		// Escogemos una fila y una columna aleatorios.
		// Si esta libre la posicion => inserto celula e incremento el contador
		// Si no está libre no incrementamos el contador y buscamos aleatoriamente otra posición.
		
		while (i < numCelulasIniciales) {
			nFila = (int) (Math.random() * this.superficie.getFilas());
			nColumna = (int) (Math.random() * this.superficie.getColumnas());
			if (this.superficie.casillaVacia(nFila, nColumna)) {
				if (tipoCelula)
					this.superficie.insertarCelula(nFila, nColumna, new CelulaSimple());
				else
					this.superficie.insertarCelula(nFila, nColumna, new CelulaCompleja());
				i++;
			}
		}
	}
	
	/**
	 * @return Devolvemos una cadena de carácteres para poder mostrar por pantalla la superficie
	 */
	public String toString(){
		return this.superficie.toString();
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
	 * 
	 * @return Devuelve el número de celulas simples iniciales.
	 */
	private static int getNumCelulasSimplesIniciales() {
		return NUM_CELULAS_SIMPLES_INICIALES;
	}

	/**
	 * 
	 * @return Devuelve el número de celulas complejas iniciales.
	 */
	private static int getNumCelulasComplejasIniciales() {
		return NUM_CELULAS_COMPLEJAS_INICIALES;
	}
	
	/**
	 * 
	 * @return Devuelve si la simulacion ha terminado.
	 */
	public boolean isSimulacionTerminada() {
		return this.simulacionTerminada;
	}

	/**
	 * Modifica el parametro dependiendo si la simulacion ha terminado o no.
	 * @param simulacionTerminada Indica si la simulacion ha terminado.
	 */
	public void setSimulacionTerminada(boolean simulacionTerminada) {
		this.simulacionTerminada = simulacionTerminada;
	}

}
