package control;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import comandos.Comando;
import comandos.ParserComando;
import logica.CelulaCompleja;
import logica.CelulaSimple;
import logica.Mundo;
import logica.MundoComplejo;
import logica.MundoSimple;

/**
 * Esta clase contiene el intérprete de los posibles comandos que se pueden
 * ejecutar en consola.
 */
public class Controlador {
	/** El atributo in se utiliza para leer de teclado. */
	private Scanner in;
	/** Creamos un objeto privado de la clase Mundo. */
	private Mundo mundo;
	/** Atributo privado que controla la finalizacion de la simulacion en Mundo*/
	private boolean simulacionTerminada;
	/**
	 * Atributo privado que indica si estamos jugando en un mundo simple o complejo.
	 */
	private boolean tipoMundo = true; // Simple: true, Complejo: false
	
	/**
	 * La constructora del Controlador inicializa los atributos.
	 * @param in Realiza las operaciones de lectura.
	 */
	public Controlador(Scanner in) {
		this.in = in;
	}

	/**
	 * Consiste en un bucle en el que se pide un comando al usuario y se ejecuta
	 * dicho comando. Además si el comando es incorrecto se muestra un mensaje
	 * de error. El bucle termina cuando el usuario teclea el comando SALIR.
	 */
	public void realizaSimulacion() {
		// Iniciamos un mundo simple por defecto
		this.mundo = new MundoSimple(3, 4, 3);
		while (!isSimulacionTerminada()) {
			System.out.println(this.mundo.toString());
			System.out.print("Comando > ");
			String entrada = in.nextLine(); // Lee la linea entera de teclado
			String[] palabras = entrada.split(" ");
			try {
				// parseaComando devuelve un comando
				Comando comando = ParserComando.parseaComando(palabras);

				if (comando != null){
					//Condiciones para asignar valor a tipoMundo y saber en que mundo estamos jugando.
					if (palabras[0].equalsIgnoreCase("jugar") && palabras[1].equalsIgnoreCase("simple"))
						tipoMundo = true;
					else if (palabras[0].equalsIgnoreCase("jugar") && palabras[1].equalsIgnoreCase("complejo"))
						tipoMundo = false;
					comando.ejecuta(this);
					
				}
				else
					System.out.println("Instruccion incorrecta");
			}
			catch (NumberFormatException e) {
				System.out.println("Se ha introducido una cadena de carácteres en vez de dos enteros.");
			}
		}
	}
	/**
	 * Crea una célula dependiendo en que mundo estemos, si el mundo es simple crea una célula simple,
	 * si el mundo es complejo, hay que introducir si queremos que la célula sea simple o compleja.
	 * @param fil Indica la fila para crear la célula.
	 * @param col Indica la columna para crear la célula.
	 */
	public void crearCelula(int fil, int col) {
		String tipoCelula;  //String para decir si queremos que sea simple o compleja
		
		try {
			if ((this.isTipoMundo()) && (this.mundo.crearCelula(fil, col, new CelulaSimple())))
				System.out.println("Creamos una nueva celula simple en la posición: (" + fil + "," + col + ")");
			else if (!this.isTipoMundo()){
				System.out.print("De que tipo: Compleja (1) o Simple (2): ");
				tipoCelula = in.nextLine(); // Lee una linea entera de teclado.
				while(!tipoCelula.equals("1") && !tipoCelula.equals("2")){
					System.out.print("Número incorrecto. Vuelve a introducir el número, 1:simple 2:compleja: ");
					tipoCelula = in.nextLine();
				}
				if (tipoCelula.equalsIgnoreCase("1") && (this.mundo.crearCelula(fil, col, new CelulaCompleja())))
					System.out.println("Creamos una nueva celula compleja en la posición: (" + fil + "," + col + ")");
				else if ((tipoCelula.equalsIgnoreCase("2")) && (this.mundo.crearCelula(fil, col, new CelulaSimple())))
					System.out.println("Creamos una nueva celula simple en la posición: (" + fil + "," + col + ")");
				/*else if (!tipoCelula.equalsIgnoreCase("1") || !tipoCelula.equalsIgnoreCase("2"))
					System.out.println("Número incorrecto. Vuelve a introducir el comando");*/
				else if((!this.mundo.crearCelula(fil, col, new CelulaCompleja())) || (this.mundo.crearCelula(fil, col, new CelulaSimple())))
					System.out.println("Error: ya está ocupada esa posición");

			}
			else
				System.out.println("Error: ya está ocupada esa posición");
		}
		catch (ArrayIndexOutOfBoundsException ex) {
			System.out.println("Error: Esa posición no se encuentra dentro de los limites de la superficie.");
		}
	}
	
