/**
 * @author Victor Estella
 */
package main.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.config.ConfigBD;
import main.modelo.Equipo;

import main.config.ConfigBD;
import main.modelo.Equipo;
import main.modelo.Jugador;
import main.modelo.JugadorEquipo;

public class AccesoJugadorEquipo {
	private final static String PATH = "datos\\jugador_equipo.txt";
	public static boolean insertar(JugadorEquipo je) {
		PreparedStatement ps = null;
		Connection conexion = null;
		int resultado = 0;
		try {
			conexion = ConfigBD.abrirConexion();
			
			String query = "INSERT INTO jugador_equipo VALUES(?,?,?,?,?);";
			ps = conexion.prepareStatement(query);
			ps.setInt(1, je.getEquipo().getCodigo());
			ps.setInt(2, je.getJugador().getCodigo());
			ps.setInt(3, je.getAñoEntrada());
			ps.setInt(4, je.getAñoSalida());
			ps.setInt(5, je.getPartidosTitular());
			resultado = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error al ejecutar la inserción: " + e.getMessage());
			e.printStackTrace();
		} finally {
			ConfigBD.cerrarConexion(conexion);
		}
		return resultado > 0;
	}
	
	public static List<JugadorEquipo> consultarTodo() {
		List<JugadorEquipo> listaJE = new ArrayList<JugadorEquipo>();
		Connection conexion = null;
		try {
			conexion = ConfigBD.abrirConexion();
			
			String query = 
					"SELECT e.codigo AS codigo_equipo, "
					+ "e.nombre AS nombre_equipo,"
					+ "e.año_fundacion, "
					+ "e.lugar_sede, "
					+ "e.estadio, "
					+ "e.socios_aficionados,"
					+ "j.codigo AS codigo_jugador, "
					+ "j.nombre AS nombre_jugador,"
					+ "j.fecha_nacimiento, "
					+ "j.nacionalidad, "
					+ "j.posicion,"
					+ "je.año_entrada, "
					+ "je.año_salida, "
					+ "je.partidos_titular"
					+ "FROM "
					+ "equipo e "
					+ "JOIN "
					+ "jugador_equipo je "
					+ "ON e.codigo = je.codigo_equipo"
					+ "JOIN "
					+ "jugador j "
					+ "ON j.codigo = je.codigo_jugador"
					+ "ORDER BY je.año_entrada;";
			PreparedStatement ps = conexion.prepareStatement(query);
			ResultSet resultado = ps.executeQuery();
			while (resultado.next()) {
				int codigoEquipo = resultado.getInt("codigo_equipo");
				String nombreEquipo = resultado.getString("nombre_equipo");
				int añoFundacion = resultado.getInt("año_fundacion");
				String lugar_sede = resultado.getString("lugar_sede");
				String estadio = resultado.getString("estadio");
				int sociosAficionados = resultado.getInt("socios_aficionados");
				int codigoJugador= resultado.getInt("codigo_jugador");
				String nombreJugador = resultado.getString("nombre_jugador");
				String fechaNacimiento = resultado.getString("fecha_nacimiento");
				String nacionalidad = resultado.getString("nacionalidad");
				String posicion = resultado.getString("posicion");
				int añoEntrada = resultado.getInt("año_entrada");
				int añoSalida= resultado.getInt("año_salida");
				int partidosTitular = resultado.getInt("partidos_titular");
				Equipo equipo = new Equipo(codigoEquipo, nombreEquipo, añoFundacion, lugar_sede, estadio, sociosAficionados);
				Jugador jugador = new Jugador(codigoJugador, nombreJugador, fechaNacimiento, nacionalidad, posicion);
				JugadorEquipo je = new JugadorEquipo(equipo, jugador, añoEntrada, añoSalida, partidosTitular);
				listaJE.add(je);
			}
		} catch (SQLException e) {
			System.out.println("Error al ejecutar la consulta: " + e.getMessage());
			e.printStackTrace();
		} finally {
			ConfigBD.cerrarConexion(conexion);
		}
		return listaJE;
	}
	
