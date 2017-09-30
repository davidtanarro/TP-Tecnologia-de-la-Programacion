package es.ucm.fdi.tp.examen;

import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.GameRules;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica4.ataxx.AtaxxMove;

public class AniadirPiezasPlayer extends Player {

	private static final long serialVersionUID = 1L;
	private int numNewPieces;

	public AniadirPiezasPlayer(int numNewPieces) {
		super();
		this.numNewPieces = numNewPieces;
	}

	@Override
	public GameMove requestMove(Piece p, Board board, List<Piece> pieces, GameRules rules) {

		return createMove(p);
	}

	protected GameMove createMove(Piece p) {
		return new AtaxxMove(p, numNewPieces);
	}
}
