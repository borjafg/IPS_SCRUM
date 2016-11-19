package model.types;

import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class Tarjeta {

	private Long numeroTarjeta;

	private int codigoSeguridad;

	@Temporal(TemporalType.DATE)
	private Date fechaCaducidad;

	@Enumerated(EnumType.STRING)
	private TipoTarjeta tipoTarjeta;

	protected Tarjeta() {
				
	}
	
	public Tarjeta(Long numeroTarjeta, int codigoSeguridad, Date fechaCaducidad, TipoTarjeta tipoTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
		this.codigoSeguridad = codigoSeguridad;
		this.fechaCaducidad = fechaCaducidad;
		this.tipoTarjeta = tipoTarjeta;
	}

	public Long getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(Long numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public int getCodigoSeguridad() {
		return codigoSeguridad;
	}

	public void setCodigoSeguridad(int codigoSeguridad) {
		this.codigoSeguridad = codigoSeguridad;
	}

	public Date getFechaCaducidad() {
		return fechaCaducidad;
	}

	public void setFechaCaducidad(Date fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	public TipoTarjeta getTipoTarjeta() {
		return tipoTarjeta;
	}

	public void setTipoTarjeta(TipoTarjeta tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}

}