package model.keys;

import java.io.Serializable;

public class ProductoEnPedidoKey implements Serializable {

	private static final long serialVersionUID = -90532127L;

	long pedido;
	long producto;

	ProductoEnPedidoKey() {

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

		ProductoEnPedidoKey other = (ProductoEnPedidoKey) obj;

		if (pedido != other.pedido)
			return false;

		if (producto != other.producto)
			return false;

		return true;
	}
}