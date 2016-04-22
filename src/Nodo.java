public class Nodo {
	private String cadena;
	
	private Nodo anterior;
	private String produccionUsada;
	public Nodo(String cadena, Nodo anterior,String produccionUsada) {	
		this.cadena = cadena;
		this.anterior = anterior;
		this.produccionUsada=produccionUsada;
	}
	
	
	public String getCadena() {
		return cadena;
	}
	public void setCadena(String cadena) {
		this.cadena = cadena;
	}
	public Nodo getAnterior() {
		return anterior;
	}
	public void setAnterior(Nodo anterior) {
		this.anterior = anterior;
	}

	public String toString() {
		if(this.anterior==null){
			return cadena;
		}
		if(cadena.equals("")){
			return  anterior + "=>" +"λ";
		}
		return   anterior + "=>" + cadena + " produccion usada "+ produccionUsada +" ";
	}


	public String getProduccionUsada() {
		return produccionUsada;
	}


	public void setProduccionUsada(String produccionUsada) {
		this.produccionUsada = produccionUsada;
	}
}
	