package calculadora;
import java.awt.EventQueue;

import vista.VistaCalc;
import modelo.ModeloCalc;
import control.ControlCalc;


public class Main {

	public static void main(String[] args) {
		
		ModeloCalc modelo = new ModeloCalc();
		final ControlCalc control = new ControlCalc(modelo);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				final VistaCalc vista = new VistaCalc(control);
				vista.setVisible(true);
			}});
	}
}
