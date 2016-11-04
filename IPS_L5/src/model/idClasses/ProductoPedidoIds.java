package model.idClasses;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProductoPedidoIds implements Serializable {

	private static final long serialVersionUID = 1701666183527842466L;

	@Column(name = "id_producto")
	private long producto;
	
	@Column(name = "id_pedido")
	private long pedido;

	public ProductoPedidoIds() {

	}

	public ProductoPedidoIds(long producto, long pedido) {
		this.producto = producto;
		this.pedido = pedido;
	}

	public long getProducto() {
		return producto;
	}
	
	public void setProducto(long producto) {
		this.producto = producto;
	}

	public long getPedido() {
		return pedido;
	}
	
	public void setPedido(long pedido) {
		this.pedido = pedido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;

		int result = 1;

		result = prime * result + (int) (pedido ^ (pedido >>> 32));
		result = prime * result + (int) (producto ^ (producto >>> 32));

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

		ProductoPedidoIds other = (ProductoPedidoIds) obj;

		if (pedido != other.pedido)
			return false;

		if (producto != other.producto)
			return false;

		return true;
	}
}