//           Programa:  3A - Linear Regression
//        Descripcion:  Programa para la prediccion a traves
//                      de regresion lineal.
//      Desarrollador:  Luis Enrique Ortiz Ramirez
//              Fecha:  20 - Marzo - 2019


import java.util.Scanner;
// m
import java.io.BufferedReader;
// add
import java.io.FileReader;
// add
import java.io.InputStreamReader;
import java.util.Arrays;


// m
// inicioClase
public class B_6_Code_Programa3_E1_19 {
	// metodo
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		String nombreArchivo = scanner.nextLine();
		// scanner.close();
		Controlador controlador = new Controlador();
		controlador.leerDatos(nombreArchivo);
		controlador.procesarDatos();
		controlador.mostrarResultados();
	}
}
// finClase

// inicioClase
class Controlador {
	// m
	private ListaEnlazada datosPrueba1;
	// m
	private ListaEnlazada datosPrueba2;
	// add
	private ListaEnlazada datosPrueba3;
	private ListaEnlazada datosPrueba4;
	private Estadisticas prueba1;
	private Estadisticas prueba2;
	private Estadisticas prueba3;
	private Estadisticas prueba4;
	// finAdd
	//private double promedio1;
	//private double promedio2;
	//private double desviacionEstandar1;
	//private double desviacionEstandar2;
	
	// metodo
	public Controlador() {
		// m
		datosPrueba1 = new ListaEnlazada();
		// m
		datosPrueba2 = new ListaEnlazada();
		// add
		datosPrueba3 = new ListaEnlazada();
		datosPrueba4 = new ListaEnlazada();
		prueba1 = new Estadisticas(datosPrueba1);
		prueba2 = new Estadisticas(datosPrueba2);
		prueba3 = new Estadisticas(datosPrueba3);
		prueba4 = new Estadisticas(datosPrueba4);
		// finAdd
	}

	// metodo
	public void procesarLinea(String linea) {
		String[] valores = linea.split(" ");
		// m
		datosPrueba1.insertar(new Par(Double.parseDouble(valores[0]),
		// add
			Double.parseDouble(valores[2])
			));
		// finAdd
		// m
		datosPrueba2.insertar(new Par(Double.parseDouble(valores[0]),
		// add
			Double.parseDouble(valores[3])
			));
		datosPrueba3.insertar(new Par(Double.parseDouble(valores[1]),
			Double.parseDouble(valores[2])
			));
		datosPrueba4.insertar(new Par(Double.parseDouble(valores[1]),
			Double.parseDouble(valores[3])
			));
		// finAdd
	}

	// metodo
	public void leerDatos(String nombreArchivo) throws Exception {
		LectorArchivos lector = new LectorArchivos(nombreArchivo);
		lector.leerTexto();
		lector.porCadaLinea(this);
	}

	// metodo
	public void procesarDatos() {
		// m
		Estadisticas prueba1 = new Estadisticas(datosPrueba1);
		// m
		Estadisticas prueba2 = new Estadisticas(datosPrueba2);
		// add
		Estadisticas prueba3 = new Estadisticas(datosPrueba3);
		// add
		Estadisticas prueba4 = new Estadisticas(datosPrueba4);
		//promedio1 = estadisticas1.promedioDatos();
		//promedio2 = estadisticas2.promedioDatos();
		//desviacionEstandar1 = estadisticas1.desviacionDatos();
		//desviacionEstandar2 = estadisticas2.desviacionDatos();
	}

	// metodo
	public void mostrarResultados() throws Exception {
		// m
		System.out.println("Prueba 1: ");
		System.out.println("\tB1: " + prueba1.obtenerB1());
		System.out.println("\tB0: " + prueba1.obtenerB0());
		System.out.println("\tR: " + prueba1.obtenerCorrelacion());
		// add
		System.out.println("\tR cuadrada: " +
			Math.pow(prueba1.obtenerCorrelacion(), 2));
		Scanner scanner = new Scanner(System.in);
		System.out.print("Valor Xk: ");
		int valorXk = scanner.nextInt();
		System.out.println("\tPrediccion: " + prueba1.obtenerPrediccion(valorXk));
		System.out.println("Prueba 2: ");
		System.out.println("\tB1: " + prueba2.obtenerB1());
		System.out.println("\tB0: " + prueba2.obtenerB0());
		System.out.println("\tR: " + prueba2.obtenerCorrelacion());
		System.out.println("\tR cuadrada: " +
			Math.pow(prueba2.obtenerCorrelacion(), 2));
		System.out.print("Valor Xk: ");
		valorXk = scanner.nextInt();
		System.out.println("\tPrediccion: " + prueba2.obtenerPrediccion(valorXk));
		System.out.println("Prueba 3: ");
		System.out.println("\tB1: " + prueba3.obtenerB1());
		System.out.println("\tB0: " + prueba3.obtenerB0());
		System.out.println("\tR: " + prueba3.obtenerCorrelacion());
		System.out.println("\tR cuadrada: " +
			Math.pow(prueba3.obtenerCorrelacion(), 2));
		System.out.print("Valor Xk: ");
		valorXk = scanner.nextInt();
		System.out.println("\tPrediccion: " + prueba3.obtenerPrediccion(valorXk));
		System.out.println("Prueba 4: ");
		System.out.println("\tB1: " + prueba4.obtenerB1());
		System.out.println("\tB0: " + prueba4.obtenerB0());
		System.out.println("\tR: " + prueba4.obtenerCorrelacion());
		System.out.println("\tR cuadrada: " +
			Math.pow(prueba4.obtenerCorrelacion(), 2));
		System.out.print("Valor Xk: ");
		valorXk = scanner.nextInt();
		System.out.println("\tPrediccion: " + prueba4.obtenerPrediccion(valorXk));
		// finAdd
	}
}
// finClase

