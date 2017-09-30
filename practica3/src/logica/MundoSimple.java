package logica;

public class MundoSimple extends Mundo {
	/** Constante para iniciar la superificie con un numero de c�lulas simples. */
	private int numCelulasSimplesIniciales = 6;
	
	/**
	 * Constructor para el mundo simple.
	 * @param n Indica el n�mero de filas.
	 * @param m Indica el n�mero de columnas.
	 * @param s Indica el n�mero de c�lulas simples.
	 */
	public MundoSimple(int n, int m, int s){
		this.filas = n;
		this.columnas = m;
		this.setNumCelulasSimplesIniciales(s);
		this.superficie = new Superficie(n, m);
		inicializaSuperficie();
	}
	
	/**
	 * Constructor vacio de mundo complejo.
	 */
	public MundoSimple() {

	}

	/**
	 * Inicia la superficie con c�lulas elegidas de manera aleatoria.
	 */
	public void inicializaSuperficie() {
		int maxCelulas = getMaxFilas() * getMaxColumnas();
		
		if (maxCelulas >= getNumCelulasSimplesIniciales())
			insertadoDeCelulasIniciales(true, getNumCelulasSimplesIniciales());
		else
			System.out.println("ERROR: Hay m�s c�lulas iniciales que posiciones en el tablero.");
	}

	// Si boolean tipoCelula es true => Inserta Celulas Simples
	// Si boolean tipoCelula es false => Inserta Celulas Complejas
	/**
	 * Inserta un numero de celulas iniciales.
	 * @param tipoCelula Indica si la celula es simple o compleja
	 * @param numCelulasIniciales Indica el n�mero de celulas iniciales a insertar
	 */
	private void insertadoDeCelulasIniciales(boolean tipoCelula, int numCelulasIniciales) {
		int nFila, nColumna, i = 0;
		// Escogemos una fila y una columna aleatorios.
		// Si esta libre la posicion => inserto celula e incremento el contador
		// Si no est� libre no incrementamos el contador y buscamos aleatoriamente otra posici�n.
		
		while (i < numCelulasIniciales) {
			nFila = (int) (Math.random() * this.superficie.getFilas());
			nColumna = (int) (Math.random() * this.superficie.getColumnas());
			if (this.superficie.casillaVacia(nFila, nColumna)) {
				if (tipoCelula)
					this.superficie.insertarCelula(nFila, nColumna, new CelulaSimple());
				i++;
			}
		}
	}
	
	/**
	 * 
	 * @return Devuelve el n�mero de celulas simples.
	 */
	public int getNumCelulasSimplesIniciales() {
		return numCelulasSimplesIniciales;
	}
	
	/**
	 *  Modifica el n�mero de celulas simples.
	 * @param numCelulasSimplesIniciales Indica el n�mero de celulas simples.
	 */
	public void setNumCelulasSimplesIniciales(int numCelulasSimplesIniciales) {
		this.numCelulasSimplesIniciales = numCelulasSimplesIniciales;
	}
	
	
}
