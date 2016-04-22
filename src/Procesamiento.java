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

		ArrayList<Regla> reglas=gramatica.getReglas();
		cola.add(new Nodo(gramatica.getSimboloInicial(),null,gramatica.getSimboloInicial()));
		while(flag){
			Nodo n=cola.remove();
			String res=n.getCadena();
			if(res.equals(cadena)){
				aceptado=true;
				flag=false;
				System.out.println(n);

				automataPila(n);
				break;
			}

			int longitud=res.length();
			for(int i=0;i<longitud;i++){
				String antes="";
				String despues="";
				char simbolo=res.charAt(i);
				if(!gramatica.getAlfabeto().contains(simbolo+"")){
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
					ArrayList<String> producciones=aux.getProducciones();
					for(int j=0;j<producciones.size();j++){
						String cadenaRes=antes+producciones.get(j)+despues;
						if(antes.length()>cadena.length()){
							flag=false;
						}else{
							if(antes.equals(cadena.substring(0,antes.length()))){
								cola.add(new Nodo(cadenaRes,n,producciones.get(j)));


							}
						}
					}
					break;
				}
			}
			if(cola.isEmpty()){
				flag=false;
			}
		}

		return aceptado;
	}
	public void automataPila(Nodo n){
		String cadenaAux=cadena;
		Stack<String> pila = new Stack<String>();
		Stack<String> resultado = new Stack<String>();

		Nodo aux=n;
		while(aux!=null){
			pila.push(aux.getProduccionUsada());

			aux=aux.getAnterior();
		}
		pila.push("Z");
		//System.out.println(pila);

		//Set<String> mySet = new HashSet<String>(Arrays.asList(pila.pop().split("")));
		resultado.push(pila.pop());
		System.out.println("<q0,"+cadenaAux+","+resultado+">");
		resultado.push(pila.pop());
		System.out.println("<q1,"+cadenaAux+","+resultado+">");

		while(!resultado.peek().equals("Z")){
			if(gramatica.getAlfabeto().contains(resultado.peek())){
				resultado.pop();
				cadenaAux=cadenaAux.substring(1, cadenaAux.length());
				System.out.println("<q1,"+cadenaAux+","+resultado+">");

			}else{

				resultado.pop();

				ArrayList<String>reversed=reverse(pila.pop().split(""));


				//				List<String> list = new ArrayList<String>(mySet);
				//				System.out.println(list);
				//				Collections.sort(list, Collections.reverseOrder());
				//				System.out.println(list);
				resultado.addAll(reversed);

				System.out.println("<q1,"+cadenaAux+","+resultado+">");
			}
		}
		System.out.println("<q2,"+","+resultado+">");
	}
	public ArrayList<String> reverse (String[]arr){
		ArrayList<String>res= new ArrayList<String>();
		for(int i=arr.length-1;i>-1;i--){

			res.add(arr[i]);
		}

		return res;
	}

}
