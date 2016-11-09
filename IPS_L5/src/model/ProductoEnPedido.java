package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import model.idClasses.ProductoPedidoIds;

@Entity
@Table(name = "ProductosPedido")
public class ProductoEnPedido implements Serializable {

	private static final long serialVersionUID = -371256129L;

	@EmbeddedId
	private ProductoPedidoIds id = new ProductoPedidoIds();

	@MapsId(value = "pedido") // Dentro del id a qué atributo referencia
	@ManyToOne
	@JoinColumn(name = "id_pedido", referencedColumnName = "id_pedido")
	private Pedido pedido;

	@MapsId(value = "producto")
	@ManyToOne
	@JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
	private Producto producto;

	@OneToMany(mappedBy = "productoPedido")
	private Set<ProductoEnOrdenTrabajo> productosEnOrdenTrabajo = new HashSet<ProductoEnOrdenTrabajo>();

	private int cantidad;

	@Column(name = "cantidad_asociada_OT")
	private int cantidadAsociadaOT;

	protected ProductoEnPedido() {

	}

	public ProductoEnPedido(Pedido pedido, Producto producto) {
		Asociacion.AsignarProducto_Pedido.link(pedido, this, producto);
	}

	public ProductoPedidoIds getId() {
		return id;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getCantidadAsociadaOT() {
		return cantidadAsociadaOT;
	}

	public void setCantidadAsociadaOT(int cantidadAsociadaOT) {
		this.cantidadAsociadaOT = cantidadAsociadaOT;
	}

	public Pedido getPedido() {
		return pedido;
	}

	void _setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Producto getProducto() {
		return producto;
	}

	void _setProducto(Producto producto) {
		this.producto = producto;
	}

	public Set<ProductoEnOrdenTrabajo> getProductosEnOrdenTrabajo() {
		return new HashSet<ProductoEnOrdenTrabajo>(productosEnOrdenTrabajo);
	}

	Set<ProductoEnOrdenTrabajo> _getProductosEnOrdenTrabajo() {
		return productosEnOrdenTrabajo;
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

		ProductoEnPedido other = (ProductoEnPedido) obj;

		if (id == null) {
			if (other.id != null)
				return false;
			
		} else if (!id.equals(other.id))
			return false;
		
		return true;
	}
}