	public static JugadorEquipo	consultar(Jugador jugador, Equipo equipo) {
		JugadorEquipo je = null;
		Connection conexion = null;
		try {
			conexion = ConfigBD.abrirConexion();
			
			String query = "SELECT e.codigo AS codigo_equipo, e.nombre AS nombre_equipo,"
					+ "e.año_fundacion, e.lugar_sede, e.estadio, e.socios_aficionados,"
					+ "j.codigo AS codigo_jugador, j.nombre AS nombre_jugador,"
					+ "j.fecha_nacimiento, j.nacionalidad, j.posicion,"
					+ "je.año_entrada, je.año_salida, je.partidos_titular"
					+ "FROM equipo e JOIN jugador_equipo je ON e.codigo = je.codigo_equipo"
					+ "JOIN jugador j ON j.codigo = je.codigo_jugador"
					+ "WHERE je.codigo_jugador = ? AND je.codigo_equipo = ?;";
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, jugador.getCodigo());
			ps.setInt(2, equipo.getCodigo());
			ResultSet resultado = ps.executeQuery();
			while (resultado.next()) {
				int añoEntrada = resultado.getInt("año_entrada");
				int añoSalida= resultado.getInt("año_salida");
				int partidosTitular = resultado.getInt("partidos_titular");
				je = new JugadorEquipo(equipo, jugador, añoEntrada, añoSalida, partidosTitular);
			}
		} catch (SQLException e) {
			System.out.println("Error al ejecutar la consulta: " + e.getMessage());
			e.printStackTrace();
		} finally {
			ConfigBD.cerrarConexion(conexion);
		}
		return je;
	}
	
	public static boolean actualizar(JugadorEquipo je) {
		PreparedStatement ps = null;
		Connection conexion = null;
		int resultado = 0;
		try {
			conexion = ConfigBD.abrirConexion();
			String query = "UPDATE jugador_equipo"
					+ "SET año_entrada = ?, año_salida = ?, partidos_titular = ?"
					+ "WHERE codigo_equipo = ? AND codigo_jugador = ?;";
			ps = conexion.prepareStatement(query);
			ps.setInt(1, je.getAñoEntrada());
			ps.setInt(2, je.getAñoSalida());
			ps.setInt(3, je.getPartidosTitular());
			ps.setInt(4, je.getEquipo().getCodigo());
			ps.setInt(5, je.getJugador().getCodigo());
			resultado = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error al ejecutar la inserción: " + e.getMessage());
			e.printStackTrace();
		} finally {
			ConfigBD.cerrarConexion(conexion);
		}
		return resultado > 0;
	}
	
	public static boolean eliminar(JugadorEquipo je) {
		PreparedStatement ps = null;
		Connection conexion = null;
		int resultado = 0;
		try {
			conexion = ConfigBD.abrirConexion();
			String query = "DELETE FROM jugador_equipo"
					+ "WHERE codigo_equipo = ? AND codigo_jugador = ?;";
			ps = conexion.prepareStatement(query);
			ps.setInt(1, je.getAñoEntrada());
			ps.setInt(2, je.getAñoSalida());
			ps.setInt(3, je.getPartidosTitular());
			ps.setInt(4, je.getEquipo().getCodigo());
			ps.setInt(5, je.getJugador().getCodigo());
			resultado = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error al ejecutar la inserción: " + e.getMessage());
			e.printStackTrace();
		} finally {
			ConfigBD.cerrarConexion(conexion);
		}
		return resultado > 0;
	}
	
	public static boolean exportar() {
		BufferedWriter bw = null;
		boolean exito = false;
		try {
			bw = new BufferedWriter(new FileWriter(PATH, false));
			
			List<JugadorEquipo> listaJE = consultarTodo();
			for (JugadorEquipo je: listaJE) {
				bw.write(je.toStringWithSeparators());
				bw.newLine();
			}
			exito = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null) {
					bw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return exito;
	}
	
	public static boolean importar() {
		BufferedReader br = null;
		boolean exito = false;
		try {
			br = new BufferedReader(new FileReader(PATH));
			String linea;
			while ((linea = br.readLine()) != null) {
				JugadorEquipo je = new JugadorEquipo(linea);
				insertar(je);
			}
			exito = true;
		} catch (FileNotFoundException e) {
			System.out.println("Error: fichero no existe " + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return exito;
	}
}
