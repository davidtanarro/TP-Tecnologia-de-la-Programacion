package ejemplo2;

import java.util.ArrayList;

public class Modelo {
	public static double PREC = 10000;
	ArrayList<ObservadorModelo> observadores;
		
	public Modelo() {
		observadores = new ArrayList<ObservadorModelo>();
	}
	
	public void calcular(double valor) {
		if (valor != 0.0) {
			double res = 1/valor;
			res = Math.round(res*PREC)/PREC;
		
			// se notifica a los observadores
			for (ObservadorModelo ob : observadores) {
				ob.actualizar(res);
				ob.notificarError("");
			}
		} else {
			// se notifica a los observadores
			for (ObservadorModelo ob : observadores) 
				ob.notificarError("¡División por 0!");
		}
	}

	public void addObservador(ObservadorModelo ob) {
		observadores.add(ob);
	}
	
	public void removeObservador(ObservadorModelo ob) {
		observadores.remove(ob);
	}
}
