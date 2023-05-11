
package main;
/**
 * 
 */

import java.util.List;

import entrada.Teclado;
import main.dao.AccesoPartido;
import main.modelo.Equipo;
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
		System.out.println("(0) Salir del programa.");
		System.out.println("(1) Insertar un partido en la base de datos.");
		System.out.println("(2) Actualizar un partido, por codigo, de la base de datos.");
		System.out.println("(3) Eliminar un partido, por codigo, de la base de datos.");
		System.out.println("(4) Consultar un partido, por codigo, de la base de datos.");
		System.out.println("(5) Consultar todos los partidos de la base de datos ordenados por posicion.");
		System.out.println("(6) Exportar los partidos de la base de datos al fichero de texto.");
		System.out.println("(7) Importar los partidos del fichero a la base de datos.");

		int opcion = Teclado.leerEntero("¿Opcion (0-7)?");
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
				
				int codigoEquipoLocalInsertar = Teclado.leerEntero("Codigo de equipo local: ");
				int codigoEquipoVisitanteInsertar = Teclado.leerEntero("Codigo de equipo visitante: ");
				int añoTemporadaInsertar = Teclado.leerEntero("Año Temporada: ");
				String fechaInsertar = Teclado.leerCadena("Fecha: ");
				int puntuacionLocalInsertar = Teclado.leerEntero("puntos local: ");;
				int puntuacionVisitanteInsertar = Teclado.leerEntero("puntos visitante: ");
				
				Equipo equipoLocalInsertar = new Equipo(codigoEquipoLocalInsertar);
				Equipo equipoVisitanteInsertar = new Equipo(codigoEquipoVisitanteInsertar);
				
				Partido partidoInsertar = new Partido(equipoLocalInsertar, equipoVisitanteInsertar,añoTemporadaInsertar,fechaInsertar,puntuacionLocalInsertar,puntuacionVisitanteInsertar);
				boolean insertado = AccesoPartido.insertarPartido(partidoInsertar);
				
				if(insertado) {
					System.out.println("Se ha insertado un Partido en la base de datos.");
				}else {
					System.out.println("Ya existe ese Partido en la base de datos.");
				}
				break;
				
			case 2:
				int codigoEquipoLocalActualizar = Teclado.leerEntero("Codigo de equipo local");
				int codigoEquipoVisitanteActualizar = Teclado.leerEntero("Codigo de equipo visitante");
				int añoTemporadaActualizar = Teclado.leerEntero("Año Temporada: ");
				
				String fechaActualizar = Teclado.leerCadena("Fecha: ");
				int puntuacionLocalActualizar = Teclado.leerEntero("puntos local: ");;
				int puntuacionVisitanteActualizar = Teclado.leerEntero("puntos visitante: ");
				
				Equipo equipoLocalActualizar = new Equipo(codigoEquipoLocalActualizar);
				Equipo equipoVisitanteActualizar = new Equipo(codigoEquipoVisitanteActualizar);
				
				Partido partidoActualizar = new Partido(equipoLocalActualizar, equipoVisitanteActualizar, añoTemporadaActualizar, fechaActualizar, puntuacionLocalActualizar, puntuacionVisitanteActualizar);
				boolean actualizado = AccesoPartido.actualizarPartido(partidoActualizar);
				
				if(actualizado) {
					System.out.println("Se ha actualizado un Partido en la base de datos.");
				}else {
					System.out.println("No existe un Partido con ese codigo en la base de datos.");
				}
				break;
				
			case 3:
				int codigoEquipoLocalEliminar = Teclado.leerEntero("Codigo equipo local: ");
				int codigoEquipoVisitanteEliminar = Teclado.leerEntero("Codigo equipo visitante: ");
				int añoTemporadaEliminar = Teclado.leerEntero("Año Temporada: ");
				Equipo equipoLocalEliminar = new Equipo(codigoEquipoLocalEliminar);
				Equipo equipoVisitanteEliminar = new Equipo(codigoEquipoVisitanteEliminar);
				Partido partidoEliminar = new Partido(equipoLocalEliminar, equipoVisitanteEliminar, añoTemporadaEliminar);
				boolean eliminado = AccesoPartido.eliminarPartido(partidoEliminar);
				if(eliminado) {
					System.out.println("Se ha eliminado un Partido en la base de datos.");
				}else {
					System.out.println("No existe un Partido con ese codigo en la base de datos.");
				}
				break;
				
			case 4:
				
				int codigoEquipoLocalConultar = Teclado.leerEntero("Codigo equipo local: ");
				int codigoEquipoVisitanteConultar = Teclado.leerEntero("Codigo equipo visitante: ");
				int añoTemporadaConultar = Teclado.leerEntero("Año Temporada: ");
				Equipo equipoLocalConultar = new Equipo(codigoEquipoLocalConultar);
				Equipo equipoVisitanteConultar = new Equipo(codigoEquipoVisitanteConultar);
				
				Partido partidoConultar = new Partido(equipoLocalConultar, equipoVisitanteConultar, añoTemporadaConultar);
				Partido Partido3 = AccesoPartido.consultarPartido(partidoConultar);
				
				if(Partido3 == null) {
					System.out.println("No existe un Partido con ese codigo en la base de datos.");
				}else {
					System.out.println(Partido3);
				}
				break;
				
			case 5:
				List<Partido> Partidos = AccesoPartido.consultarTodosPartidos();
				if(Partidos == null) {
					System.out.println("No hay Partidoes en la base de datos.");
				}else {
					System.out.println(Partidos);
				}
				
				break;
				
			case 6:
				String pathExportar = Teclado.leerCadena("Direccion donde quiere guardar el archivo: ");
				boolean escrito = AccesoPartido.exportarPartidos(pathExportar);
				if(escrito) {
					System.out.println("Se ha exportado la tabla al fichero.");
				}else {
					System.out.println("No se ha podido exportar la tabla al fichero.");
				}
				break;
				
			case 7:
				String pathImportar = Teclado.leerCadena("Direccion del archivo a importar: ");
				boolean importados = AccesoPartido.importarPartidos(pathImportar);
				
				if(!importados) {
					System.out.println("No se han importado Partidoes del fichero de texto.");
				}else {
					System.out.println(importados);
				}
				break;
				
			default:
				System.out.println("La opcion debe estar comprendida entre 0 y 7.");
			
			}
		} while (opcion != 0);
		System.out.println("El programa ha finalizado con exito!");
	}

}
