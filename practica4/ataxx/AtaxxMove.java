package es.ucm.fdi.tp.practica4.ataxx;

import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameError;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

public class AtaxxMove extends GameMove {

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
	 * Atributo protegido de la clase AtaxxMove que indica la fila destino.
	 */
	protected int destRow;
	
	/**
	 * Atributo protegido de la clase AtaxxMove que indica la columna destino.
	 */
	protected int destCol;

	/**
	 * Constructor de la clase AtaxxMove.
	 */
	public AtaxxMove() {
	}

	/**
	 * Constructor de la clase AtaxxMove.
	 * @param iniRow Contiene la fila actual.
	 * @param iniCol Contiene la columna actual.
	 * @param destRow Contiene la fila destino.
	 * @param destCol Contiene la columna destino.
	 * @param p Contiene la pieza.
	 */
	public AtaxxMove(int iniRow, int iniCol, int destRow, int destCol, Piece p) {
		super(p);
		this.destRow = destRow;
		this.destCol = destCol;
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
		//Error: La posici�n destino est� ocupada.
		else if (board.getPosition(destRow, destCol) != null) {
			throw new GameError("Position (" + destRow + "," + destCol + ") is already occupied");
		} 
		else {
			int dista = distancia(iniRow, iniCol, destRow, destCol);
			//Esa pieza no puede moverse a esa posici�n porque no est� dentro de la distancia adecuada.
			if (dista > 2) {
				throw new GameError("Position (" + destRow + "," + destCol + ") is not valid for piece in (" + iniRow
						+ "," + iniCol + ").");
			} else {
				board.setPosition(destRow, destCol, p);
				// Se elimina la pieza de origen si la distancia del movimiento es dos
				if (dista == 2)
					board.setPosition(iniRow, iniCol, null);
				
				// Se cambian las piezas de alrededor de color
				int maxPos = 8, nf = -1, nc = -1;
				int[] incrFil = {	-1,-1,-1,
									 0,    0,
									 1, 1, 1};
				int[] incrCol = {	-1, 0, 1,
									-1,    1,
									-1, 0, 1};
				
				for (int k = 0; k < maxPos; k++) {
					nf = destRow + incrFil[k];
					nc = destCol + incrCol[k];
					// Cuidado con el orden de los metodos de este if
					if (posCorrecta(nf, nc, board) && board.getPosition(nf, nc) != null && !isObs(pieces, board, nf, nc))
						board.setPosition(nf, nc, p);
				}
			}
		}
		
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
	 * @return Devuelve si la casilla contiene un obst�culo.
	 */
	private boolean isObs(List<Piece> pieces, Board board, int nf, int nc) {
		boolean isObstacle = true;
		
		// Es un obstaculo a no ser que salte una pieza diciendo que es mentira
		for (int i = 0; i < pieces.size(); i++)
			if (board.getPosition(nf, nc).equals(pieces.get(i)))
				isObstacle = false;
		
		return isObstacle;
	}

	/**
	 * 
	 * @param iniRow2 Contiene la fila actual.
	 * @param iniCol2 Contiene la columna actual.
	 * @param destRow2 Contiene la fila destino.
	 * @param destCol2 Contiene la columna destino.
	 * @return Devuelve la m�ximo n�mero para saber cuantas posiciones ha avanzado la pieza.
	 */
	private int distancia(int iniRow2, int iniCol2, int destRow2, int destCol2) {
		return Math.max(Math.abs(iniRow2 - destRow2), Math.abs(iniCol2 - destCol2));
	}
	
	@Override
	public GameMove fromString(Piece p, String str) {
		String[] words = str.split(" ");
		if (words.length != 4) {
			return null;
		}
		try {
			int destRow, destCol, iniRow, iniCol;
			iniRow = Integer.parseInt(words[0]);
			iniCol = Integer.parseInt(words[1]);
			destRow = Integer.parseInt(words[2]);
			destCol = Integer.parseInt(words[3]);
			return new AtaxxMove(iniRow, iniCol, destRow, destCol, p);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	@Override
	public String help() {
		return "Row and column for origin and for destination, separated by spaces (four numbers).";
	}

}
