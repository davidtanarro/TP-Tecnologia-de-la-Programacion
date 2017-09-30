//Ejemplo 5
package interfazMap;

public class Persona {
	
    private String dni;
    private String nombre;
    private int edad;

	public Persona(String dni, String nombre, int edad) {
		this.dni = dni;
		this.nombre = nombre;
		this.edad = edad;
	}
	
	public String getDni(){
		return dni;
	}
	
	public String toString(){
		return "[nombre = " + nombre + ", Dni = " + dni + ", Edad = " + edad +"]";
	}
	
}
