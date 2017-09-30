package logica;

public class MundoComplejo extends Mundo{
	/** Constante para iniciar la superificie con un numero de células simples. */
	private int numCelulasSimplesIniciales = 19;
	/** Constante para iniciar la superificie con un numero de células complejas. */
	private int numCelulasComplejasIniciales = 2;
	
	/**
	 * Constructor para el mundo complejo.
	 * @param n Indica el número de filas.
	 * @param m Indica el número de columnas.
	 * @param s Indica el número de células simples.
	 * @param c Indica el número de células complejas.
	 */
	public MundoComplejo(int n, int m, int s, int c){
		this.filas = n;
		this.columnas = m;
		this.setNumCelulasSimplesIniciales(s);
		this.setNumCelulasComplejasIniciales(c);
		this.superficie = new Superficie(n, m);
		inicializaSuperficie();
	}

	/**
	 * Constructor vacio de mundo complejo.
	 */
	public MundoComplejo() {

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
	 * 
	 * @return Devuelve el número de celulas simples.
	 */
	public int getNumCelulasSimplesIniciales() {
		return numCelulasSimplesIniciales;
	}

	/**
	 *  Modifica el número de celulas simples.
	 * @param numCelulasSimplesIniciales Indica el número de celulas simples.
	 */
	public void setNumCelulasSimplesIniciales(int numCelulasSimplesIniciales) {
		this.numCelulasSimplesIniciales = numCelulasSimplesIniciales;
	}

	/**
	 * 
	 * @return Devuelve el número de celulas complejas.
	 */
	public int getNumCelulasComplejasIniciales() {
		return numCelulasComplejasIniciales;
	}

	/**
	 *  Modifica el número de celulas complejas.
	 * @param numCelulasComplejasIniciales Indica el número de celulas complejas.
	 */
	public void setNumCelulasComplejasIniciales(int numCelulasComplejasIniciales) {
		this.numCelulasComplejasIniciales = numCelulasComplejasIniciales;
	}
	
	
}
