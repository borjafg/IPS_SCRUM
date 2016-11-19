package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Transportista implements Serializable {

	private static final long serialVersionUID = -6506192766768385279L;

	@Id
	@Column(name = "id_transportista")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRANSPORTISTAS_SEQ")
	@SequenceGenerator(name = "TRANSPORTISTAS_SEQ", sequenceName = "TRANSPORTISTAS_SEQ", allocationSize = 1, initialValue = 1)
	private Long id;

	private String nombre;

	@OneToMany(mappedBy = "transportista")
	private Set<Envio> envios = new HashSet<Envio>();

	protected Transportista() {

	}

	public Transportista(String nombre) {
		this.nombre = nombre;
	}

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Envio> getEnvios() {
		return new HashSet<Envio>(envios);
	}

	Set<Envio> _getEnvios() {
		return envios;
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

		Transportista other = (Transportista) obj;

		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		return true;
	}

}