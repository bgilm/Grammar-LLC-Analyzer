import java.util.*;
/***
 * En esta clase se realiza el procesamiento de la cadena y, en caso de ser aceptada, se envian
 * los movimientos realizados por el autómata de pila a la clase Principal para ser impresos en 
 * consola.
 * @author  Benjamín Gil Mendoza
 * @author 	Carla Lorena Flores Subias
 * @version 1.0
 * @since 2016-03-25
 */
public class Procesamiento {
	private Gramatica gramatica;
	private String cadena;
	private Queue<Nodo> cola= new LinkedList<Nodo>();

	/**
	 * Este es el constructor de la clase Procesamiento
	 * @param gramatica representa la gramática sobre la
	 * cual se analizará la cadena
	 * @param cadena representa la cadena a analizar.
	 */
	public Procesamiento(Gramatica gramatica, String cadena) {
		this.gramatica = gramatica;
		this.cadena = cadena;
	}
	/**
	 * En este método se define si la cadena es aceptada por la gramática.
	 * Mientras la variable flag sea verdadero, se realiza la comparación de un objeto 
	 * de la clase nodo para saber si corresponde con la cadena a analizar. Primero se
	 * compara si los símbolos terminales actuales corresponden con la cadena a analizar
	 * hasta la longitud actual de la misma. Si la longitud es igual a la cadena, la cadena 
	 * coincide con los símbolos terminales y ya no hay símbolos terminales, la cadena se 
	 * envia a una pila representada por el método autómata de pila para su procesamiento.
	 *  Si la cadena coincide pero no posee la misma longitud se procesan los resultados de 
	 *  aplicar las reglas de producción al símbolo más izquierda de la cadena. Si los 
	 *  símbolos terminales no coinciden se termina el proceso.
	 * @return
	 */
	public String aceptaCadena(){
		String aceptado="Cadena no aceptada";
		boolean flag=true;

		ArrayList<Regla> reglas=gramatica.getReglas();
		cola.add(new Nodo(gramatica.getSimboloInicial(),null,gramatica.getSimboloInicial()));
		while(flag){
			Nodo n=cola.remove();
			String res=n.getCadena();
			if(res.equals(cadena)){
				aceptado="La cadena es aceptada,corrida:\n"+automataPila(n);
				flag=false;
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
	/**
	 * En este método se procesa un nodo cuyas símbolos coinciden
	 * con la cadena a analizar y se realizan las movidas de la pila 
	 * para verificar si la cadena es aceptada. 
	 * @param n Nodo cuyo símbolos coinciden
	 * @return String con las movidas que la pila realizó para aceptar
	 * la cadena
	 */
	public String automataPila(Nodo n){
		String imprime="";
		String cadenaAux=cadena;
		if(cadenaAux.equals("")){
			cadenaAux="λ";
		}
		Stack<String> pila = new Stack<String>();
		Stack<String> resultado = new Stack<String>();

		Nodo aux=n;
		while(aux!=null){
			pila.push(aux.getProduccionUsada());
			aux=aux.getAnterior();
		}
		pila.push("Z");
		resultado.push(pila.pop());
		imprime=imprime+"<q0,"+cadenaAux+","+imprimirResultado(resultado)+">"+"\n";
		resultado.push(pila.pop());
		System.out.println("<q1,"+cadenaAux+","+imprimirResultado(resultado)+">");
		while(!resultado.peek().equals("Z")){
			if(gramatica.getAlfabeto().contains(resultado.peek())){
				resultado.pop();
				cadenaAux=cadenaAux.substring(1, cadenaAux.length());
				if(cadenaAux.equals("")){
					cadenaAux="λ";
				}
				imprime=imprime+"<q1,"+cadenaAux+","+imprimirResultado(resultado)+">"+"\n";
			}else{
				resultado.pop();
				if(!pila.isEmpty()){
					ArrayList<String>reversed=reverse(pila.pop().split(""));
					resultado.addAll(reversed);
					imprime=imprime+"<q1,"+cadenaAux+","+imprimirResultado(resultado)+">"+"\n";
				}	
			}
		}
		imprime=imprime+"<q2,"+"λ,"+imprimirResultado(resultado)+">"+"\n";
		return imprime;
	}
	/**
	 * En este método se voltea y separa el contenido de las 
	 * cadenas procesadas dentro de la pila para realizar las
	 * movidas del autómata
	 * @param arr Contenido de la regla de correspondencia al
	 * tope de la pila
	 * @return tope de la pila ordenado para su procesamiento
	 */
	public ArrayList<String> reverse (String[]arr){
		ArrayList<String>res= new ArrayList<String>();
		for(int i=arr.length-1;i>-1;i--){
			res.add(arr[i]);
		}
		return res;
	}
	/**
	 * Imprime la representación en string del estado de la pila después
	 * de cada movida del autómata
	 * @param r Pila actual
	 * @return Representación en string de la pila
	 */
	public String imprimirResultado(Stack<String> r){
		String s="";
		for(String st:r){
			s=st+s;
		}
		return s;
	}

}
