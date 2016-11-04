package model.idClasses;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProductoPaqueteIds implements Serializable {

	private static final long serialVersionUID = 8628075489649270087L;

	@Column(name = "id_paquete")
	private long paquete;

	private ProductoOrdenTrabajoIds productoOrdenTrabajo;

	public ProductoPaqueteIds() {

	}

	public ProductoPaqueteIds(long paquete, ProductoOrdenTrabajoIds productoOrdenTrabajo) {
		this.paquete = paquete;
		this.productoOrdenTrabajo = productoOrdenTrabajo;
	}

	public long getPaquete() {
		return paquete;
	}

	public void getPaquete(long paquete) {
		this.paquete = paquete;
	}

	public ProductoOrdenTrabajoIds getProductoOrdenTrabajo() {
		return productoOrdenTrabajo;
	}

	protected void setProductoOrdenTrabajo(ProductoOrdenTrabajoIds productoOrdenTrabajo) {
		this.productoOrdenTrabajo = productoOrdenTrabajo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;

		int result = 1;

		result = prime * result + (int) (paquete ^ (paquete >>> 32));
		result = prime * result + ((productoOrdenTrabajo == null) ? 0 : productoOrdenTrabajo.hashCode());

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

		ProductoPaqueteIds other = (ProductoPaqueteIds) obj;

		if (paquete != other.paquete)
			return false;

		if (productoOrdenTrabajo == null) {
			if (other.productoOrdenTrabajo != null)
				return false;
		} else if (!productoOrdenTrabajo.equals(other.productoOrdenTrabajo))
			return false;

		return true;
	}

}