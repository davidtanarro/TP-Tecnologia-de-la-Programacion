// Ejemplo 1
package estaticas_y_no_estaticas;

// transp.19. Ejemplo de acceso a miembros de
// la clase interna desde inner classes.
//--------------------------------------------
class OuterClass {
	private int f = 1;

	public class InnerClass1 {
		private int f = 2;
		public class InnerClass2 {
			private int f = 3;
			public void test() {
				System.out.println(this.f + " " + InnerClass1.this.f);
			}
		}
		public void test() {
			System.out.println(this.f + " " + OuterClass.this.f);
		}
	}
}

public class Ejemplo3 {
	public static void main(String[] s) {
		OuterClass x = new OuterClass();
		OuterClass.InnerClass1 a = x.new InnerClass1();
		OuterClass.InnerClass1.InnerClass2 b = a.new InnerClass2();
		a.test();
		b.test();
	}
}
