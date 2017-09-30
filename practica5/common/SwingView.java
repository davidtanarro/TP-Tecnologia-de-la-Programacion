package es.ucm.fdi.tp.practica5.common;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import es.ucm.fdi.tp.basecode.bgame.Utils;
import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.basecode.bgame.model.Game.State;
import es.ucm.fdi.tp.basecode.bgame.model.GameError;
import es.ucm.fdi.tp.examenJunio.PulsePlayer;

public abstract class SwingView extends JFrame implements GameObserver {

	private PulsePlayer pulsePlayer;
	private JComboBox<Integer> distancia;
	private JButton pulseButton;
	
	
	private static final long serialVersionUID = 1L;
	/** Controlador */
	protected Controller ctrl;
	/** Vista local de un jugador. Si es null, todos juegan en una vista */
	private Piece localPiece;
	/** Jugador al que le toca jugar */
	private Piece turn;
	/** Tablero */
	private Board board;
	/** Lista de jugadores */
	private List<Piece> pieces;
	
	/** Observador del juego */
	protected Observable<GameObserver> game;
	
	/** Indica si la partida esta en juego */
	private boolean inPlay;
	/** Indica si esta en fase de ejecucion un movimiento */
	private boolean inMovExec;
	/**  Habilita y desabilita vistas y componentes */
	private boolean enable;

	/** Panel donde se instancia el tablero */
	private JPanel boardPanel;
	/** Panel donde se instancia el area de barra de herramientas */
	private JPanel toolBarPanel;
	/** Panel donde se notifica informacion de la partida */
	private JTextArea statusArea;

	/** Boton para cambiar el color de un jugador */
	private JButton chooseColorButton;
	/** Boton para cambiar el modo de juego de un jugador */
	private JButton setButton;
	/** Boton para realizar un movimiento aleatorio */
	private JButton randomButton;
	/** Boton para realizar un movimiento con inteligencia artificial */
	private JButton intelligentButton;
	/** Boton para terminar de jugar */
	private JButton quitButton;
	/** Boton para volver a jugar una nueva partida */
	private JButton restartButton;
	
	/** Jugador aleatorio */
	private Player randomPlayer;
	/** Jugador con inteligencia artificial */
	private Player aiPlayer;

	/** Mapa que relacciona un jugador con un color */
	private Map<Piece, Color> pieceColors;
	/** Iterador que permite recorrer colores */
	Iterator <Color> colorsIter;
		/**
		 * @param p Jugador
		 * @return Color de un jugador
		 */
		final protected Color getPieceColor(Piece p) {
			Color c = pieceColors.get(p);
			if (c == null) {
				c = colorsIter.next();
				pieceColors.put(p, c);
			}
			return c;
		}
	
	/** Atributo que contiene la pieza y el modo del jugador. */
	private Map<Piece, PlayerMode> playerTypes;
	/** Enumerado que contiene los modos de juego. */
	enum PlayerMode {
		MANUAL("Manual"), RANDOM("Random"), AI("Intelegent");
		private String name;
		PlayerMode(String name) {
			this.name = name;
		}
		@Override
		public String toString() {
			return name;
		}
	}
	/** ComboBox que permite seleccionar un jugador */
	private JComboBox<Piece> playerModesCB;
	/** ComboBox que permite seleccionar el modo de juego de un jugador */
	private JComboBox<PlayerMode> modesCB;
	/** ComboBox que permite seleccionar un jugador */
	private JComboBox<Piece> playerColorsCB;
	
	/** Tabla con la informacion de los jugadores */
	private PlayerInfoTableModel playerInfoTable;
	private class PlayerInfoTableModel extends DefaultTableModel {

		private static final long serialVersionUID = 1L;
		private String[] colNames;

		public PlayerInfoTableModel() {
			this.colNames = new String[] { "Players.", "Mode", "#Pieces" };
		}

		@Override
		public String getColumnName(int col) {
			return colNames[col];
		}

		@Override
		public int getColumnCount() {
			return colNames.length;
		}

		@Override
		public int getRowCount() {
			return  pieces == null ? 0 : pieces.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			if (pieces == null)
				return null;
			Piece p = pieces.get(rowIndex);
			if(columnIndex == 0)
				return p;
			else if(columnIndex == 1)
				return playerTypes.get(p);
			else {
				int cont = 0;
				for (int i = 0; i < board.getRows(); i++) 
				  for (int j = 0; j < board.getCols(); j++)
					  if (board.getPosition(i, j) != null && board.getPosition(i, j).equals(p))
						  cont++;
			
				return cont;
			}
		}

