package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import model.keys.ProductoEnPedidoKey;

@Entity
@Table(name = "ProductosPedido")
@IdClass(ProductoEnPedidoKey.class)
public class ProductoEnPedido implements Serializable {

	private static final long serialVersionUID = -371256129L;

	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_pedido", referencedColumnName = "id_pedido")
	private Pedido pedido;

	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
	private Producto producto;

	@OneToMany(mappedBy = "productoPedido")
	private Set<ProductoEnOrdenTrabajo> productosEnOrdenTrabajo = new HashSet<ProductoEnOrdenTrabajo>();

	@OneToMany(mappedBy = "productoPedido")
	private Set<ProductoEnPaquete> productoPaquete;

	private int cantidad;

	@Column(name = "cantidad_asociada_OT")
	private int cantidadAsociadaOT;

	ProductoEnPedido() {

	}

	public ProductoEnPedido(Pedido pedido, Producto producto) {
		Asociacion.AsignarProducto_Pedido.link(pedido, this, producto);
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

	public Set<ProductoEnPaquete> getPaquetes() {
		return new HashSet<ProductoEnPaquete>(productoPaquete);
	}

	Set<ProductoEnPaquete> _getPaquetes() {
		return productoPaquete;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + ((pedido == null) ? 0 : pedido.hashCode());
		result = prime * result + ((producto == null) ? 0 : producto.hashCode());

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
		if (pedido == null) {
			if (other.pedido != null)
				return false;
		} else if (!pedido.equals(other.pedido))
			return false;

		if (producto == null) {
			if (other.producto != null)
				return false;
		} else if (!producto.equals(other.producto))
			return false;

		return true;
	}
}
