package es.ucm.fdi.tp.examenJunio;


import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import es.ucm.fdi.tp.basecode.bgame.control.GameFactory;
import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.ConsoleCtrl;
import es.ucm.fdi.tp.basecode.bgame.control.ConsoleCtrlMVC;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.Game;
import es.ucm.fdi.tp.basecode.bgame.model.GameError;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.basecode.bgame.model.AIAlgorithm;
import es.ucm.fdi.tp.basecode.minmax.MinMax;
import es.ucm.fdi.tp.practica5.ataxx.AtaxxFactoryExt;
import es.ucm.fdi.tp.practica5.attt.AdvancedTTTFactoryExt;
import es.ucm.fdi.tp.practica5.connectn.ConnectNFactoryExt;
import es.ucm.fdi.tp.practica5.foxHounds.FoxHoundsFactoryExt;
import es.ucm.fdi.tp.practica5.ttt.TicTacToeFactoryExt;
import es.ucm.fdi.tp.practica6.remotectrl.GameServer;
import es.ucm.fdi.tp.practica6.remotectrl.GameClient;


public class Main {

	/**
	 * The possible views.
	 * <p>
	 * Vistas disponibles.
	 */
	enum ViewInfo {
		WINDOW("window", "Swing"), CONSOLE("console", "Console");

		private String id;
		private String desc;

		ViewInfo(String id, String desc) {
			this.id = id;
			this.desc = desc;
		}

		public String getId() {
			return id;
		}

		public String getDesc() {
			return desc;
		}

		@Override
		public String toString() {
			return id;
		}
	}

	/**
	 * The available games.
	 * <p>
	 * Juegos disponibles.
	 */
	enum GameInfo {
		ATAXX("ataxx", "Ataxx"), CONNECTN("cn", "ConnectN"), TicTacToe("ttt", "Tic-Tac-Toe"),
		AdvancedTicTacToe("attt","Advanced Tic-Tac-Toe"), FoxHounds("fh","FoxHounds");

		private String id;
		private String desc;

		GameInfo(String id, String desc) {
			this.id = id;
			this.desc = desc;
		}

		public String getId() {
			return id;
		}

		public String getDesc() {
			return desc;
		}

		@Override
		public String toString() {
			return id;
		}

	}

	/**
	 * Player modes (manual, random, etc.)
	 * <p>
	 * Modos de juego.
	 */
	enum PlayerMode {
		MANUAL("m", "Manual"), RANDOM("r", "Random"), AI("a", "Automatics");

		private String id;
		private String desc;

		PlayerMode(String id, String desc) {
			this.id = id;
			this.desc = desc;
		}

		public String getId() {
			return id;
		}

		public String getDesc() {
			return desc;
		}

		@Override
		public String toString() {
			return id;
		}
	}

	/**
	 * Player modes (manual, random, etc.)
	 * <p>
	 * Modos de juego.
	 */
	private enum AppMode {
		NORMAL("normal", "Normal mode"), SERVER("server", "Server mode"), CLIENT("client", "Client mode");

		private String id;
		private String desc;

		AppMode(String id, String desc) {
			this.id = id;
			this.desc = desc;
		}

		public String getId() {
			return id;
		}

		public String getDesc() {
			return desc;
		}

		@Override
		public String toString() {
			return id;
		}
	}

	//Indicamos los algoritmos que queremos utilizar para el 'jugador
	// automatico':
	private enum AlgorithmForAIPlayer {
		NONE("none", "No AI Algorithm"), MINMAX("minmax", "MinMax"), MINMAXAB("minmaxab",
				"MinMax with Alhpa-Beta Prunning");

		private String id;
		private String desc;

		AlgorithmForAIPlayer(String id, String desc) {
			this.id = id;
			this.desc = desc;
		}

		public String getId() {
			return id;
		}

		public String getDesc() {
			return desc;
		}

		@Override
		public String toString() {
			return desc;
		}
	}

	/**
	 * Default game to play.
	 * <p>
	 * Juego por defecto.
	 */
	final private static GameInfo DEFAULT_GAME = GameInfo.CONNECTN;

