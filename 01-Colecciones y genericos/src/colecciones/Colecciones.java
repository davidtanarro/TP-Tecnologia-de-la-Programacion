//Ejemplo 5
package colecciones;

import java.util.*;


public class Colecciones {

    public static void main(String[] args) {
    	
    		TreeSet<Integer> set = new TreeSet<Integer>();
    		
    		set.add(58);
    		set.add(25);
    		set.add(44);
    		set.add(25);
    		
    		Colecciones.<Integer>escribeColeccion(set);
    		
    }

    public static <E> void escribeColeccion(Collection<E> col) {
    	
    		Iterator<E> it = col.iterator();
    		while (it.hasNext()) {
    			E elem = it.next();
    			System.out.println(elem);
    		} 
    		
    }
    
}