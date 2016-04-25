import java.io.*;
import java.util.*;
/***
 * En esta clase se comprueba si una cadena es aceptada por una gramática.
 *@author  Benjamín Gil Mendoza
 * @author 	Carla Lorena Flores Subias
 * @version 1.0
 * @since 2016-03-25
 */
public class Principal{
	/**
	 * En el main se lee un archivo y se extraen los componentes de una gramática 
	 * para ingresarlos en un objeto de la clase Gramática. Posteriormente se recibe 
	 * la cadena a comparar y se envia a la clase procesamiento para comprobar si es 
	 * aceptada o no. Finalmente se muestra el resultado y se pregunta al usuario qué
	 * desea hacer. El proceso se repite mientras el usuario desee ingresar otra cadena
	 * o bien desee ingresar otra gramática.  
	 * @param args argumentos del método main
	 * @throws FileNotFoundException en caso de no encontrarse el archivo especificado.
	 * 			Si esto sucede, se muestra un mensaje de "archivo no encontrado" y se 
	 * 			le pide al usuario que ingrese otro nombre de archivo.
	 * @throws IOException en caso de suscitarse un error en la lectura del archivo. 
	 * @throws NumberFormatException en case de que la opción ingresada en el menú no sea un número. 
	 */
	public static void main(String[] args){
		Scanner s= new Scanner(System.in);
		boolean otraGramatica=true;        
		boolean otraCadena=true;
		boolean menu=true;
		while(otraGramatica){
			otraCadena=true;
			System.out.print("Escriba el nombre del archivo de prueba:\n");
			String nombreArchivo=s.nextLine();

			File archivo = new File(nombreArchivo);

			try {
				BufferedReader buff = new BufferedReader(new FileReader(archivo));
				String[] aux=null;
				ArrayList<Regla> reglas=new ArrayList<Regla>();
				aux=buff.readLine().split(",");
				for(String str: aux){	
					reglas.add(new Regla(str));
				}
				ArrayList<String> alfabeto=new ArrayList<String>();
				aux=buff.readLine().split(",");
				for(String str: aux){	
					alfabeto.add(str);
				}
				String inicial=buff.readLine();
				String linea;
				String[] arrAux=null;
				while((linea = buff.readLine()) != null) {
					arrAux=linea.split("->");
					String produccion=arrAux[1];
					if(produccion.equals("lmd")){
						produccion="";
					}
					for(int i=0;i<reglas.size();i++){
						Regla r=reglas.get(i);
						if(r.getSimbolo().equals(arrAux[0])){
							r.agregarProduccion(produccion);
							break;
						}
					}
				}
				Gramatica g=new Gramatica(alfabeto,reglas,inicial);
				System.out.println(g);
				while(otraCadena){
					boolean aceptado=true;
					System.out.println("Ingrese la cadena a evaluar");
					String cadena=s.nextLine();
					for(int i=0;i<cadena.length();i++){
						if(!alfabeto.contains(cadena.charAt(i)+"")){
							aceptado=false;
							break;
						}
					}
					if(aceptado){
						Procesamiento p=new Procesamiento(g,cadena);
						System.out.println(p.aceptaCadena());
					}else{
						System.out.println("Los simbolos de la cadena no están en el alfabeto, la cadena no es aceptada");
					}
					menu=true;
					while(menu){
						System.out.println("¿Qué desea hacer?\n1.Ingresar otra cadena \n2.Ingresar otra gramática \n3.Salir");
						String r=s.nextLine();
						try{
							int res=Integer.parseInt(r);
							switch(res){
							case 1:
								menu=false;
								break;
							case 2:
								otraCadena=false;
								menu=false;
								break;
							case 3:
								otraGramatica=false;
								otraCadena=false;
								menu=false;
								break;
							default:
								System.out.println("Opción no válida");
								break;
							}
						}catch(NumberFormatException e){
							System.out.println("Ingrese un número");
						}
					}
				}
				buff.close();

			} catch (FileNotFoundException e) {
				System.out.println("El archivo no fue encontrado...");			    
			} catch (IOException e) {
				System.out.println("Error en la lectura...");
			}
		}
		s.close();
	}	
}