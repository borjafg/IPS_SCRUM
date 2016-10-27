package ui.almacen.almacenero.util;

import model.Incidencia;

public class MyIncidencia {
	
	private Incidencia incidencia;
	
	public MyIncidencia(Incidencia incidencia) {
		this.incidencia = incidencia;
	}
	
	public String toString() {
		return "Causa: " + this.incidencia.getCausa() + " - Fecha: " + this.incidencia.getFecha();
	}

}
