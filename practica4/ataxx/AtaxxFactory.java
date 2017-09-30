package es.ucm.fdi.tp.practica4.ataxx;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import es.ucm.fdi.tp.basecode.bgame.control.ConsolePlayer;
import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.DummyAIPlayer;
import es.ucm.fdi.tp.basecode.bgame.control.GameFactory;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.AIAlgorithm;
import es.ucm.fdi.tp.basecode.bgame.model.GameError;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.GameRules;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.basecode.bgame.views.GenericConsoleView;

public class AtaxxFactory implements GameFactory {

	private static final long serialVersionUID = 1L;

	private Integer fil, col, obs;
	
	/**
	 * Constructor de la clase AtaxxFactory con sus parámetros inicializados por defecto.
	 */
	public AtaxxFactory() {
		// Todo por defecto (filas y columnas tienen que ser impar)
		this.fil = 5;
		this.col = 5;
		this.obs = 0;
	}
	/**
	 * Constructor de la clase AtaxxFactory con sus parámetros filas y columnas inicializados por defecto.
	 */
	public AtaxxFactory(int obs) {
		// Filas y columnas por defecto (filas y columnas tienen que ser impar)
		if (obs < 0 && obs > (fil*col))
			throw new GameError("Obstacles must be at least 0");
		else{
			this.fil = 5;
			this.col = 5;
			this.obs = obs;
		}
	}	
	
	/**
	 * Constructor de la clase AtaxxFactory con sus parámetro obstáculos inicializado por defecto.
	 */
	public AtaxxFactory(int dimRows, int dimCols) {
		// Obstaculos por defecto (filas y columnas tienen que ser impar)
		if ((dimRows < 5 || dimCols < 5) || (dimRows % 2 == 0 || dimCols % 2 == 0)) {
			throw new GameError("Dimension must be at least 5 and odd");
		} else {
			this.fil = dimRows;
			this.col = dimCols;
			this.obs = 0;
		}
	}
	
	/**
	 * Constructor de la clase AtaxxFactory con sus parámetros inicializados mediante los argumentos.
	 */
	public AtaxxFactory(int dimRows, int dimCols, int obs) {
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
	public GameRules gameRules() {
		return new AtaxxRules(this.fil, this.col, this.obs);
	}

	@Override
	public Player createConsolePlayer() {
		ArrayList<GameMove> possibleMoves = new ArrayList<GameMove>();
		possibleMoves.add(new AtaxxMove());
		return new ConsolePlayer(new Scanner(System.in), possibleMoves);
		// return new ConsolePlayerFromListOfMoves(new Scanner(System.in));
	}

	@Override
	public Player createRandomPlayer() {
		return new AtaxxRandomPlayer();
	}

	@Override
	public Player createAIPlayer(AIAlgorithm alg) {
		return new DummyAIPlayer(createRandomPlayer(), 1000);
	}

	@Override
	public List<Piece> createDefaultPieces() {
		List<Piece> pieces = new ArrayList<Piece>();
		
		pieces.add(new Piece("X"));
		pieces.add(new Piece("O"));
		
		return pieces;
	}

	@Override
	public void createConsoleView(Observable<GameObserver> game, Controller ctrl) {
		new GenericConsoleView(game, ctrl);
		
	}

	@Override
	public void createSwingView(Observable<GameObserver> game, Controller ctrl,
			Piece viewPiece, Player randPlayer, Player aiPlayer) {
		throw new UnsupportedOperationException("There is no swing view");
		
	}

}
