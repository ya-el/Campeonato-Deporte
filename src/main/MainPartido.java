
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
		Equipo equipoLocal ;
		Equipo equipoVisitante;
		
		do {

			opcion = menuOpciones();
			switch (opcion) {
			case 0:
				break;

			case 1:
				int codigoEquipoLocal = Teclado.leerEntero("Codigo de equipo local");
				int codigoEquipoVisitante = Teclado.leerEntero("Codigo de equipo visitante") ;
				equipoLocal = new Equipo(codigoEquipoLocal);
				equipoVisitante = new Equipo(codigoEquipoVisitante);
				Partido partidoInsertar = new Partido(equipoLocal, equipoVisitante, opcion, null, opcion, opcion);
				boolean insertado = AccesoPartido.insertarPartido(partidoInsertar);
				if(insertado) {
					System.out.println("Se ha insertado un Partido en la base de datos.");
				}else {
					System.out.println("Ya existe ese Partido en la base de datos.");
				}
				break;
				
			case 2:
				
				Partido partidoActualizar = null;
				boolean actualizado = AccesoPartido.actualizarPartido(partidoActualizar);
				if(actualizado) {
					System.out.println("Se ha actualizado un Partido en la base de datos.");
				}else {
					System.out.println("No existe un Partido con ese codigo en la base de datos.");
				}
				break;
				
			case 3:
				int codigoEquipoLocalEliminar = Teclado.leerEntero("Codigo equipo local a eliminar: ");
				int codigoEquipoVisitanteEliminar = Teclado.leerEntero("Codigo equipo visitante a eliminar: ");
				int añoTemporadaEliminar = Teclado.leerEntero("Año Temporada: ");
				Equipo equipoLocal = new Equipo(codigoEquipoLocal);
				Equipo equipoVisitante = new Equipo(codigoEquipoVisitante);

				boolean eliminado = AccesoPartido.eliminarPartido();
				if(eliminado) {
					System.out.println("Se ha eliminado un Partido en la base de datos.");
				}else {
					System.out.println("No existe un Partido con ese codigo en la base de datos.");
				}
				break;
				
			case 4:
				Partido partidoConultar = null;
				Partido Partido3 = AccesoPartido.consultarPartido(partidoConultar);
				
				if(Partido3 == null) {
					System.out.println("No existe un Partido con ese codigo en la base de datos.");
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
				System.out.println("La opcion debe estar comprendida entre 0 y 7.");
			
			}
		} while (opcion != 0);
		System.out.println("El programa ha finalizado con exito!");
	}

}
