package comandos;

import control.Controlador;

public interface Comando {
	
	/**
	 * Ejecuta el comando correspondiente sobre el mundo
	 * @param mundo Mundo a ejecutar.
	 */
	public abstract void ejecuta(Controlador controlador);
	
	/**
	 * @param cadenaComando Recibe un array de String.
	 * @return Devuelve el comando que representa el String.
	 */
	public abstract Comando parsea(String[] cadenaComando);

	/**
	 * @return Devuelve la cadena de caracteres de la ayuda de cada comando
	 */
	public abstract String textoAyuda();
}
