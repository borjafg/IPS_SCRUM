package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import model.types.MetodosPago;

@Entity
@Table(name = "Clientes")
public class Cliente implements Serializable {

	private static final long serialVersionUID = -189376471L;

	@Id
	@Column(name = "id_cliente")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLIENTES_SEQ")
	@SequenceGenerator(name = "CLIENTES_SEQ", sequenceName = "CLIENTES_SEQ", allocationSize=1)
	private long id;

	private String nombre;

	@Enumerated(EnumType.STRING)
	@Column(name = "metodo_pago")
	private MetodosPago metodoPago;

	@Column(name = "direccion")
	private String direccionCompleta;

	@OneToMany(mappedBy = "cliente")
	private Set<Pedido> pedidos = new HashSet<Pedido>();

	public Cliente() {

	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public MetodosPago getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(MetodosPago metodoPago) {
		this.metodoPago = metodoPago;
	}

	public String getDireccionCompleta() {
		return direccionCompleta;
	}

	public void setDireccionCompleta(String direccionCompleta) {
		this.direccionCompleta = direccionCompleta;
	}

	public Set<Pedido> getPedidos() {
		return new HashSet<Pedido>(pedidos);
	}

	Set<Pedido> _getPedidos() {
		return pedidos;
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

		Cliente other = (Cliente) obj;
		if (id != other.id)
			return false;

		return true;
	}
}