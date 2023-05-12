package main.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
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

	public static boolean esta(int codigo) {
		Connection conexion = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// Establecer la conexión con la base de datos
			conexion = ConfigBD.abrirConexion();
			// Preparar la consulta SQL
			String sql = "SELECT codigo FROM equipo WHERE codigo = ?";
			pstmt = conexion.prepareStatement(sql);
			pstmt.setInt(1, codigo);

			// Ejecutar la consulta SQL
			rs = pstmt.executeQuery();

			return rs.next();

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			// Cerrar los objetos de conexión
			try {
				ConfigBD.cerrarConexion(conexion);
			} catch (Exception e) {
				/* ignored */ }
		}

		return false;
	}

	// Consulta todos los equipos de la tabla equipo y los devuelve en un List
	public static List<Equipo> consultarTodo() {
		List<Equipo> todosEquipos = new ArrayList<Equipo>();
		Connection conexion = null;
		{

			try {

				conexion = ConfigBD.abrirConexion();
				System.out.println("Conectado");
				String sentenciaConsultar = "SELECT * FROM equipo ORDER BY nombre";
				Statement sentencia = conexion.createStatement();
				ResultSet resultados = sentencia.executeQuery(sentenciaConsultar);
				while (resultados.next()) {
					Equipo e = new Equipo(resultados.getInt("codigo"), resultados.getString("nombre"),
							resultados.getInt("año_fundacion"), resultados.getString("lugar_sede"),
							resultados.getString("estadio"), resultados.getInt("socios_aficionados"));
					todosEquipos.add(e);
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
				conexion = ConfigBD.abrirConexion();
				String sentenciaConsultar = "SELECT * FROM equipo WHERE codigo = ('" + codigo + "');";
				Statement sentencia = conexion.createStatement();
				ResultSet resultados = sentencia.executeQuery(sentenciaConsultar);
				while (resultados.next()) {
					String nombre = resultados.getString("nombre");
					int añoFundacion = resultados.getInt("año_fundacion");
					String lugarSede = resultados.getString("lugar_sede");
					String estadio = resultados.getString("estadio");
					int sociosAficionados = resultados.getInt("socios_aficionados");

					e = new Equipo(codigo, nombre, añoFundacion, lugarSede, estadio, sociosAficionados);

				}
				resultados.close();
				sentencia.close();

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
			String sentenciaInsertar = "INSERT INTO equipo (nombre, año_fundacion, lugar_sede, estadio, socios_aficionados) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement sentencia = conexion.prepareStatement(sentenciaInsertar);
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
			String sentenciaActualizar = "UPDATE equipo SET nombre = ?, año_fundacion = ?, lugar_sede = ?, estadio = ?, socios_aficionados = ? WHERE codigo = ?";
			PreparedStatement sentencia = conexion.prepareStatement(sentenciaActualizar);
			sentencia.setString(1, nombre);
			sentencia.setInt(2, añoFundacion);
			sentencia.setString(3, lugarSede);
			sentencia.setString(4, estadio);
			sentencia.setInt(5, sociosAficionados);
			sentencia.setInt(6, codigo);

			int filasActualizadas = sentencia.executeUpdate();
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

	// Importa la tabla equipo desde un fichero
	public static boolean importarEquipos(String path) {
		BufferedReader br = null;
		try {
			File fichero = new File(path);
			br = new BufferedReader(new FileReader(fichero));

			String linea = br.readLine();
			while (linea != null) {
				String[] datos = linea.split(";");

				int codigo = Integer.parseInt(datos[0]);
				String nombre = datos[1];
				int añoFundacion = Integer.parseInt(datos[2]);
				String lugarSede = datos[3];
				String estadio = datos[5];
				int sociosAficionados = Integer.parseInt(datos[5]);

				Equipo e = new Equipo(codigo, nombre, añoFundacion, lugarSede, estadio, sociosAficionados);

				insertar(e);
				linea = br.readLine();

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
				if (br != null) {
					br.close();
				}
			} catch (IOException ioe) {
				System.out.println("Error al cerrar el fichero: " + ioe.getMessage());
				ioe.printStackTrace();
			}
		}
		return false;
	}

	// Exporta la tabla equipo a un fichero

	public static boolean exportarEquipos(String path) {
		BufferedWriter bw = null;
		List<Equipo> equipos = consultarTodo();
		try {
			bw = new BufferedWriter(new FileWriter(path, false));
			for (int i = 0; i < equipos.size(); i++) {

				bw.write(equipos.get(i).toStringWithSeparators());
				bw.newLine();
			}

		} catch (IOException ioe) {
			System.out.println("Error al escribir en el fichero: " + ioe.getMessage());
			ioe.printStackTrace();
		} finally {
			try {
				if (bw != null) {
					bw.close();
					return true;
				}
			} catch (IOException ioe) {
				System.out.println("Error al cerrar el fichero: " + ioe.getMessage());
				ioe.printStackTrace();
			}
		}
		return false;
	}

}