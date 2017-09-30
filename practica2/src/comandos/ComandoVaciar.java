package comandos;

import logica.Mundo;

public class ComandoVaciar implements Comando{

	@Override
	public void ejecuta(Mundo mundo) {
		System.out.println("Vaciando la superficie....");
		mundo.vaciarSuperficie();
	}

	@Override
	public Comando parsea(String[] cadenaComando) {
		Comando com = null;
		
		if((cadenaComando.length == 1) && (cadenaComando[0].equalsIgnoreCase("vaciar")))
			com = new ComandoVaciar();
		
		return com;
	}

	@Override
	public String textoAyuda() {
		return "    VACIAR: crea un mundo vacío\n";
	}

}
