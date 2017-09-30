//Ejemplo 5
package interfazMap;

import java.util.*;


public class PruebaMapa {

	public static void main(String[] args) {
		
		Map<String, Persona> grupo = new HashMap<String, Persona>();
		
		Persona p1 = new Persona("232323245M", "pepe", 37);
		Persona p2 = new Persona("525252545W", "luis", 31);
		
		grupo.put(p1.getDni(),p1);
		grupo.put(p2.getDni(),p2);
		
		//Imprimimos todas las entradas
		System.out.println("Imprimimos todas las entradas:");
		System.out.println(grupo);
		
		//Bœsqueda de un valor
		System.out.println("Imprimimos una entrada en concreto:");
		System.out.println(grupo.get("525252545W"));
		
		for (Map.Entry<String, Persona> entrada: grupo.entrySet()){
			
			String clave = entrada.getKey();
			Persona valor = entrada.getValue();
			System.out.println("clave = "+ clave + ", valor = " + valor);
			
		}
		
	}	

}
