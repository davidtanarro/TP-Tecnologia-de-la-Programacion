// Ejemplo 3
package comparables;

public class Fecha implements Comparable<Fecha>{
	  private int anno, mes, dia;
	  
	  public Fecha(int d, int m, int a) { dia = d; mes = m; anno = a; }

	  public boolean less(Fecha f) {
	    return (this.anno < f.anno || (this.anno == f.anno && 
	             (this.mes < f.mes || (this.mes == f.mes && this.dia < f.dia))));
	  } 

	  public boolean equal(Fecha f) {
	      return super.equals(f);
	  }

	    public String toString() {
		return this.dia + "/" + this.mes + "/" + this.anno;
	    }
	}

