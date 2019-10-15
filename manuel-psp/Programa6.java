
/*
* Programa de Lectura y Escritura en archivos
* Programa que almacena(escribe) y recupera(lee) una serie de numeros dentro y desde un archivo
* Manuel Herrera Lara
* 05/06/2019
*/

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.lang.Integer;
import java.util.NoSuchElementException;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
* Clase de prueba para leer y escribir en un archivo
*/
public class TestArchivo{ //EsClase
	public static void main(String[] args) throws IOException{ //EsMetodo
		String nombreArchivo = "";
		int cantidadNumeros = 0;		
		Archivo miArchivo = new Archivo();
		Scanner entrada = new Scanner(System.in);	
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);	
		System.out.println("Teclea el nombre del archivo: ");			
		nombreArchivo = br.readLine();		

		try{
			boolean salir = false;
			int opcion = 0;
			String linea ="";
			while(!salir){
				try{
					System.out.println("Teclea el modo del archivo: 1.- (read) o  2.- (write) o 3.- (salir)");								
	       				opcion = Integer.parseInt(br.readLine());			
					switch(opcion){
						case 1: miArchivo.leer(nombreArchivo);
							break;
						case 2: System.out.println("Teclea la cantidad de numeros a registrar: ");
							cantidadNumeros = entrada.nextInt();						
							miArchivo.escribir(nombreArchivo, cantidadNumeros);
							break;	
						case 3: salir=true;
							break;			
					}	
				}catch(InputMismatchException e){
					System.out.println("Debes insertar un numero");
					entrada.next();
				}
			}	
		}catch(NoSuchElementException e){
			System.out.println("No se ha podido encontrar el siguiente elemento del scanner.");
			System.out.println(e.toString());
		}						
	}
}
//FinClase

/*
* Clase que representa las operaciones basicas de un archivo(lectura y escritura)
*/
class Archivo{ //EsClase
	
	//metodo que lee un archivo
	public void leer(String nombreArchivo){ //EsMetodo
		//archivo a leer
		File file = new File(nombreArchivo);
		try{
			//BufferedReader que lee cada linea del archivo
			BufferedReader in = new BufferedReader(new FileReader(file));
			String linea="";
			while(linea != null){ //mientras haya que leer o la linea no este vacia
				linea = in.readLine();
				System.out.println(linea);//mostramos la linea leeida
			}
			//cerramos el flujo de datos del BufferedReader
			in.close();
		}catch(FileNotFoundException e1){
			System.err.println("Archivo no encontrado: " + file);
		}catch(IOException e2){
			//capturamos cualquier otra excepcion
			e2.printStackTrace();
		}
	}

	//metodo que escribe en un archivo
	public void escribir(String nombreArchivo, int cantidadNumeros) throws NoSuchElementException{ //EsMetodo
		//archivo para escribir 
		File file = new File(nombreArchivo);
		
		//leer cada linea desde el teclado(Entrada Estandar)
		try{
			InputStreamReader isr = new InputStreamReader(System.in);//se indica que se lee desde teclado
			BufferedReader in = new BufferedReader(isr);
			//creamos un objeto de tipo PrintWriter para escribir en el archivo
			PrintWriter out = new PrintWriter(new FileWriter(file, true));

			//leemos cada linea desde el teclado, segun la cantidad de numeros y la escribimos en el archivo
			String s = "";
			// while ((s = in.readLine()) != null) {				
		 	// 		out.println(s);
 			// 	}
			int i=0;
			while(i<cantidadNumeros){
				s = in.readLine();
				out.println(s);
				i++;
			}
						
			//cerrar los flujos de datos de lectura(BufferedReader) y escritura(PrintWriter)
			//in.close();
			out.close();
		}catch(IOException e){
			//capturar cualquier excepcion
			e.printStackTrace();
		}
	}
}
//FinClase