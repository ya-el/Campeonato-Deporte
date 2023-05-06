package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.ConfigBD;
import modelo.Partido;

/**
 * @author YASSINE EL ATTAR
 *
 */
public class AccesoPartido {

	//insertar un partido con una sentencia insert 
	//usamos el objeto partido para obtener los valores de un partido
	public static boolean InsertarPartido(Partido partido) {
		Connection conexion = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conexion = ConfigBD.abrirConexion();
			System.out.println("Conectado");
			String sentenciaInsertar = "INSERT INTO partido (codigo_equipo_local, codigo_equipo_visitante, año_temporada, fecha, puntuacion_local, puntuacion_visitante) "
					+ "VALUES (?,?,?,?,?,?)";

			System.out.println(sentenciaInsertar);
			PreparedStatement sentencia = conexion.prepareStatement(sentenciaInsertar);
			sentencia.setInt(1, partido.getCodigoEquipoLocal());
			sentencia.setInt(2, partido.getCodigoEquipoVisitante());
			sentencia.setDouble(3, partido.getAñoTemporada());
			sentencia.setString(4, partido.getFecha());
			sentencia.setInt(5, partido.getPuntuacionLocal());
			sentencia.setInt(6, partido.getPuntuacionVisitante());
			
			
			int filasInsertadas = sentencia.executeUpdate();
			if (filasInsertadas == 0) {
				System.out.println("Ya existe ese partido en la base de datos.");
				return false;
			} else {
				System.out.println("Se ha insertado el partido en la base de datos.");
				return true;
			}
		}

