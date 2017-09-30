package es.ucm.fdi.tp.practica4.ataxx;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.Utils;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.FiniteRectBoard;
import es.ucm.fdi.tp.basecode.bgame.model.Game.State;
import es.ucm.fdi.tp.basecode.bgame.model.GameError;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.GameRules;
import es.ucm.fdi.tp.basecode.bgame.model.Pair;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

public class AtaxxRules implements GameRules {

	/**
	 * Atributo privado que contiene la fila.
	 */
	private int fil;
	/**
	 * Atributo privado que contiene la columna.
	 */
	private int col;
	
	/**
	 * Atributo privado que contiene el número de obstáculos.
	 */
	private int obs;
	
	/**
	 * Atributo protected que indica el estado del juego.
	 */
	protected final Pair<State, Piece> gameInPlayResult = new Pair<State, Piece>(State.InPlay, null);
	
	public AtaxxRules(int dimRows, int dimCols, int obs) {
		// Nada por defecto (filas y columnas tienen que ser impar)
		if ((dimRows < 5 || dimCols < 5) || (dimRows % 2 == 0 || dimCols % 2 == 0) || (obs < 0 && obs > (dimRows*dimCols))) {
			throw new GameError("Dimension must be at least 5 and odd");
		} else {
			this.fil = dimRows;
			this.col = dimCols;
			this.obs = obs;
		}
	}
	
	@Override
	public String gameDesc() {
		return "Ataxx " + fil + "x" + col;
	}

	@Override
	public Board createBoard(List<Piece> pieces) {
		Board b = new FiniteRectBoard(this.fil, this.col);
		
		if (pieces.size() >= 2) {
			b.setPosition(0, 0, pieces.get(0));
			b.setPosition(b.getRows()-1, b.getCols()-1, pieces.get(0));
			b.setPosition(0, b.getCols()-1, pieces.get(1));
			b.setPosition(b.getRows()-1, 0, pieces.get(1));
		}
		if (pieces.size() >= 3) {
			b.setPosition(b.getRows()/2, 0, pieces.get(2));
			b.setPosition(b.getRows()/2, b.getCols()-1, pieces.get(2));
		}
		if (pieces.size() == 4) {
			b.setPosition(0, b.getCols()/2, pieces.get(3));
			b.setPosition(b.getRows()-1, b.getCols()/2, pieces.get(3));
		}
		
		generateObstacules(b, this.obs, true);
			
		return b;
	}

	private void generateObstacules(Board b, int obs2, boolean symetric) {
		int f, c, i = 0;
		if (symetric) {
			int capacity = b.getCols()*b.getRows(); // 4 cuadrantes
			
			int count = 0;
			for (int k = 0; k < b.getRows(); k++)
				for (int j = 0; j < b.getCols(); j++)
					if (b.getPosition(k, j) != null)
						count++;
			// Capacidad es el numero de casillas libres quitando la fila y columna central.
			capacity = capacity - count -  b.getCols() - b.getRows() + 1;
			
			while (i < this.obs && i < capacity){
				f = Utils.randomInt(b.getRows()/2);
				c = Utils.randomInt(b.getCols()/2);
				if (b.getPosition(f, c) == null) { // Primer cuadrante
					b.setPosition(f, c, new Piece("*"));
					i++;
					if (i < this.obs && i < capacity) { // Cuarto cuadrante
						b.setPosition((b.getRows()-1) - f, (b.getCols()-1) - c, new Piece("*"));
						i++;
						if (i < this.obs && i < capacity) { // Segundo cuadrante
							b.setPosition(f, (b.getCols()-1) - c, new Piece("*"));
							i++;
							if (i < this.obs && i < capacity) { // Tercer cuadrante
								b.setPosition((b.getRows()-1) - f, c, new Piece("*"));
								i++;
							}
						}
					}
				}
			}
		}
		else {
			while (i < this.obs && !b.isFull()){
				f = Utils.randomInt(b.getRows());
				c = Utils.randomInt(b.getCols());
				if (b.getPosition(f, c) == null) {
					b.setPosition(f, c, new Piece("*"));
					i++;
				}
			}
		}
	}

	@Override
	public Piece initialPlayer(Board board, List<Piece> pieces) {
		return pieces.get(0); // Empieza por defecto el primer jugador de la lista
	}

	@Override
	public int minPlayers() {
		return 2; // Por defecto
	}

	@Override
	public int maxPlayers() {
		return 4; // Por defecto
	}

