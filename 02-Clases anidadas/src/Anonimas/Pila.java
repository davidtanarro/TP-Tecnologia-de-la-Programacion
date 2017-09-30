// Ejemplo 3
package Anonimas;

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
		
		return new Iterator<T>() {
			
			int curr = 0; // posici—n de recorrido del iterador.

			public boolean hasNext() {
				return curr <= last;
			}

			public T next() {
				curr++;
				return (T)elem[curr-1];
			}

			public void remove() {}
			
	    };

    }

}