// add
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

// add
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
}
// finClase
// finAdd

// inicioClase
class Nodo {
	// m
	public Par valor;
	public Nodo siguiente;

	// metodo
	public Nodo() {
		this.siguiente = null;
	}

	// m
	// metodo
	public Nodo(Par valor) {
		this.valor = valor;
		this.siguiente = null;
	}

	// metodo
	public Nodo(Nodo siguiente) {
		this.siguiente = siguiente;
	}

	// m
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

	// m
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

	// m
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
class Estadisticas {
	private ListaEnlazada listaDatos;
	// m
	private Double sumaX;
	// m
	private Double promedioX;
	private Double desviacion;
	// add
	private Double sumaY;
	private Double promedioY;
	private Double valorB0;
	private Double valorB1;
	private Double correlacion;
	private Double prediccion;
	// finAdd

	// metodo
	public Estadisticas(ListaEnlazada lista) {
		this.listaDatos = lista;
		// add
		this.sumaX = null;
		this.sumaY = null;
		this.promedioX = null;
		this.promedioY = null;
		this.desviacion = null;
		this.valorB0 = null;
		this.valorB1 = null;
		this.correlacion = null;
		this.prediccion = null;
		// finAdd
	}

	// mC
	// metodo
	public void sumarDatos() {
		if (sumaX == null) {
			listaDatos.iniciarIterador();
			Par par = listaDatos.siguienteValor();
			sumaX = 0.0;
			while (par != null) {
				sumaX += par.primero;
				par = listaDatos.siguienteValor();
			}
		}
		if (sumaY == null) {
			listaDatos.iniciarIterador();
			Par par = listaDatos.siguienteValor();
			sumaY = 0.0;
			while (par != null) {
				sumaY += par.segundo;
				par = listaDatos.siguienteValor();
			}
		}
	}

	// mC
	// metodo
	public void promedioDatos() {
		if (promedioX == null) {
			if (sumaX == null) sumarDatos();
			promedioX = sumaX / listaDatos.longitud();
		}
		if (promedioY == null) {
			if (sumaY == null) sumarDatos();
			promedioY = sumaY / listaDatos.longitud();
		}
	}

	// metodo
	public double desviacionDatos() {
		//if (desviacion != null) return desviacion;
		//listaDatos.iniciarIterador();
		//Double dato = listaDatos.siguienteValor();
		//double sumaDesv = 0.0;
		//while (dato != null) {
		//	sumaDesv += Math.pow(dato - promedio, 2);
		//	dato = listaDatos.siguienteValor();
		//}
		//desviacion = Math.sqrt(sumaDesv/(listaDatos.longitud()-1));
		// m
		return 0;
	}

	// add
	// metodo
	public Double obtenerB1() {
		if (promedioX == null || promedioY == null) promedioDatos();
		double temporal = 0.0;
		double dividendo = Sumatoria.sumaXY(listaDatos);
		temporal = listaDatos.longitud() * promedioX;
		temporal *= promedioY;
		dividendo -= temporal;
		double divisor = Sumatoria.sumaX(listaDatos, 2);
		divisor -= listaDatos.longitud() * (Math.pow(promedioX, 2));
		valorB1 = dividendo / divisor;
		return valorB1;
	}
	// finAdd

	// add
	// metodo
	public Double obtenerB0() {
		if (promedioX == null || promedioY == null) promedioDatos();
		valorB0 = promedioY - valorB1 * promedioX;
		return valorB0;
	}
	// finAdd

	// add
	// metodo
	public Double obtenerCorrelacion() {
		int n = listaDatos.longitud();
		if (correlacion == null) {
			if (sumaX == null) sumarDatos();
			if (sumaY == null) sumarDatos();
			double dividendo = Sumatoria.sumaXY(listaDatos) * n;
			dividendo -= sumaX * sumaY;
			double divisor = Sumatoria.sumaX(listaDatos, 2) * n;
			divisor -= Math.pow(sumaX, 2);
			double multiploDivisor = Sumatoria.sumaY(listaDatos, 2) * n;
			multiploDivisor -= Math.pow(sumaY, 2);
			divisor *= multiploDivisor;
			divisor = Math.sqrt(divisor);
			correlacion = dividendo / divisor;
		}
		return correlacion;
	}
	// finAdd

	// add
	// metodo
	public Double obtenerPrediccion(double valorX) {
		if (prediccion == null) {
			prediccion = valorB0 + valorB1 * valorX;
		}
		return prediccion;
	}
	// finAdd
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