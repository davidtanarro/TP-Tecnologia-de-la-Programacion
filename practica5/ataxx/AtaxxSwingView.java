package es.ucm.fdi.tp.practica5.ataxx;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica5.common.FiniteRectBoardSwingView;

public class AtaxxSwingView extends FiniteRectBoardSwingView {
	
	private static final long serialVersionUID = 1L;

	/** Jugador de Ataxx */
	private AtaxxSwingPlayer player;
	
	/**	Fila origen */
	private Integer orow;
	/**	Columna origen */
	private Integer ocol;
	/**	Fila destino */
	private Integer drow;
	/**	Columna destino */
	private Integer dcol;
	
	/**	Indica que ya se ha clicleado una vez si es true y si es false indica que aun no se ha clickeado */
	private boolean primerClick;
	/** Indica si esta activo el tablero para poder aceptar movimientos manuales o no*/
	private boolean activate;
	/**
	 * Constructor de la vista del Ataxx
	 * @param g Observador
	 * @param c Controlador
	 * @param localPiece Pieza Local
	 * @param randomPlayer Jugador aleatorio
	 * @param autoPlayer Jugador automatico
	 */
	public AtaxxSwingView(Observable<GameObserver> g, Controller c,
			Piece localPiece, Player randomPlayer, Player autoPlayer) {
		super(g, c, localPiece, randomPlayer, autoPlayer);
		player = new AtaxxSwingPlayer();
		primerClick = true;
	}

	/**
	 * Sobreescribe el metodo de FiniteRectBoardSwingView
	 */
	@Override
	protected void handleMouseClick(int row, int col, int clickCount,
			int mouseButton) {
		 // do nothing if the board is not active
		if (!activate || (mouseButton != 1 && mouseButton != 3))
			return;
		
		if (mouseButton == 1) {
			if (primerClick){
				this.orow = row;
				this.ocol = col;
				primerClick = false;
				addContentToStatusArea("Click on an empty cell");
			} else {
				this.drow = row;
				this.dcol = col;
	
				player.setMoveValue(orow, ocol, drow, dcol);
				decideMakeManualMove(player);
				primerClick = true;
			}
		}
		else { // mouseButton == 3
			primerClick = true;
			if (this.orow != null && this.ocol != null)
				addContentToStatusArea("Move cancelled");
			this.orow = null;
			this.ocol = null;
		}
	}

	/**
	 * Activa el tablero
	 * SwingView llama a este métodos para indicar que acepta movimientos manuales
	 */
	@Override
	protected void activateBoard() {
		activate = true;
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
