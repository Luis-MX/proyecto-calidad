//           Programa:  2A - LOC Counter
//        Descripcion:  Contador de lineas de codigo para programas
//                      en el lenguaje Java.
//      Desarrollador:  Luis Enrique Ortiz Ramirez
//              Fecha:  27 - Febrero -2019


import java.util.Scanner;
// m
import java.io.BufferedReader;
// add
import java.io.FileReader;
import java.util.Arrays;

// m
// inicioClase
public class B_6_Code_Programa2_E1_19 {

	// metodo
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		String nombreArchivo = scanner.nextLine();
		scanner.close();
		Controlador controlador = new Controlador();
		controlador.leerDatos(nombreArchivo);
		// controlador.procesarDatos();
		controlador.mostrarResultados();
	}
}
// finClase


// inicioClase
class Controlador {
	// m
	private int lineasPrograma;
	// m
	private int lineasClase;
	// m
	private int numeroMetodos;
	// m
	private String nombreClase;
	// m
	private boolean dentroDeClase;
	// m
	private boolean dentroDeMetodo;
	// add
	private boolean dentroDeEncabezado;

	// metodo
	public Controlador() {
		// columna1 = new ListaEnlazada();
		// columna2 = new ListaEnlazada();
	}

	// metodo
	public void procesarLinea(String linea) {
		// m
		linea = linea.trim();
		// columna1.insertar(Double.parseDouble(valores[0]));
		// columna2.insertar(Double.parseDouble(valores[1]));
		// add
		if (linea.startsWith("/**") || linea.startsWith("*/")) {
			if (linea.startsWith("/**")) {
				dentroDeEncabezado = true;
			}
			if (linea.startsWith("*/")) {
				dentroDeEncabezado = false;
			}
		} else if (linea.startsWith("//")) {
			if (linea.equals("// inicioClase")) {
				dentroDeClase = true;
				nombreClase = null;
				lineasClase = 0;
				numeroMetodos = 0;
			}
			if (linea.equals("// finClase")) {
				System.out.println("Clase " + nombreClase + ":");
				System.out.println("     Lineas de la clase: " + lineasClase);
				System.out.println("     Metodos de la clase: " + numeroMetodos);
				dentroDeClase = false;
			}
			if (linea.equals("// metodo")) {
				numeroMetodos ++;
			}
		} else {
			if (!linea.isEmpty() && !dentroDeEncabezado) {
				lineasPrograma ++;
				lineasClase ++;
				if (nombreClase == null) {
					String[] palabras = linea.split(" ");
					if (palabras[0].equals("class")) {
						nombreClase = palabras[1];
					} else if (palabras[1].equals("class")) {
						nombreClase = palabras[2];
					}
 				}
			}
		}
		// finAdd
	}

	// metodo
	public void leerDatos(String nombreArchivo) throws Exception {
		LectorArchivos lector = new LectorArchivos(nombreArchivo);
		lector.leerTexto();
		lector.porCadaLinea(this);
	}

	//public void procesarDatos() {
		// Estadisticas estadisticas1 = new Estadisticas(columna1);
		// Estadisticas estadisticas2 = new Estadisticas(columna2);
		// promedio1 = estadisticas1.promedioDatos();
		// promedio2 = estadisticas2.promedioDatos();
		// desviacionEstandar1 = estadisticas1.desviacionDatos();
		// desviacionEstandar2 = estadisticas2.desviacionDatos();
	//}

	// metodo
	public void mostrarResultados() {
		//System.out.println("Promedio 1: " + promedio1);
		//System.out.println("Promedio 2: " + promedio2);
		//System.out.println("Desviacion 1: " + desviacionEstandar1);
		// m
		System.out.println("             Lineas totales del programa: " + lineasPrograma);
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