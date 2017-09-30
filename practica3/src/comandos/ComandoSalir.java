package comandos;

import control.Controlador;

public class ComandoSalir implements Comando{

	@Override
	public void ejecuta(Controlador controlador) {
		boolean salir = true;
		controlador.setSimulacionTerminada(salir);
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
