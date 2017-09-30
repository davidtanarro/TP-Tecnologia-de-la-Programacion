package es.ucm.fdi.tp.practica5.ataxx;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.GameError;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica4.ataxx.AtaxxFactory;


public class AtaxxFactoryExt extends AtaxxFactory {

	private static final long serialVersionUID = 1L;

	/** Se encarga de llamar a la constructora de AtaxxFactory para obtener el modelo */
	public AtaxxFactoryExt() {
		// Todo por defecto (filas y columnas tienen que ser impar)
		super();
	}

	/** Se encarga de llamar a la constructora de AtaxxFactory para obtener el modelo */
	public AtaxxFactoryExt(int obs) {
		// Filas y columnas por defecto (filas y columnas tienen que ser impar)
		super(obs);
	}	

	/** Se encarga de llamar a la constructora de AtaxxFactory para obtener el modelo */
	public AtaxxFactoryExt(int dimRows, int dimCols) {
		// Obstaculos por defecto (filas y columnas tienen que ser impar)
		super(dimRows, dimCols);
	}

	/** Se encarga de llamar a la constructora de AtaxxFactory para obtener el modelo */
	public AtaxxFactoryExt(int dimRows, int dimCols, int obs) {
		// Nada por defecto (filas y columnas tienen que ser impar)
		super(dimRows, dimCols, obs);
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
					new AtaxxSwingView(g, c, viewPiece, random, auto);
				}
				
			});
		} catch(InvocationTargetException | InterruptedException e){
			throw new GameError("Something went wrong while creating th");
		}
		
	}
}
