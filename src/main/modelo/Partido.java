package main.modelo;

import main.dao.AccesoEquipo;

/**
 * @author YASSINE EL ATTAR lunes
 *
 */
public class Partido {
	
	private static final String SEPARADOR = ";";
	
	private Equipo equipoLocal;
	private Equipo equipoVisitante;
	private int aņoTemporada;
	private String fecha;
	private int puntuacionLocal;
	private int puntuacionVisitante;

	private int codigoEquipoLocal;

	private int codigoEquipoVisitante;


	public Partido(Equipo equipoLocal,Equipo equipoVisitante, int aņoTemporada, String fecha, int puntuacionLocal,
			int puntuacionVisitante) {
		super();
		this.equipoLocal = equipoLocal;
		this.equipoVisitante = equipoVisitante;
		this.aņoTemporada = aņoTemporada;
		this.fecha = fecha;
		this.puntuacionLocal = puntuacionLocal;
		this.puntuacionVisitante = puntuacionVisitante;
	}
	
	public Partido(String linea) {
		super();
		String[] datos = linea.split(SEPARADOR);
		int codigoEquipoLocal = Integer.parseInt(datos[0]);
		this.equipoLocal =  AccesoEquipo.consultar(codigoEquipoLocal);
		int codigoEquipoVisitante = Integer.parseInt(datos[1]);
		this.equipoVisitante =  AccesoEquipo.consultar(codigoEquipoLocal);
		this.aņoTemporada = Integer.parseInt(datos[2]);
		this.fecha = datos[3];
		this.puntuacionLocal = Integer.parseInt(datos[5]);
		this.puntuacionVisitante = Integer.parseInt(datos[5]);
	}
	
	public Partido(int codigoEquipoLocal, int codigoEquipoVisitante, int aņoTemporada) {
		super();
		this.codigoEquipoLocal = codigoEquipoLocal;
		this.codigoEquipoVisitante = codigoEquipoVisitante;
		this.aņoTemporada = aņoTemporada;
	}

	public String toStringWithSeparators() {
		return codigoEquipoLocal + SEPARADOR + codigoEquipoVisitante + SEPARADOR + aņoTemporada + SEPARADOR + fecha
				+ SEPARADOR + puntuacionLocal + SEPARADOR + puntuacionVisitante;
	}

	@Override
	public String toString() {
		return "Partido [codigoEquipoLocal=" + codigoEquipoLocal + ", codigoEquipoVisitante=" + codigoEquipoVisitante
				+ ", aņoTemporada=" + aņoTemporada + ", fecha=" + fecha + ", puntuacionLocal=" + puntuacionLocal
				+ ", puntuacionVisitante=" + puntuacionVisitante + "]";
	}


	public Equipo getEquipoLocal() {
		return equipoLocal;
		//return equipo.getCodigo();
	}


	public Equipo getEquipoVisitante() {
		return equipoVisitante;
		//return equipo.getCodigo();
	}


	public int getAņoTemporada() {
		return aņoTemporada;
	}


	public void setAņoTemporada(int aņoTemporada) {
		this.aņoTemporada = aņoTemporada;
	}


	public String getFecha() {
		return fecha;
	}


	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	public int getPuntuacionLocal() {
		return puntuacionLocal;
	}


	public void setPuntuacionLocal(int puntuacionLocal) {
		this.puntuacionLocal = puntuacionLocal;
	}


	public int getPuntuacionVisitante() {
		return puntuacionVisitante;
	}


	public void setPuntuacionVisitante(int puntuacionVisitante) {
		this.puntuacionVisitante = puntuacionVisitante;
	}



	


	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
