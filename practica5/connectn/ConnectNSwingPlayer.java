package es.ucm.fdi.tp.practica5.connectn;

import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.GameRules;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.basecode.connectn.ConnectNMove;

public class ConnectNSwingPlayer extends Player {
	
	private static final long serialVersionUID = 1L;
	/**	Fila origen */
	private int row;
	/**	Columna origen */
	private int col;

	public ConnectNSwingPlayer() {
	}

	/**
	 * Sobreescribe el metodo de Player para crear un movimiento
	 */
	@Override
	public GameMove requestMove(Piece p, Board board, List<Piece> pieces, GameRules rules) {
		return createMove(row, col, p);
	}

	/**
	 * Actualiza los atributos a los nuevos valores donde se realizara el movimiento
	 * @param row Fila origen
	 * @param col Columna origen
	 */
	public void setMoveValue(int row, int col) {
		this.row = row;
		this.col = col;
	}
	/**
	 * Se encarga de crear un movimiento
	 * @param row Fila origen
	 * @param col Columna origen
	 * @param drow Fila destino
	 * @param dcol Columna destino
	 * @param p Pieza
	 * @return Devuelve un movimiento
	 */
	protected GameMove createMove(int row, int col, Piece p) {
		return new ConnectNMove(row, col, p);
	}

}
