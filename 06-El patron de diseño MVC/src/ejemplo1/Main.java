package ejemplo1;

/**
 * Ejemplo mínimo de MVC.
 */

import java.awt.EventQueue;


public class Main {

	public static void main(String[] args) {
		Modelo modelo = new Modelo();
		final Controlador control = new Controlador(modelo);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				final Vista vista = new Vista(control);
				vista.setVisible(true);
			}});


	}

}
