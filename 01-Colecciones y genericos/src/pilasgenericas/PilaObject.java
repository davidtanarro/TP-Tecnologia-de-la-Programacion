// Ejemplo 1
package pilasgenericas;


public class PilaObject {
	
	  private Object[] datos;
	  private int numDatos;
	  private static final int TAM_INI = 10, TAM_INC = 10;

	  public PilaObject() {
	    datos = new Object[TAM_INI];
	    numDatos = 0;
	  }

	  public void push(Object dato) {
	    if (numDatos == datos.length) {
	      Object[] aux = new Object[datos.length + TAM_INC];
	      System.arraycopy(datos,0,aux,0,datos.length);
	      datos = aux;
	    }
	    datos[numDatos++] = dato;
	  }

	  public Object pop() throws Exception {
	    if (numDatos > 0) {
	      numDatos--;
	      return datos[numDatos];
	    } else { throw new Exception("horror"); }
	  }

	  
	  public static void main(String[] args) throws Exception {
	    	
	    		PilaObject p = new PilaObject();
	    		p.push(new String("Hola!"));
	    		p.push(new String("Que tal?"));
	    		// ClassCastException
	    		// p.push(new Integer(37));

	    		String o = (String)p.pop(); 
	    		System.out.println(o);
	    		o = (String)p.pop();
	    		System.out.println(o);
	    		
	    		p.push(new Integer(44));
	    		Integer n = (Integer)p.pop(); 
	    		System.out.println(n.intValue());
	    		
	    		p.push(12);
	    		System.out.println(p.pop());
	    		p.push(3.14);
	    		System.out.println(p.pop());
	    		p.push("cadena");
	    		System.out.println(p.pop());
	    		
	  }

}
