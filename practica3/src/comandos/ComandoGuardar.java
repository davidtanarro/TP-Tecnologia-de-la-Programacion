package comandos;

import control.Controlador;

public class ComandoGuardar implements Comando {
	/**
	 * Atributo privado que contiene el nombre del archivo donde se va a guardar la información.
	 */
	private String nombreFichero;
	
	public ComandoGuardar() {
	}
	
	/**
	 * @param nombreFich Contiene el nombre del fichero.
	 */
	public ComandoGuardar(String nombreFich) {
		this.nombreFichero = nombreFich;
	}
	@Override
	public void ejecuta(Controlador controlador) {
		controlador.guardar(this.nombreFichero);
	}

	@Override
	public Comando parsea(String[] cadenaComando) {
		Comando com = null;
		
		if((cadenaComando.length == 2) && (cadenaComando[0].equalsIgnoreCase("guardar")))
			com = new ComandoGuardar(new String(cadenaComando[1]));
	
		return com;
	}

	@Override
	public String textoAyuda() {
		return "    GUARDAR: guarda un fichero\n";
	}

}
