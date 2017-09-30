// Ejemplo 2
package arrayList;

import java.util.*;


public class PruebaArrayList {
	
	public static void imprimeEmpleados(ArrayList<Empleado> p) {
		
		for (Empleado e : p)
			System.out.println("nombre = " + e.getNombre() + ", sueldo = " + e.getSueldo());
		
		System.out.println("-------------------------------------");
		
	}

	public static void main(String[] args) {

		ArrayList<Empleado> personal = new ArrayList<Empleado>();
		
		personal.add(new Empleado("pepe", 25000));
		personal.add(new Empleado("leopoldo", 28000));
		personal.add(new Empleado("maria", 15000));
		
		// imprimimos informaci—n sobre los empleados
		imprimeEmpleados(personal);

		// aumentamos sueldo en un 5%
		for (Empleado e : personal)
			e.subirSueldo(5);

		// imprimimos informaci—n sobre los empleados
		imprimeEmpleados(personal);

		// consultar n¼ de elementos
		System.out.println("N¼ de elementos de personal: " + personal.size());

		// Accedemos al segundo elemento
		System.out.println("Accedemos al segundo elemento de personal: " + personal.get(1));

		// Modificamos un registro
		personal.set(0, new Empleado("luis", 12000));
		System.out.println("-------------------------------------");

		// imprimimos informaci—n sobre los empleados
		imprimeEmpleados(personal);

	}

}
