package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.sqlite.SQLiteConfig;

public class ConfigBD {

	private static final String DRIVER = "org.sqlite.JDBC";
	private static final String URLBD = "jdbc:sqlite:db\\campeonato.db";

	public static Connection abrirConexion() {
		Connection conexion = null;

		try {
			// Carga el driver
			Class.forName(DRIVER);
			// Configurar db
			SQLiteConfig config = new SQLiteConfig();
			config.enforceForeignKeys(true);
			// Abre conexión
			conexion = DriverManager.getConnection(URLBD, config.toProperties());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Error al cargar driver" + e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error al obtener la conexión a la bd" + e.getMessage());
			e.printStackTrace();
		}

		return conexion;

	}

	public static void cerrarConexion(Connection conexion) {
		// TODO Auto-generated method stub
		try {
			conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
