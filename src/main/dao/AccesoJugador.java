package main.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.sqlite.SQLiteConfig;

import main.config.ConfigBD;
import main.modelo.Jugador;

/*
 * Beni Burdet DAM 1ºA
 */

public class AccesoJugador {

	public static boolean insertar(Jugador jugador) {
		boolean insertado = false;
		Connection conexion = null;
		try {
			conexion = ConfigBD.abrirConexion();
			String sentenciaInsertar = "INSERT INTO jugador(nombre, fecha_nacimiento, nacionalidad, posicion) "
					+ "VALUES ('" + jugador.getNombre() + "', '" + jugador.getFechaNacimiento() + "', '" + jugador.getNacionalidad() + "', '" + jugador.getPosicion()
					+ "')";
			System.out.println(sentenciaInsertar);

			Statement sentencia = conexion.createStatement();
			int filasInsertadas = sentencia.executeUpdate(sentenciaInsertar);
			if (filasInsertadas != 0) {
				insertado = true;
			}

		} catch (SQLException sqle) {
			System.out.println("Error de SQL: " + sqle.getMessage());
			sqle.printStackTrace();
		} finally {
			if (conexion != null) {
				ConfigBD.cerrarConexion(conexion);
			}
		}
		return insertado;
	}

	public static boolean actualizar(Jugador jugador) {
		boolean actualizado = false;
		Connection conexion = null;
		try {

			conexion = ConfigBD.abrirConexion();
			String sentenciaActualizar = "UPDATE jugador " + "SET nombre = '" + jugador.getNombre() + "', fecha_nacimiento = '"
					+ jugador.getFechaNacimiento() + "', nacionalidad = '" + jugador.getNacionalidad() + "', posicion = '" + jugador.getNacionalidad() + "'"
					+ "WHERE codigo = " + jugador.getCodigo();
			Statement sentencia = conexion.createStatement();
			int filasActualizadas = sentencia.executeUpdate(sentenciaActualizar);
			if (filasActualizadas != 0) {
				actualizado = true;
			}
		} catch (SQLException sqle) {
			System.out.println("Error de SQL: " + sqle.getMessage());
			sqle.printStackTrace();
		} finally {
			if (conexion != null) {
				ConfigBD.cerrarConexion(conexion);
			}
		}
		return actualizado;
	}

	public static boolean eliminar(int codigo) {
		boolean eliminado = false;
		Connection conexion = null;
		try {
			SQLiteConfig config = new SQLiteConfig();
			config.enforceForeignKeys(true);
			conexion = ConfigBD.abrirConexion();

			String sentenciaEliminar = "DELETE FROM jugador " + "WHERE codigo = " + codigo;
			Statement sentencia = conexion.createStatement();
			int filasEliminadas = sentencia.executeUpdate(sentenciaEliminar);
			if (filasEliminadas != 0) {
				eliminado = true;
			}

		} catch (SQLException sqle) {
			System.out.println("Error de SQL: " + sqle.getMessage());
			sqle.printStackTrace();
		} finally {
			if (conexion != null) {
				ConfigBD.cerrarConexion(conexion);
			}
		}
		return eliminado;
	}

	public static Jugador consultarPorCodigo(int codigo) {
		Jugador jugador = null;
		Connection conexion = null;

		try {
			conexion = ConfigBD.abrirConexion();
			String sentenciaConsultar = "SELECT * FROM jugador WHERE codigo = ('" + codigo + "');";
			Statement sentencia = conexion.createStatement();
			ResultSet resultados = sentencia.executeQuery(sentenciaConsultar);
			while (resultados.next()) {
				String nombre = resultados.getString("nombre");
				String fecha_nacimiento = resultados.getString("fecha_nacimiento");
				String nacionalidad = resultados.getString("nacionalidad");
				String posicion = resultados.getString("posicion");

				jugador = new Jugador(codigo, nombre, fecha_nacimiento, nacionalidad, posicion);

			}
			resultados.close();
			sentencia.close();
		} catch (SQLException sqle) {
			System.out.println("Error de SQL: " + sqle.getMessage());
			sqle.printStackTrace();
		} finally {
			if (conexion != null) {
				ConfigBD.cerrarConexion(conexion);
			}
		}
		return jugador;
	}

	public static List<Jugador> consultarJugadores() {
		List<Jugador> jugadores = new ArrayList<Jugador>();
		Connection conexion = null;
		try {
			conexion = ConfigBD.abrirConexion();
			String sentenciaConsultar = "SELECT codigo, nombre, fecha_nacimiento, nacionalidad, posicion FROM jugador "
					+ "ORDER BY posicion";
			Statement sentencia = conexion.createStatement();
			ResultSet resultados = sentencia.executeQuery(sentenciaConsultar);
			while (resultados.next()) {

				int codigo = resultados.getInt("codigo");
				String nombre = resultados.getString("nombre");
				String fechaNacimiento = resultados.getString("fecha_nacimiento");
				String nacionalidad = resultados.getString("nacionalidad");
				String posicion = resultados.getString("posicion");

				Jugador jugador = new Jugador(codigo, nombre, fechaNacimiento, nacionalidad, posicion);
				jugadores.add(jugador);
			}
			resultados.close();
			sentencia.close();
		} catch (SQLException sqle) {
			System.out.println("Error de SQL: " + sqle.getMessage());
			sqle.printStackTrace();
		} finally {
			if (conexion != null) {
				ConfigBD.cerrarConexion(conexion);
			}
		}
		return jugadores;
	}

	public static boolean exportarTabla() {
		
		return false;
	}
	
	
	
	
	

}
