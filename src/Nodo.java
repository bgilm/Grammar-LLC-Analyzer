
public class Nodo {
	private String cadena;
	
	private Nodo anterior;
	public Nodo(String cadena, Nodo anterior) {	
		this.cadena = cadena;
		this.anterior = anterior;
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
		return "Nodo [cadena=" + cadena + ", anterior=" + anterior + "]";
	}
	
}
