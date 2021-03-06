// Ejemplo 3
package comparables;

public class SelectionAlgorithmEstatico {
	
    public static <T extends Comparable<T>> void sortArray(T[] v) {
    		T minx;
    		int minj;
    		for (int i = 0; i < v.length-1; i++) {
    			minj = i;
    			minx = v[i];
    			for (int j = i+1; j < v.length; j++) {
    				if (v[j].less(minx)) {
    					minj = j;
    					minx = v[j];
    				}
    			}
    			v[minj] = v[i];
    			v[i] = minx;
    		}
    }

    public static <T extends Comparable<T>> void escribeArray(T[] arr) {
	for (int i = 0; i < arr.length; i++)
	    System.out.print(arr[i] + "  ");
	System.out.println();
    }

    public static void main(String[] args) {

	Rational[] arr = {
	    new Rational(3,4),
	    new Rational(1,2),
	    new Rational(1,7),
	    new Rational(1,5)
	};
	
	Fecha[] fec = {
	    new Fecha(25,12,2015),
	    new Fecha(17,11,2015),
	    new Fecha(13,12,2015)
	};

	System.out.println("-- ordenando racionales --");
	SelectionAlgorithmEstatico.<Rational>escribeArray(arr);
	SelectionAlgorithmEstatico.<Rational>sortArray(arr);
	SelectionAlgorithmEstatico.<Rational>escribeArray(arr);
	
	System.out.println("-- ordenando fechas --");
	SelectionAlgorithmEstatico.<Fecha>escribeArray(fec);
	SelectionAlgorithmEstatico.<Fecha>sortArray(fec);
	SelectionAlgorithmEstatico.<Fecha>escribeArray(fec);
	
    }
    
}
