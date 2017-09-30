package logica;

/**
 * La clase Posición guarda todas las posiciones libres a donde se puede mover la celula.
 * Como mucho se guardan ocho posiciones.
 */
public class Casilla {
	/**
	 * Coordenada que indica la fila en la Casilla.
	 */
	private int x;
	
	/**
	 * Coordenada que indica la columna en la Casilla.
	 */
	private int y;

	/**
	 * Constructor de la clase Posicion.
	 * @param x Marca la fila de la posición que va a guardar.
	 * @param y Marca la columna de la posición que va a guardar.
	 */
	public Casilla(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @return Devuelve la fila de esa posición.
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * @param x Modifica la fila de la posición.
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return Devuelve la columna de esa posición.
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y Modifica la columna de la posición.
	 */
	public void setY(int y) {
		this.y = y;
	}
}
