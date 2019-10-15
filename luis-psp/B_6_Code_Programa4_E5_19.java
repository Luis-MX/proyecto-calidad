//           Programa:  4A - Calculo de tamanos
//        Descripcion:  Programa para calcular los tamanos relativos
//                      de partes usando la desviacion estandar.
//      Desarrollador:  Luis Enrique Ortiz Ramirez
//              Fecha:  29 - Abril - 2019


import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Arrays;


// inicioClase
public class B_6_Code_Programa4_E4_19 {
	// metodo
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		String nombreArchivo = scanner.nextLine();
		Controlador controlador = new Controlador();
		controlador.leerDatos(nombreArchivo);
		controlador.procesarDatos();
		controlador.mostrarResultados();
	}
}
// finClase


// inicioClase
class Controlador {
	private ListaEnlazada datosPrueba;
	private double lnVS;
	private double lnS;
	private double lnM;
	private double lnL;
	private double lnVL;
	
	// metodo
	public Controlador() {
		datosPrueba = new ListaEnlazada();
	}

	// metodo
	public void procesarLinea(String linea) {
		String[] valores = linea.split(" ");
		// m
		datosPrueba.insertar(new Par(Double.parseDouble(valores[1]),
			Double.parseDouble(valores[2])
			));
	}

	// metodo
	public void leerDatos(String nombreArchivo) throws Exception {
		LectorArchivos lector = new LectorArchivos(nombreArchivo);
		lector.leerTexto();
		lector.porCadaLinea(this);
	}

	// metodo
	public void procesarDatos() {
		// add
		ListaEnlazada divisiones = Sumatoria.procesarSumaObjeto(datosPrueba, new OperacionDivision());
		ListaEnlazada logaritmos = Sumatoria.procesarSumaObjeto(divisiones, new OperacionLogaritmo());
		double promedio = Sumatoria.sumaX(logaritmos, 1) / logaritmos.longitud();
		double varianza = Sumatoria.sumaObjeto(logaritmos, new OperacionRestaPromedio(promedio));
		varianza = Math.sqrt(varianza / (logaritmos.longitud() - 1));
		lnVS = promedio - 2 * varianza;
		lnVS = Math.exp(lnVS);
		lnS = promedio - varianza;
		lnS = Math.exp(lnS);
		lnM = promedio;
		lnM = Math.exp(lnM);
		lnL = promedio + varianza;
		lnL = Math.exp(lnL);
		lnVL = promedio + 2 * varianza;
		lnVL = Math.exp(lnVL);
	}

	// metodo
	public void mostrarResultados() throws Exception {
		System.out.println("VS: " + lnVS);
		System.out.println("S: " + lnS);
		System.out.println("M: " + lnM);
		System.out.println("L: " + lnL);
		System.out.println("VL: " + lnVL);
	}
}
// finClase


// inicioClase
class Par {
	public Double primero;
	public Double segundo;

	// metodo
	public Par(Double primero, Double segundo) {
		this.primero = primero;
		this.segundo = segundo;
	}
}
// finClase


// inicioClase
class Operacion {

	// metodo
	public double calcular(Par par) {
		return 0.0;
	}
}
// finClase


// inicioClase
class OperacionDivision extends Operacion {

	// metodo
	public double calcular(Par par) {
		return par.primero / par.segundo;
	}
}
// finClase


// inicioClase
class OperacionLogaritmo extends Operacion {

	// metodo
	public double calcular(Par par) {
		return Math.log(par.primero);
	}
}
// finClase


// inicioClase
class OperacionRestaPromedio extends Operacion {

	private final double promedio;

	// metodo
	public OperacionRestaPromedio(double promedio) {
		this.promedio = promedio;
	}

	// metodo
	public double calcular(Par par) {
		return Math.pow(par.primero - promedio, 2);
	}
}
// finClase


// inicioClase
class Sumatoria {

