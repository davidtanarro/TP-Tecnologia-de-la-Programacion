package comandos;

import control.Controlador;

public class ComandoPaso implements Comando{

	@Override
	public void ejecuta(Controlador controlador) {
		controlador.daUnPaso();
	}

	@Override
	public Comando parsea(String[] cadenaComando) {
		Comando com = null;
	
		if((cadenaComando.length == 1) && (cadenaComando[0].equalsIgnoreCase("paso")))
			com = new ComandoPaso();
		
		return com;
	}

	@Override
	public String textoAyuda() {
		return "    PASO: realiza un paso en la simulacion\n";
	}

}