	@Override
	public Pair<State, Piece> updateState(Board board, List<Piece> pieces,
			Piece turn) {
		Pair<State, Piece> state = gameInPlayResult;
		
		if(nextPlayer(board, pieces, turn) == null){
			ArrayList<Integer> numPieces = new ArrayList<>();
			
			for (int i = 0; i < pieces.size(); i++)
				numPieces.add(countPieces(board, pieces.get(i)));
			
			int max = numPieces.get(0);
			Piece winner = pieces.get(0);
			int numOccurMax = 1;
			
			for(int i = 1; i < pieces.size(); i++) {
				if(numPieces.get(i) > max){
					max = numPieces.get(i);
					winner = pieces.get(i);
					numOccurMax = 1;
				}
				else if (numPieces.get(i) == max)
					numOccurMax++;
			}
			if (numOccurMax > 1)
				state = new Pair<State, Piece>(State.Draw, null);
			else
				state = new Pair<State, Piece>(State.Won, winner);
		}
		return state;
	}
	/**
	 * 
	 * @param board Contiene el tablero.
	 * @param p Contiene el jugador actual.
	 * @return Devuelve el número de piezas que tiene ese jugador.
	 */
	private Integer countPieces(Board board, Piece p) {
		int count = 0;

		for (int i = 0; i < board.getRows(); i++)
			for (int j = 0; j < board.getCols(); j++)
				if (board.getPosition(i, j) == p)
					count++;
		return count;
	}

	@Override
	public Piece nextPlayer(Board board, List<Piece> pieces, Piece turn) {
		int numPieces = pieces.size();  
		int i = pieces.indexOf(turn);  //Contiene el jugador actual.
		int j = (i + 1) % numPieces;   //Contiene el siguiente jugador.
		//Calculamos los movimientos válidos del siguiente jugador.
		List<GameMove> listGameMove = validMoves(board, pieces, pieces.get(j));  
		boolean hayMasDeUnJugador = hayMasDeUnJugador (board, pieces, turn);
		//Si el siguiente jugador no tiene movimientos y el tablero no está lleno calculamos
		// los movimiento válidos del siguiente jugador.
		while(listGameMove.size() == 0 && !board.isFull()) {
			j = (j + 1) % numPieces;
			listGameMove = validMoves(board, pieces, pieces.get(j));
		}
		
		if (hayMasDeUnJugador && !board.isFull())
			return pieces.get(j);
		else
			return null;
	}
	
	/**
	 * 
	 * @param board Contiene el tablero actual.
	 * @param pieces Contiene la lista de piezas.
	 * @param turn Contiene el turno del jugador actual.
	 * @return Devuelve true si en el tablero hay mas de un jugador sin contar con el jugador del turno actual.
	 */
	private boolean hayMasDeUnJugador (Board board, List<Piece> pieces, Piece turn) {
		boolean hayMasDeUnJugador = false;

		for (int i = 0 ; i < board.getRows(); i++)
			for (int j = 0 ; j < board.getCols(); j++)
				if (board.getPosition(i, j) != turn && !isObs(pieces, board, i, j))
					hayMasDeUnJugador = true;
		return hayMasDeUnJugador;
	}
	
	/**
	 * 
	 * @param pieces Contiene la lista de piezas.
	 * @param board Contiene el tablero actual.
	 * @param nf Contiene la fila.
	 * @param nc Contiene la columna.
	 * @return Devuelve si en la posición (nf, nc) hay un obstáculo.
	 */
	private boolean isObs(List<Piece> pieces, Board board, int nf, int nc) {
		boolean isObstacle = true;
		
		// Es un obstaculo a no ser que salte una pieza diciendo que es mentira
		for (int i = 0; i < pieces.size(); i++)
			if (board.getPosition(nf, nc) == pieces.get(i))
				isObstacle = false;
		
		return isObstacle;
	}


	@Override
	public List<GameMove> validMoves(Board board, List<Piece> playersPieces,
			Piece turn) {
		int maxPos = 24, nf = -1, nc = -1;
		List<GameMove> list = new ArrayList<GameMove>();
		
		int[] incrFil = {	-2,-2,-2,-2,-2,
							-1,-1,-1,-1,-1,
							 0, 0,    0, 0,
							 1, 1, 1, 1, 1,
							 2, 2, 2, 2, 2 };
		
		int[] incrCol = {	-2,-1, 0, 1, 2,
							-2,-1, 0, 1, 2,
							-2,-1,    1, 2,
							-2,-1, 0, 1, 2,
							-2,-1, 0, 1, 2 };
		
		
		for (int i = 0; i < board.getRows(); i++)
			for (int j = 0; j < board.getCols(); j++)
				if (board.getPosition(i, j) == turn)
					for (int k = 0; k < maxPos; k++) {
						nf = i + incrFil[k];
						nc = j + incrCol[k];
						if (posCorrecta(nf, nc, board) && board.getPosition(nf, nc) == null)
							list.add(new AtaxxMove(i, j, nf, nc, turn));
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
