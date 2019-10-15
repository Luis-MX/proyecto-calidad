/*
* Contador de Codigo
* Programa que cuenta las lineas de codigo de un programa(Script), sus elementos
* y el tamano de LOC de cada elemento
* Manuel Herrera Lara
* 27/Febrero/2019
*/

import java.io.File;//add
import java.io.FileNotFoundException;//add
import java.util.Scanner;//add

//add
public class B_04_Code_Programa2_E3_19{ //EsClase
	//add
	public static void main(String[] args){	//EsMetodo
					
		String nombreClase = "";//add
		int contadorMetodos = 0;//add
		int contadorParte = 0;//add
		int contadorTotal = 0;//add
		String salida = "";//add		
		
		//base
		try{
			//base
			File file = new File(args[0]);//archivo a leer
			//base
			Scanner s = new Scanner(file);
			//base
			while(s.hasNextLine()){//mientras el archivo tenga mas lineas
				//base
				String linea = s.nextLine();//llama la siguiente linea del archivo											
				linea = linea.trim();//add
				
				//extraigo el nombre de la clase
				if(linea.endsWith("//EsClase")){//add
					String[] palabras = linea.split(" ");//add
					for(String item: palabras){//add
						if(item.equals("//EsClase")){//add
							if(palabras[0].equals("public")){//add
								String nombre = palabras[2];//add
								String nombreClaseActual = "";//add
								for(int i=0; i<(nombre.length())-1; i++){//add
									char letra = nombre.charAt(i);//add
									nombreClaseActual += letra;//add
								}//add
								nombreClase = nombreClaseActual;//add
							 }//add
				
						}else if(palabras[0].equals("class")){//add
							String nombre = palabras[1];//add
							String nombreClaseActual = "";//add
							for(int i=0; i<(nombre.length())-1; i++){//add
								char letra = nombre.charAt(i);//add
								nombreClaseActual += letra;//add
							}//add
							nombreClase = nombreClaseActual;//add
						}//add
					}//add
				}//add			
									
				if(linea.endsWith("//EsClase")){//add					
					contadorParte = 0;//add
					contadorMetodos = 0;//add
					contadorParte++;//add
					contadorTotal++;//add
					//System.out.println("Es una clase");
				}else if(linea.endsWith("//EsMetodo")){//add
					contadorMetodos++;//add
					contadorParte++;//add
					contadorTotal++;//add
					//System.out.println("Es un metodo");
				}else if(linea.startsWith("//FinClase")){//add
					//System.out.println("Es fin");
					//contadorParte--;
					System.out.println("Clase: " + nombreClase + " Numero Metodos: " + contadorMetodos + " LOC Parte: "+ contadorParte);//add					
				}else if(!(linea.isEmpty())){//add
					if(!(linea.startsWith("//"))){//add
						if(!(linea.startsWith("/*"))){//add
							if(!(linea.startsWith("*/"))){//add
								if(!(linea.startsWith("*"))){//add					
									contadorParte++;//add
									contadorTotal++;//add							
								}//add
							}//add
						}//add
				
					}//add		
				}//add
				//System.out.println(linea + " "+ contadorParte + " " +contadorTotal);
				//System.out.println(contadorMetodos + " " + contadorParte + " " +contadorTotal);
			//base																																							
			}
			System.out.println("LOC Total: "+contadorTotal);//add
		//base
		}catch(FileNotFoundException e1){//primera excepcion
			//base
			System.out.println("El archivo no existe...");
		//base
		}
	}//add
}//add
//FinClase
