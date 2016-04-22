import java.util.ArrayList;


public class Regla {
	private String simbolo;
	
	private ArrayList<String> producciones;
	
	public Regla(String simbolo) {
		this.simbolo = simbolo;
		
		producciones=new ArrayList<String>();
	}
	public String getSimbolo() {
		return simbolo;
	}
	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}
	public ArrayList<String> getProducciones() {
		return producciones;
	}
	public void agregarProduccion(String s){
		producciones.add(s);
	}
	@Override
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
