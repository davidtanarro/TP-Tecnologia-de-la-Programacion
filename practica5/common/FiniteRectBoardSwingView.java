package es.ucm.fdi.tp.practica5.common;

import java.awt.Color;
import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica5.common.BoardComponent.Shapes;

public abstract class FiniteRectBoardSwingView extends SwingView {
	
	private static final long serialVersionUID = 1L;
	/** Tablero del juego */
	private BoardComponent boardComp;
	
	/**
	 * Se encarga de llamar la vista para crear
	 * @param g Observador
	 * @param c Controlador
	 * @param localPiece Pieza Local
	 * @param randomPlayer Jugador aleatorio
	 * @param autoPlayer Jugador automatico
	 */
	public FiniteRectBoardSwingView(Observable<GameObserver> g, Controller c, Piece localPiece, Player randomPlayer,
			Player autoPlayer) {
		
		super(g, c, localPiece, randomPlayer, autoPlayer);
		
	}

	/**
	 * Inicializa la parte gráfica del tablero cuando SwingView lo pide:
	 * 		construir el componente y pasarlo a SwingView llamando a setBoardArea
	 */
	@Override
	protected void initBoardGui() {
        //JComponent board = new BoardComponent(game) {
        boardComp = new BoardComponent(game) {
       
			private static final long serialVersionUID = 1L;

			@Override
            protected void mouseClicked(int row, int col, int clickCount, int mouseButton) {
                handleMouseClick(row, col, clickCount, mouseButton); // call handleMouseClick to let subclasses handle the event
            }
            @Override
            protected Color getPieceColor(Piece p) {
                return FiniteRectBoardSwingView.this.getPieceColor(p);
            }
          
            @Override  
            protected Shapes getPieceShape(Piece p) {
                return FiniteRectBoardSwingView.this.getPieceShape(p);
            }
        };
        setBoardArea(boardComp);// install the board in the view
	}

	/** Cuando SwingView nos pide redibujar el tablero lo pedimos al BoardComponent */
	@Override
	protected void redrawBoard() {
		// ask boardComp to redraw the board
		boardComp.repaint();
	}
	
	/**
	 * Consigue dar una forma a una pieza
	 * @param p Pieza
	 * @return Tipo de forma
	 */
	public Shapes getPieceShape(Piece p) {
		List<Piece> pieces = getPieces();
		if ( pieces != null && pieces.contains(p) ) {
			return Shapes.CIRCLE;
		} else {
			return Shapes.RECTANGLE;
		}
	}
	
	/**
	 * Se encarga de procesar la informacion al haber clickeado el raton
	 * @param row
	 * @param col
	 * @param clickCount
	 * @param mouseButton
	 */
	protected abstract void handleMouseClick(int row, int col, int clickCount, int mouseButton);

}
