package comandos;

import control.Controlador;

public class ComandoAyuda implements Comando{

	@Override
	public void ejecuta(Controlador controlador) {
		String cad = "POSIBLES COMANDOS:\n";
		cad += ParserComando.AyudaComandos();
		System.out.println(cad);
	}

	@Override
	public Comando parsea(String[] cadenaComando) {
		Comando com = null;
		
		if((cadenaComando.length == 1) && (cadenaComando[0].equalsIgnoreCase("ayuda")))
			com = new ComandoAyuda();
		
		return com;
	}

	@Override
	public String textoAyuda() {
		return "    AYUDA: muestra esta ayuda\n";
	}

}
