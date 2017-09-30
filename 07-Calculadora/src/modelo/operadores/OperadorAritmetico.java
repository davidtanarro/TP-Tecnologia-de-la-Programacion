package modelo.operadores;

import modelo.ExcNumeroNoValido;

public interface OperadorAritmetico  {
	
	public abstract OperadorAritmetico parsea(String oa) throws ExcNumeroNoValido;
	public abstract double ejecuta();
}
