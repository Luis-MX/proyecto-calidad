import java.util.Scanner;
import java.io.*;
import java.util.Arrays;


// inicioClase
public class B_6_Code_Programa1_E1_19 {
	// metodo
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		String nombreArchivo = scanner.nextLine();
		scanner.close();
		Controlador controlador = new Controlador();
		controlador.leerDatos(nombreArchivo);
		controlador.procesarDatos();
		controlador.mostrarResultados();
	}
}
// finClase

// inicioClase
class Controlador {
	private ListaEnlazada columna1;
	private ListaEnlazada columna2;
	private double promedio1;
	private double promedio2;
	private double desviacionEstandar1;
	private double desviacionEstandar2;
	
	// metodo
	public Controlador() {
		columna1 = new ListaEnlazada();
		columna2 = new ListaEnlazada();
	}

	// metodo
	public void procesarLinea(String linea) {
		String[] valores = linea.split(" ");
		columna1.insertar(Double.parseDouble(valores[0]));
		columna2.insertar(Double.parseDouble(valores[1]));
	}

	// metodo
	public void leerDatos(String nombreArchivo) throws Exception {
		LectorArchivos lector = new LectorArchivos(nombreArchivo);
		lector.leerTexto();
		lector.porCadaLinea(this);
	}

	// metodo
	public void procesarDatos() {
		Estadisticas estadisticas1 = new Estadisticas(columna1);
		Estadisticas estadisticas2 = new Estadisticas(columna2);
		promedio1 = estadisticas1.promedioDatos();
		promedio2 = estadisticas2.promedioDatos();
		desviacionEstandar1 = estadisticas1.desviacionDatos();
		desviacionEstandar2 = estadisticas2.desviacionDatos();
	}

	// metodo
	public void mostrarResultados() {
		System.out.println("Promedio 1: " + promedio1);
		System.out.println("Promedio 2: " + promedio2);
		System.out.println("Desviacion 1: " + desviacionEstandar1);
		System.out.println("Desviacion 2: " + desviacionEstandar2);
	}
}
// finClase

// inicioClase
class Nodo {
	public Double valor;
	public Nodo siguiente;

	// metodo
	public Nodo() {
		this.siguiente = null;
	}

	// metodo
	public Nodo(double valor) {
		this.valor = valor;
		this.siguiente = null;
	}

	// metodo
	public Nodo(Nodo siguiente) {
		this.siguiente = siguiente;
	}

	// metodo
	public Nodo(double valor, Nodo siguiente) {
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
	public void insertar(double valor) {
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
	public Double siguienteValor() {
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
	private Double suma;
	private Double promedio;
	private Double desviacion;

	// metodo
	public Estadisticas(ListaEnlazada lista) {
		this.listaDatos = lista;
	}

	// metodo
	public double sumaDatos() {
		if (suma != null) return suma;
		listaDatos.iniciarIterador();
		Double dato = listaDatos.siguienteValor();
		suma = 0.0;
		while (dato != null) {
			suma += dato;
			dato = listaDatos.siguienteValor();
		}
		return suma;
	}

	// metodo
	public double promedioDatos() {
		if (promedio != null) return promedio;
		if (suma == null) suma = sumaDatos();
		promedio = suma / listaDatos.longitud();
		return promedio;
	}

	// metodo
	public double desviacionDatos() {
		if (desviacion != null) return desviacion;
		listaDatos.iniciarIterador();
		Double dato = listaDatos.siguienteValor();
		double sumaDesv = 0.0;
		while (dato != null) {
			sumaDesv += Math.pow(dato - promedio, 2);
			dato = listaDatos.siguienteValor();
		}
		desviacion = Math.sqrt(sumaDesv/(listaDatos.longitud()-1));
		return desviacion;
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