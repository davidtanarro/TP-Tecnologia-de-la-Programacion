package comandos;


import control.Controlador;

public class ComandoCargar implements Comando {
	
	/**
	 * Atributo privado que contiene el nombre del archivo donde se va a guardar la información.
	 */
	private String nombreFichero;
	
	public ComandoCargar() {
		
	}
	
	/**
	 * 
	 * @param nombreFich Contiene el nombre del fichero.
	 */
	public ComandoCargar(String nombreFich) {
		this.nombreFichero = nombreFich;
	}

	@Override
	public void ejecuta(Controlador controlador) {
		controlador.cargar(this.nombreFichero);
	}

	@Override
	public Comando parsea(String[] cadenaComando) {
		Comando com = null;
		
		if((cadenaComando.length == 2) && (cadenaComando[0].equalsIgnoreCase("cargar")))
			com = new ComandoCargar(new String(cadenaComando[1]));
	
		return com;
	}

	@Override
	public String textoAyuda() {
		return "    CARGAR: carga un fichero\n";
	}

}