	/**
	 * default view to use.
	 * <p>
	 * Vista por defecto.
	 */
	final private static ViewInfo DEFAULT_VIEW = ViewInfo.WINDOW;

	/**
	 * Default player mode to use.
	 * <p>
	 * Modo de juego por defecto.
	 */
	final private static PlayerMode DEFAULT_PLAYERMODE = PlayerMode.MANUAL;

	/**
	 * Default Application mode to use.
	 */
	final private static AppMode DEFAULT_APPMODE = AppMode.NORMAL;

	/**
	 * 
	 */
	final private static String DEFAULT_SERVERPORT = "2020";

	/**
	 * 
	 */
	final private static String DEFAULT_SERVERHOST = "localhost";

	/**
	 * 
	 */
	final private static AlgorithmForAIPlayer DEFAULT_AIALG = AlgorithmForAIPlayer.NONE;

	/**
	 * This field includes a game factory that is constructed after parsing the
	 * command-line arguments. Depending on the game selected with the -g option
	 * (by default {@link #DEFAULT_GAME}).
	 * 
	 * <p>
	 * Este atributo incluye una factoria de juego que se crea despues de
	 * extraer los argumentos de la linea de ordenes. Depende del juego
	 * seleccionado con la opcion -g (por defecto, {@link #DEFAULT_GAME}).
	 */
	private static GameFactory gameFactory;

	/**
	 * List of pieces provided with the -p option, or taken from
	 * {@link GameFactory#createDefaultPieces()} if this option was not
	 * provided.
	 * 
	 * <p>
	 * Lista de fichas proporcionadas con la opcion -p, u obtenidas de
	 * {@link GameFactory#createDefaultPieces()} si no hay opcion -p.
	 */
	private static List<Piece> pieces;

	/**
	 * A list of player modes. The i-th mode corresponds to the i-th piece in
	 * the list {@link #pieces}. They correspond to what is provided in the -p
	 * option (or using the default value {@link #DEFAULT_PLAYERMODE}).
	 * 
	 * <p>
	 * Lista de modos de juego. El modo i-esimo corresponde con la ficha i-esima
	 * de la lista {@link #pieces}. Esta lista contiene lo que se proporciona en
	 * la opcion -p (o el valor por defecto {@link #DEFAULT_PLAYERMODE}).
	 */
	private static List<PlayerMode> playerModes;

	/**
	 * The view to use. Depending on the selected view using the -v option or
	 * the default value {@link #DEFAULT_VIEW} if this option was not provided.
	 * 
	 * <p>
	 * Vista a utilizar. Dependiendo de la vista seleccionada con la opcion -v o
	 * el valor por defecto {@link #DEFAULT_VIEW} si el argumento -v no se
	 * proporciona.
	 */
	private static ViewInfo view;

	/**
	 * {@code true} if the option -m was provided, to use a separate view for
	 * each piece, and {@code false} otherwise.
	 * 
	 * <p>
	 * {@code true} si se incluye la opcion -m, para utilizar una vista separada
	 * por cada ficha, o {@code false} en caso contrario.
	 */
	private static boolean multiviews;

	/**
	 * Number of rows provided with the option -d ({@code null} if not
	 * provided).
	 * 
	 * <p>
	 * Numero de filas proporcionadas con la opcion -d, o {@code null} si no se
	 * incluye la opcion -d.
	 */
	private static Integer dimRows;
	/**
	 * Number of columns provided with the option -d ({@code null} if not
	 * provided).
	 * 
	 * <p>
	 * Numero de columnas proporcionadas con la opcion -d, o {@code null} si no
	 * se incluye la opcion -d.
	 * 
	 */
	private static Integer dimCols;

	/**
	 * The algorithm to be used by the automatic player. Not used so far, it is
	 * always {@code null}.
	 * 
	 * <p>
	 * Algoritmo a utilizar por el jugador automatico. Actualmente no se
	 * utiliza, por lo que siempre es {@code null}.
	 */
	private static AIAlgorithm aiPlayerAlg;

	/**
	 * The depth of the maximum depth in the MinMax Algorithm.
	 * 
	 * <p>
	 * La profundidad máxima del árbol MinMax
	 */
	private static Integer minmaxTreeDepth;

