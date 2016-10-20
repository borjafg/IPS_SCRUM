package model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import model.types.EstadoPedido;

@Entity
@Table(name = "Pedidos")
public class Pedido {

	@Id
	@Column(name = "id_pedido")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PEDIDOS_SEQ")
	@SequenceGenerator(name = "PEDIDOS_SEQ", sequenceName = "PEDIDOS_SEQ", allocationSize=1)
	private long id;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Column(name = "direccion")
	private String direccionCompleta;

	@Enumerated(EnumType.STRING)
	private EstadoPedido estado;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente", referencedColumnName="id_cliente")
	private Cliente cliente;

	@OneToMany(mappedBy = "pedido")
	private Set<ProductoEnPedido> listaProductosPedidos = new HashSet<ProductoEnPedido>();

	Pedido() {

	}

	public Pedido(Cliente cliente) {
		Asociacion.Pedir.link(cliente, this);
	}

	public long getId() {
		return id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDireccionCompleta() {
		return direccionCompleta;
	}

	public void setDireccionCompleta(String direccionCompleta) {
		this.direccionCompleta = direccionCompleta;
	}
	
	public EstadoPedido getEstado() {
		return estado;
	}
	
	public void setEstado(EstadoPedido estado) {
		this.estado = estado;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	void _setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Set<ProductoEnPedido> getListaProductosPedidos() {
		return new HashSet<ProductoEnPedido>(listaProductosPedidos);
	}

	Set<ProductoEnPedido> _getListaProductosPedidos() {
		return listaProductosPedidos;
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

		Pedido other = (Pedido) obj;
		if (id != other.id)
			return false;

		return true;
	}
}