package comandos;

public class ParserComando {
	/**	Definicion del array de Comandos */
	private static Comando[] comandos = {
		new ComandoPaso(),
		new ComandoAyuda(),
		new ComandoSalir(),
		new ComandoIniciar(),
		new ComandoVaciar(),
		new ComandoEliminarCelula(),
		new ComandoCrearCelulaSimple(),
		new ComandoCrearCelulaCompleja()
		};
	
	/**
	 * Metodo de Ayuda de todos los comandos
	 * @return Devuelve la cadena de ayuda de todos los comandos
	 */
	public static String AyudaComandos() {
		// Un objeto StringBuffer representa una cadena cuyo tamaño puede variar. 
		StringBuffer sb = new StringBuffer();
		
		// Añade/encadena cada string que devuelve el metodo textoAyuda() de cada comando
		for (Comando c: comandos)
			sb.append(c.textoAyuda());
		
		return sb.toString();
	}
	
	/**
	 * Metodo que comprueba el comando introducido
	 * @param palabras Contiene el string introducido por teclado
	 * @return Devuelve el comando introducido o null si no es valido.
	 */
	public static Comando parseaComando(String[] palabras){
 		Comando select = null;
 		
		for (Comando c: comandos) {
			Comando coman = c.parsea(palabras);
 			if (coman != null)
 				select = coman;
 		}
 		
		return select;
	}
}
