package main.modelo;

public class Equipo {
	//Hecho por Alvaro Delicado

	private static final String SEPARATOR = ";";
	private int codigo;
	private String nombre;
	private int añoFundacion;
	private String lugarSede;
	private String estadio;
	private int sociosAficionados;

	public Equipo(int codigo, String nombre, int añoFundacion, String lugarSede, String estadio, int sociosAficionados) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.añoFundacion = añoFundacion;
		this.lugarSede = lugarSede;
		this.estadio = estadio;
		this.sociosAficionados = sociosAficionados;
	}
	
	

	/*public Equipoe(String nombre, int añoFundacion, String lugarSede, String estadio, int sociosAficionados) {
		this.nombre = nombre;
		this.añoFundacion = añoFundacion;
		this.lugarSede = lugarSede;
		this.estadio = estadio;
		this.sociosAficionados = sociosAficionados;
	}
*/


	public Equipo(int codigo, String nombre) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
	}



	public Equipo(String nombre, int añoFundacion, String lugarSede, String estadio, int sociosAficionados) {
		this.nombre = nombre;
		this.añoFundacion = añoFundacion;
		this.lugarSede = lugarSede;
		this.estadio = estadio;
		this.sociosAficionados = sociosAficionados;
	}
	



	public Equipo(int codigoEquipo) {
		this.codigo = codigoEquipo;
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

	public int getAñoFundacion() {
		return añoFundacion;
	}

	public void setAñoFundacion(int anioFundacion) {
		this.añoFundacion = anioFundacion;
	}

	public String getLugarSede() {
		return lugarSede;
	}

	public void setLugarSede(String lugarSede) {
		this.lugarSede = lugarSede;
	}

	public String getEstadio() {
		return estadio;
	}

	public void setEstadio(String estadio) {
		this.estadio = estadio;
	}

	public int getSociosAficionados() {
		return sociosAficionados;
	}

	public void setSociosAficionados(int sociosAficionados) {
		this.sociosAficionados = sociosAficionados;
	}

	
	public String toStringWithSeparators() {
		return codigo + SEPARATOR 
				+ nombre + SEPARATOR 
				+ añoFundacion + SEPARATOR 
				+ lugarSede + SEPARATOR 
				+ estadio + SEPARATOR 
				+ sociosAficionados;
	}

	@Override
	public String toString() {
		return "Equipo [codigo=" + codigo + ", nombre=" + nombre + ", añoFundacion=" + añoFundacion + ", lugarSede="
				+ lugarSede + ", estadio=" + estadio + ", sociosAficionados=" + sociosAficionados + "]";
	}
	
	
	
	

}