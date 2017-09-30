package comandos;

import logica.Mundo;

public class ComandoEliminarCelula implements Comando {
	/** Atributo privado que contiene las filas introducidas por teclado. */
	private int fil;
	/** Atributo privado que contiene las columnas introducidas por teclado. */
	private int col;

	/**
	 * Constructor sin parametros
	 */
	public ComandoEliminarCelula() {
		
	}
	
	/**
	 * Constructor con parametros inicializados
	 * @param f Contiene la fila de la c�lula
	 * @param c Contiene la columna de la c�lula
	 */
	public ComandoEliminarCelula(int f, int c) {
		this.fil = f;
		this.col = c;
	}
	
	@Override
	public void ejecuta(Mundo mundo) {
		if((fil < 0 || fil >= mundo.getMaxFilas()) ||  (col < 0 || col >= mundo.getMaxColumnas()))
			System.out.println("Error: Esa posici�n no se encuentra dentro de los limites de la superficie.");
		else if (mundo.eliminarCelula(this.fil, this.col))
			System.out.println("Eliminada la celula en la posici�n: (" + this.fil + "," + this.col + ")");
		else
			System.out.println("Error: ya est� libre esa posici�n");
	}

	@Override
	public Comando parsea(String[] cadenaComando) {
		Comando com = null;
		
		if ((cadenaComando.length == 3) && (cadenaComando[0].equalsIgnoreCase("eliminarCelula"))) {
			int f = new Integer(cadenaComando[1]);
			int c = new Integer(cadenaComando[2]);
			com = new ComandoEliminarCelula(f, c);
		}
	
		return com;
	}

	@Override
	public String textoAyuda() {
		return "    ELIMINARCELULA F C: elimina una celula de la posici�n (f,c) si es posible\n";
	}
}
