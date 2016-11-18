package model;

import java.io.Serializable;
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
import model.types.MetodosPago;
import model.types.PedidoPagado;
import model.types.TipoEnvio;

@Entity
@Table(name = "Pedidos")
public class Pedido implements Serializable {

	private static final long serialVersionUID = 8608870293081680294L;

	@Id
	@Column(name = "id_pedido")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PEDIDOS_SEQ")
	@SequenceGenerator(name = "PEDIDOS_SEQ", sequenceName = "PEDIDOS_SEQ", allocationSize = 1, initialValue = 1)
	private long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	@Column(name = "direccion")
	private String direccionCompleta;

	@Enumerated(EnumType.STRING)
	@Column(name = "metodo_pago")
	private MetodosPago metodoPago;

	@Enumerated(EnumType.STRING)
	private TipoEnvio tipoEnvio;

	@Enumerated(EnumType.STRING)
	private EstadoPedido estado;

	@Enumerated(EnumType.STRING)
	private PedidoPagado pagado;

	@ManyToOne
	@JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
	private Cliente cliente;

	@OneToMany(mappedBy = "pedido")
	private Set<ProductoEnPedido> listaProductosPedidos = new HashSet<ProductoEnPedido>();

	@OneToMany(mappedBy = "pedido")
	private Set<Paquete> paquetes = new HashSet<Paquete>();

	// ==============================================
	// Constructores
	// ==============================================

	protected Pedido() {

	}

	public Pedido(Cliente cliente) {
		Asociacion.Pedir.link(cliente, this);
	}

	// ==============================================
	// Id del pedido
	// ==============================================

	public long getId() {
		return id;
	}

	// ==============================================
	// Fecha en la que se hizo
	// ==============================================

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	// ==============================================
	// Direccion a la que hay que enviarlo
	// ==============================================

	public String getDireccionCompleta() {
		return direccionCompleta;
	}

	public void setDireccionCompleta(String direccionCompleta) {
		this.direccionCompleta = direccionCompleta;
	}

	// ==============================================
	// Metodo de pago
	// ==============================================

	public MetodosPago getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(MetodosPago metodoPago) {
		this.metodoPago = metodoPago;
	}

	// ==============================================
	// Tipo de envío
	// ==============================================

	public TipoEnvio getTipoEnvio() {
		return tipoEnvio;
	}

	public void setTipoEnvio(TipoEnvio tipoEnvio) {
		this.tipoEnvio = tipoEnvio;
	}

	// ==============================================
	// Estado del pedido
	// ==============================================

	public EstadoPedido getEstado() {
		return estado;
	}

	public void setEstado(EstadoPedido estado) {
		this.estado = estado;
	}

	// ==============================================
	// Esta pagado el pedido
	// ==============================================

	public PedidoPagado getPagado() {
		return pagado;
	}

	public void setPagado(PedidoPagado pagado) {
		this.pagado = pagado;
	}

	// ==============================================
	// Cliente que hizo el pedido
	// ==============================================

	public Cliente getCliente() {
		return cliente;
	}

	void _setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	// ==============================================
	// Productos que forman el pedido
	// ==============================================

	public Set<ProductoEnPedido> getListaProductosPedidos() {
		return new HashSet<ProductoEnPedido>(listaProductosPedidos);
	}

	Set<ProductoEnPedido> _getListaProductosPedidos() {
		return listaProductosPedidos;
	}

	// ==============================================
	// Paquetes en los que esta el pedido
	// ==============================================

	public Set<Paquete> getPaquetes() {
		return new HashSet<Paquete>(paquetes);
	}

	Set<Paquete> _getPaquetes() {
		return paquetes;
	}

	// ==============================
	// Equals y HashCode
	// ==============================

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