		catch (ClassNotFoundException cnfe) {
			System.out.println("Error al cargar el conector de SQLite: " + cnfe.getMessage());
			cnfe.printStackTrace();
		} catch (SQLException sqle) {
			System.out.println("Error de SQL: " + sqle.getMessage());
			sqle.printStackTrace();
		} finally {
			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException sqle) {
				System.out.println("Error al cerrar la base de datos: " + sqle.getMessage());
				sqle.printStackTrace();
			}
		}

		return false;
	}
	
	//actualizar un partido usando un objeto
	//el usuario proporciona un partido como objeto
	//proporciona el SET y las claves primarias en un solo objeto
	public static boolean ActualizarPartido(Partido partido) {
		Connection conexion = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conexion = ConfigBD.abrirConexion();
			System.out.println("Conectado");
			/*
			String sentenciaInsertar = 
					"UPDATE partido "
					+ "SET fecha = " + partido.getFecha() + ", "
					+ "SET puntuacion_local = "+ partido.getPuntuacionLocal() +", "
					+ "SET puntuacion_visitante = "+ partido.getPuntuacionVisitante() + ", "
					+ "WHERE codigo_equipo_local = "+  partido.getCodigoEquipoLocal() +", "
					+ "and codigo_equipo_visitante = "+ partido.getCodigoEquipoVisitante() +", "
					+ "and año_temporada = "+ partido.getAñoTemporada() + ",";
			
			System.out.println(sentenciaInsertar);
			PreparedStatement sentencia = conexion.prepareStatement(sentenciaInsertar);
			*/
			String sentenciaInsertar = 
					"UPDATE partido "
					+ "SET fecha = ? , "
					+ "SET puntuacion_local = ?, "
					+ "SET puntuacion_visitante = ?, "
					+ "WHERE codigo_equipo_local = ?, "
					+ "and codigo_equipo_visitante = ?, "
					+ "and año_temporada = ?,";
			
			System.out.println(sentenciaInsertar);
			PreparedStatement sentencia = conexion.prepareStatement(sentenciaInsertar);
			sentencia.setInt(4, partido.getCodigoEquipoLocal());
			sentencia.setInt(5, partido.getCodigoEquipoVisitante());
			sentencia.setDouble(6, partido.getAñoTemporada());
			sentencia.setString(1, partido.getFecha());
			sentencia.setInt(2, partido.getPuntuacionLocal());
			sentencia.setInt(3, partido.getPuntuacionVisitante());
			
			
			int filasInsertadas = sentencia.executeUpdate();
			if (filasInsertadas == 0) {
				System.out.println("Error al actualizar");
				return false;
			} else {
				System.out.println("Se ha actualizado el partido en la base de datos.");
				return true;
			}
		}

		catch (ClassNotFoundException cnfe) {
			System.out.println("Error al cargar el conector de SQLite: " + cnfe.getMessage());
			cnfe.printStackTrace();
		} catch (SQLException sqle) {
			System.out.println("Error de SQL: " + sqle.getMessage());
			sqle.printStackTrace();
		} finally {
			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException sqle) {
				System.out.println("Error al cerrar la base de datos: " + sqle.getMessage());
				sqle.printStackTrace();
			}
		}
		return false;
	}

	//para borrar un partido usamos un objeto partido con solo las claves primarias
	public static boolean EliminarPartido(Partido partido) {
		Connection conexion = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conexion = ConfigBD.abrirConexion();
			System.out.println("Conectado");
			/*
			String sentenciaInsertar = 
					"DELETE FROM partido "
					+ "WHERE codigo_equipo_local = "+  partido.getCodigoEquipoLocal() +", "
					+ "and codigo_equipo_visitante = "+ partido.getCodigoEquipoVisitante() +", "
					+ "and año_temporada = "+ partido.getAñoTemporada() + ",";
					
			System.out.println(sentenciaInsertar);
			PreparedStatement sentencia = conexion.prepareStatement(sentenciaInsertar);
			*/
			String sentenciaInsertar = 
					"DELETE FROM partido "
					+ "WHERE codigo_equipo_local = ?, "
					+ "and codigo_equipo_visitante = ?, "
					+ "and año_temporada = ?,";
					
			System.out.println(sentenciaInsertar);
			PreparedStatement sentencia = conexion.prepareStatement(sentenciaInsertar);
			sentencia.setInt(1, partido.getCodigoEquipoLocal());
			sentencia.setInt(2, partido.getCodigoEquipoVisitante());
			sentencia.setDouble(3, partido.getAñoTemporada());
			
			int filasInsertadas = sentencia.executeUpdate();
			if (filasInsertadas == 0) {
				System.out.println("Error al intentar borrar el partido de la base de datos.");
				return false;
			} else {
				System.out.println("Se ha borrado el partido en la base de datos.");
				return true;
			}
		}

		catch (ClassNotFoundException cnfe) {
			System.out.println("Error al cargar el conector de SQLite: " + cnfe.getMessage());
			cnfe.printStackTrace();
		} catch (SQLException sqle) {
			System.out.println("Error de SQL: " + sqle.getMessage());
			sqle.printStackTrace();
		} finally {
			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException sqle) {
				System.out.println("Error al cerrar la base de datos: " + sqle.getMessage());
				sqle.printStackTrace();
			}
		}
		return false;
	}
	
	//una consulta 
	public static Partido ConsultarPartido(Partido partidoEntrada) {
		Partido partido = null;
		Connection conexion = null;
		try {
			conexion = ConfigBD.abrirConexion();
			String sentenciaConsultar = "SELECT * FROM partido "
					+ "WHERE codigo_equipo_local = ?, "
					+ "and codigo_equipo_visitante = ?, "
					+ "and año_temporada = ?"
					+ "ORDER BY fecha";
			PreparedStatement sentencia = conexion.prepareStatement(sentenciaConsultar);
			sentencia.setInt(1, partidoEntrada.getCodigoEquipoLocal());
			sentencia.setInt(2, partidoEntrada.getCodigoEquipoVisitante());
			sentencia.setDouble(3, partidoEntrada.getAñoTemporada());
			ResultSet resultados = sentencia.executeQuery(sentenciaConsultar);
			while (resultados.next()) {
				partido = new Partido(
					resultados.getInt("codigo_equipo_local"),
					resultados.getInt("codigo_equipo_visitante"),
					resultados.getInt("año_temporada"),
					resultados.getString("fecha"),
					resultados.getInt("puntuacion_local"),
					resultados.getInt("puntuacion_visitante")
				);
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
		return partido;
	}

	public static List<Partido> ConsultarTodosPartidos() {
		List<Partido> partidos = new ArrayList<Partido>();
		Partido partido = null;
		Connection conexion = null;
		try {
			conexion = ConfigBD.abrirConexion();
			String sentenciaConsultar = "SELECT * FROM partido "
					+ "ORDER BY posicion";
			PreparedStatement sentencia = conexion.prepareStatement(sentenciaConsultar);
			ResultSet resultados = sentencia.executeQuery(sentenciaConsultar);
			while (resultados.next()) {
				partido = new Partido(
					resultados.getInt("codigo_equipo_local"),
					resultados.getInt("codigo_equipo_visitante"),
					resultados.getInt("año_temporada"),
					resultados.getString("fecha"),
					resultados.getInt("puntuacion_local"),
					resultados.getInt("puntuacion_visitante")
				);
				 partidos.add(partido);
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
		return  partidos;
	}
	
	public static boolean ImportarPartidos() {
		return false;
	}
	
	public static boolean ExportarPartidos() {
		return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
