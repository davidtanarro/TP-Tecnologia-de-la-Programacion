// Ejemplo 4
package comodines;

import pilasgenericas.PilaGenerica2;


public class PilaComodines {
	
    //public static void escribePilaNumeros(PilaGenerica2<Number> p) throws Exception {
      public static void escribePilaNumeros(PilaGenerica2<? extends Number> p) throws Exception {
    	//public static void escribePilaNumeros(PilaGenerica2<?> p) throws Exception {
    	while (!p.vacia()) 
    	    System.out.println(p.pop());
        }
	
    public static void main(String[] args) throws Exception {
    	
    		PilaGenerica2<Integer> pi = new PilaGenerica2<Integer>();
    		pi.push(25);
    		pi.push(44);
    		escribePilaNumeros(pi);
    		
    }

}

