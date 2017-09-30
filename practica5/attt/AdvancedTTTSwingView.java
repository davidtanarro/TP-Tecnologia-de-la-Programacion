package es.ucm.fdi.tp.practica5.attt;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica5.common.FiniteRectBoardSwingView;

public class AdvancedTTTSwingView extends FiniteRectBoardSwingView {

	private static final long serialVersionUID = 1L;

	/** Jugador de AdvancedTTT */
	private AdvancedTTTSwingPlayer player;

	/**	Fila origen */
	private int orow;
	/**	Columna origen */
	private int ocol;
	/**	Fila destino */
	private int drow;
	/**	Columna destino */
	private int dcol;
	
	/**	Indica que ya se ha clicleado una vez si es true y si es false indica que aun no se ha clickeado */
	private boolean primerClick;
	/** Indica si esta activo el tablero para poder aceptar movimientos manuales o no*/
	private boolean activate;
	/**
	 * Constructor de la vista del AdvancedTTT
	 * @param g Observador
	 * @param c Controlador
	 * @param localPiece Pieza Local
	 * @param randomPlayer Jugador aleatorio
	 * @param autoPlayer Jugador automatico
	 */
	public AdvancedTTTSwingView(Observable<GameObserver> g, Controller c,
			Piece localPiece, Player randomPlayer, Player autoPlayer) {
		super(g, c, localPiece, randomPlayer, autoPlayer);
		player = new AdvancedTTTSwingPlayer();
		primerClick = true;
	}

	/**
	 * Sobreescribe el metodo de FiniteRectBoardSwingView
	 */
	@Override
	protected void handleMouseClick(int row, int col, int clickCount,
			int mouseButton) {
		// do nothing if the board is not active
		if (!activate || mouseButton != 1)
			return;
		
		if (primerClick) {
			this.orow = row;
			this.ocol = col;
			if (fullBoardRules()) { // Caso para clickar dos veces
				primerClick = false;
				addContentToStatusArea("Click on an empty cell");
			}
			else { // Caso clickando una vez
				player.setMoveValue(orow, ocol, orow, ocol);
				decideMakeManualMove(player);
			}
		} else {
			this.drow = row;
			this.dcol = col;
			player.setMoveValue(orow, ocol, drow, dcol);
			decideMakeManualMove(player);
			primerClick = true;
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
	
	/**
	 * Indentifica si en el tablero hay 3 huecos libres y el resto esté lleno de piezas
	 * @return true si esta lleno de piezas, falso en caso contrario
	 */
	private boolean fullBoardRules() {
		Board b = getBoard();
		int cont = 0;
		for(int i = 0; i < b.getRows(); i++)
			for(int j = 0; j < b.getCols(); j++)
				if(b.getPosition(i, j) != null)
					cont++;
		if(cont < (b.getRows() * b.getCols() - 3))
			return false;
		else
			return true;
	}

}
