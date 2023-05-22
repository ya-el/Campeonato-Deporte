package main.modelo;

public class EquipoEstadistica {
	
	private int ganados;
	private int perdidos;
	private int empatados;
	
	public EquipoEstadistica(int ganados, int perdidos, int empatados) {
		super();
		this.ganados = ganados;
		this.perdidos = perdidos;
		this.empatados = empatados;
	}

	@Override
	public String toString() {
		return "EquipoEstadistica [ganados=" + ganados + ", perdidos=" + perdidos + ", empatados=" + empatados + "]";
	}

	public int getGanados() {
		return ganados;
	}

	public void setGanados(int ganados) {
		this.ganados = ganados;
	}

	public int getPerdidos() {
		return perdidos;
	}

	public void setPerdidos(int perdidos) {
		this.perdidos = perdidos;
	}

	public int getEmpatados() {
		return empatados;
	}

	public void setEmpatados(int empatados) {
		this.empatados = empatados;
	}
	
	
}
