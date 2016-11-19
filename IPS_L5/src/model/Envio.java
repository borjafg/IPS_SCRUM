package model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Envio implements Serializable {

	private static final long serialVersionUID = -18776459457658774L;

	@Id
	@Column(name = "id_envio")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ENVIOS_SEQ")
	@SequenceGenerator(name = "ENVIOS_SEQ", sequenceName = "ENVIOS_SEQ", allocationSize = 1, initialValue = 1)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	@ManyToOne
	@JoinColumn(name = "id_transportista", referencedColumnName = "id_transportista")
	private Transportista transportista;

	@OneToMany(mappedBy = "envio")
	private Set<Paquete> paquetesEnvio = new HashSet<Paquete>();

	public Envio(Transportista transportista) {
		Asociacion.AsignarEnvio_Transportista.link(this, transportista);
	}

	public Long getId() {
		return id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Transportista getTransportista() {
		return transportista;
	}

	void setTransportista(Transportista transportista) {
		this.transportista = transportista;
	}

	public Set<Paquete> getPaquetesEnvio() {
		return new HashSet<Paquete>(paquetesEnvio);
	}

	Set<Paquete> _getPaquetesEnvio() {
		return paquetesEnvio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;

		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());

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

		Envio other = (Envio) obj;

		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		return true;
	}

}