package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import model.types.Tarjeta;
import model.types.TipoCliente;

@Entity
@Table(name = "Clientes")
public class Cliente implements Serializable {

	private static final long serialVersionUID = -189376471L;

	@Id
	@Column(name = "id_cliente")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLIENTES_SEQ")
	@SequenceGenerator(name = "CLIENTES_SEQ", sequenceName = "CLIENTES_SEQ", allocationSize = 1, initialValue = 1)
	private long id;

	private String login;

	private String nombre;

	@Enumerated(EnumType.STRING)
	private TipoCliente tipoCliente;

	@Column(name = "direccion")
	private String direccionCompleta;

	@Embedded
	private Tarjeta tarjeta;

	@OneToMany(mappedBy = "cliente")
	private Set<Pedido> pedidos = new HashSet<Pedido>();

	// =======================================
	// Constructor
	// =======================================

	public Cliente() {

	}

	// =======================================
	// Id del cliente
	// =======================================

	public long getId() {
		return id;
	}

	// =======================================
	// Login del cliente
	// =======================================

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	// =======================================
	// Nombre del cliente
	// =======================================

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	// =======================================
	// Tipo de cliente
	// =======================================

	public TipoCliente getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	// =======================================
	// Direccion del cliente
	// =======================================

	public String getDireccionCompleta() {
		return direccionCompleta;
	}

	public void setDireccionCompleta(String direccionCompleta) {
		this.direccionCompleta = direccionCompleta;
	}

	// =======================================
	// Tarjeta del cliente
	// =======================================

	public Tarjeta getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(Tarjeta tarjeta) {
		this.tarjeta = tarjeta;
	}

	// =======================================
	// Lista de pedidos del cliente
	// =======================================

	public Set<Pedido> getPedidos() {
		return new HashSet<Pedido>(pedidos);
	}

	Set<Pedido> _getPedidos() {
		return pedidos;
	}

	// =======================================
	// HashCode - Equals
	// =======================================

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

		Cliente other = (Cliente) obj;
		if (id != other.id)
			return false;

		return true;
	}
}