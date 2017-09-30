package tp.examples.swing.mvc;


import tp.examples.swing.mvc.logic.SlidePuzzle;
import tp.examples.swing.mvc.control.WindowControler;
import tp.examples.swing.mvc.views.window.WindowView;


public class Main {

	public static void main(String[] args) {
		
		SlidePuzzle game = new SlidePuzzle();              // MODELO
		WindowControler ctrl = new WindowControler(game);  // CONTROLADOR
		new WindowView(ctrl,game);                         // VISTA
		
		ctrl.run();
		
	}

}
