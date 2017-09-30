package es.ucm.fdi.tp.practica4.foxHounds;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.Utils;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.FiniteRectBoard;
import es.ucm.fdi.tp.basecode.bgame.model.Game.State;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.GameRules;
import es.ucm.fdi.tp.basecode.bgame.model.Pair;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

public class FoxHoundsRules implements GameRules {

	/**
	 * Atributo privado que contiene la fila.
	 */
	private int fil;
	/**
	 * Atributo privado que contiene la columna.
	 */
	private int col;
	
	/**
	 * Atributo protected que indica el estado del juego.
	 */
	protected final Pair<State, Piece> gameInPlayResult = new Pair<State, Piece>(State.InPlay, null);
	
	public FoxHoundsRules(int dimRows, int dimCols) {
		this.fil = dimRows;
		this.col = dimCols;
	}
	
	@Override
	public String gameDesc() {
		return "FoxHounds " + fil + "x" + col;
	}

	@Override
	public Board createBoard(List<Piece> pieces) {
		Board b = new FiniteRectBoard(this.fil, this.col);
		
		if (pieces.size() == 2) {
			b.setPosition(0, 1, pieces.get(1));
			b.setPosition(0, 3, pieces.get(1));
			b.setPosition(0, 5, pieces.get(1));
			b.setPosition(0, 7, pieces.get(1));
			
			b.setPosition(7, 0, pieces.get(0));
		}
		
			
		return b;
	}

	
	@Override
	public Piece initialPlayer(Board board, List<Piece> pieces) {
		int juegaAleatorio = Utils.randomInt(pieces.size());
		return pieces.get(juegaAleatorio);
	}

	@Override
	public int minPlayers() {
		return 2; // Por defecto
	}

	@Override
	public int maxPlayers() {
		return 2; // Por defecto
	}

	@Override
	public Pair<State, Piece> updateState(Board board, List<Piece> pieces,
			Piece turn) {
		Pair<State, Piece> state = gameInPlayResult;
		
		if(nextPlayer(board, pieces, turn) == null){
			Piece winner = turn;
			
			state = new Pair<State, Piece>(State.Won, winner);
		}
		
		return state;
	}

	@Override
	public Piece nextPlayer(Board board, List<Piece> pieces, Piece turn) {
		int numPieces = pieces.size();  
		int i = pieces.indexOf(turn);  //Contiene el jugador actual.
		int j = (i + 1) % numPieces;   //Contiene el siguiente jugador.
		
		//Calculamos los movimientos válidos del siguiente jugador.
		List<GameMove> listGameMove = validMoves(board, pieces, pieces.get(j)); 
		
		if (listGameMove.size() > 0)
			return pieces.get(j);
		else
			return null;
	}


	@Override
	public List<GameMove> validMoves(Board board, List<Piece> playersPieces,
			Piece turn) {
		List<GameMove> list = new ArrayList<GameMove>();
		
		if (turn == playersPieces.get(0)) { // Si es el zorro
			int maxPos = 4, nf = -1, nc = -1;
			int[] incrFil = {	-1,-1,
								 1, 1 };
			int[] incrCol = {	-1, 1,
								-1, 1 };
			
			for (int i = 0; i < board.getRows(); i++)
				for (int j = 0; j < board.getCols(); j++)
					if (board.getPosition(i, j) != null && board.getPosition(i, j).equals(turn))
						for (int k = 0; k < maxPos; k++) {
							nf = i + incrFil[k];
							nc = j + incrCol[k];
							if (posCorrecta(nf, nc, board) && board.getPosition(nf, nc) == null)
								list.add(new FoxHoundsMove(i, j, nf, nc, turn));
						}
		}
		else { // Si son los perros
			int maxPos = 2, nf = -1, nc = -1;
			int[] incrFil = {	 1, 1 };
			int[] incrCol = {	-1, 1 };
			
			
			for (int i = 0; i < board.getRows(); i++)
				for (int j = 0; j < board.getCols(); j++)
					if (board.getPosition(i, j) != null && board.getPosition(i, j).equals(turn))
						for (int k = 0; k < maxPos; k++) {
							nf = i + incrFil[k];
							nc = j + incrCol[k];
							if (posCorrecta(nf, nc, board) && board.getPosition(nf, nc) == null)
								list.add(new FoxHoundsMove(i, j, nf, nc, turn));
						}
		}
		
		return list;
	}

	/**
	 * Comprueba si una casilla es correcta
	 * @param fil Numero de fila a comprobar
	 * @param col Numero de columna a comprobar
	 * @return Devuelve si una casilla esta dentro de la superficie
	 */
	private boolean posCorrecta(int f, int c, Board board) {
		boolean posicionCorrecta = false;
		
		if ((0 <= f && f < board.getRows()) && (0 <= c && c < board.getCols()))
			posicionCorrecta = true;
		
		return posicionCorrecta;
	}

	@Override
	public double evaluate(Board board, List<Piece> pieces, Piece turn, Piece p) {
		// TODO Auto-generated method stub
		return 0;
	}
}
