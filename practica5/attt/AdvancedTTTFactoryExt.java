package es.ucm.fdi.tp.practica5.attt;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import es.ucm.fdi.tp.basecode.attt.AdvancedTTTFactory;
import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.GameError;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

public class AdvancedTTTFactoryExt extends AdvancedTTTFactory {

	private static final long serialVersionUID = 1L;

	/** Se encarga de llamar a la constructora de AdvancedTTTFactory para obtener el modelo */
	public AdvancedTTTFactoryExt(Integer dim) {
		super();
	}

	/** Constructor */
	public AdvancedTTTFactoryExt() {
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
					new AdvancedTTTSwingView(g, c, viewPiece, random, auto);
				}
				
			});
		} catch(InvocationTargetException | InterruptedException e){
			throw new GameError("Something went wrong while creating th");
		}
		
	}
}