		public void refresh() {
			fireTableDataChanged();
		}
	}
	
	/**
	 * Obtiene a quien le toca jugar
	 * @return Devuelve un jugador
	 */
	protected Piece getTurn() {
		return turn;
	}

	/**
	 * Obtiene la lista de jugadores
	 * @return Devuelve la lista
	 */
	final protected List<Piece> getPieces() {
		return pieces;
	}
	
	/**
	 * Obtiene el tablero actual
	 * @return Devuelve el tablero
	 */
	protected Board getBoard() {
		return board;
	}

	/**
	 * Instala el tablero en la vista
	 * @param c Pasaremos el tablero
	 */
	final protected void setBoardArea(JComponent c) {
		boardPanel.add(c, BorderLayout.CENTER);
	}
	
	/**
	 * Añade a la barra de herramientas una componente grafica
	 * @param c Componente grafica
	 */
	final protected void addToCtrlArea(JComponent c) {
		toolBarPanel.add(c);
	}

	/**
	 * Notifica un mensaje en el area de texto
	 * @param msg
	 */
	final protected void addContentToStatusArea(String msg) {
		statusArea.append("* " + msg + "\n");
	}

	protected abstract void initBoardGui();

	protected abstract void activateBoard();
	protected abstract void deActivateBoard();

	protected abstract void redrawBoard();

