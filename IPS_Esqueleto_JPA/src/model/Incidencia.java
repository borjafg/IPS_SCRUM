package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Incidencias")
public class Incidencia implements Serializable {

	private static final long serialVersionUID = -189376471L;

	@Id
	@Column(name = "id_incidencia")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INCIDENCIAS_SEQ")
	@SequenceGenerator(name = "INCIDENCIAS_SEQ", sequenceName = "INCIDENCIAS_SEQ", allocationSize=1)
	private long id;

	@ManyToOne
	@JoinColumn(name = "id_ordenTrabajo")
	private OrdenTrabajo ordenTrabajo;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private String causa;

	Incidencia() {

	}

	public Incidencia(OrdenTrabajo ordenTrabajo) {
		Asociacion.RegistrarIncidencia.link(ordenTrabajo, this);
	}

	public long getId() {
		return id;
	}

	public OrdenTrabajo getOrdenTrabajo() {
		return ordenTrabajo;
	}

	void _setOrdenTrabajo(OrdenTrabajo ordenTrabajo) {
		this.ordenTrabajo = ordenTrabajo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getCausa() {
		return causa;
	}

	public void setCausa(String causa) {
		this.causa = causa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + (int) (id ^ (id >>> 32));

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (getClass() != obj.getClass())
			return false;

		Incidencia other = (Incidencia) obj;

		if (id != other.id)
			return false;

		return true;
	}
}