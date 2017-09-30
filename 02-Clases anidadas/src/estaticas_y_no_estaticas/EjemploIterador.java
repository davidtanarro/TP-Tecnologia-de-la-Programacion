// Ejemplo 1
package estaticas_y_no_estaticas;

// transp. 28.  Iteradores como clases internas.

import java.util.*;

class Stack<T> implements Iterable<T> {
	private static final int initSize = 10;
	private Object[] elem;
	private int last;

	Stack() {
		elem = new Object[initSize];
		last = -1;
	}
	T pop() {
		if ( last >= 0) return (T)elem[last--];
		else return null;
	}
	void push(T x) {
		//    if ( last == elem.length-1 ) resize();
		elem[++last]=x;
	}

	public Iterator<T> iterator() {
		return new Iter();
	}

	// Clase interna Iter, definida dentro de Stack.
	private class Iter implements Iterator<T> {
		int curr = 0; // posición de recorrido del iterador.

		@Override
		public boolean hasNext() {
			return curr <= last;
		}

		@Override
		public T next() {
			curr++;
			return (T)elem[curr-1];
		}

		@Override
		public void remove() {}
	} }



public class EjemploIterador {
	public static void main(String[] arg) {
		Stack<String> s = new Stack<String>();
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
