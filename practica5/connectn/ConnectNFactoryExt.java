package es.ucm.fdi.tp.practica5.connectn;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.GameError;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.basecode.connectn.ConnectNFactory;

public class ConnectNFactoryExt extends ConnectNFactory {

	private static final long serialVersionUID = 1L;
	/** Se encarga de llamar a la constructora de ConnectNFactory para obtener el modelo */
	public ConnectNFactoryExt(Integer dim) {
		super(dim);
	}
	/** Constructor */
	public ConnectNFactoryExt() {
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
					new ConnectNSwingView(g, c, viewPiece, random, auto);
				}
				
			});
		} catch(InvocationTargetException | InterruptedException e){
			throw new GameError("Something went wrong while creating th");
		}
		
	}

}
