package logica;

/**
 * Representa una c�lula del mundo.
 */
public abstract class Celula {

	/**
	 * Realiza el movimiento de una c�lula.
	 * @param f Fila actual de la c�lula.
	 * @param c Columna actual de la c�lula.
	 * @param superficie Contiene la superficie actual.
	 * @return Devuelve la casilla a la que se va a mover la c�lula.
	 */
	public abstract Casilla ejecutaMovimiento(int f, int c, Superficie superficie);
	
	/**
	 * @return Devuelve si la c�lula es comestible.
	 */
	public abstract boolean esComestible();
	
}