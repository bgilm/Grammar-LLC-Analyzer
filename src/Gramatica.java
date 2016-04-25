import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
/***
 * En esta clase se reciben los componentes de una gramática y se crea el objeto gramática.
 * @author  Benjamín Gil Mendoza
 * @author 	Carla Lorena Flores Subias
 * @version 1.0
 * @since 2016-03-25
 */
public class Gramatica {
	private ArrayList<String> alfabeto;
	private ArrayList<Regla> reglas;
	private String simboloInicial;
	/**
	 * Constructor de la clase gramática  
	 * @param alfabeto alfabeto de la gramática
	 * @param reglas conjunto de producciones de la gramática
	 * @param simboloInicial símbolo inicial de la gramática
	 */
	public Gramatica(ArrayList<String> alfabeto, ArrayList<Regla> reglas,String simboloInicial) {
		this.alfabeto = alfabeto;
		this.reglas = reglas;
		this.simboloInicial=simboloInicial;
	}
	/**
	 * Regresa el alfabeto
	 *
	 * @return alfabeto
	 */
	public ArrayList<String> getAlfabeto() {
		return alfabeto;
	}
	/**
	 * Regresa las reglas de correspondencia
	 *
	 * @return Arraylist con las producciones
	 */
	public ArrayList<Regla> getReglas() {
		return reglas;
	}
	/**
	 * Establece el valor del arreglo de producciones
	 *
	 * @param Arraylist con las producciones
	 */
	public void setReglas(ArrayList<Regla> reglas) {
		this.reglas = reglas;
	}
	/**
	 * Regresa el símbolo inicial
	 *
	 * @return símbolo inicial
	 */
	public String getSimboloInicial() {
		return simboloInicial;
	}
	/**
	 *Establece el valor del símbolo inicial
	 *
	 * @param simboloInicial nuevo valor del símbolo inicial
	 */
	public void setSimboloInicial(String simboloInicial) {
		this.simboloInicial = simboloInicial;
	}
	@Override
	/**
	 * Devuelve la representación en String del objeto gramática
	 *
	 * @return Representación en String de la gramática
	 */
	public String toString() {
		String stringReglas="";
		for(Regla str:reglas){
			stringReglas=stringReglas+str+"\n";
			
		}
		return "Gramática\nAlfabeto: " + alfabeto + "\nReglas:\n" + stringReglas
				+ "SimboloInicial: " + simboloInicial ;
	}
	
	
	
	
}
