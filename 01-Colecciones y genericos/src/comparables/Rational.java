// Ejemplo 3
package comparables;

public class Rational implements Comparable<Rational> {
	  private int num, den;
	  public Rational(int n, int d){ num = n; den = d; }

	  public boolean less(Rational r) {
	    return (((double)this.num)/this.den < ((double)r.num) / r.den);
	  } 

	  public boolean equal(Rational f) {
	      return super.equals(f);
	  }

	    public String toString(){
		return String.valueOf(num) + "/" + String.valueOf(den);
	    }
	}
