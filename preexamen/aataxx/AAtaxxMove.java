package es.ucm.fdi.tp.examen.aataxx;

import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameError;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

public class AAtaxxMove extends GameMove {

	private static final long serialVersionUID = 1L;

	/**
	 * Atributo protegido de la clase AtaxxMove que indica la fila actual
	 */
	protected int iniRow;
	
	/**
	 * Atributo protegido de la clase AtaxxMove que indica la columna actual
	 */
	protected int iniCol;

	/**
	 * Constructor de la clase AtaxxMove.
	 */
	public AAtaxxMove() {
	}

	/**
	 * Constructor de la clase AtaxxMove.
	 * @param iniRow Contiene la fila actual.
	 * @param iniCol Contiene la columna actual.
	 * @param destRow Contiene la fila destino.
	 * @param destCol Contiene la columna destino.
	 * @param p Contiene la pieza.
	 */
	public AAtaxxMove(int iniRow, int iniCol, Piece p) {
		super(p);
		this.iniRow = iniRow;
		this.iniCol = iniCol;
	}
	
	
	@Override
	public void execute(Board board, List<Piece> pieces) {
		Piece p = getPiece(); 
		
		//Error: esa pieza no pertenece a ese jugador.
		if (board.getPosition(iniRow, iniCol) != p) {
			throw new GameError(
					"Position (" + iniRow + "," + iniCol + ") does not contains a piece of type " + p + ".");
		}
		else if (validMoves(board, pieces, p, iniRow, iniCol) <= 0) {
			throw new GameError(
					"No hay movimientos poibles para  la posicion (" + iniRow + "," + iniCol + ")");
		}
		else {
			int[] incrFila = { -1,  0, 0, 1};
			int[] incrColu = {  0, -1, 1, 0};
			int maxPosi = 4, nfi = -1, nco = -1;
			for (int z = 0; z < maxPosi; z++) {
				nfi = iniRow + incrFila[z];
				nco = iniCol + incrColu[z];
				// Cuidado con el orden de los metodos de este if
				if (posCorrecta(nfi, nco, board) && board.getPosition(nfi, nco) == null) {
					board.setPosition(nfi, nco, p);
					
					// Se cambian las piezas de alrededor de color
					int maxPos = 8, nf = -1, nc = -1;
					int[] incrFil = {	-1,-1,-1,
										 0,    0,
										 1, 1, 1};
					int[] incrCol = {	-1, 0, 1,
										-1,    1,
										-1, 0, 1};
					
					for (int k = 0; k < maxPos; k++) {
						nf = nfi + incrFil[k];
						nc = nco + incrCol[k];
						// Cuidado con el orden de los metodos de este if
						if (posCorrecta(nf, nc, board) && board.getPosition(nf, nc) != null && !isObs(pieces, board, nf, nc))
							board.setPosition(nf, nc, p);
					}
				}
			}
			
		}
		
	}
	
	private int validMoves(Board board, List<Piece> pieces, Piece p, int fil, int col) {
		int[] incrFila = { -1,  0, 0, 1};
		int[] incrColu = {  0, -1, 1, 0};
		int cont = 0;

		int maxPosi = 4, nf = -1, nc = -1;

		if (board.getPosition(fil, col) == p)
			for (int z = 0; z < maxPosi; z++) {
				nf = fil + incrFila[z];
				nc = col + incrColu[z];
				// Cuidado con el orden de los metodos de este if
				if (posCorrecta(nf, nc, board) && board.getPosition(nf, nc) == null)
					cont++;
			}
		
		return cont;
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
	/**
	 * 
	 * @param pieces Contiene la lista de jugadores.
	 * @param board Contiene el tablero.
	 * @param nf Contiene la fila actual.
	 * @param nc Contiene la columna ctual.
	 * @return Devuelve si la casilla contiene un obstáculo.
	 */
	private boolean isObs(List<Piece> pieces, Board board, int nf, int nc) {
		boolean isObstacle = true;
		
		// Es un obstaculo a no ser que salte una pieza diciendo que es mentira
		for (int i = 0; i < pieces.size(); i++)
			if (board.getPosition(nf, nc).equals(pieces.get(i)))
				isObstacle = false;
		
		return isObstacle;
	}
	
	@Override
	public GameMove fromString(Piece p, String str) {
		String[] words = str.split(" ");
		if (words.length != 2) {
			return null;
		}
		try {
			int iniRow, iniCol;
			iniRow = Integer.parseInt(words[0]);
			iniCol = Integer.parseInt(words[1]);
			return new AAtaxxMove(iniRow, iniCol, p);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	@Override
	public String help() {
		return "Row and column for origin and for destination, separated by spaces (four numbers).";
	}

}
