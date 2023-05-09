package main.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.sqlite.SQLiteConfig;

import main.config.ConfigBD;
import main.modelo.Jugador;

/**
 * @author Beni Burdet DAM 1A
 */

public class AccesoJugador {

	public static boolean insertar(Jugador jugador) {
		boolean insertado = false;
		Connection conexion = null;
		try {
			conexion = ConfigBD.abrirConexion();
			String sentenciaInsertar = "INSERT INTO jugador(nombre, fecha_nacimiento, nacionalidad, posicion) "
					+ "VALUES ('" + jugador.getNombre() + "', '" + jugador.getFechaNacimiento() + "', '"
					+ jugador.getNacionalidad() + "', '" + jugador.getPosicion() + "')";
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
			String sentenciaActualizar = "UPDATE jugador " + "SET nombre = '" + jugador.getNombre()
					+ "', fecha_nacimiento = '" + jugador.getFechaNacimiento() + "', nacionalidad = '"
					+ jugador.getNacionalidad() + "', posicion = '" + jugador.getPosicion() + "'" + "WHERE codigo = "
					+ jugador.getCodigo();
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
		BufferedWriter flujoSalida = null;
		List<Jugador> jugadores = AccesoJugador.consultarJugadores();
		try {
			FileWriter escritor = new FileWriter("jugadores.txt", false);
			flujoSalida = new BufferedWriter(new FileWriter("jugadores.txt", false));
			for (int i = 0; i < jugadores.size(); i++) {

				flujoSalida.write(jugadores.get(i).toStringWithSeparators());
				flujoSalida.newLine();
			}

		} catch (IOException ioe) {
			System.out.println("Error al escribir en el fichero: " + ioe.getMessage());
			ioe.printStackTrace();
		} finally {
			try {
				if (flujoSalida != null) {
					flujoSalida.close();
					return true;
				}
			} catch (IOException ioe) {
				System.out.println("Error al cerrar el fichero: " + ioe.getMessage());
				ioe.printStackTrace();
			}
		}
		return false;
	}

	public static List<Jugador> importarTabla() {
		BufferedReader flujoEntrada = null;
		List<Jugador> jugadores = new ArrayList<Jugador>();
		try {
			File fichero = new File("jugadores.txt");
			flujoEntrada = new BufferedReader(new FileReader(fichero));

			String linea = flujoEntrada.readLine();
			while (linea != null) {
				String[] datos = linea.split(";");
				
				int codigo = Integer.parseInt(datos[0]);
				String nombre = datos[1];
				String fechaNacimiento = datos[2];
				String nacionalidad = datos[3];
				String posicion = datos[4];

				Jugador jugador = new Jugador(codigo, nombre, fechaNacimiento, nacionalidad, posicion);
				jugadores.add(jugador);

				linea = flujoEntrada.readLine();

			}
			for(int i = 0; i < jugadores.size(); i++) {
				AccesoJugador.insertar(jugadores.get(i));
			}
		} catch (FileNotFoundException fnfe) {
			System.out.println("Error al abrir el fichero: " + fnfe.getMessage());
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			System.out.println("Error al leer del fichero: " + ioe.getMessage());
			ioe.printStackTrace();
		} catch (NumberFormatException nfe) {
			System.out.println("Error al convertir de cadena a numero: " + nfe.getMessage());
			nfe.printStackTrace();
		} finally {
			try {
				if (flujoEntrada != null) {
					flujoEntrada.close();
				}
			} catch (IOException ioe) {
				System.out.println("Error al cerrar el fichero: " + ioe.getMessage());
				ioe.printStackTrace();
			}
		}
		return jugadores;
	}

}
