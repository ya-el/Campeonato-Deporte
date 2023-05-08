package main.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import main.config.ConfigBD;
import main.modelo.Equipo;


public class AccesoEquipo {

	// Hecho por Alvaro Delicado

	// Consulta todos los equipos de la tabla equipo y los devuelve en un List
	public static List<Equipo> consultarTodo() {
		List<Equipo> todosEquipos = new ArrayList<Equipo>();
		Connection conexion = null;
		{

			try {

				conexion = ConfigBD.abrirConexion();
				System.out.println("Conectado");
				int contadorEquipos = 0;
				String sentenciaConsultar = "SELECT * FROM equipo ORDER BY nombre";
				Statement sentencia = conexion.createStatement();
				ResultSet resultados = sentencia.executeQuery(sentenciaConsultar);
				while (resultados.next()) {
					Equipo e = new Equipo(resultados.getInt("codigo"), resultados.getString("nombre"),
							resultados.getInt("año_fundacion"), resultados.getString("lugar_sede"),
							resultados.getString("estadio"), resultados.getInt("socios_aficionados"));
					todosEquipos.add(e);
					contadorEquipos++;
				}
				resultados.close();
				sentencia.close();
			}

			catch (SQLException sqle) {

				System.out.println("Error de SQL: " + sqle.getMessage());
				sqle.printStackTrace();
			} finally {

				ConfigBD.cerrarConexion(conexion);
			}

		}
		return todosEquipos;
	}

	// Consulta un equipo de la tabla equipo, por codigo, y devuelve un objeto
	// Equipo
	public static Equipo consultar(int codigo) {
		Equipo e = null;
		Connection conexion = null;
		{

			try {
				int contador = 0;
				conexion = ConfigBD.abrirConexion();
				System.out.println("Conectado");
				int contadorEquipos = 0;
				String sentenciaConsultar = "SELECT * FROM equipo";
				Statement sentencia = conexion.createStatement();
				ResultSet resultados = sentencia.executeQuery(sentenciaConsultar);
				while (resultados.next()) {
					contador++;
				}

				for (int i = 0; i < contador; i++) {
					if (codigo == resultados.getInt("codigo")) {
						e = new Equipo(resultados.getInt("codigo"), resultados.getString("nombre"),
								resultados.getInt("año_fundacion"), resultados.getString("lugar_sede"),
								resultados.getString("estadio"), resultados.getInt("socios_aficionados"));
						return e;
					}
				}
				resultados.close();
				sentencia.close();
			}

			catch (SQLException sqle) {

				System.out.println("Error de SQL: " + sqle.getMessage());
				sqle.printStackTrace();
			} finally {

				ConfigBD.cerrarConexion(conexion);
			}

		}
		return e;
	}

	// Inserta un Equipo en la tabla equipo

	public static boolean insertar(Equipo equipo) {
		Connection conexion = null;
		try {
			String nombre = equipo.getNombre();
			int añoFundacion = equipo.getAñoFundacion();
			String lugarSede = equipo.getLugarSede();
			String estadio = equipo.getEstadio();
			int sociosAficionados = equipo.getSociosAficionados();
			conexion = ConfigBD.abrirConexion();
			String sentenciaInsertar = "INSERT INTO equipo (nombre, año_fundacion, lugar_sede, estadio, socios_aficionados "
					+ "VALUES (?, ?, ?, ?, ?)";
			PreparedStatement sentencia = conexion.prepareStatement(sentenciaInsertar);//Error, REVISAR  ¡¡¡!!!
			sentencia.setString(1, nombre);
			sentencia.setInt(2, añoFundacion);
			sentencia.setString(3, lugarSede);
			sentencia.setString(4, estadio);
			sentencia.setInt(5, sociosAficionados);
			int filasInsertadas = sentencia.executeUpdate();
			if (filasInsertadas != 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException sqle) {
			System.out.println("Error de SQL: " + sqle.getMessage());
			sqle.printStackTrace();
		}
		ConfigBD.cerrarConexion(conexion);
		return false;

	}

	// Actualiza un equipo por codigo, de la tabla equipo

	public static boolean actualizar(int codigo, Equipo equipo) {
		Connection conexion = null;
		try {
			String nombre = equipo.getNombre();
			int añoFundacion = equipo.getAñoFundacion();
			String lugarSede = equipo.getLugarSede();
			String estadio = equipo.getEstadio();
			int sociosAficionados = equipo.getSociosAficionados();
			conexion = ConfigBD.abrirConexion();
			String sentenciaActualizar = "UPDATE equipo " + "SET nombre = ?, año_fundacion = ?, lugar_sede = ?, estadio = ?, socios_aficionados = ? WHERE codigo = ?";
			PreparedStatement sentencia = conexion.prepareStatement(sentenciaActualizar);
			sentencia.setString(1, nombre);
			sentencia.setInt(2, añoFundacion);
			sentencia.setString(3, lugarSede);
			sentencia.setString(4, estadio);
			sentencia.setInt(5, sociosAficionados);
			sentencia.setInt(6, codigo);

			int filasActualizadas = sentencia.executeUpdate(sentenciaActualizar);
			if (filasActualizadas == 0) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException sqle) {
			System.out.println("Error de SQL: " + sqle.getMessage());
			sqle.printStackTrace();
		}
		ConfigBD.cerrarConexion(conexion);
		return false;

	}

	// Elimina un equipo de la tabla equipo por codigo
	public static boolean eliminar(int codigo) {
		Connection conexion = null;
		try {
			conexion = ConfigBD.abrirConexion();
			String sentenciaEliminar = "DELETE FROM equipo " + "WHERE codigo = " + codigo;
			Statement sentencia = conexion.createStatement();
			int filasEliminadas = sentencia.executeUpdate(sentenciaEliminar);
			if (filasEliminadas == 0) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException sqle) {
			System.out.println("Error de SQL: " + sqle.getMessage());
			sqle.printStackTrace();
		}
		ConfigBD.cerrarConexion(conexion);
		return false;
	}

	// Exporta la tabla equipo a un fichero

	/*
	 * public static void exportar() { Connection conexion = null; try { conexion =
	 * ConfigBD.abrirConexion(); } catch (SQLException sqle) {
	 * System.out.println("Error de SQL: " + sqle.getMessage());
	 * sqle.printStackTrace(); } ConfigBD.cerrarConexion(conexion); }
	 */
	public static void main(String args[]) {
		Equipo e = new Equipo(1, "a", 1, "a", "a", 1);
		Equipo e1 = new Equipo(2, "b", 2, "b", "b", 2);

		AccesoEquipo.insertar(e1);
		AccesoEquipo.consultarTodo();

	}

}