	// metodo
	public static Double sumaX(ListaEnlazada datos, int potencia) {
		double suma = 0.0;
		datos.iniciarIterador();
		Par par = datos.siguienteValor();
		while (par != null) {
			suma += Math.pow(par.primero, potencia);
			par = datos.siguienteValor();
		}
		return suma;
	}

	// metodo
	public static Double sumaY(ListaEnlazada datos, int potencia) {
		double suma = 0.0;
		datos.iniciarIterador();
		Par par = datos.siguienteValor();
		while (par != null) {
			suma += Math.pow(par.segundo, potencia);
			par = datos.siguienteValor();
		}
		return suma;
	}

	// metodo
	public static Double sumaXY(ListaEnlazada datos) {
		double suma = 0.0;
		datos.iniciarIterador();
		Par par = datos.siguienteValor();
		while (par != null) {
			suma += par.primero * par.segundo;
			par = datos.siguienteValor();
		}
		return suma;
	}

	// metodo
	public static ListaEnlazada procesarSumaObjeto(ListaEnlazada datos, Operacion operacion) {
		double suma = 0.0;
		ListaEnlazada duplicado = new ListaEnlazada();
		datos.iniciarIterador();
		Par par = datos.siguienteValor();
		while (par != null) {
			suma += operacion.calcular(par);
			duplicado.insertar(new Par(operacion.calcular(par), suma));
			par = datos.siguienteValor();
		}
		return duplicado;
	}

	// metodo
	public static Double sumaObjeto(ListaEnlazada datos, Operacion operacion) {
		double suma = 0.0;
		datos.iniciarIterador();
		Par par = datos.siguienteValor();
		while (par != null) {
			suma += operacion.calcular(par);
			par = datos.siguienteValor();
		}
		return suma;
	}
}
// finClase


// inicioClase
class Nodo {
	public Par valor;
	public Nodo siguiente;

	// metodo
	public Nodo() {
		this.siguiente = null;
	}

	// metodo
	public Nodo(Par valor) {
		this.valor = valor;
		this.siguiente = null;
	}

	// metodo
	public Nodo(Nodo siguiente) {
		this.siguiente = siguiente;
	}

	// metodo
	public Nodo(Par valor, Nodo siguiente) {
		this.valor = valor;
		this.siguiente = siguiente;
	}

	// metodo
	public Nodo obtenerSiguiente() {
		if (siguiente == null) return null;
		return new Nodo(valor, siguiente);
	}
}
// finClase


// inicioClase
class ListaEnlazada {
	private Nodo primero;
	private Nodo ultimo;
	private int longitud;
	private Nodo iterador;

	// metodo
	public ListaEnlazada() {
		longitud = 0;
		iterador = null;
	}

	// metodo
	public void insertar(Par valor) {
		if (longitud == 0) {
			primero = new Nodo(valor);
			ultimo = primero;
			longitud++;
		} else {
			Nodo nodo = new Nodo(valor);
			ultimo.siguiente = nodo;
			ultimo = nodo;
			longitud++;
		}
	}

	// metodo
	public int longitud() {
		return longitud;
	}

	// metodo
	public void iniciarIterador() {
		iterador = primero;
	}

	// metodo
	public Par siguienteValor() {
		if (iterador == null) return null;
		Nodo actual = iterador;
		iterador = iterador.siguiente;
		return actual.valor;
	}
}
// finClase


// inicioClase
class LectorArchivos {
	private String nombreArchivo;
	private String texto;

	// metodo
	public LectorArchivos(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
		texto = "";
	}

	// metodo
	public String leerTexto() throws Exception {
		BufferedReader bufer = new BufferedReader(new FileReader(nombreArchivo));
		String linea = bufer.readLine();
		texto = "";
		while (linea != null) {
			texto += linea +"\n";
			linea = bufer.readLine();
		}
		bufer.close();
		return texto;
	}

	// metodo
	public void porCadaLinea(Controlador controlador) {
		for (String linea : texto.split("\n")) {
			controlador.procesarLinea(linea);
		}
	}
}
// finClase