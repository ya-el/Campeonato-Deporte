/**
 * @author Victor Estella
 */
package main.modelo;

import main.dao.AccesoEquipo;
import main.dao.AccesoJugador;


public class JugadorEquipo {
	private static final String SEPARADOR = ";";
	
	private Equipo equipo;
	private Jugador jugador;
	private int añoEntrada;
	private int añoSalida;
	private int partidosTitular;
	
	/**
	 * @param equipo
	 * @param jugador
	 * @param añoEntrada
	 * @param añoSalida
	 * @param partidosTitular
	 */
	public JugadorEquipo(Equipo equipo, Jugador jugador, int añoEntrada, int añoSalida, int partidosTitular) {
		this.equipo = equipo;
		this.jugador = jugador;
		this.añoEntrada = añoEntrada;
		this.añoSalida = añoSalida;
		this.partidosTitular = partidosTitular;
	}
	
	public JugadorEquipo(String cadena) {
		String[] parametros = cadena.split(SEPARADOR);
		int codigoEquipo = Integer.parseInt(parametros[0]);
		this.equipo = AccesoEquipo.consultar(codigoEquipo);
		int codigoJugador = Integer.parseInt(parametros[1]);
		this.jugador = AccesoJugador.consultarPorCodigo(codigoJugador);
		this.añoEntrada = Integer.parseInt(parametros[2]);
		this.añoSalida = Integer.parseInt(parametros[3]);
		this.añoEntrada = Integer.parseInt(parametros[2]);
	}

	/**
	 * @return the equipo
	 */
	public Equipo getEquipo() {
		return equipo;
	}

	/**
	 * @param equipo the equipo to set
	 */
	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	/**
	 * @return the jugador
	 */
	public Jugador getJugador() {
		return jugador;
	}

	/**
	 * @param jugador the jugador to set
	 */
	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	/**
	 * @return the añoEntrada
	 */
	public int getAñoEntrada() {
		return añoEntrada;
	}

	/**
	 * @param añoEntrada the añoEntrada to set
	 */
	public void setAñoEntrada(int añoEntrada) {
		this.añoEntrada = añoEntrada;
	}

	/**
	 * @return the añoSalida
	 */
	public int getAñoSalida() {
		return añoSalida;
	}

	/**
	 * @param añoSalida the añoSalida to set
	 */
	public void setAñoSalida(int añoSalida) {
		this.añoSalida = añoSalida;
	}

	/**
	 * @return the partidosTitular
	 */
	public int getPartidosTitular() {
		return partidosTitular;
	}

	/**
	 * @param partidosTitular the partidosTitular to set
	 */
	public void setPartidosTitular(int partidosTitular) {
		this.partidosTitular = partidosTitular;
	}

	@Override
	public String toString() {
		return "JugadorEquipo ["
				+ "equipo=" + equipo
				+ ", jugador=" + jugador
				+ ", añoEntrada=" + añoEntrada
				+ ", añoSalida=" + añoSalida
				+ ", partidosTitular=" + partidosTitular
				+ "]";
	}
	
	public String toStringWithSeparators() {
		return equipo.getCodigo() + SEPARADOR +
				jugador.getCodigo() + SEPARADOR +
				añoEntrada + SEPARADOR +
				añoSalida + SEPARADOR +
				partidosTitular;
	}
	
}
