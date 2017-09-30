package comandos;

import control.Controlador;

public class ComandoIniciar implements Comando{

	@Override
	public void ejecuta(Controlador controlador) {
		controlador.vaciarSuperficie();
		controlador.inicializaSuperficie();
	}

	@Override
	public Comando parsea(String[] cadenaComando) {
		Comando com = null;
	
		if((cadenaComando.length == 1) && (cadenaComando[0].equalsIgnoreCase("iniciar")))
			com = new ComandoIniciar();
	
		return com;
	}

	@Override
	public String textoAyuda() {
		return "    INICIAR: inicia una nueva simulación\n";
	}

}
