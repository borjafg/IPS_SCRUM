package model.idClasses;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProductoOrdenTrabajoIds implements Serializable {

	private static final long serialVersionUID = -1223586949206560020L;

	@Column(name = "id_ordenTrabajo")
	private long ordenTrabajo;

	private ProductoPedidoIds productoPedido;

	public ProductoOrdenTrabajoIds() {

	}

	public ProductoOrdenTrabajoIds(long ordenTrabajo, ProductoPedidoIds productoPedido) {
		this.ordenTrabajo = ordenTrabajo;
		this.productoPedido = productoPedido;
	}

	public long getOrdenTrabajo() {
		return ordenTrabajo;
	}
	
	protected void setOrdenTrabajo(long ordenTrabajo) {
		this.ordenTrabajo = ordenTrabajo;
	}

	public ProductoPedidoIds getProductoPedido() {
		return productoPedido;
	}
	
	protected void setOrdenTrabajo(ProductoPedidoIds productoPedido) {
		this.productoPedido = productoPedido;
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
		
		ProductoOrdenTrabajoIds other = (ProductoOrdenTrabajoIds) obj;
		
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