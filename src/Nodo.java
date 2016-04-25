/***
 * En esta clase se almacenan los resultados de cada una de las movidas realizadas
 * con la movida que la predece y la producción usada para llegar a dicho resultado 
 * @author  Benjamín Gil Mendoza
 * @author 	Carla Lorena Flores Subias
 * @version 1.0
 * @since 2016-03-25
 */

/**
 * @author Carly
 *
 */
public class Nodo {
	private String cadena;
	private Nodo anterior;
	private String produccionUsada;
	/**
	 * Es el constructor de la clase Nodo
	 * @param cadena almacena la cadena resultado de la operación con símbolos 
	 * terminales y no terminales
	 * @param anterior registra el nodo con el estado anterior de la cadena
	 * @param produccionUsada almacena la regla de producción que dió como
	 * resultado la cadena
	 */
	public Nodo(String cadena, Nodo anterior,String produccionUsada) {	
		this.cadena = cadena;
		this.anterior = anterior;
		this.produccionUsada=produccionUsada;
	}
	/**
	 * @return la cadena resultado
	 */
	public String getCadena() {
		return cadena;
	}
	/**
	 * Establece el valor de la cadena resultado
	 * @param cadena valor de la cadena
	 */
	public void setCadena(String cadena) {
		this.cadena = cadena;
	}
	/**
	 * Regresa el nodo anterior 
	 * @return nodo anterior
	 */
	public Nodo getAnterior() {
		return anterior;
	}
	/**
	 * Establece el valor del nodo anterior
	 * @param anterior nodo anterior que produjo la cadena
	 */
	public void setAnterior(Nodo anterior) {
		this.anterior = anterior;
	}

	/**
	 * Devuelve la produccion utilizada para generar la cadena
	 * resultado
	 * @return produccion utilixada
	 */
	public String getProduccionUsada() {
		return produccionUsada;
	}

	/**
	 * Establece el valor de la produccón utilizada
	 * @param produccionUsada la producción que se utilizó para generar la cadena
	 */
	public void setProduccionUsada(String produccionUsada) {
		this.produccionUsada = produccionUsada;
	}
}
	