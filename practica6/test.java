package es.ucm.fdi.tp.practica6;


import es.ucm.fdi.tp.basecode.bgame.Utils;


/*
                   UN MAIN AUXILIAR: LA CLASE TEST
             
     Para simplificar la forma de ejecutar la aplicacion, en lugar de
     cambiar los parametros de la 'linea de comandos' cada vez, podemos
     crear clases auxiliares para llamar a "Main.main".
          
 */


public class test {
	
	public static void main(String[] args) {
		
		test0();
		
//		test1();
//		test2();
		
//		test1();
//		test3();
//		test3();
	
	}
	
	
	public static void test0() {
		
		String[] a = { "-am", "server", "-g", "ataxx"};
		String[] b = { "-am", "client" };
		String[] c = { "-am", "client" };

		launch(a);
		Utils.sleep(1000);
		
		launch(b);
		Utils.sleep(1000);
		
		launch(c);
		
	}
	
	
	public static void test1() {
		
		String[] a = { "-am", "server", "-g", "cn", "-d", "7x7", "-o", "3" };
		
		launch(a);
		
	}

	
	public static void test2() {
		
		String[] b = { "-am", "client", "-aialg", "minmax", "-md", "5" };
		String[] c = { "-am", "client", "-aialg", "minmax", "-md", "5" };

		launch(b);
		Utils.sleep(80000);
		
		launch(c);
		
	}
	
	
	public static void test3() {
		
		String[] b = { "-am", "client", "-aialg", "minmax", "-md", "5" };

		launch(b);
		
	}
	

	public static void launch(final String[] args) {
		
		new Thread() {
			
			@Override
			public void run() {
				Main.main(args);
			}
			
		}.start();
		
	}
	
}