	/**
	 * 
	 * @return Devuelve si la simulacion ha terminado.
	 */
	public boolean isSimulacionTerminada() {
		return this.simulacionTerminada;
	}

	/**
	 * Modifica el parametro dependiendo si la simulacion ha terminado o no.
	 * @param simulacionTerminada Indica si la simulacion ha terminado.
	 */
	public void setSimulacionTerminada(boolean simulacionTerminada) {
		this.simulacionTerminada = simulacionTerminada;
	}

	/**
	 * Método para vaciar la superficie.
	 */
	public void vaciarSuperficie(){
		mundo.vaciarSuperficie();
	}
	/*public boolean crearCelula(int f, int c, Celula celula){
		return mundo.crearCelula(f, c, celula);
	}*/
	/**
	 * Método para eliminar una célula.
	 * @param f Contiene la fila de la celula a eliminar.
	 * @param c Contiene la columna de la celula a eliminar.
	 * @return ok Devuelve true si se ha podido elimininar la célula, en caso contrario, devuelve false.
	 */
	public boolean eliminarCelula(int f, int c){
		return mundo.eliminarCelula(f, c);
	}
	
	/**
	 * Método llamado desde el controlador, que ejecuta la orden de dar un paso.
	 */
	public void daUnPaso(){
		mundo.evoluciona();
	}
	
	/**
	 * Método llamado desde el controlador, que ejecuta la orden de inicializar la superficie.
	 */
	public void inicializaSuperficie() {
		mundo.inicializaSuperficie();
	}

	/**
	 * 
	 * @return Devuelve el número de filas que contiene el mundo.
	 */
	public int getMaxFilas() {
		return mundo.getMaxFilas();
	}

	/**
	 * 
	 * @return Devuelve el número de columnas que contiene el mundo.
	 */
	public int getMaxColumnas() {
		return mundo.getMaxColumnas();
	}

	/**
	 * 
	 * @return Devuelve el tipo de mundo en el que nos encontramos.
	 */
	public boolean isTipoMundo() {
		return tipoMundo;
	}

	/**
	 * 
	 * @param tipoMundo Indica el tipo de mundo en el que estamos.
	 */
	public void setTipoMundo(boolean tipoMundo) {
		this.tipoMundo = tipoMundo;
	}

	/**
	 * Método llamado desde el controlador, que ejecuta la orden de jugar.
	 * @param mundo2 Mundo actual.
	 */
	public void jugar(Mundo mundo2) {
		this.mundo = mundo2;
		
	}

	/**
	 * Metodo para cargar el contenido del fichero.
	 * @param nombreFichero Indica el nombre del archivo con el que vamos a trabajar.
	 */
	public void cargar(String nombreFichero) {
		File fichero = new File(nombreFichero);
		Scanner s = null;
		String linea = null;  //String para leer la primera palabra del fichero.
		Mundo mundoPrevio = mundo;
		try {
			// Leemos el contenido del fichero
			s = new Scanner(fichero);
			linea = s.nextLine(); 	// Guardamos la linea en un String 
			
			if (linea.equalsIgnoreCase("simple")) {
				this.mundo = new MundoSimple();
				mundo.leerMundo(fichero, s);
			}
			else if (linea.equalsIgnoreCase("complejo")) {
				this.mundo = new MundoComplejo();
				mundo.leerMundo(fichero, s);
			}
			else
				System.out.println("Error: Mundo desconocido");
			
		} catch (Exception ex) { 
				System.out.println("Error: No se ha cargado bien el archivo");
				mundo = mundoPrevio;
		} finally {
			// Cerramos el fichero tanto si la lectura ha sido correcta o no
			try {
				if (s != null)
					s.close();
			} catch (Exception ex2) {
				System.out.println("El fichero no se ha podido cerrar bien");
			}
		}
	}

	/**
	 * Método para guardar el contenido en un fichero.
	 * @param nombreFichero Indica el nombre del archivo donde vamos a guardar el contenido del mundo.
	 */
	public void guardar(String nombreFichero) {
		FileWriter fichero = null;
		
		try {
			fichero = new FileWriter(nombreFichero);

			// Escribimos linea a linea en el fichero
			if (isTipoMundo())
				fichero.write("simple\r\n");
			else
				fichero.write("complejo\r\n");
			
			mundo.escribirMundo(fichero);
			
			fichero.close();

		} catch (Exception ex) {
			System.out.println("Error al guardar fichero");
		}
		
		
	}
	
}
