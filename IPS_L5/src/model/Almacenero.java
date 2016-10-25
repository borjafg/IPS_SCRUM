package model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Almaceneros")
public class Almacenero {

	@Id
	@Column(name = "id_almacenero")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ALMACENEROS_SEQ")
	@SequenceGenerator(name = "ALMACENEROS_SEQ", sequenceName = "ALMACENEROS_SEQ", allocationSize = 1, initialValue = 1)
	private long id;

	@Column(unique = true)
	private String login;

	@OneToMany(mappedBy = "almaceneroRecoger")
	private Set<OrdenTrabajo> ordenesTrabajoRecoger = new HashSet<OrdenTrabajo>();

	@OneToMany(mappedBy = "almaceneroEmpaquetar")
	private Set<OrdenTrabajo> ordenesTrabajoEmpaquetar = new HashSet<OrdenTrabajo>();

	public Almacenero() {

	}

	public long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Set<OrdenTrabajo> getOrdenesTrabajoRecoger() {
		return new HashSet<OrdenTrabajo>(ordenesTrabajoRecoger);
	}

	Set<OrdenTrabajo> _getOrdenesTrabajoRecoger() {
		return ordenesTrabajoRecoger;
	}

	public Set<OrdenTrabajo> getOrdenesTrabajoEmpaquetar() {
		return new HashSet<OrdenTrabajo>(ordenesTrabajoEmpaquetar);
	}

	Set<OrdenTrabajo> _getOrdenesTrabajoEmpaquetar() {
		return ordenesTrabajoEmpaquetar;
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

		Almacenero other = (Almacenero) obj;

		if (id != other.id)
			return false;

		return true;
	}
}