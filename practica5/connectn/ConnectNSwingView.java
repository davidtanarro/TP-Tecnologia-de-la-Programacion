package es.ucm.fdi.tp.practica5.connectn;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica5.common.FiniteRectBoardSwingView;

public class ConnectNSwingView extends FiniteRectBoardSwingView {
	
	private static final long serialVersionUID = 1L;
	/** Jugador de ConnectN */
	private ConnectNSwingPlayer player;
	/** Indica si esta activo el tablero para poder aceptar movimientos manuales o no*/
	private boolean activate;
	/**
	 * Constructor de la vista del ConnectN
	 * @param g Observador
	 * @param c Controlador
	 * @param localPiece Pieza Local
	 * @param randomPlayer Jugador aleatorio
	 * @param autoPlayer Jugador automatico
	 */
	public ConnectNSwingView(Observable<GameObserver> g, Controller c, Piece localPiece, Player randomPlayer,
			Player autoPlayer) {
		
		super(g, c, localPiece, randomPlayer, autoPlayer);
		player = new ConnectNSwingPlayer();
	}
	
	/**
	 * Sobreescribe el metodo de FiniteRectBoardSwingView
	 */
	@Override
	protected void handleMouseClick(int row, int col, int clickCount, int mouseButton) {
	 // do nothing if the board is not active
		if (!activate || mouseButton != 1)
			return;
		player.setMoveValue(row, col);
		decideMakeManualMove(player);
	}

	/**
	 * Activa el tablero
	 * SwingView llama a este métodos para indicar que acepta movimientos manuales
	 */
	@Override
	protected void activateBoard() {
		activate = true;
		addContentToStatusArea("Click on an empty cell");
	}
	/**
	 * Desactiva el tablero
	 * SwingView llama a este método para indicar que no acepta movimientos manuales
	 */
	@Override
	protected void deActivateBoard() {
		activate = false;
	}
}
