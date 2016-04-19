import java.util.*;
public class Procesamiento {
	private Gramatica gramatica;
	private String cadena;
	private Queue<Nodo> cola= new LinkedList<Nodo>();

	public Procesamiento(Gramatica gramatica, String cadena) {
		this.gramatica = gramatica;
		this.cadena = cadena;
	}
	public boolean aceptaCadena(){
		boolean aceptado=false;
		boolean flag=true;
		int lonca=cadena.length();
		//saca todas las reglas pq son constantes
		ArrayList<Regla> reglas=gramatica.getReglas();
		//agrega el 1 simbolo a la cola
		cola.add(new Nodo(gramatica.getSimboloInicial(),null));
		int cont=0;
		while(flag){
			//lo mata y guarda en n
			Nodo n=cola.remove();
			//saca el texto del nodo
			String res=n.getCadena();
			if(res.equals(cadena)){
				aceptado=true;
				flag=false;
				System.out.println(n);
				break;
			}
			//Busca el primer simbolo no terminal del texto
			int longitud=res.length();
			for(int i=0;i<longitud;i++){
				String antes="";
				String despues="";
				char simbolo=res.charAt(i);
				//cuando lo encuentra busca la regla correspondiente al simbolo no terminal
				if(!gramatica.getAlfabeto().contains(simbolo+"")){
					//Concatena con lo que habia antes
					if(longitud>1){
						antes=res.substring(0,i);
						despues=res.substring(i+1,longitud);
					}
					Regla aux=null;
					for(int j=0;j<reglas.size();j++){
						if(reglas.get(j).getSimbolo().equals(simbolo+"")){
							aux=reglas.get(j);
							break;
						}
					}
					//guarda las producciones del simbolo no terminal en la cola 
					ArrayList<String> producciones=aux.getProducciones();
					for(int j=0;j<producciones.size();j++){
						String cadenaRes=antes+producciones.get(j)+despues;
						if(antes.length()>cadena.length()){
							flag=false;
						}else{
							if(antes.equals(cadena.substring(0,antes.length()))){
								cola.add(new Nodo(cadenaRes,n));
							}
						}
					}
					break;
				}
			}
			if(cola.isEmpty()){
				flag=false;
			}
			cont++;
		}

		return aceptado;
	}

}