	/**
	 * Number of obstacles for Ataxx.
	 */
	private static Integer numAtaxxObstacles;

	/**
	 * The application mode.
	 */
	private static AppMode applicationMode;

	/**
	 * The server port.
	 */
	private static Integer serverPort;

	/**
	 * The server host name.
	 */
	private static String serverHost;

	/**
	 * Processes the command-line arguments and modify the fields of this class
	 * with corresponding values. E.g., the factory, the pieces, etc.
	 *
	 * <p>
	 * Procesa la linea de ordenes del programa y crea los objetos necesarios
	 * para los atributos de esta clase. Por ejemplo, la factoria, las fichas,
	 * etc.
	 * 
	 * 
	 * @param args
	 *            Command line arguments.
	 * 
	 *            <p>
	 *            Lista de argumentos de la linea de ordenes.
	 * 
	 * 
	 */
	private static void parseArgs(String[] args) {

		// define the valid command line options
		//
		Options cmdLineOptions = new Options();
		cmdLineOptions.addOption(constructHelpOption()); // -h or --help
		cmdLineOptions.addOption(constructGameOption()); // -g or --game
		cmdLineOptions.addOption(constructViewOption()); // -v or --view
		cmdLineOptions.addOption(constructMlutiViewOption()); // -m or
																// --multiviews
		cmdLineOptions.addOption(constructPlayersOption()); // -p or --players
		cmdLineOptions.addOption(constructDimensionOption()); // -d or --dim
		cmdLineOptions.addOption(constructAtaxxObstaclesOption()); // -o or
																	// --obstacles
		cmdLineOptions.addOption(constructAppModeOption()); // -a or --app-mode
		cmdLineOptions.addOption(constructServerPortOption()); // -sp or
																// --server-port
		cmdLineOptions.addOption(constructServerHostOption()); // -sh or
																// --server-host
		cmdLineOptions.addOption(constructMinMaxDepathOption()); // -md or
																	// --minmax-depth
		cmdLineOptions.addOption(constructAIAlgOption());

		// parse the command line as provided in args
		//
		CommandLineParser parser = new DefaultParser();
		try {
			CommandLine line = parser.parse(cmdLineOptions, args);
			parseHelpOption(line, cmdLineOptions);
			parseDimOptionn(line);
			parseAtaxxObstaclesOptionn(line);
			parseGameOption(line);
			parseViewOption(line);
			parseMultiViewOption(line);
			parsePlayersOptions(line);
			parseAppModeOptions(line);
			parseServerPortOption(line);
			parseServerHostOption(line);
			parseMixMaxDepthOption(line);
			parseAIAlgOption(line);

			// if there are some remaining arguments, then something wrong is
			// provided in the command line!
			//
			String[] remaining = line.getArgs();
			if (remaining.length > 0) {
				String error = "Illegal arguments:";
				for (String o : remaining)
					error += (" " + o);
				throw new ParseException(error);
			}

		} catch (ParseException | GameError e) {
			// new Piece(...) might throw GameError exception
			System.err.println(e.getLocalizedMessage());
			System.exit(1);
		}

	}

	private static Option constructMinMaxDepathOption() {
		Option opt = new Option("md", "minmax-depth", true, "The maximum depth of the MinMax tree");
		opt.setArgName("number");
		return opt;
	}

