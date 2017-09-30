// Ejemplo 2
package Locales;

import java.util.Iterator;


public class EjemploIterator {
	
	public static void main(String[] arg) {
		
		Pila<String> s = new Pila<String>();
		s.push("uno");
		s.push("dos");
		s.push("tres");
		s.push("cuatro");

		System.out.println("Accediendo al iterador: ");
		Iterator<String> it = s.iterator();
		while (it.hasNext()) {
			System.out.print(it.next() + " ");
		}
		System.out.println();
		System.out.println("Sacando los elementos de la pila: ");
		String val;
		while ( (val = s.pop()) != null) {
			System.out.print(val + " ");
		}
		
	}
	
}