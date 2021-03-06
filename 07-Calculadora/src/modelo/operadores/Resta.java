package modelo.operadores;

import modelo.ExcNumeroNoValido;
import modelo.ParserOperacionAritmetica;

public class Resta extends OperadorBinario {
	
	public Resta() {
		super(0.0, 0.0);
	}
	
	public Resta(double op1, double op2) {
		super(op1, op2);
	}

	@Override
	public String toString() {
		return  "(" + op1 + "-" + op2 + ")";
	}

	@Override
	public double ejecuta() {
		return op1 - op2;
	}

	public OperadorAritmetico parsea(String oa) throws ExcNumeroNoValido {
		String[] elems = oa.split(" ");
		if (elems.length == 3 && elems[1].equalsIgnoreCase("-") &&
				ParserOperacionAritmetica.esNumero(elems[0]) &&
				ParserOperacionAritmetica.esNumero(elems[2]))
			return new Resta(ParserOperacionAritmetica.parseaNum(elems[0]),ParserOperacionAritmetica.parseaNum(elems[2]));
		else
			return null;
	}
}
