package logica;

public class CelulaCompleja extends Celula {
	private int comidas;
	
	/** Constante privada en la clase (para que no pueda ser modificada) que indica los parámetros con los que se inicia la célula. */
	private static final int MAX_COMER = 3;
	
	/**
	 *  Constructor celula compleja
	 */
	public CelulaCompleja() {
		this.comidas = MAX_COMER;
	}
	@Override
	public Casilla ejecutaMovimiento(int f, int c, Superficie superficie) {
		Casilla nuevaPos = null; // Casilla a devolver
		int fAlea, cAlea;
		
		do {
			fAlea = (int) (Math.random() * superficie.getFilas());
			cAlea = (int) (Math.random() * superficie.getColumnas());	
		} while (fAlea == f && cAlea == c);
		
		if (isPosValida(fAlea, cAlea, superficie)) {
			nuevaPos = new Casilla(fAlea, cAlea);
			System.out.print("Celula Compleja en (" + f + "," + c + ") se mueve a (" + nuevaPos.getX() + "," + nuevaPos.getY() + ")");
		
			if (!superficie.casillaVacia(nuevaPos.getX(), nuevaPos.getY())) {
				System.out.println(" --COME-");
				// Eliminar celula simple
				superficie.eliminarCelula(nuevaPos.getX(), nuevaPos.getY());
				// Decrementar el contador de comidas de la celula
				setComidas(getComidas()-1);
			}
			else
				System.out.println(" --NO COME-");
			
			if (movimientoDeCelulaCompleja(f, c, nuevaPos.getX(), nuevaPos.getY(), superficie))
				System.out.println("Explota la celula compleja en (" + nuevaPos.getX() + "," + nuevaPos.getY() + ")");
		}
		else
			System.out.println("La celula compleja (" + f + "," +  c + ") no se mueve porque es amiga de la celula compleja (" + fAlea + "," +  cAlea + ")");
		
		return nuevaPos;
	}
	

	/**
	 * Realiza el movimiento de una celula
	 * @param fil Contiene la fila donde se encuentra la celula
	 * @param col Contiene la columna donde se encuentra la celula
	 * @param filAleatoria Contiene la fila donde se movera aleatoriamente la celula
	 * @param colAleatoria Contiene la columna donde se movera aleatoriamente la celula
	 * @return Devuelve true si la celula se reproduce al moverse
	 */
	private boolean movimientoDeCelulaCompleja(int fil, int col, int filAleatoria, int colAleatoria, Superficie superficie){
		boolean explota = false;
		
		superficie.moverCelula(fil, col, filAleatoria, colAleatoria);
		if (getComidas() == 0) {
			explota = true;
			superficie.eliminarCelula(filAleatoria, colAleatoria);
		}
		
		return explota;
	}
	
	/**
	 * Comprueba si una casilla es correcta
	 * @param fil Numero de fila a comprobar
	 * @param col Numero de columna a comprobar
	 * @return Devuelve si una casilla esta dentro de la superficie
	 */
	private boolean isPosValida(int f, int c, Superficie superficie) {
		boolean posValida = false;
		
		if ((superficie.casillaVacia(f, c) || superficie.isEsComestible(f, c)))
			posValida = true;
		
		return posValida;
	}

	@Override
	public boolean esComestible() {
		// Devuelve false al ser una celula compleja
		return false;
	}
	/**
	 * Consulta el número de células que puede comer.
	 * @return Devuelve el atributo con el número de celulas que puede comer.
	 */
	private int getComidas() {
		return comidas;
	}
	/**
	 * Modifica el número de células que puede comer.
	 * @param comidas Indica el número de celulas que puede comer.
	 */
	private void setComidas(int comidas) {
		this.comidas = comidas;
	}
	
	// Representacion de Celula Simple:	|1-2-X|
	// Representacion de Celula Compleja:	| 2-* | <-
	/**
	 * @return Devuelve la cadena de cadena con la representacion de una celula.
	 */
	public String toString() {
		String r = "";
		r = r + " " + getComidas() + "-* ";
		return r;
	}
}
