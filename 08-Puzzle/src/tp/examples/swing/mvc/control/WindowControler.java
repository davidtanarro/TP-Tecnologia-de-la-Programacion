package tp.examples.swing.mvc.control;


import tp.examples.swing.mvc.logic.SlidePuzzle;
import tp.examples.swing.mvc.logic.GameError;


public class WindowControler {
	
	SlidePuzzle game;
	
	boolean exit;
	
	public WindowControler(SlidePuzzle game) {
		this.game = game;
		exit = false;
	}

	public void reset(int rows, int cols) {
		game.reset(rows, cols);
	}

	public void move(int row, int col) {
		try {
			game.move(row, col);
		} catch (GameError e) {
			
		}
	}
	
	public void requestExit() {
		exit = true;
	}

	public void run() {
	}
	
}
