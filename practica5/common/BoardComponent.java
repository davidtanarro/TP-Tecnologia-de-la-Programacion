package es.ucm.fdi.tp.practica5.common;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import es.ucm.fdi.tp.basecode.bgame.Utils;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.Game.State;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class BoardComponent extends JComponent implements GameObserver { 
	private static final long serialVersionUID = 1L;

// En el PDF su nombre es JBoard e implementa GameObserver
	/** Atributo que contiene el tablero. */
	private Board board;
	
	private int _CELL_HEIGHT = 50; // En el PDF esta sin inicializar
	private int _CELL_WIDTH = 50;

	/** Atrbuto que contiene la fila. */
	private int rows;
	/** Atrbuto que contiene la columna. */
	private int cols;
	
	/** Enumerado que contiene las dos formas de las figuras.*/
	public enum Shapes {
		CIRCLE, RECTANGLE
	}
	
	/** Mapa que contiene la pieza y el color. */
	private HashMap<Piece, Color> pieceColors = new HashMap<Piece, Color>();
	/** Iterador para definir los colores. */
	private Iterator<Color> colorsIter = Utils.colorsGenerator();
	
	/**
	 * Constructor de la clase BoardComponent.
	 * @param game 
	 */
	public BoardComponent(final Observable<GameObserver> game) {
		initGUI();
		//SwingUtilities.invokeLater(new Runnable() {
		//	@Override
			//public void run() {
				game.addObserver(BoardComponent.this); // Registramos la JComponente como Observador del Modelo
			//}
		//});
	}
	
	

	private void initGUI() {

		// Se usa "MouseListener" para capturar los eventos del ratón.
		addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				//System.out.println("Mouse Released: " + "(" + e.getX() + "," + e.getY() + ")");
			}

			@Override
			public void mousePressed(MouseEvent e) {
				//System.out.println("Mouse Pressed: " + "(" + e.getX() + "," + e.getY() + ")");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				//System.out.println("Mouse Exited Component: " + "(" + e.getX() + "," + e.getY() + ")");
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				//System.out.println("Mouse Entered Component: " + "(" + e.getX() + "," + e.getY() + ")");
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				//System.out.println("Mouse Clicked:: " + "(" + e.getX() + "," + e.getY() + ")");
				
				int col = (e.getX() / _CELL_WIDTH);
				int row = (e.getY() / _CELL_HEIGHT);
				int mouseButton = 0;
				if (SwingUtilities.isLeftMouseButton(e)) // Botón izquierdo del ratón
					mouseButton = 1;
				else if (SwingUtilities.isMiddleMouseButton(e)) // Botón central del ratón
					mouseButton = 2;
				else if (SwingUtilities.isRightMouseButton(e)) // Botón derecho del ratón
					mouseButton = 3;
				if (mouseButton == 0)
					return; // Unknown button, don't know if it is possible!
				BoardComponent.this.mouseClicked(row, col, e.getClickCount(), mouseButton);
			}
		});
		
		//this.setSize(new Dimension(rows * _CELL_HEIGHT, cols * _CELL_WIDTH));
		//this.setPreferredSize(new Dimension(200, 200)); // PDF
		this.setPreferredSize(new Dimension(rows * _CELL_HEIGHT + 20 * cols, cols * _CELL_WIDTH));
		repaint();
		
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);// Siempre llamar a "paintComponent" de la super clase antes de dibujar!
		
		fillBoard(g); // En "fillBoard" ponemos el código que dibuja el tablero usando el Graphics g
	    repaint();
	}
	/**
	 * Metodo para dibujar el tablero.
	 * @param g Contiene los graficos para dibujar el tablero.
	 */
	private void fillBoard(Graphics g) {
		
		if (board == null) {
			g.setColor( Color.red );
			g.drawString("Waiting for game to start!",20,  this.getHeight()/2);
			return;
		} else {

			int cols = board.getCols();
			int rows = board.getRows();
			
			_CELL_WIDTH  = this.getWidth()  / cols;
			_CELL_HEIGHT = this.getHeight() / rows;
	
			for (int i = 0; i < rows; i++)
				for (int j = 0; j < cols; j++)
					drawCell(i, j, g);
		}
		
	}

	/**
	 * @param p Contiene la pieza
	 * @return Devuelve el color que contiene la pieza.
	 */
	protected Color getPieceColor(Piece p) {
		Color c = pieceColors.get(p);

        if (c == null) {
            pieceColors.put(p, colorsIter.next());
        }

        return c;
	}

	/**
	 * Dibuja una celda.
	 * @param row Contiene la fila.
	 * @param col Contiene la columna.
	 * @param g Contiene los graficos.
	 */
	private void drawCell(int row, int col, Graphics g) {
		int x = col * _CELL_WIDTH;
        int y = row * _CELL_HEIGHT;

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(x + 2, y + 2, _CELL_WIDTH - 4, _CELL_HEIGHT - 4);

        Piece p = board.getPosition(row, col);

        if (p != null) {
			Color c = getPieceColor(p);
			Shapes s = getPieceShape(p);
			
			g.setColor(c);
			switch(s) {
			
			case CIRCLE:
				g.fillOval(x + 4, y + 4, _CELL_WIDTH - 8, _CELL_HEIGHT - 8);
				g.setColor(Color.black);
				g.drawOval(x + 4, y + 4, _CELL_WIDTH - 8, _CELL_HEIGHT - 8);
				break;
			case RECTANGLE: //Para los obstaculos.
				g.fillRect(x + 4, y + 4, _CELL_WIDTH - 8, _CELL_HEIGHT - 8);
				g.setColor(Color.black);
				g.drawRect(x + 4, y + 4, _CELL_WIDTH - 8, _CELL_HEIGHT - 8);
				break;
			default:
				break;
			}
		}
	}
	/**
	 * @param p Contiene la pieza.
	 * @return Devuelve la forma de la pieza, en este caso un circulo.
	 */
	protected Shapes getPieceShape(Piece p) {
		return Shapes.CIRCLE;
	}

	
	// El método "mouseClicked" se implementará en el SwingView de cada juego concreto.
	protected void mouseClicked(int row, int col, int clickCount, int mouseButton) {
	}

	/** GAME OBSERVER CALLBACKS **/
	/**
	 * Cambia el estado del tablero.
	 * @param board2 Contiene el estado del tablero modificado.
	 */
	private void changeOnBoard(Board board2) {
		this.board = board2; // Para obtener el estado inicial del tablero desde el Modelo.
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                repaint(); // Para redibujar de nuevo la componente visual con los cambios.
            }
        });
	}
	
	@Override
	public void onGameStart(Board board, String gameDesc, List<Piece> pieces, Piece turn) {
		changeOnBoard(board);
	}

	@Override
	public void onGameOver(Board board, State state, Piece winner) {
		changeOnBoard(board);
	}

	@Override
	public void onMoveStart(Board board, Piece turn) {}

	@Override
	public void onMoveEnd(Board board, Piece turn, boolean success) {
		changeOnBoard(board);
	}

	@Override
	public void onChangeTurn(Board board, Piece turn) {
		changeOnBoard(board);
	}

	@Override
	public void onError(String msg) {}

}
