package comandos;

import logica.Mundo;

public class ComandoSalir implements Comando{

	@Override
	public void ejecuta(Mundo mundo) {
		boolean salir = true;
		mundo.setSimulacionTerminada(salir);
		System.out.println("Fin de la simulacion.....");
	}

	@Override
	public Comando parsea(String[] cadenaComando) {
		Comando com = null;
		
		if((cadenaComando.length == 1) && (cadenaComando[0].equalsIgnoreCase("salir")))
			com = new ComandoSalir();
		
		return com;
	}

	@Override
	public String textoAyuda() {
		return  "    SALIR: cierra la aplicación\n";
	}

}