	/**
	 * Constructor de una vista
	 * @param g Observador
	 * @param c Controlador
	 * @param localPiece Pieza Local
	 * @param randomPlayer Jugador aleatorio
	 * @param autoPlayer Jugador automatico
	 */
	public SwingView(Observable<GameObserver> g, Controller c, Piece localPiece, Player randomPlayer, Player aiPlayer) {
		this.ctrl = c;
		this.game = g;
		this.localPiece = localPiece;
		this.pieceColors = new HashMap <Piece, Color>();
		this.playerTypes = new HashMap <Piece, PlayerMode>();
		this.randomPlayer = randomPlayer;
		this.aiPlayer = aiPlayer;
		this.inPlay = false;
		this.colorsIter = Utils.colorsGenerator();
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				game.addObserver(SwingView.this);
			}
		});

		initGUI();
		
	}

	/**
	 * Construye la parte gráfica del Control y pide a la subclase de construir la del tablero (usando initBoardGui)
	 */
	private void initGUI() {
		
		this.setTitle("Board Games");
		
			JPanel mainPanel = new JPanel(new BorderLayout());
		
		this.setContentPane(mainPanel);

			 // board panel
				boardPanel = new JPanel(new BorderLayout());
			
			mainPanel.add(boardPanel, BorderLayout.CENTER);
			
		initBoardGui();

			 // tool bar panel
				toolBarPanel = new JPanel();
			    toolBarPanel.setLayout(new BoxLayout(toolBarPanel, BoxLayout.Y_AXIS));
			
			mainPanel.add(toolBarPanel, BorderLayout.LINE_END);
			
		initCtrlPanel();

		this.addWindowListener(new WindowListener() {

			@Override
			public void windowClosing(WindowEvent e) {
				quit();
			}

			@Override
			public void windowOpened(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
			}

			@Override
			public void windowClosed(WindowEvent e) {
			}

			@Override
			public void windowActivated(WindowEvent e) {
			}	
		});

		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		//this.pack();
		this.setSize(700, 500);
		this.setVisible(true);
	}

	/** Metodo que añade todos los elementos a la interfaz gráfica. */
	protected void initCtrlPanel() {
		addStatusArea();
		addPlayerInfoTable () ;
		addPlayerColorsCtrl() ;
		addPlayerModesCtrl();
		addAutoPlayerButtons();
		addPulseButton();
		addQuitButton();
	}
	
	final protected  void addPulseButton() {
		if (localPiece == null || localPiece.equals(turn )) {
			JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER ));
			p.setBorder(BorderFactory.createTitledBorder("Pulso electromacnetico"));
	
			distancia = new JComboBox<Integer>();
			for (int i = 1; i < 6; i++)
				distancia.addItem(i);
			pulseButton = new JButton("pulse");
			
				pulseButton.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e){
						int d = (Integer) distancia.getSelectedItem();
						
						try {
							pulsePlayer = new PulsePlayer(d);
							ctrl.makeMove(pulsePlayer);
						}
						catch (GameError _e) {
						}
					}
				});
				
			p.add(distancia);
			p.add(pulseButton);	
			
			addToCtrlArea(p);
		}
	
	}

	/** Metodo que muestra los mensajes de información sobre el estado de la partida. */
	final protected void addStatusArea() {
		JPanel p = new JPanel(new BorderLayout());
		//p.setPreferredSize(new Dimension(225, 175));
		p.setBorder(BorderFactory.createTitledBorder("Status Messages"));
				statusArea = new JTextArea(5, 10);
				statusArea.setEditable(false);
			JScrollPane statusAreaScroll = new JScrollPane(statusArea);
		
		p.add(statusAreaScroll, BorderLayout.CENTER);
		
		addToCtrlArea(p);
	}
	
	/** Método que añade al panel una tabla que muestra los modos de los jugadores */
	final protected void addPlayerInfoTable() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBorder(BorderFactory.createTitledBorder("Player Information"));
		
			playerInfoTable = new PlayerInfoTableModel();
			JTable table = new JTable (playerInfoTable){
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public Component prepareRenderer(TableCellRenderer renderer, int row, int col){
					Component comp = super.prepareRenderer(renderer, row, col);
					comp.setBackground(pieceColors.get(pieces.get(row)));
					return comp;
				}
			};
			table.setFillsViewportHeight(true);
		JScrollPane sp = new JScrollPane(table);
		mainPanel.setPreferredSize(new Dimension(100, 100));
		mainPanel.add(sp);
		addToCtrlArea(mainPanel);
	}
	
	/** Método que añaden al panel el boton para elegir los colores y un comboBox la lista de piezas. */
	final protected void addPlayerColorsCtrl() {
		JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT ));
		p.setBorder(BorderFactory.createTitledBorder("Piece Colors"));

		playerColorsCB = new JComboBox<Piece>();
		
		chooseColorButton = new JButton("Choose Color");
			chooseColorButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e){
					Piece p = (Piece) playerColorsCB.getSelectedItem();
					ColorChooser c = new ColorChooser(new JFrame(), "Select Piece Color", pieceColors.get(p));
					if (c.getColor() != null && !pieceColors.containsValue(c.getColor())) { //Condicion para que dos piezas no tengan el mismo color.
						pieceColors.put(p, c.getColor());
						repaint();
					}
					else {
						//addContentToStatusArea("Atention: The color is alredy chosen.");
						//final ImageIcon iconWarning = new ImageIcon("src/es/ucm/fdi/tp/icons/warning.png");
						JOptionPane.showOptionDialog(new JFrame(), "Atention: The color is alredy chosen.", "Warning",
								JOptionPane.CLOSED_OPTION, JOptionPane.CLOSED_OPTION, null/*iconWarning*/, null, null);
					}
				}
			});
			
		p.add(playerColorsCB);
		p.add(chooseColorButton);	
		
		addToCtrlArea(p);
	}
	
	/** Método que añaden al panel el boton SET y los comboBox para los modos y la lista de piezas. */
	final protected void addPlayerModesCtrl() {
		JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p.setBorder(BorderFactory.createTitledBorder("Player Modes"));
		
		modesCB = new JComboBox<PlayerMode>();
			modesCB.addItem(PlayerMode.MANUAL);
			if (randomPlayer != null) {
				modesCB.addItem(PlayerMode.RANDOM);
			}
			if (aiPlayer != null) {
				modesCB.addItem(PlayerMode.AI);
			}
		
		playerModesCB = new JComboBox<Piece>(new DefaultComboBoxModel<Piece>() {
				/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

				@Override
				public void setSelectedItem(Object o) {
					super.setSelectedItem(o);
					if (playerTypes.get(o) != PlayerMode.MANUAL) {
						modesCB.setSelectedItem(PlayerMode.AI);
					} else {
						modesCB.setSelectedItem(PlayerMode.MANUAL);
					}
				}
		});
		
		setButton = new JButton("Set");
			setButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Piece p = (Piece) playerModesCB.getSelectedItem();
						PlayerMode m = (PlayerMode) modesCB.getSelectedItem();
						PlayerMode currMode = playerTypes.get(p);
						playerTypes.put(p, m);
						playerInfoTable.refresh();
						if (currMode == PlayerMode.MANUAL && m != PlayerMode.MANUAL) {
							decideMakeAutomaticMove();
						}
					}
				});
			
			p.add(playerModesCB);
			p.add(modesCB);
			p.add(setButton);
		addToCtrlArea(p);
	}
	
	/** Método que añaden al panel los botones RANDOM y INTELLIGENT*/
	final protected void addAutoPlayerButtons() {
		
		if (aiPlayer == null && randomPlayer == null) return;
		
		JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p.setPreferredSize(new Dimension(100, 150));
		p.setBorder(BorderFactory.createTitledBorder("Automatic moves"));

		if (randomPlayer != null){
		
		randomButton = new JButton("Random");
		randomButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				decideMakeManualMove(randomPlayer);
			}
		});
		p.add(randomButton);		
		
		}
		
		if (aiPlayer != null){
		
		intelligentButton = new JButton("Intelligent");
		intelligentButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				decideMakeManualMove(aiPlayer);
			}
		});
		p.add(intelligentButton);
		
		}
		
		addToCtrlArea(p);
		
	}
	
	/** Método que añaden al panel los botones QUIT y RESTART*/
	final protected void addQuitButton(){
		//final ImageIcon iconQuit = new ImageIcon("src/es/ucm/fdi/tp/icons/exit.png");
		JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
		quitButton = new JButton("Quit", null/*iconQuit*/);
		quitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				quit();
			}
		});
		p.add(quitButton);
		
		if(localPiece == null) { // El boton Restart solo esta disponible en monovista
			//final ImageIcon iconRestart = new ImageIcon("src/es/ucm/fdi/tp/icons/reiniciar.png");
			restartButton = new JButton("Restart", null/*iconRestart*/);
			restartButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					try{
						ctrl.restart();
						statusArea.setText("");
					} catch(GameError _e){
					}
				}
			});
			p.add(restartButton);
		}
		
		addToCtrlArea(p);
	}
	
	/** Metodo para salir del juego */
	final protected void quit() {
		//final ImageIcon iconCross = new ImageIcon("src/es/ucm/fdi/tp/icons/salir.png");
		int n = JOptionPane.showOptionDialog(new JFrame(), "Are sure you want to quit?", "Quit",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null/*iconCross*/, null, null);

		if (n == 0) {
			try {
				ctrl.stop();
			} catch (GameError _e) {
			}	
			setVisible(false);
			dispose();
			System.exit(0);	
		}
	}

	/** @param manualPlayer Contiene un jugador manual */
	final protected void decideMakeManualMove(Player manualPlayer) {
		if (inMovExec || !inPlay)
			return;
		if (localPiece != null && !localPiece.equals(turn))
			return;
		if (playerTypes.get(turn) != PlayerMode.MANUAL)
			return;
		makeManualMove(manualPlayer);
	}
	
	/** @param manualPlayer Contiene un jugador manual */
	public void makeManualMove(Player manualPlayer) {
		passMoveToCtroller(manualPlayer);
	}
	
	/** Indica si el jugador es aleatorio o de inteligencia artificial. */
	private void decideMakeAutomaticMove() {	
		if (playerTypes.get(turn) == PlayerMode.AI)
			decideMakeAutomaticMove(aiPlayer);
		else
			decideMakeAutomaticMove(randomPlayer);
	}
	
	/** @param player Indica si el jugador es manual o automatico. */
	final protected void decideMakeAutomaticMove(Player player) {
		if (inMovExec || !inPlay)  //Si le partida no está en juego no realiza el movimiento.
			return;
		if (localPiece != null && !localPiece.equals(turn))
			return;
		if (playerTypes.get(turn) == PlayerMode.MANUAL)
			return;
		makeAutomaticMove(player);
	}
	
	/** @param player Indica si el jugador es manual o automatico. */
	public void makeAutomaticMove(Player player) {
		passMoveToCtroller(player);
	}

	/** @param player Indica si el jugador es manual o automatico. */
	private void passMoveToCtroller(Player player) {
		// disableView();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					ctrl.makeMove(player);
				}
				catch (GameError _e) {
				}
			}
			
		});
	}

	/** GAME OBSERVER CALLBACKS **/

	@Override
	public void onGameStart(final Board board, final String gameDesc, final List<Piece> pieces, final Piece turn) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				handleGameStart(board, gameDesc, pieces, turn);
			}
		});
	}
	/**
	 * Inicia el juego, inicializando jugadores y colores.
	 * @param board
	 * @param gameDesc
	 * @param pieces
	 * @param turn
	 */
	private void handleGameStart(Board board, String gameDesc, List<Piece> pieces, Piece turn){
		this.setTitle("Board Games: " + gameDesc + (localPiece == null ? "" : " (" + localPiece + ") "));
		this.turn = turn;
		this.board = board;
		this.pieces = pieces;
		this.inPlay= true;

		initializePlayersTypes();
		initializePiecesColors();
		
		disableView();
	    
		handleTurnChange(turn);
	}

	@Override
	public void onGameOver(final Board board, final State state, final Piece winner) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				handleOnGameOver(board, state, winner);
			}
		});
	}
	
	/**
	 * @param board Contiene el estado del tablero.
	 * @param state Contiene el estado del juego.
	 * @param winner Indica si hay un ganador.
	 */
	private void handleOnGameOver(Board board, State state, Piece winner) {
		addContentToStatusArea("Game Over: " + state);
		playerInfoTable.refresh();
		String s = "";
		if(state == State.Stopped) {
			setVisible(false);
			dispose();
		} else if(state == State.Draw) {
			s = "Draw";
		} else if(state == State.Won) {
			if(winner == localPiece) {
				s = "You won";
			} else {
				s = "The winner is: " + winner;
			}
		}
		addContentToStatusArea(statusArea.getText() + s);
	}

	@Override
	public void onMoveStart(Board board, Piece turn) {
		if (this.turn == turn) {
			inMovExec = true;
		}
		this.board = board;
	}

	@Override
	public void onMoveEnd(Board board, Piece turn, boolean success) {
		if (this.turn == turn) {
			inMovExec = false;
		}
		if (!success ) {
			handleTurnChange(turn);
		}
		this.board = board;
	}

	@Override
	public void onChangeTurn(Board board, final Piece turn) {
		SwingUtilities . invokeLater ( new Runnable() {
			@Override
			public void run() {
				handleTurnChange(turn);
			}
		});
	}

	@Override
	public void onError(String msg) {
		if (!inPlay || localPiece == null || turn.equals(localPiece )) {
			JOptionPane.showMessageDialog(new JFrame(), msg, "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Inicializamos a MANUAL a todos los jugadores.
	 */
	private void initializePlayersTypes() {
		if (localPiece == null) {
			for (Piece p : pieces) {
				if (playerTypes.get(p) == null) {
					playerTypes.put(p, PlayerMode.MANUAL);
					playerModesCB.addItem(p);
				}
			}
		}
		else {
			if (playerTypes.get(localPiece) == null){
				playerTypes.put(localPiece, PlayerMode.MANUAL);
				playerModesCB.addItem(localPiece);
			}
		}
		
	}
	
	/**
	 * Iniciliza los colores de los jugadores.
	 */
	private void initializePiecesColors() {
		playerColorsCB.removeAllItems();  //Vacia los elementos.
		for (Piece p : pieces) {
			if (pieceColors.get(p) == null) {  
				pieceColors.put(p, colorsIter.next());
			}
			playerColorsCB.addItem(p);
		}
	}

	/**
	 * Metodo para cambiar el turno.
	 * @param turn2 Contiene el turno de la pieza.
	 */
	private void handleTurnChange(Piece turn2) {		
		this.turn = turn2;
        addContentToStatusArea("Turn for " + (turn.equals(localPiece) ? "You (" + turn + ")" : turn));
        playerInfoTable.refresh(); //Actualiza la tabla
        
        if (localPiece == null || localPiece.equals(turn) && playerTypes.get(turn) == PlayerMode.MANUAL)
        	enableView();  //Habilita la vista.
        else
        	disableView();  //Deshabilita la vista.
        
        decideMakeAutomaticMove();  //Realiza un movimiento automatico.
        
	}
	/**
	 * Metodo que permite que la vista esté activa y todos los botones correspondientes.
	 */
	private void enableView() {
		enable = true;
		
		setRandMoveButtonStatus(enable);
		setAIMoveButtonStatus(enable);
		setRestartButtonStatus(enable);
		
		activateBoard();
		
	}
	/**
	 * Metodo para desactivar todos los botones correspondientes.
	 */
	private void disableView() {
		enable = false;
		
		setRandMoveButtonStatus(enable);
		setAIMoveButtonStatus(enable);
		setRestartButtonStatus(enable);
		
		deActivateBoard();
	}

	/**
	 * Modifica el estado del boton.
	 * @param enable2 Booleano para inidicar si desactivamos el boton
	 */
	private void setRandMoveButtonStatus(boolean enable2) {
		if (randomButton != null)
			randomButton.setEnabled(enable2);
		
	}
	/**
	 * Modifica el estado del boton.
	 * @param enable2 Booleano para inidicar si desactivamos el boton
	 */
	private void setAIMoveButtonStatus(boolean enable2) {
		if (intelligentButton != null)
			intelligentButton.setEnabled(enable2);
	}

	/**
	 * Modifica el estado del boton.
	 * @param enable2 Booleano para inidicar si desactivamos el boton
	 */
	private void setRestartButtonStatus(boolean enable2) {
		if (restartButton != null)
			restartButton.setEnabled(enable2);
	}

}
