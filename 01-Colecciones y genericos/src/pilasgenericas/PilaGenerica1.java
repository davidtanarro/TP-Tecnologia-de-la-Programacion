// Ejemplo 1
package pilasgenericas;


public class PilaGenerica1<T> {
	
	  private Object[] datos;
	  private int numDatos;
	  private static final int TAM_INI = 10, TAM_INC = 10;

	  public PilaGenerica1() {
	      datos = new Object[TAM_INI];
	      numDatos = 0;
	  }

	  public void push(T dato) {
	    if (numDatos == datos.length) {
		Object[] aux = new Object[datos.length + TAM_INC];
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
	    } else { throw new Exception("horror"); }
	  }

	  
	  public static void main(String[] args) throws Exception {
	    	
		PilaGenerica1<Number> p1 = new PilaGenerica1<Number>();
		
		p1.push(new Integer(25));
		p1.push(new Double(3.14));
		p1.push(41);
		p1.push(54.23);
		
		System.out.println(p1.pop());
		System.out.println(p1.pop());
		System.out.println(p1.pop());
		System.out.println(p1.pop());

		PilaGenerica1<String> p2 = new PilaGenerica1<String>();
		
		p2.push(new String("uno"));
		p2.push("dos");
		
		System.out.println(p2.pop());
		System.out.println(p2.pop());
		
		PilaGenerica1<Object> p3 = new PilaGenerica1<Object>();
		
		p3.push("cadena");
		p3.push(12);
		p3.push(5.34);
		
		System.out.println(p3.pop());
		System.out.println(p3.pop());
		System.out.println(p3.pop());
		
	  }

}

