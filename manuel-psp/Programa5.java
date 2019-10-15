
/*
* Programa que obtiene la regresi�n m�ltiple con Gauss Jordan
* Programa que calcula la regresi�n m�ltiple apartir de un sistema de ecuaciones, usando
* el m�todo de Gauss Jordan
* Manuel Herrera Lara
* 29/05/2019
*/

import java.lang.Math;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.Double;

public class ProgramaRegresionMultiple{ //EsClase
	public static void main(String args[]){ //EsMetodo
		int numeroIncognitas = 0;
		int numeroFilas = 0;
		int numeroColumnas = 0;		
		//crear 4 listas enlazadas para almacenar cada columna de datos  
		ListaEnlazada listaW = new ListaEnlazada();
		ListaEnlazada listaX = new ListaEnlazada();
		ListaEnlazada listaY = new ListaEnlazada();
		ListaEnlazada listaZ = new ListaEnlazada();

		//leer archivo, separar los reglones por espacio y llenar las listas
		try{
			File file = new File(args[0]);//archivo a leer
			Scanner s = new Scanner(file);
			while(s.hasNextLine()){//mientras el archivo tenga mas lineas
				String linea = s.nextLine();//llama la siguiente linea del archivo
				String[] datos = linea.split("-");//separamos la linea
				numeroIncognitas = datos.length;//por las 4 columnas que son w,x,y,z				
				//llenar listas				
				listaW.insertarFinal(Double.parseDouble(datos[0]));
				listaX.insertarFinal(Double.parseDouble(datos[1]));
				listaY.insertarFinal(Double.parseDouble(datos[2]));
				listaZ.insertarFinal(Double.parseDouble(datos[3]));						
			}
		}catch(FileNotFoundException e1){//primera excepcion
			System.out.println("El archivo no existe...");
		}

		//dise�amos el tama�o de la matriz seg�n el n�mero de incognitas
		numeroFilas = numeroIncognitas;
		numeroColumnas = numeroIncognitas;
		Matriz matriz = new Matriz(numeroFilas, numeroColumnas);
		//creamos el sistema de ecuaciones
		matriz.formarEcuaciones(listaW,listaX,listaY,listaZ);
		double[][] matrizOriginal = matriz.getMatriz();
		double[] matrizBetas = matriz.getMatrizBetas();
		//se resuelve el sistema de ecuaciones con Gauss Jordan
		double[] resultadosBetas = matriz.evaluarEcuaciones(matrizOriginal, matrizBetas);
		for (int i=0; i<resultadosBetas.length; i++) {
			System.out.println(resultadosBetas[i]);
		}

		//calculamos la regresi�n m�ltiple
		//argumentos w,x,y para calcular la regresi�n multiple
		double argumentoW = Double.parseDouble(args[1]);
		double argumentoX = Double.parseDouble(args[2]);
		double argumentoY = Double.parseDouble(args[3]);
		double valorRegresionMultiple = matriz.evaluarRegresionMultiple(argumentoW,argumentoX,argumentoY);
		System.out.println("\n Regresion M�ltiple: Horas Proyectadas >> "+ valorRegresionMultiple);		
	}
}
//FinClase


/*
* Clase que representa un nodo de la lista ligada
*/
class Nodo{ //EsClase 
	
	//atributos
	protected Object dato;
	protected Nodo ligaDer;
	
	//constructor
	public Nodo(Object dato){ //EsMetodo
		this.dato = dato;
		ligaDer = null;
	}
	
	public Object getDato(){ //EsMetodo
		return dato;
	}
	
	public void setDato(Object dato){ //EsMetodo
		this.dato = dato;
	}
	
	public Nodo getLigaDer(){ //EsMetodo
		return ligaDer;
	}
	
	public void setLigaDer(Nodo n){ //EsMetodo
		ligaDer = n;
	}
}
//FinClase

/*
* Clase que representa una lista enlazada y sus operaciones b�sicas
*/
class ListaEnlazada{ //EsClase

	protected Nodo primero;
	protected Nodo ultimo;
	
	public ListaEnlazada(){ //EsMetodo
		primero = null;
		ultimo = null;
	}
	
