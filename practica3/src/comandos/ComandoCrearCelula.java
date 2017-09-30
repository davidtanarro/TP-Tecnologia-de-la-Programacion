package comandos;


import control.Controlador;

public class ComandoCrearCelula implements Comando {
	/** Atributo privado que contiene las filas introducidas por teclado. */
	private int fil;
	/** Atributo privado que contiene las columnas introducidas por teclado. */
	private int col;

	/**
	 * Constructor sin parametros
	 */
	public ComandoCrearCelula() {
		
	}
	
	/**
	 * Constructor con parametros inicializados
	 * @param f Contiene la fila de la célula
	 * @param c Contiene la columna de la célula
	 */
	public ComandoCrearCelula(int f, int c) {
		this.fil = f;
		this.col = c;
	}
	
	@Override
	public void ejecuta(Controlador controlador) {
		controlador.crearCelula(fil, col);
	}

	@Override
	public Comando parsea(String[] cadenaComando) {
		Comando com = null;
		
		if ((cadenaComando.length == 3) && (cadenaComando[0].equalsIgnoreCase("CrearCelula"))) {
			int f = new Integer(cadenaComando[1]);
			int c = new Integer(cadenaComando[2]);
			com = new ComandoCrearCelula(f, c);
		}
	
		return com;
	}

	@Override
	public String textoAyuda() {
		return "    CREARCELULA F C: crea una nueva celula en la posición (f,c) si es posible\n";
	}

}
