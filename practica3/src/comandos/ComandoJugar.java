package comandos;

import control.Controlador;
import logica.Mundo;
import logica.MundoComplejo;
import logica.MundoSimple;

public class ComandoJugar implements Comando {
	/**
	 * Atributo privado de la clase mundo.
	 */
	private Mundo mundo;
	
	/** 
	 * Atributo privado que contiene las filas introducidas por teclado. 
	 */
	private int fil;
	
	/** 
	 * Atributo privado que contiene las columnas introducidas por teclado. 
	 */
	private int col;
	
	/** 
	 * Atributo privado que contiene el número de células simples.
	 */
	private int nSimples;
	
	/**
	 *  Atributo privado que contiene el número de células complejas.
	 */
	private int nComplejas;
	
	
	/**
	 * Constructor sin parametros
	 */
	public ComandoJugar() {
		
	}
	/**
	 * Constructor con parametros inicializados que crea el mundo simple.
	 * @param n Contiene la fila de la célula
	 * @param m Contiene la columna de la célula
	 * @param s Contiene el número de celulas simples iniciales.
	 */
	public ComandoJugar(int n, int m, int s) {
		this.fil = n;
		this.col = m;
		this.nSimples = s;
		this.mundo = new MundoSimple(fil, col, nSimples);
	}
	
	/**
	 * Constructor con parametros inicializados que crea el mundo complejo.
	 * @param n Contiene la fila de la célula
	 * @param m Contiene la columna de la célula
	 * @param s Contiene el número de celulas simples iniciales.
	 * @param c Contiene el número de células complejas iniciales.
	 */
	public ComandoJugar(int n, int m, int s, int c) {
		this.fil = n;
		this.col = m;
		this.nSimples = s;
		this.nComplejas = c;
		this.mundo = new MundoComplejo(fil, col, nSimples, nComplejas);
	}

	@Override
	public void ejecuta(Controlador controlador) {
		controlador.jugar(this.mundo);
	}

	@Override
	public Comando parsea(String[] cadenaComando) {
		int n, m, s, c;
		Comando com = null;
		
		if ((cadenaComando.length == 5) && (cadenaComando[0].equalsIgnoreCase("jugar")) && (cadenaComando[1].equalsIgnoreCase("simple"))) {
			n = new Integer(cadenaComando[2]);
			m = new Integer(cadenaComando[3]);
			s = new Integer(cadenaComando[4]);
			com = new ComandoJugar(n, m, s);
			
		}
		else if ((cadenaComando.length == 6) && (cadenaComando[0].equalsIgnoreCase("jugar")) && (cadenaComando[1].equalsIgnoreCase("complejo"))) {
			n = new Integer(cadenaComando[2]);
			m = new Integer(cadenaComando[3]);
			s = new Integer(cadenaComando[4]);
			c = new Integer(cadenaComando[5]);
			com = new ComandoJugar(n, m, s, c);
		}
	
		return com;
	}

	@Override
	public String textoAyuda() {
		return  "    JUGAR SIMPLE/COMPLEJO: inicia un nuevo juego\n";
	}

}
