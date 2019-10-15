
import java.lang.Math;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ProgramaMedidasDispersion{
	
	public static void main(String args[]){
		
		ListaEnlazada lista = new ListaEnlazada();
		try{
			File file = new File(args[0]);//archivo a leer
			Scanner s = new Scanner(file);
			Double n;
			while(s.hasNextLine()){//mientras el archivo tenga mas lineas
				String linea = s.nextLine();//llama la siguiente linea del archivo
				n = Double.parseDouble(linea);//se castea la linea leeida a double
				lista.insertarFinal(n);//se almacena el elemento leeido en la lista
				//System.out.println(n);		
			}
		}catch(FileNotFoundException e1){//primera excepcion
			System.out.println("El archivo no existe...");
		}

		ProgramaMedidasDispersion medidas = new ProgramaMedidasDispersion();
		Double media = lista.calcularMedia();
		System.out.println("Media: " + media);

		Double desviacionEstandar = lista.calcularDesvEstandar();
		System.out.println("Desviacion Estandar: " + desviacionEstandar);
		
	}
}

/*
*	Clase que representa un nodo de la lista ligada
*/
class Nodo{
	
	//atributos
	protected Object dato;
	protected Nodo ligaDer;
	
	public Nodo(Object dato){
		this.dato = dato;
		ligaDer = null;
	}
	
	public Object getDato(){
		return dato;
	}
	
	public void setDato(Object dato){
		this.dato = dato;
	}
	
	public Nodo getLigaDer(){
		return ligaDer;
	}
	
	public void setLigaDer(Nodo n){
		ligaDer = n;
	}
}

/*
*	Clase que representa una lista enlazada
*/

class ListaEnlazada{

	protected Nodo primero;
	protected Nodo ultimo;
	
	public ListaEnlazada(){
		primero = null;
		ultimo = null;
	}
	
	public boolean insertarFinal(Object dato){
		boolean flag = false;
		
		Nodo nuevo = new Nodo(dato);
		if(nuevo != null){
			if(primero == null){
				primero = nuevo;				
			}else{
				ultimo.setLigaDer(nuevo);				
			}
			ultimo = nuevo;
			flag = true;
		}
		return flag;
	}
	
	public Object eliminarFinal(){	
		Object valor = null;		
		//validar que hay elementos
		if(primero != null){
			
			//si es el primero y es el unico
			if(primero.getLigaDer() == null){
				primero = null;
			}else{	
				Nodo temp = null;	
				Nodo penultimo = primero; 
				while(penultimo.getLigaDer() != null){
					temp=penultimo;
					penultimo = penultimo.getLigaDer();
					valor=penultimo.getDato();
				}
				temp.setLigaDer(null);
			}						
		}
		return valor;	
	}
	
	public void imprimir(){
		Nodo temp = primero;
		Object valor = null;
		while(temp != null){
			valor = temp.getDato();
			System.out.println(valor + "-->");
			temp = temp.getLigaDer();
		}
		System.out.println("null");
	}

	//metodo que calcula la media de un conjunto de datos
	public Double calcularMedia(){
		Nodo temp = primero;
		Double valor = 0.0;
		Double suma = 0.0;
		int contador = 0;
		//mientras haya valor en los nodos y no esten en null
		while(temp != null){
			valor = (Double)temp.getDato();
			suma += valor;
			contador++;			
			temp = temp.getLigaDer();//me cambio al siguiente nodo
		}
		
		Double resultado = (suma/contador);
		//System.out.println("Contador: "+contador);
		return resultado;
	}
	
	//metodo que calcula la desviacion estandar de un conjunto de datos
	public Double calcularDesvEstandar(){
		Nodo temp = primero;
		Double media = calcularMedia();
		Double valor = null;
		int contador = 0;
		Double suma = 0.0;
		while(temp != null){
			valor = (Double)temp.getDato();
			Double r1 = Math.pow((valor-media), 2);
			suma += r1;
			contador++;
			temp = temp.getLigaDer();
		}
		Double r2 = suma/(contador-1);
		Double rFinal = Math.sqrt(r2);
		return rFinal; 
	}		
}