	private static void parseMixMaxDepthOption(CommandLine line) throws ParseException {
		String depthVal = line.getOptionValue("md");
		minmaxTreeDepth = null;

		if (depthVal != null) {
			try {
				minmaxTreeDepth = Integer.parseInt(depthVal);
			} catch (NumberFormatException e) {
				throw new ParseException("Invalid value for the MinMax depth '" + depthVal + "'");
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	private static Option constructAIAlgOption() {
		String optionInfo = "The AI algorithm to use ( ";
		for (AlgorithmForAIPlayer alg : AlgorithmForAIPlayer.values()) {
			optionInfo += alg.getId() + " [for " + alg.getDesc() + "] ";
		}
		optionInfo += "). By defualt, no algorithm is used.";
		Option opt = new Option("aialg", "ai-algorithm", true, optionInfo);
		opt.setArgName("algorithm for ai player");
		return opt;
	}

	/**
	 * 
	 * @param line
	 * @throws ParseException
	 */
	private static void parseAIAlgOption(CommandLine line) throws ParseException {
		String aialg = line.getOptionValue("aialg", DEFAULT_AIALG.getId());

		AlgorithmForAIPlayer selectedAlg = null;
		for (AlgorithmForAIPlayer a : AlgorithmForAIPlayer.values()) {
			if (a.getId().equals(aialg)) {
				selectedAlg = a;
				break;
			}
		}

		if (selectedAlg == null) {
			throw new ParseException("Uknown AI algorithms '" + aialg + "'");
		}

		switch (selectedAlg) {
		case MINMAX:
			aiPlayerAlg = minmaxTreeDepth == null ? new MinMax(false) : new MinMax(minmaxTreeDepth, false);
			break;
		case MINMAXAB:
			aiPlayerAlg = minmaxTreeDepth == null ? new MinMax() : new MinMax(minmaxTreeDepth);
			break;
		case NONE:
			aiPlayerAlg = null;
			break;
		}
	}

	private static Option constructAppModeOption() {
		Option opt = new Option("am", "app-mode", true, "Application Mode");
		opt.setArgName("mode");
		return opt;
	}

	private static void parseAppModeOptions(CommandLine line) throws ParseException {
		String appModelVal = line.getOptionValue("am", DEFAULT_APPMODE.getId());
		applicationMode = null;

		for (AppMode m : AppMode.values()) {
			if (m.getId().equals(appModelVal)) {
				applicationMode = m;
				break;
			}
		}

		if (applicationMode == null) {
			throw new ParseException("Uknown mode '" + appModelVal + "'");
		}

	}

	private static Option constructServerPortOption() {
		Option opt = new Option("sp", "server-port", true,
				"The port to use when starting a server, or whenconnecting to a server.");
		opt.setArgName("port");
		return opt;
	}

	private static void parseServerPortOption(CommandLine line) throws ParseException {
		String port = line.getOptionValue("sp", DEFAULT_SERVERPORT);

		try {
			serverPort = Integer.parseInt(port);
		} catch (NumberFormatException e) {
			throw new ParseException("Invalid port '" + port + "'");
		}
	}

	private static Option constructServerHostOption() {
		Option opt = new Option("sh", "server-host", true, "The host on which the server is running.");
		opt.setArgName("host name");
		return opt;
	}

	private static void parseServerHostOption(CommandLine line) throws ParseException {
		serverHost = line.getOptionValue("sh", DEFAULT_SERVERHOST);
	}

	/**
	 * Builds the Ataxx obstacles option (-o or --obstacles)
	 * 
	 * @return CLI {@link {@link Option} for the Ataxx obstacles option.
	 */
	private static Option constructAtaxxObstaclesOption() {
		Option opt = new Option("o", "obstacles", true, "The number of obstacles to be used for Ataax");
		opt.setArgName("number");
		return opt;
	}

	/**
	 * Parses the Ataxx obstacles option (-o or --obstacles). It sets the value
	 * of {@link #numAtaxxObstacles} accordingly.
	 * 
	 * 
	 * @param line
	 *            CLI {@link CommandLine} object.
	 * @throws ParseException
	 *             If an invalid value is provided.
	 */
	private static void parseAtaxxObstaclesOptionn(CommandLine line) throws ParseException {
		String obstVal = line.getOptionValue("o", null);

		if (obstVal != null) {
			try {
				numAtaxxObstacles = Integer.parseInt(obstVal);
			} catch (NumberFormatException e) {
				throw new ParseException("Invalid value for ataax obstacles '" + obstVal + "'");
			}
		}
	}

	/**
	 * Builds the multiview (-m or --multiviews) CLI option.
	 * 
	 * <p>
	 * Construye la opcion CLI -m.
	 * 
	 * @return CLI {@link {@link Option} for the multiview option.
	 */

	private static Option constructMlutiViewOption() {
		return new Option("m", "multiviews", false,
				"Create a separate view for each player (valid only when using the " + ViewInfo.WINDOW + " view)");
	}

	/**
	 * Parses the multiview option (-m or --multiview). It sets the value of
	 * {@link #multiviews} accordingly.
	 * 
	 * <p>
	 * Extrae la opcion multiview (-m) y asigna el valor de {@link #multiviews}.
	 * 
	 * @param line
	 *            CLI {@link CommandLine} object.
	 */
	private static void parseMultiViewOption(CommandLine line) {
		multiviews = line.hasOption("m");
	}

	/**
	 * Builds the view (-v or --view) CLI option.
	 * 
	 * <p>
	 * Construye la opcion CLI -v.
	 * 
	 * @return CLI {@link Option} for the view option.
	 *         <p>
	 *         Objeto {@link Option} de esta opcion.
	 */
	private static Option constructViewOption() {
		String optionInfo = "The view to use ( ";
		for (ViewInfo i : ViewInfo.values()) {
			optionInfo += i.getId() + " [for " + i.getDesc() + "] ";
		}
		optionInfo += "). By defualt, " + DEFAULT_VIEW.getId() + ".";
		Option opt = new Option("v", "view", true, optionInfo);
		opt.setArgName("view identifier");
		return opt;
	}

	/**
	 * Parses the view option (-v or --view). It sets the value of {@link #view}
	 * accordingly.
	 * 
	 * <p>
	 * Extrae la opcion view (-v) y asigna el valor de {@link #view}.
	 * 
	 * @param line
	 *            CLI {@link CommandLine} object.
	 * @throws ParseException
	 *             If an invalid value is provided (the valid values are those
	 *             of {@link ViewInfo}.
	 */
	private static void parseViewOption(CommandLine line) throws ParseException {
		String viewVal = line.getOptionValue("v", DEFAULT_VIEW.getId());
		// view type
		for (ViewInfo v : ViewInfo.values()) {
			if (viewVal.equals(v.getId())) {
				view = v;
			}
		}
		if (view == null) {
			throw new ParseException("Uknown view '" + viewVal + "'");
		}
	}

	/**
	 * Builds the players (-p or --player) CLI option.
	 * 
	 * <p>
	 * Construye la opcion CLI -p.
	 * 
	 * @return CLI {@link Option} for the list of pieces/players.
	 *         <p>
	 *         Objeto {@link Option} de esta opcion.
	 */
	private static Option constructPlayersOption() {
		String optionInfo = "A player has the form A:B (or A), where A is sequence of characters (without any whitespace) to be used for the piece identifier, and B is the player mode (";
		for (PlayerMode i : PlayerMode.values()) {
			optionInfo += i.getId() + " [for " + i.getDesc() + "] ";
		}
		optionInfo += "). If B is not given, the default mode '" + DEFAULT_PLAYERMODE.getId()
				+ "' is used. If this option is not given a default list of pieces from the corresponding game is used, each assigmed the mode '"
				+ DEFAULT_PLAYERMODE.getId() + "'.";

		Option opt = new Option("p", "players", true, optionInfo);
		opt.setArgName("list of players");
		return opt;
	}

	/**
	 * Parses the players/pieces option (-p or --players). It sets the value of
	 * {@link #pieces} and {@link #playerModes} accordingly.
	 *
	 * <p>
	 * Extrae la opcion players (-p) y asigna el valor de {@link #pieces} y
	 * {@link #playerModes}.
	 * 
	 * @param line
	 *            CLI {@link CommandLine} object.
	 * @throws ParseException
	 *             If an invalid value is provided (@see
	 *             {@link #constructPlayersOption()}).
	 *             <p>
	 *             Si se proporciona un valor invalido (@see
	 *             {@link #constructPlayersOption()}).
	 */
	private static void parsePlayersOptions(CommandLine line) throws ParseException {

		String playersVal = line.getOptionValue("p");

		if (playersVal == null) {
			// if no -p option, we take the default pieces from the
			// corresponding
			// factory, and for each one we use the default player mode.
			pieces = gameFactory.createDefaultPieces();
			playerModes = new ArrayList<PlayerMode>();
			for (int i = 0; i < pieces.size(); i++) {
				playerModes.add(DEFAULT_PLAYERMODE);
			}
		} else {
			pieces = new ArrayList<Piece>();
			playerModes = new ArrayList<PlayerMode>();
			String[] players = playersVal.split(",");
			for (String player : players) {
				String[] playerInfo = player.split(":");
				if (playerInfo.length == 1) { // only the piece name is provided
					pieces.add(new Piece(playerInfo[0]));
					playerModes.add(DEFAULT_PLAYERMODE);
				} else if (playerInfo.length == 2) { // piece name and mode are
														// provided
					pieces.add(new Piece(playerInfo[0]));
					PlayerMode selectedMode = null;
					for (PlayerMode mode : PlayerMode.values()) {
						if (mode.getId().equals(playerInfo[1])) {
							selectedMode = mode;
						}
					}
					if (selectedMode != null) {
						playerModes.add(selectedMode);
					} else {
						throw new ParseException("Invalid player mode in '" + player + "'");
					}
				} else {
					throw new ParseException("Invalid player information '" + player + "'");
				}
			}
		}
	}

	/**
	 * Builds the game (-g or --game) CLI option.
	 * 
	 * <p>
	 * Construye la opcion CLI -g.
	 * 
	 * @return CLI {@link {@link Option} for the game option.
	 *         <p>
	 *         Objeto {@link Option} de esta opcion.
	 */

	private static Option constructGameOption() {
		String optionInfo = "The game to play ( ";
		for (GameInfo i : GameInfo.values()) {
			optionInfo += i.getId() + " [for " + i.getDesc() + "] ";
		}
		optionInfo += "). By defualt, " + DEFAULT_GAME.getId() + ".";
		Option opt = new Option("g", "game", true, optionInfo);
		opt.setArgName("game identifier");
		return opt;
	}

	/**
	 * Parses the game option (-g or --game). It sets the value of
	 * {@link #gameFactory} accordingly. Usually it requires that
	 * {@link #parseDimOptionn(CommandLine)} has been called already to parse
	 * the dimension option.
	 * 
	 * <p>
	 * Extrae la opcion de juego (-g). Asigna el valor del atributo
	 * {@link #gameFactory}. Normalmente necesita que se haya llamado antes a
	 * {@link #parseDimOptionn(CommandLine)} para extraer la dimension del
	 * tablero.
	 * 
	 * @param line
	 *            CLI {@link CommandLine} object.
	 * @throws ParseException
	 *             If an invalid value is provided (the valid values are those
	 *             of {@link GameInfo}).
	 *             <p>
	 *             Si se proporciona un valor invalido (Los valores validos son
	 *             los de {@link GameInfo}).
	 */
	/**
	 * @param line
	 * @throws ParseException
	 */
	private static void parseGameOption(CommandLine line) throws ParseException {
		String gameVal = line.getOptionValue("g", DEFAULT_GAME.getId());
		GameInfo selectedGame = null;

		for (GameInfo g : GameInfo.values()) {
			if (g.getId().equals(gameVal)) {
				selectedGame = g;
				break;
			}
		}

		if (selectedGame == null) {
			throw new ParseException("Uknown game '" + gameVal + "'");
		}

		switch (selectedGame) {
		case FoxHounds:
			gameFactory = new FoxHoundsFactoryExt();
			break;
		case AdvancedTicTacToe:
			gameFactory = new AdvancedTTTFactoryExt();
			break;
		case CONNECTN:
			if (dimRows != null && dimCols != null && dimRows == dimCols) {
				gameFactory = new ConnectNFactoryExt(dimRows);
			} else {
				gameFactory = new ConnectNFactoryExt();
			}
			break;
		case TicTacToe:
			gameFactory = new TicTacToeFactoryExt();
			break;
		case ATAXX:
			if (dimRows != null && dimCols != null && dimRows == dimCols) {
				if (numAtaxxObstacles == null) {
					gameFactory = new AtaxxFactoryExt(dimRows, dimCols);
				} else {
					gameFactory = new AtaxxFactoryExt(dimRows, dimCols, numAtaxxObstacles);
				}
			} else {
				if (numAtaxxObstacles == null) {
					gameFactory = new AtaxxFactoryExt();
				} else {
					gameFactory = new AtaxxFactoryExt(numAtaxxObstacles);
				}
			}
			break;
		default:
			throw new UnsupportedOperationException("Something went wrong! This program point should be unreachable!");
		}

	}

	/**
	 * Builds the dimension (-d or --dim) CLI option.
	 * 
	 * <p>
	 * Construye la opcion CLI -d.
	 * 
	 * @return CLI {@link {@link Option} for the dimension.
	 *         <p>
	 *         Objeto {@link Option} de esta opcion.
	 */
	private static Option constructDimensionOption() {
		return new Option("d", "dim", true,
				"The board size (if allowed by the selected game). It must has the form ROWSxCOLS.");
	}

	/**
	 * Parses the dimension option (-d or --dim). It sets the value of
	 * {@link #dimRows} and {@link #dimCols} accordingly. The dimension is
	 * ROWSxCOLS.
	 * 
	 * <p>
	 * Extrae la opcion dimension (-d). Asigna el valor de los atributos
	 * {@link #dimRows} and {@link #dimCols}. La dimension es de la forma
	 * ROWSxCOLS.
	 * 
	 * @param line
	 *            CLI {@link CommandLine} object.
	 * @throws ParseException
	 *             If an invalid value is provided.
	 *             <p>
	 *             Si se proporciona un valor invalido.
	 */
	private static void parseDimOptionn(CommandLine line) throws ParseException {
		String dimVal = line.getOptionValue("d");
		if (dimVal != null) {
			try {
				String[] dim = dimVal.split("x");
				if (dim.length == 2) {
					dimRows = Integer.parseInt(dim[0]);
					dimCols = Integer.parseInt(dim[1]);
				} else {
					throw new ParseException("Invalid dimension: " + dimVal);
				}
			} catch (NumberFormatException e) {
				throw new ParseException("Invalid dimension: " + dimVal);
			}
		}

	}

	/**
	 * Builds the help (-h or --help) CLI option.
	 * 
	 * <p>
	 * Construye la opcion CLI -h.
	 * 
	 * @return CLI {@link {@link Option} for the help option.
	 *         <p>
	 *         Objeto {@link Option} de esta opcion.
	 */

	private static Option constructHelpOption() {
		return new Option("h", "help", false, "Print this message");
	}

	/**
	 * Parses the help option (-h or --help). It print the usage information on
	 * the standard output.
	 * 
	 * <p>
	 * Extrae la opcion help (-h) que imprime informacion de uso del programa en
	 * la salida estandar.
	 * 
	 * @param line
	 *            * CLI {@link CommandLine} object.
	 * @param cmdLineOptions
	 *            CLI {@link Options} object to print the usage information.
	 * 
	 */
	private static void parseHelpOption(CommandLine line, Options cmdLineOptions) {
		if (line.hasOption("h")) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp(Main.class.getCanonicalName(), cmdLineOptions, true);
			System.exit(0);
		}
	}

	/**
	 * Starts a game using a {@link ConsoleCtrl} which is not based on MVC. Is
	 * used only for teaching the difference from the MVC one.
	 * 
	 * <p>
	 * Método para iniciar un juego con el controlador {@link ConsoleCtrl}, no
	 * basado en MVC. Solo se utiliza para mostrar las diferencias con el
	 * controlador MVC.
	 * 
	 */
	public static void startGameNoMVC() {
		Game g = new Game(gameFactory.gameRules());
		Controller c = null;

		switch (view) {
		case CONSOLE:
			ArrayList<Player> players = new ArrayList<Player>();
			for (int i = 0; i < pieces.size(); i++) {
				switch (playerModes.get(i)) {
				case AI:
					players.add(gameFactory.createAIPlayer(aiPlayerAlg));
					break;
				case MANUAL:
					players.add(gameFactory.createConsolePlayer());
					break;
				case RANDOM:
					players.add(gameFactory.createRandomPlayer());
					break;
				default:
					throw new UnsupportedOperationException(
							"Something went wrong! This program point should be unreachable!");
				}
			}
			c = new ConsoleCtrl(g, pieces, players, new Scanner(System.in));
			break;
		case WINDOW:
			throw new UnsupportedOperationException(
					"Swing Views are not supported in startGameNoMVC!! Please use startGameMVC instead.");
		default:
			throw new UnsupportedOperationException("Something went wrong! This program point should be unreachable!");
		}

		c.start();
	}

	/**
	 * Starts a game. Should be called after {@link #parseArgs(String[])} so
	 * some fields are set to their appropriate values.
	 * 
	 * <p>
	 * Inicia un juego. Debe llamarse despues de {@link #parseArgs(String[])}
	 * para que los atributos tengan los valores correctos.
	 * 
	 */
	public static void startGame() {
		Game g = new Game(gameFactory.gameRules());
		Controller c = null;

		switch (view) {
		case CONSOLE:
			ArrayList<Player> players = new ArrayList<Player>();
			for (int i = 0; i < pieces.size(); i++) {
				switch (playerModes.get(i)) {
				case AI:
					players.add(gameFactory.createAIPlayer(aiPlayerAlg));
					break;
				case MANUAL:
					players.add(gameFactory.createConsolePlayer());
					break;
				case RANDOM:
					players.add(gameFactory.createRandomPlayer());
					break;
				default:
					throw new UnsupportedOperationException(
							"Something went wrong! This program point should be unreachable!");
				}
			}
			c = new ConsoleCtrlMVC(g, pieces, players, new Scanner(System.in));
			gameFactory.createConsoleView(g, c);
			break;
		case WINDOW:
			c = new Controller(g, pieces);
			if (multiviews) {
				for (Piece p : pieces) {
					gameFactory.createSwingView(g, c, p, gameFactory.createRandomPlayer(),
							gameFactory.createAIPlayer(aiPlayerAlg));
				}
			} else {
				gameFactory.createSwingView(g, c, null, gameFactory.createRandomPlayer(),
						gameFactory.createAIPlayer(aiPlayerAlg));
			}
			break;
		default:
			throw new UnsupportedOperationException("Something went wrong! This program point should be unreachable!");
		}

		try {
			c.start();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}

	private static void startServer() {
		// Usamos el valor de la opcion '--server-port':
		GameServer c = new GameServer(gameFactory, pieces, serverPort);
		c.start();
	}	
	
	private static void startClient() {
		try {
			// Usamos los valores de '--server-host' y '--server-port':
			GameClient c = new GameClient(serverHost, serverPort);
			
			// Usamos el 'GameFactory' y la ficha 'Piece' enviados por el Servidor
     		// al Cliente para crear la Vista:
			gameFactory = c.getGameFactoty();
			gameFactory.createSwingView(c, c, c.getPlayerPiece(), gameFactory.createRandomPlayer(),
					gameFactory.createAIPlayer(aiPlayerAlg));
			// La Vista ve 'c' como un "Controller" y como un "modelo".
			c.start();
		} catch (Exception e) {
			System.err.println("Error occured while connecting to server: " + e.getMessage());
		}
	}

	/**
	 * The main method. It calls {@link #parseArgs(String[])} and then
	 * {@link #startGame()}.
	 * 
	 * <p>
	 * Metodo main. Llama a {@link #parseArgs(String[])} y a continuacion inicia
	 * un juego con {@link #startGame()}.
	 * 
	 * @param args
	 *            Command-line arguments.
	 * 
	 */
	public static void main(String[] args) {
		
		
		// Se tiene que a�adir soporte para analizar las nuevas opciones
		// de la 'linea de comandos':
		
	parseArgs(args);
		
		// Dependiendo del valor '--application-mode', invocamos a "startGame"
		// para usar el modo 'normal' de la Practica 5, a "startClient" para
	    // usar el modo 'cliente', o a "startServer" para usar el modo 'servidor':
		switch (applicationMode) {
		case NORMAL:
			startGame();
			break;
		case CLIENT:
			startClient();
			break;
		case SERVER:
			startServer();
			break;
		default:
			throw new UnsupportedOperationException("Somwthing went wrong. This program point should be unrechable");
		}
	}

}

