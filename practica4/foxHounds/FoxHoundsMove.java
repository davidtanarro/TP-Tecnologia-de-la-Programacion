package es.ucm.fdi.tp.practica4.foxHounds;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.Utils;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameError;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

public class FoxHoundsMove extends GameMove {
	private int distancia = 0;

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
	public FoxHoundsMove() {
	}

	public FoxHoundsMove(int d, Piece p) {
		super(p);
		this.distancia = d;
	}
	
	/**
	 * Constructor de la clase AtaxxMove.
	 * @param iniRow Contiene la fila actual.
	 * @param iniCol Contiene la columna actual.
	 * @param destRow Contiene la fila destino.
	 * @param destCol Contiene la columna destino.
	 * @param p Contiene la pieza.
	 */
	public FoxHoundsMove(int iniRow, int iniCol, int destRow, int destCol, Piece p) {
		super(p);
		this.destRow = destRow;
		this.destCol = destCol;
		this.iniRow = iniRow;
		this.iniCol = iniCol;
	}
	
	
	@Override
	public void execute(Board board, List<Piece> pieces) {
		Piece p = getPiece();
		
		if (distancia != 0) {
			int fila;
			int columna;
			do {
				fila = Utils.randomInt(board.getRows());
				columna = Utils.randomInt(board.getCols());
			}
			while (board.getPosition(fila, columna) != null && !board.getPosition(fila, columna).equals(p));

			for (int f = fila-distancia; f <= fila+distancia; f++)
				for (int c = columna-distancia; c <= columna+distancia; c++)
					if (f != fila && c != columna) {
						if (board.getPosition(f, c) != null && !isObs(pieces, board, f, c)) {
							// realizaMovimientoEnDiagonal
							List<GameMove> list = new ArrayList<GameMove>();
							
							int maxPos = 4, nf = -1, nc = -1;
							int[] incrFil = {	-1,-1,
												 1, 1 };
							int[] incrCol = {	-1, 1,
												-1, 1 };
							
							for (int i = 0; i < board.getRows(); i++)
								for (int j = 0; j < board.getCols(); j++)
									if (board.getPosition(i, j) != null && board.getPosition(i, j).equals(p))
										for (int k = 0; k < maxPos; k++) {
											nf = i + incrFil[k];
											nc = j + incrCol[k];
											if (posCorrecta(nf, nc, board) && board.getPosition(nf, nc) == null)
												list.add(new FoxHoundsMove(i, j, nf, nc, p));
										}
							
							int numMoves = list.size();
							if(numMoves > 0){
								list.get(Utils.randomInt(numMoves));
								// Y haria el movimiento seleccionado
							}
						}
					}
			
			distancia = 0;
		}
		else {
			//Error: esa pieza no pertenece a ese jugador.
			if (board.getPosition(iniRow, iniCol) != p) {
				throw new GameError(
						"Position (" + iniRow + "," + iniCol + ") does not contains a piece of type " + p + ".");
			} 
			//Error: La posición destino está ocupada.
			else if (board.getPosition(destRow, destCol) != null) {
				throw new GameError("Position (" + destRow + "," + destCol + ") is already occupied");
			} 
			else {
				if (movimientoValido(iniRow, iniCol, destRow, destCol, p)) {
					board.setPosition(iniRow, iniCol, null);
					board.setPosition(destRow, destCol, p);
				}
				else
					throw new GameError("Position (" + destRow + "," + destCol + ") no es valida");
				
			}
		}
	}
	
	private boolean posCorrecta(int f, int c, Board board) {
		boolean posicionCorrecta = false;
		
		if ((0 <= f && f < board.getRows()) && (0 <= c && c < board.getCols()))
			posicionCorrecta = true;
		
		return posicionCorrecta;
	}


	private boolean isObs(List<Piece> pieces, Board board, int nf, int nc) {
		boolean isObstacle = true;
		
		// Es un obstaculo a no ser que salte una pieza diciendo que es mentira
		for (int i = 0; i < pieces.size(); i++)
			if (board.getPosition(nf, nc).equals(pieces.get(i)))
				isObstacle = false;
		
		return isObstacle;
	}

	private boolean movimientoValido(int iniRow, int iniCol, int destRow, int destCol, Piece p) {
		if (iniRow != destRow && iniCol != destCol && distancia(iniRow, iniCol, destRow, destCol) == 1)
			return true;
		else
			return false;
	}
	
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
			return new FoxHoundsMove(iniRow, iniCol, destRow, destCol, p);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	@Override
	public String help() {
		return "Row and column for origin and for destination, separated by spaces (four numbers).";
	}

}
