package es.ucm.fdi.tp.practica4.foxHounds;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import es.ucm.fdi.tp.basecode.bgame.control.ConsolePlayer;
import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.DummyAIPlayer;
import es.ucm.fdi.tp.basecode.bgame.control.GameFactory;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.AIAlgorithm;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.GameRules;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.basecode.bgame.views.GenericConsoleView;

public class FoxHoundsFactory implements GameFactory {

	private static final long serialVersionUID = 1L;

	private Integer fil, col;
	
	/**
	 * Constructor de la clase AtaxxFactory con sus parámetros inicializados por defecto.
	 */
	public FoxHoundsFactory() {
		// Todo por defecto (filas y columnas tienen que ser impar)
		this.fil = 8;
		this.col = 8;
	}
	
	@Override
	public GameRules gameRules() {
		return new FoxHoundsRules(this.fil, this.col);
	}

	@Override
	public Player createConsolePlayer() {
		ArrayList<GameMove> possibleMoves = new ArrayList<GameMove>();
		possibleMoves.add(new FoxHoundsMove());
		return new ConsolePlayer(new Scanner(System.in), possibleMoves);
		// return new ConsolePlayerFromListOfMoves(new Scanner(System.in));
	}

	@Override
	public Player createRandomPlayer() {
		return new FoxHoundsRandomPlayer();
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
