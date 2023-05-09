package main.modelo;

import main.dao.AccesoEquipo;

/**
 * @author YASSINE EL ATTAR
 *
 */
public class Partido {
	
	private static final String SEPARADOR = ";";
	
	private Equipo equipoLocal;
	private Equipo equipoVisitante;
	private int añoTemporada;
	private String fecha;
	private int puntuacionLocal;
	private int puntuacionVisitante;

	private int codigoEquipoLocal;

	private int codigoEquipoVisitante;


	public Partido(Equipo equipoLocal,Equipo equipoVisitante, int añoTemporada, String fecha, int puntuacionLocal,
			int puntuacionVisitante) {
		super();
		this.equipoLocal = equipoLocal;
		this.equipoVisitante = equipoVisitante;
		this.añoTemporada = añoTemporada;
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
		this.añoTemporada = Integer.parseInt(datos[2]);
		this.fecha = datos[3];
		this.puntuacionLocal = Integer.parseInt(datos[5]);
		this.puntuacionVisitante = Integer.parseInt(datos[5]);
	}
	
	public Partido(int codigoEquipoLocal, int codigoEquipoVisitante, int añoTemporada) {
		super();
		this.codigoEquipoLocal = codigoEquipoLocal;
		this.codigoEquipoVisitante = codigoEquipoVisitante;
		this.añoTemporada = añoTemporada;
	}

	public String toStringWithSeparators() {
		return codigoEquipoLocal + SEPARADOR + codigoEquipoVisitante + SEPARADOR + añoTemporada + SEPARADOR + fecha
				+ SEPARADOR + puntuacionLocal + SEPARADOR + puntuacionVisitante;
	}

	@Override
	public String toString() {
		return "Partido [codigoEquipoLocal=" + codigoEquipoLocal + ", codigoEquipoVisitante=" + codigoEquipoVisitante
				+ ", añoTemporada=" + añoTemporada + ", fecha=" + fecha + ", puntuacionLocal=" + puntuacionLocal
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


	public int getAñoTemporada() {
		return añoTemporada;
	}


	public void setAñoTemporada(int añoTemporada) {
		this.añoTemporada = añoTemporada;
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
