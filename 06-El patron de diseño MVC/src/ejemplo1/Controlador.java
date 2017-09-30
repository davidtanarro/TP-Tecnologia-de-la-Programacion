package ejemplo1;

public class Controlador {
	Modelo modelo;
	
	public Controlador(Modelo m) {
		modelo = m;
	}
	
	public void calcular(String str) {
		try {
			double valor = Double.parseDouble(str);
			modelo.calcular(valor);
		} catch (NumberFormatException e) {
		}
	}

	public void addObservador(ObservadorModelo ob) {
		modelo.addObservador(ob);
	}
	
	public void removeObservador(ObservadorModelo ob) {
		modelo.removeObservador(ob);
	}

}
