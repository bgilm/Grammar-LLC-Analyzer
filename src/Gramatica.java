import java.util.ArrayList;


public class Gramatica {
	private ArrayList<String> alfabeto;
	private ArrayList<Regla> reglas;
	private String simboloInicial;
	public Gramatica(ArrayList<String> alfabeto, ArrayList<Regla> reglas,String simboloInicial) {
		this.alfabeto = alfabeto;
		this.reglas = reglas;
		this.simboloInicial=simboloInicial;
	}
	public ArrayList<String> getAlfabeto() {
		return alfabeto;
	}
	public void setAlfabeto(ArrayList<String> alfabeto) {
		this.alfabeto = alfabeto;
	}
	public ArrayList<Regla> getReglas() {
		return reglas;
	}
	public void setReglas(ArrayList<Regla> reglas) {
		this.reglas = reglas;
	}
	public String getSimboloInicial() {
		return simboloInicial;
	}
	public void setSimboloInicial(String simboloInicial) {
		this.simboloInicial = simboloInicial;
	}
	@Override
	public String toString() {
		return "Gramatica [alfabeto=" + alfabeto + ", reglas=" + reglas
				+ ", simboloInicial=" + simboloInicial + "]";
	}
	
	
	
	
}
