// Ejemplo 2
package Locales;

import java.util.Iterator;


class Pila<T> implements Iterable<T> {
	
	private static final int initSize = 10;
	private Object[] elem;
	private int last;

	Pila() {
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
		
		// Clase interna Iter, definida dentro del metodo.
		class Iter implements Iterator<T> {
			
			int curr = 0; // posici—n de recorrido del iterador.

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
			
		} 
		
		return new Iter();
		
	}

}
