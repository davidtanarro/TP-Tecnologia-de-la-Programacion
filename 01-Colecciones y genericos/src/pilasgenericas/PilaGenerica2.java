// Ejemplo 4
package pilasgenericas;

public class PilaGenerica2<T extends Number> {
	
    private Number[] datos;
    private int numDatos;
    private static final int TAM_INI = 10, TAM_INC = 10;

    public PilaGenerica2() {
    		datos = new Number[TAM_INI];
    		numDatos = 0;
    }

    public void push(T dato) {
    		if (numDatos == datos.length) {
    			Number[] aux = new Number[datos.length+TAM_INC];
    			System.arraycopy(datos,0,aux,0,datos.length);
    			datos = aux;
    		}
    		datos[numDatos++] = dato;
    }

    @SuppressWarnings("unchecked")
	public T pop() throws Exception {
    		if (numDatos > 0) {
    			numDatos--;
    			return (T)datos[numDatos];
    		} else { throw new Exception("horror, pila vacia."); }
    }

    
    public static void main(String[] args) throws Exception {
    	
    		PilaGenerica2<Number> p1 = new PilaGenerica2<Number>();
    		
    		p1.push(44);
    		p1.push(37.45);
    		// error de compilacion.
    		// pi.push("hola");
    		
    		System.out.println(p1.pop());
    		System.out.println(p1.pop());
    		
    		PilaGenerica2<Integer> p2 = new PilaGenerica2<Integer>();
    		
    		p2.push(23);
    		System.out.println(p2.pop());
    		// error de compilacion.
        // p2.push(23.5);

    		PilaGenerica2<Double> p3 = new PilaGenerica2<Double>();
    		
    		p3.push(3.14);
    		System.out.println(p3.pop());
    		// error de compilacion.
    		// p3.push(5);
    			
    }

    // Este metodo no venia implementado por el profesor (Cuidado: revisar)
	public boolean vacia() {
		if (numDatos == 0)
			return true;
		else
			return false;
	}

}
