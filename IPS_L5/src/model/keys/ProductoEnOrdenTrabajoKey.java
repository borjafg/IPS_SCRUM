package model.keys;

import java.io.Serializable;

public class ProductoEnOrdenTrabajoKey implements Serializable {

	private static final long serialVersionUID = -48282903L;

	long ordenTrabajo;
	ProductoEnPedidoKey productoPedido;

	ProductoEnOrdenTrabajoKey() {

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + (int) (ordenTrabajo ^ (ordenTrabajo >>> 32));
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

		ProductoEnOrdenTrabajoKey other = (ProductoEnOrdenTrabajoKey) obj;

		if (ordenTrabajo != other.ordenTrabajo)
			return false;

		if (productoPedido == null) {
			if (other.productoPedido != null)
				return false;
		} else if (!productoPedido.equals(other.productoPedido))
			return false;

		return true;
	}
}