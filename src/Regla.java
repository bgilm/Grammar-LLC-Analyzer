import java.util.ArrayList;
/***
 * En esta clase se recibe el símbolo no terminal y las producciones de una 
 * regla de producción y genera el objeto regla
 * @author Carla Lorena Flores Subias, Benjamín Gil Mendoza
 * @version 1.0
 * @since 2016-03-25
 */

public class Regla {
	private String simbolo;
	
	private ArrayList<String> producciones;
	/**
	 * Constructor de la clase Regla
	 * @param simbolo no terminal que genera la producción
	 */
	public Regla(String simbolo) {
		this.simbolo = simbolo;
		
		producciones=new ArrayList<String>();
	}
	/**
	 * Regresa el símbolo de la producción
	 *
	 * @return símbolo no terminal que genera la producción
	 */
	public String getSimbolo() {
		return simbolo;
	}
	/**
	 * Establece el valor del símbolo no terminal que genera la producción
	 * @param simbolo símbolo no terminal que genera la producción
	 */
	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}
	/**
	 * Regresa las producciones del símbolo no terminal de la producción
	 *
	 * @return Arreglo con las producciones
	 */
	public ArrayList<String> getProducciones() {
		return producciones;
	}
	/**
	 * Agrega una producción
	 *
	 * @param Regla de producción
	 */
	public void agregarProduccion(String s){
		producciones.add(s);
	}
	@Override
	/**
	 * Devuelve la representación en String del objeto Regla
	 * @return Representación en String de la regla de producción.
	 */
	public String toString() {
		String stringProducciones="";
		for(int i=0;i<producciones.size();i++){
			if(i!=producciones.size()-1){
				stringProducciones=stringProducciones+producciones.get(i)+"|";
			}else{
				stringProducciones=stringProducciones+producciones.get(i);
			}
		}
		return simbolo + "->" + stringProducciones;
	}
	
}
