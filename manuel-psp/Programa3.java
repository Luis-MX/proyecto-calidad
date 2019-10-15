/*
* Programa Estadistico
* Programa que calcula los parametros de regresion lineal b0 y b1;
* asi como el coeficiente de correlacion apartir de 2 conjuntos de datos
* Manuel Herrera Lara
* 25/Marzo/2019
*/

import java.lang.Math;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.math.BigDecimal;

public class ProgramaEstadistico{ //EsClase
	public static void main(String args[]){ //EsMetodo
		
		ListaEnlazada lista = new ListaEnlazada();
		try{
			File file = new File(args[0]);//archivo a leer
			Scanner s = new Scanner(file);
			while(s.hasNextLine()){//mientras el archivo tenga mas lineas
				String linea = s.nextLine();//llama la siguiente linea del archivo
				String[] datos = linea.split("-");
				Double datoA = Double.parseDouble(datos[0]);
				Double datoB = Double.parseDouble(datos[1]);
				ParDatos pares = new ParDatos(datoA,datoB);
				lista.insertarFinal(pares);	
			}
		}catch(FileNotFoundException e1){//primera excepcion
			System.out.println("El archivo no existe...");
		}

		BigDecimal xK = new BigDecimal(args[1]);
		//System.out.println(xK);

		CalculosEstadisticos calculos = new CalculosEstadisticos();
		calculos.calcularCorrelacion(lista);
		calculos.calcularRegresionLineal(lista, xK);
		System.out.println(calculos.toString());
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

	//metodo que calcula la media de un conjunto de datos
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
* Clase que representa n par de datos, los cuales van a ser almacendos en un nodo
*/
class ParDatos{ //EsClase
	private Double datoA;
	private Double datoB;

	public ParDatos(Double a, Double b){ //EsMetodo
		datoA = a;
		datoB = b;
	}

	public void setDatoA(Double a){ //EsMetodo
		datoA = a;
	}

	public Double getDatoA(){ //EsMetodo
		return datoA;
	}

	public void setDatoB(Double b){ //EsMetodo
		datoB = b;
	}

	public Double getDatoB(){ //EsMetodo
		return datoB;
	}
}
//FinClase

class CalculosEstadisticos{ //EsClase
	private BigDecimal r;
	private BigDecimal rCuadrada;
	private BigDecimal prediccionYK;
	private BigDecimal betaUno;
	private BigDecimal betaCero;

	public CalculosEstadisticos(){ //EsMetodo
		r = new BigDecimal("0.0");
		rCuadrada = new BigDecimal("0.0");
		prediccionYK = new BigDecimal("0.0");
		betaUno = new BigDecimal("0.0");
		betaCero = new BigDecimal("0.0");
	}

	//metodo que calcula la correlacion
	public void calcularCorrelacion(ListaEnlazada lista){ //EsMetodo
		Double totalConjuntoX = 0.0;
		Double sumatoriaCuadradaX = 0.0;
		Double mediaX = 0.0;
		Double totalConjuntoY = 0.0;
		Double sumatoriaCuadradaY = 0.0;
		Double mediaY = 0.0;
		Double sumatoriaXY = 0.0;
		
		Nodo temp = lista.getPrimero();
		ParDatos pares = null;
		while(temp != null){
			pares = (ParDatos)temp.getDato();
			Double datoA = pares.getDatoA();
			Double datoB = pares.getDatoB();
			totalConjuntoX += datoA;
			sumatoriaCuadradaX += Math.pow(datoA, 2);
			mediaX += datoA;
			totalConjuntoY += datoB;
			sumatoriaCuadradaY += Math.pow(datoB, 2);
			mediaY += datoB;
			sumatoriaXY += (datoA)*(datoB);				
			temp = temp.getLigaDer();
		}
		mediaX = mediaX/10;
		mediaY = mediaY/10;

		//usando la clase BigDecimal para que los resultados no aparezcan con notacion cientifica
		BigDecimal totalElementos = new BigDecimal("10");
		BigDecimal bdSumatoriaXY = new BigDecimal(sumatoriaXY);
		BigDecimal bdSumatoriaX = new BigDecimal(totalConjuntoX);
		BigDecimal bdSumatoriaY = new BigDecimal(totalConjuntoY);
		BigDecimal bdProductoUno = totalElementos.multiply(bdSumatoriaXY);
		BigDecimal bdProductoDos = bdSumatoriaX.multiply(bdSumatoriaY);
		BigDecimal bdParteSuperior = bdProductoUno.subtract(bdProductoDos);
		BigDecimal bdSumatoriaCuadradaX = new BigDecimal(sumatoriaCuadradaX);
		BigDecimal bdTotalCunjuntoX = new BigDecimal(totalConjuntoX);
		BigDecimal cuadradoConjuntoX = bdTotalCunjuntoX.pow(2);
		BigDecimal bdSumatoriaCuadradaY = new BigDecimal(sumatoriaCuadradaY);
		BigDecimal bdTotalConjuntoY = new BigDecimal(totalConjuntoY);
		BigDecimal cuadradoConjuntoY = bdTotalConjuntoY.pow(2);
		BigDecimal bdValor = totalElementos.multiply(bdSumatoriaCuadradaX);
		BigDecimal bdValor2 = bdValor.subtract(cuadradoConjuntoX);
		BigDecimal bdValor3 = totalElementos.multiply(bdSumatoriaCuadradaY);
		BigDecimal bdValor4 = bdValor3.subtract(cuadradoConjuntoY);
		BigDecimal bdParteInferior = bdValor2.multiply(bdValor4);
		Double raizCuadrada = Math.sqrt(bdParteInferior.doubleValue());
		Double correlacion = bdParteSuperior.doubleValue() / raizCuadrada;
		BigDecimal bdCorrelacion = new BigDecimal(correlacion);	
		BigDecimal correlacionCuadrada = bdCorrelacion.pow(2);
		
		r = bdCorrelacion;
		rCuadrada = correlacionCuadrada;				
	}

	//metodo que calcula los parametros de regresion lineal b0 y b1
	public void calcularRegresionLineal(ListaEnlazada lista, BigDecimal xK){ //EsMetodo
		Double sumatoriaXY = 0.0;
		Double mediaX = 0.0;
		Double mediaY = 0.0;
		Double sumatoriaCuadradaX = 0.0;
		Double mediaCuadradaX = 0.0;
		
		Nodo temp = lista.getPrimero();
		ParDatos pares = null;
		while(temp != null){
			pares = (ParDatos)temp.getDato();
			Double datoA = pares.getDatoA();
			Double datoB = pares.getDatoB();		
			mediaX += datoA;
			mediaY += datoB;
			sumatoriaXY += (datoA)*(datoB); 
			sumatoriaCuadradaX += Math.pow(datoA, 2);
			temp = temp.getLigaDer();			
		}
		mediaX = mediaX/10;
		mediaY = mediaY/10;
		mediaCuadradaX = Math.pow(mediaX, 2);
		//usando la clase BigDecimal para que los resultados no aparezcan con notacion cientifica
		BigDecimal bdSumatoriaXY = new BigDecimal(sumatoriaXY);
		BigDecimal bdMediaX = new BigDecimal(mediaX);
		BigDecimal bdMediaY = new BigDecimal(mediaY);
		BigDecimal bdSumatoriaCuadradaX = new BigDecimal(sumatoriaCuadradaX);
		BigDecimal bdMediaCuadradaX = new BigDecimal(mediaCuadradaX);

		BigDecimal totalElementos = new BigDecimal("10");
		BigDecimal bdProducto = totalElementos.multiply(bdMediaX);
		BigDecimal bdProductoAvg = bdProducto.multiply(bdMediaY);
		BigDecimal bdParteSuperior = bdSumatoriaXY.subtract(bdProductoAvg);
		//System.out.println(bdParteSuperior);
		BigDecimal bdProductoMedia = totalElementos.multiply(bdMediaCuadradaX);
		BigDecimal bdParteInferior = bdSumatoriaCuadradaX.subtract(bdProductoMedia);
		//System.out.println(bdParteInferior);
		//obtenemos b1
		Double beta1 = bdParteSuperior.doubleValue() / bdParteInferior.doubleValue();
		BigDecimal bdBeta1 = new BigDecimal(beta1);
		//obtenemos b0
		BigDecimal valorTemporal = bdBeta1.multiply(bdMediaX);
		BigDecimal beta0 = bdMediaY.subtract(valorTemporal);
		//obtenemos Yk
		BigDecimal estimadoProxyXK = xK;
		BigDecimal bdProductoYK = bdBeta1.multiply(estimadoProxyXK);
		BigDecimal yK = beta0.add(bdProductoYK);

		betaUno = bdBeta1;
		betaCero = beta0;
		prediccionYK = yK;
	}

	public String toString(){ //EsMetodo
		String resultado ="b0: " + betaCero + "\nb1: " + betaUno + "\nr: " + r
			+ "\nr^2: " + rCuadrada + "\n yK: " + prediccionYK;
		return resultado;
	}
}
//FinClase

	


	

