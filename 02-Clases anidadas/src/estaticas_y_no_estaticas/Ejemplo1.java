// Ejemplo 1
package estaticas_y_no_estaticas;

// transp. 7. Ejemplo de acceso a clases anidadas estáticas.
//---------------------------------------------------------

class Externa {
	public void m1() {  
		Interna i = new Interna();
		i.a4 = 1;
		System.out.println("a3="+i.getA3()+" a4="+i.a4);
	}
	public static class Interna {
		private static int a3 = 5;
		private int a4 = 6;
		public static int getA3() { return a3; }
		public static void setA3(int a) { a3 = a; } 
		public int getA4() { return a4; }
	}
}

public class Ejemplo1 {
	public static void main(String[] s) {  
		Externa e = new Externa();
		Externa.Interna i2 = new Externa.Interna();
		e.m1();
		Externa.Interna.setA3(3);
		e.m1();
		System.out.println(i2.getA4());
	} 
}
