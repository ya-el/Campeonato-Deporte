package modelo;

public class Equipo {
	//Hecho por Alvaro Delicado


	private int codigo;
	private String nombre;
	private int aņoFundacion;
	private String lugarSede;
	private String estadio;
	private int sociosAficionados;

	public Equipo(int codigo, String nombre, int aņoFundacion, String lugarSede, String estadio, int sociosAficionados) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.aņoFundacion = aņoFundacion;
		this.lugarSede = lugarSede;
		this.estadio = estadio;
		this.sociosAficionados = sociosAficionados;
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

	public int getAnioFundacion() {
		return aņoFundacion;
	}

	public void setAnioFundacion(int anioFundacion) {
		this.aņoFundacion = anioFundacion;
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

	public String toString() {
		return "Equipo [codigo = " + codigo + 
				", nombre = " + nombre + 
				", anioFundacion = " + aņoFundacion + 
				", lugarSede = " + lugarSede + 
				", estadio = " + estadio + 
				", sociosAficionados = " + sociosAficionados + "]";
	}

}
