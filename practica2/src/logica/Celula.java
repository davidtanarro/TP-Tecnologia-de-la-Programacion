package logica;

/**
 * Representa una célula del mundo.
 */
public abstract class Celula {

	/**
	 * Realiza el movimiento de una célula.
	 * @param f Fila actual de la célula.
	 * @param c Columna actual de la célula.
	 * @param superficie Contiene la superficie actual.
	 * @return Devuelve la casilla a la que se va a mover la célula.
	 */
	public abstract Casilla ejecutaMovimiento(int f, int c, Superficie superficie);
	
	/**
	 * @return Devuelve si la célula es comestible.
	 */
	public abstract boolean esComestible();
	
}