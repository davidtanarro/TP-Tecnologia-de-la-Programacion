// Ejemplo 2
package arrayList;

public class Empleado {
	
	private double sueldo;
	private String nombre;

	public Empleado(String n, double s) {
		nombre = n;
		sueldo = s;
	}

	public void subirSueldo(double porcentaje) {
		double aumento = sueldo * porcentaje / 100;
		sueldo += aumento;
	}

	public String toString() {
		return "Empleado [ nombre = " + nombre + " sueldo = " + sueldo + " ]";
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public double getSueldo(){
		return sueldo;
	}
	
}
