package ejemplo1;

import java.util.ArrayList;


public class Modelo {
	ArrayList<ObservadorModelo> observadores;
		
	public Modelo() {
		observadores = new ArrayList<ObservadorModelo>();
	}
	
	public void calcular(double valor) {
		double res = valor*valor;
		
		// se notifica a los observadores
		for (ObservadorModelo ob : observadores) 
			ob.actualizar(res);
	}

	public void addObservador(ObservadorModelo ob) {
		observadores.add(ob);
	}
	
	public void removeObservador(ObservadorModelo ob) {
		observadores.remove(ob);
	}
}
