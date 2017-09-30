package es.ucm.fdi.tp.practica5.foxHounds;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.GameError;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica4.foxHounds.FoxHoundsFactory;


public class FoxHoundsFactoryExt extends FoxHoundsFactory {

	private static final long serialVersionUID = 1L;

	/** Se encarga de llamar a la constructora de AtaxxFactory para obtener el modelo */
	public FoxHoundsFactoryExt() {
		// Todo por defecto (filas y columnas tienen que ser impar)
		super();
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
					new FoxHoundsSwingView(g, c, viewPiece, random, auto);
				}
				
			});
		} catch(InvocationTargetException | InterruptedException e){
			throw new GameError("Something went wrong while creating th");
		}
		
	}
}
