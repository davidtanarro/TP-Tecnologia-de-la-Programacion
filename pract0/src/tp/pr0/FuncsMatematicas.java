package tp.pr0;

public class FuncsMatematicas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 6; ++i) {
			for (int j = 0; j <= i; ++j) {
				System.out.print(FuncsMatematicas.combinatorio(i,j) + " ");
			}
			System.out.println();
		}
	}
	public static int factorial (int n){
		int resultado;
		
		if (n < 0)
			resultado = 0;
		else if (n == 0)
			resultado = 1;
		else
			resultado = n*factorial(n-1);
		
		return resultado;
	}
	public static int combinatorio (int n, int k){
		int resultado;
		
		if (0 <= k && k <= n)
			resultado = factorial(n)/(factorial(k)*factorial(n-k));
		else
			resultado = 0;
		
		return resultado;
	}
}
