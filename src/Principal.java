import java.io.*;
import java.util.*;

public class Principal{
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
						aceptado=p.aceptaCadena();
						if(aceptado){
							System.out.println("La cadena es aceptada");
						}else{
							System.out.println("La cadena no es aceptada");
						}
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