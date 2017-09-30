package comandos;

import logica.CelulaSimple;
import logica.Mundo;

public class ComandoCrearCelulaSimple implements Comando{
	/** Atributo privado que contiene las filas introducidas por teclado. */
	private int fil;
	/** Atributo privado que contiene las columnas introducidas por teclado. */
	private int col;

	/**
	 * Constructor sin parametros
	 */
	public ComandoCrearCelulaSimple() {
		
	}
	
	/**
	 * Constructor con parametros inicializados
	 * @param f Contiene la fila de la célula
	 * @param c Contiene la columna de la célula
	 */
	public ComandoCrearCelulaSimple(int f, int c) {
		this.fil = f;
		this.col = c;
	}
	
	@Override
	public void ejecuta(Mundo mundo) {
		if((fil < 0 || fil >= mundo.getMaxFilas()) ||  (col < 0 || col >= mundo.getMaxColumnas()))
			System.out.println("Error: Esa posición no se encuentra dentro de los limites de la superficie.");
		else if (mundo.crearCelula(this.fil, this.col,new CelulaSimple()))
			System.out.println("Creamos una nueva celula simple en la posición: (" + this.fil + "," + this.col + ")");
		else
			System.out.println("Error: ya está ocupada esa posición");
	}

	@Override
	public Comando parsea(String[] cadenaComando) {
		Comando com = null;
		
		if ((cadenaComando.length == 3) && (cadenaComando[0].equalsIgnoreCase("CrearCelulaSimple"))) {
			int f = new Integer(cadenaComando[1]);
			int c = new Integer(cadenaComando[2]);
			com = new ComandoCrearCelulaSimple(f, c);
		}
	
		return com;
	}

	@Override
	public String textoAyuda() {
		return "    CREARCELULASIMPLE F C: crea una nueva celula simple en la posición (f,c) si es posible\n";
	}

}
