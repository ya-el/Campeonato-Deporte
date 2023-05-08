package main.modelo;



/*
 * Beni Burdet DAM 1ºA
 */

public class Jugador {

	private int codigo;
	private String nombre;
	private String fechaNacimiento;
	private String nacionalidad;
	private String posicion;

	public Jugador(int codigo, String nombre, String fechaNacimiento, String nacionalidad, String posicion) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.posicion = posicion;
	}

	public Jugador(String nombre, String fechaNacimiento, String nacionalidad, String posicion) {
		super();
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.posicion = posicion;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getPosicion() {
		return posicion;
	}

	public void setSocios_aficionados(String posicion) {
		this.posicion = posicion;
	}

	@Override
	public String toString() {
		return "\nJugador [codigo=" + codigo + ", nombre=" + nombre + ", fechaNacimiento=" + fechaNacimiento
				+ ", nacionalidad=" + nacionalidad + ", posicion=" + posicion + "]";
	}

}
