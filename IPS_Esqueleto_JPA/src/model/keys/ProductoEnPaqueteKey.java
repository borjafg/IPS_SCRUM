package model.keys;

import java.io.Serializable;

public class ProductoEnPaqueteKey implements Serializable {

	private static final long serialVersionUID = -28027885L;

	ProductoEnPedidoKey productoPedido;
	long paquete;

	ProductoEnPaqueteKey() {

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + (int) (paquete ^ (paquete >>> 32));
		result = prime * result + ((productoPedido == null) ? 0 : productoPedido.hashCode());

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

		ProductoEnPaqueteKey other = (ProductoEnPaqueteKey) obj;

		if (paquete != other.paquete)
			return false;

		if (productoPedido == null) {
			if (other.productoPedido != null)
				return false;
		} else if (!productoPedido.equals(other.productoPedido))
			return false;
		return true;
	}
}