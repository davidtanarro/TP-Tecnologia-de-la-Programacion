package es.ucm.fdi.tp.practica5.ttt;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.GameError;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.basecode.ttt.TicTacToeFactory;

public class TicTacToeFactoryExt extends TicTacToeFactory{

	private static final long serialVersionUID = 1L;

	/** Se encarga de llamar a la constructora de TicTacToeFactory para obtener el modelo */
	public TicTacToeFactoryExt(Integer dim) {
		super();
	}

	/** Constructor */
	public TicTacToeFactoryExt() {
	}

	/**
	 * Se encarga de crear una vista para un jugador
	 */
	@Override
	public void createSwingView(final Observable<GameObserver> g, final Controller c, final Piece viewPiece,
			final Player random, final Player auto) {
		
		try{
			SwingUtilities.invokeAndWait(new Runnable() {
				@Override
				public void run() {
					new TicTacToeSwingView(g, c, viewPiece, random, auto);
				}
				
			});
		} catch(InvocationTargetException | InterruptedException e){
			throw new GameError("Something went wrong while creating th");
		}
		
	}
}
