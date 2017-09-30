package comandos;

import logica.Mundo;

public class ComandoPaso implements Comando{

	@Override
	public void ejecuta(Mundo mundo) {
		mundo.evoluciona();
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