	public boolean insertarFinal(Object dato){ //EsMetodo
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
	
	public Object eliminarFinal(){ //EsMetodo	
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

	public Nodo getPrimero(){ //EsMetodo
		return primero;
	}
	
	public void imprimir(){ //EsMetodo
		Nodo temp = primero;
		Object valor = null;
		while(temp != null){
			valor = temp.getDato();
			System.out.println(valor + "-->");
			temp = temp.getLigaDer();
		}
		System.out.println("null");
	}

	//m�todo que calcula la media de un conjunto de datos
	public Double calcularMedia(){ //EsMetodo
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
	
	//m�todo que calcula la desviacion estandar de un conjunto de datos
	public Double calcularDesvEstandar(){ //EsMetodo
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
//FinClase

/*
* Clase que realiza los calculos para obtener el valor de cada t�rmino en las ecuaciones
*/
class CalculosMatematicos{ //EsClase 
	
	//m�todo que calcula la sumatoria de una lista de datos
	public double sumatoria(ListaEnlazada lista){ //EsMetodo
		double sumatoriaLista = 0.0;
		double valorNodo = 0.0;
		Nodo temp = lista.getPrimero();
		while(temp != null){
			valorNodo = (double)temp.getDato();
			sumatoriaLista += valorNodo;
			temp = temp.getLigaDer();
		}
		return sumatoriaLista;
	}

	//m�todo que calcula la sumatoria cuadrada de una lista de datos
	public double sumatoriaCuadrada(ListaEnlazada lista){ //EsMetodo
		double sumatoriaCuadrada = 0.0;
		double valorNodo = 0.0;
		Nodo temp = lista.getPrimero();
		while(temp != null){
			valorNodo = (double)temp.getDato();
			sumatoriaCuadrada += Math.pow(valorNodo, 2);
			temp = temp.getLigaDer();
		}
		return sumatoriaCuadrada;
	}

	//m�todo que calcula la sumatoria del producto de 2 listas
	public double sumatoriaProductoXY(ListaEnlazada listaX, ListaEnlazada listaY){ //EsMetodo
		double sumatoria = 0.0;
		double valorNodoX = 0.0;
		double valorNodoY = 0.0;
		Nodo temp = listaX.getPrimero();
		Nodo temp2 = listaY.getPrimero();
		while(temp != null){
			while(temp2 != null){
				valorNodoX = (double)temp.getDato();
				valorNodoY = (double)temp2.getDato();
				sumatoria += valorNodoX * valorNodoY;
				temp = temp.getLigaDer();
				temp2 = temp2.getLigaDer();
			}
		}
		return sumatoria;
	}

	//m�todo que devuelve el tama�o de una lista
	public int getSizeLista(ListaEnlazada lista){ //EsMetodo		
		Nodo temp = lista.getPrimero();
		int totalElementos = 0;
		while(temp != null){
			totalElementos++;
			temp = temp.getLigaDer();
		}
		return totalElementos;
	}
}
//FinClase

/*
* Clase que representa una matriz y resuelve un sistema de ecuaciones mediante Gauss Jordan
*/
class Matriz{ //EsClase 
	private int filas;
	private int columnas;
	private double datos[][];
	//arreglo que almacena los resultados las ecuaciones
	private double resultadosEcuacion[];

	//constructor que inicializa a la matriz seg�n las inc�gnitas que tiene
	public Matriz(int numeroFilas, int numeroColumnas){ //EsMetodo
		filas = numeroFilas;
		columnas = numeroColumnas;
		datos = new double[filas][columnas];
		resultadosEcuacion = new double[filas];
	}

	//m�todo que inicializa la matriz en ceros
	public void inicializar(double valor){ //EsMetodo
		for(int i=0; i<getFilas(); i++){
			for(int j=0; j<getColumnas(); j++){
				System.out.print(datos[i][j]+"\t");
			}
			System.out.println("");
		}
	}

	//m�todo que cambia un elemento en la matriz, dadas sus coordenadas
	public boolean cambiar(int fila, int columna, int valor){ //EsMetodo
		//validar el rango
		if(fila>=0 && fila<getFilas() && columna>=0 && columna<getColumnas()){
			datos[fila][columna] = valor;
			return true;	
		}else{
			return false;
		}
	}

	//m�todo que devuelve el elemento de una celda en caso de encontrarse en la matriz,
	//conforme a las cordenadas dadas y si no esta el elemento devuelve -1
	public double leer(int fila, int columna){ //EsMetodo
		//validar el rango
		if(fila>=0 && fila<getFilas() && columna>=0 && columna<getColumnas()){
			return datos[fila][columna];					
		}else{
			return -1;
		}
	}

	//metodo que muestra todos los elementos de la matriz
	public void imprimir(){ //EsMetodo
		for(int i=0; i<getFilas(); i++){
			for(int j=0; j<getColumnas(); j++){
				System.out.print(datos[i][j]+"\t");
			}
			System.out.println("\n");
		}
	}

	//m�todo que muestra todos los resultados de las ecuaciones(Los Betas)
	public void imprimirBetas(){ //EsMetodo
		for(int i=0; i<resultadosEcuacion.length; i++){
			System.out.println(resultadosEcuacion[i]);
		}
	}

	//m�todo que devuelve la matriz original
	public double[][] getMatriz(){ //EsMetodo
		return datos;
	}

	//m�todo que devuelve el arreglo de resultados(Betas)
	public double[] getMatrizBetas(){ //EsMetodo
		return resultadosEcuacion;
	}

	//m�todo que devuelve el n�mero de filas de la matriz
	public int getFilas(){ //EsMetodo
		return filas;
	}

	//m�todo que devuelve el n�mero de columnas de la matriz	
	public int getColumnas(){ //EsMetodo
		return columnas;
	}

	//m�todo que forma el sistema de ecuaciones y pasa sus coeficientes a una matriz
	public void formarEcuaciones(ListaEnlazada listaW, ListaEnlazada listaX, ListaEnlazada listaY, ListaEnlazada listaZ){ //EsMetodo
		//calcular el valor de los t�rminos
		CalculosMatematicos calculos = new CalculosMatematicos();
		//primera ecuaci�n
		double termino1E1 = calculos.getSizeLista(listaW);
		double termino2E1 = calculos.sumatoria(listaW);
		double termino3E1 = calculos.sumatoria(listaX);
		double termino4E1 = calculos.sumatoria(listaY);
		double termino5E1 = calculos.sumatoria(listaZ);
		//segunda ecuaci�n
		double termino1E2 = calculos.sumatoria(listaW);
		double termino2E2 = calculos.sumatoriaCuadrada(listaW);
		double termino3E2 = calculos.sumatoriaProductoXY(listaW,listaX);
		double termino4E2 = calculos.sumatoriaProductoXY(listaW,listaY);
		double termino5E2 = calculos.sumatoriaProductoXY(listaW,listaZ);
		//tercera ecuaci�n
		double termino1E3 = calculos.sumatoria(listaX);
		double termino2E3 = calculos.sumatoriaProductoXY(listaW,listaX);
		double termino3E3 = calculos.sumatoriaCuadrada(listaX);
		double termino4E3 = calculos.sumatoriaProductoXY(listaX, listaY);
		double termino5E3 = calculos.sumatoriaProductoXY(listaX, listaZ);
		//cuarta ecuaci�n
		double termino1E4 = calculos.sumatoria(listaY);
		double termino2E4 = calculos.sumatoriaProductoXY(listaW,listaY);
		double termino3E4 = calculos.sumatoriaProductoXY(listaX, listaY);
		double termino4E4 = calculos.sumatoriaCuadrada(listaY);
		double termino5E4 = calculos.sumatoriaProductoXY(listaY,listaZ);
	
		//pasamos los coeficientes del sistema de ecuaciones a una matriz
		datos[0][0] = termino1E1;
		datos[0][1] = termino2E1;
		datos[0][2] = termino3E1;
		datos[0][3] = termino4E1;
		resultadosEcuacion[0] = termino5E1;
		datos[1][0] = termino1E2;
		datos[1][1] = termino2E2;
		datos[1][2] = termino3E2;
		datos[1][3] = termino4E2;
		resultadosEcuacion[1] = termino5E2;
		datos[2][0] = termino1E3;
		datos[2][1] = termino2E3;
		datos[2][2] = termino3E3;
		datos[2][3] = termino4E3;
		resultadosEcuacion[2] = termino5E3;
		datos[3][0] = termino1E4;
		datos[3][1] = termino2E4;
		datos[3][2] = termino3E4;
		datos[3][3] = termino4E4;
		resultadosEcuacion[3] = termino5E4;
	}

	//m�todo que resuelve el sistema de ecuaciones mediante Gauss Jordan
	public double[] evaluarEcuaciones(double m[][], double r[]){ //EsMetodo
		//el metodo de Gauss Jordan trabaja con la idea de convertir la matriz aumentada en la matriz identidad
       		 for (int i = 0; i <= r.length - 1; i++) {
            		double d, c = 0;
		        d = m[i][i];//1.- Seleccionamos el pivote            
            		for (int s = 0; s <= r.length - 1; s++) {//2.- Pasamos a convertir en 1 al pivote seleccionado
                		m[i][s] = ((m[i][s]) / d);
            		}
		        r[i] = ((r[i]) / d);
            
		        for (int x = 0; x <= r.length - 1; x++) {
                		if (i != x) {
		                    c = m[x][i];                    
                		    for (int y = 0; y <= r.length - 1; y++) {//3.- Se hace cero a todos los elementos de la colunma que no sean el pivote
		                        m[x][y] = m[x][y] - c * m[i][y];
                		    }
		                    r[x] = r[x] - c * r[i];                   
                		}// fin if (i != x)
		        }// fin for (int x = 0; x <= r.length - 1; x++)
	        }//fin bucle i
        	return r;// retorna la solucion l sistema
	}

	//m�todo que calcula la regresi�n m�ltiple seg�n los valores de 3 inc�gnitas
	public double evaluarRegresionMultiple(double w, double x, double y){ //EsMetodo
		double regresionMultiple = 0.0; 
		//System.out.println(resultadosEcuacion[0] + " " + resultadosEcuacion[1] + " " + resultadosEcuacion[2] + " " +resultadosEcuacion[3]);
		regresionMultiple = resultadosEcuacion[0] + w*resultadosEcuacion[1] + x*resultadosEcuacion[2] + y*resultadosEcuacion[3];
		return regresionMultiple;
	}
}
//FinClase

