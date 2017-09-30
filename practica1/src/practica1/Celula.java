package practica1;

/**
 * Representa una célula del mundo. Contiene atributos privados para contabilizar el número de
 * pasos en los que la célula no se ha movido y el número de pasos dados (tanto si se ha movido 
 * como si no) realizados en el mundo
 */
public class Celula {
	
	/**
	 * numero de pasos en los que la celula no se ha movido.
	 */
	private int pasosNoMovidos;
	
	/**
	 * numero de pasos dados (tanto si se ha movido como si no).
	 */
	private int pasosDados;

	/**
	 * Constante privada en la clase (para que no pueda ser modificada) que indica los parámetros con los que se inicia la célula.
	 */
	private static final int MAX_PASOS_SIN_MOVER = 1;
	
	/**
	 * Constante privada en la clase (para que no pueda ser modificada) que indica los parámetros con los que se inicia la célula.
	 */
	private static final int PASOS_REPRODUCCION = 2;

	/**
	 * Constructor de la clase Celula.
	 */
	public Celula() {
		this.pasosNoMovidos = MAX_PASOS_SIN_MOVER;
		this.pasosDados = PASOS_REPRODUCCION;
	}
	
	/**
	 * @return Devuelve el número de pasos no movidos de la célula.
	 */
	public int getPasosNoMovidos() {
		return pasosNoMovidos;
	}

	/**
	 * 
	 * @param pasosNoMovidos Modifica el número de pasos no movidos de la célula.
	 */
	public void setPasosNoMovidos(int pasosNoMovidos) {
		this.pasosNoMovidos = pasosNoMovidos;
	}

	/**
	 * @return Devuelve el número de pasos dados que contiene la célula.
	 */
	public int getPasosDados() {
		return pasosDados;
	}

	/**
	 * @param pasosDados Modifica el número de pasos dados de la célula.
	 */
	public void setPasosDados(int pasosDados) {
		this.pasosDados = pasosDados;
	}

	/**
	 * @return Devuelve el número que contiene la constante MAX_PASOS_SIN_MOVER.
	 */
	public int getMaxPasosSinMover() {
		return MAX_PASOS_SIN_MOVER;
	}

	/**
	 * @return Devuelve el número que contiene la constante PASOS_REPRODUCCION.
	 */
	public int getPasosReproduccion() {
		return PASOS_REPRODUCCION;
	}

	/**
	 * @return Devuelve una cádena de caracteres que muestra por pantalla el contenido de una célula.
	 */
	public String toString() {
		String r = "";
		r = r + getPasosNoMovidos() + "-" + getPasosDados();
		return r;
	}
}