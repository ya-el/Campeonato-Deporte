package main;
/**
 * 
 */

import java.util.List;

import entrada.Teclado;
import main.dao.AccesoPartido;
import main.modelo.Partido;

/**
 * @author YASSINE EL ATTAR
 *
 */
public class MainPartido {

	/**
	 * @param args
	 */
	public static int menuOpciones() {
		System.out.println("\n(0) Salir del programa.");
		System.out.println("(1) Insertar un partido en la base de datos.");
		System.out.println("(2) Actualizar un partido, por c�digo, de la base de datos.");
		System.out.println("(3) Eliminar un partido, por c�digo, de la base de datos.");
		System.out.println("(4) Consultar un partido, por c�digo, de la base de datos.");
		System.out.println("(5) Consultar todos los partidos de la base de datos ordenados por posicion.");
		System.out.println("(6) Exportar los partidos de la base de datos al fichero de texto.");
		System.out.println("(7) Importar los partidos del fichero a la base de datos.");

		int opcion = Teclado.leerEntero("¿Opci�n (0-7)?");
		return opcion;
	}

	public static void main(String[] args) {
		int opcion = -1;

		do {

			opcion = menuOpciones();
			switch (opcion) {
			case 0:
				break;

			case 1:
				String nombre = Teclado.leerCadena("Introduce el nombre del Partido:");
				String fechaNacimiento = Teclado.leerCadena("Introduce fecha de nacimiento del Partido:");
				String nacionalidad = Teclado.leerCadena("Introduce la nacionalidad del Partido:");
				String posicion = Teclado.leerCadena("Introduce la posicion del Partido:");
				
				Partido Partido = new Partido(nombre, fechaNacimiento, nacionalidad, posicion);
				boolean insertado = AccesoPartido.insertarPartido(Partido);
				if(insertado) {
					System.out.println("Se ha insertado un Partido en la base de datos.");
				}else {
					System.out.println("Ya existe ese Partido en la base de datos.");
				}
				break;
				
			case 2:
				int codigo = Teclado.leerEntero("Codigo del Partido a actualizar:");
				nombre = Teclado.leerCadena("Introduce el nombre del Partido:");
				fechaNacimiento = Teclado.leerCadena("Introduce fecha de nacimiento del Partido:");
				nacionalidad = Teclado.leerCadena("Introduce la nacionalidad del Partido:");
				posicion = Teclado.leerCadena("Introduce la posicion del Partido:");
				
				Partido Partido2 = new Partido(codigo, nombre, fechaNacimiento, nacionalidad, posicion);
				boolean actualizado = AccesoPartido.actualizarPartido(Partido2);
				if(actualizado) {
					System.out.println("Se ha actualizado un Partido en la base de datos.");
				}else {
					System.out.println("No existe un Partido con ese c�digo en la base de datos.");
				}
				break;
				
			case 3:
				codigo = Teclado.leerEntero("Codigo del Partido a eliminar:");
				
				boolean eliminado = AccesoPartido.eliminarPartido(codigo);
				if(eliminado) {
					System.out.println("Se ha eliminado un Partido en la base de datos.");
				}else {
					System.out.println("No existe un Partido con ese c�digo en la base de datos.");
				}
				break;
				
			case 4:
				codigo = Teclado.leerEntero("Codigo del Partido a consultar:");
				Partido Partido3 = AccesoPartido.consultarPartido(codigo);
				
				if(Partido3 == null) {
					System.out.println("No existe un Partido con ese c�digo en la base de datos.");
				}else {
					System.out.println(Partido3);
				}
				break;
				
			case 5:
				List<Partido> Partidoes = AccesoPartido.consultarTodosPartidos();
				if(Partidoes == null) {
					System.out.println("No hay Partidoes en la base de datos.");
				}else {
					System.out.println(Partidoes);
				}
				
				break;
				
			case 6:
				String path = Teclado.leerCadena("Direccion");
				boolean escrito = AccesoPartido.exportarPartidos(path);
				if(escrito) {
					System.out.println("Se ha exportado la tabla al fichero.");
				}else {
					System.out.println("No se ha podido exportar la tabla al fichero.");
				}
				break;
				
			case 7:
				String pathImportar = Teclado.leerCadena("Direccion");
				boolean importados = AccesoPartido.importarPartidos(pathImportar);
				if(!importados) {
					System.out.println("No se han importado Partidoes del fichero de texto.");
				}else {
					System.out.println(importados);
				}
				break;
				
			default:
				System.out.println("La opci�n debe estar comprendida entre 0 y 7.");
			
			}
		} while (opcion != 0);
	}

}
