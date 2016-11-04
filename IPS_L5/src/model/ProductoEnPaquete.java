package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import model.idClasses.ProductoPaqueteIds;

@Entity
@Table(name = "ProductosPaquete")
public class ProductoEnPaquete implements Serializable {

	private static final long serialVersionUID = -371256129L;

	@EmbeddedId
	private ProductoPaqueteIds id = new ProductoPaqueteIds();

	@MapsId(value = "productoOrdenTrabajo")
	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "id_ordenTrabajo", referencedColumnName = "id_ordenTrabajo"),
			@JoinColumn(name = "id_pedido", referencedColumnName = "id_pedido"),
			@JoinColumn(name = "id_producto", referencedColumnName = "id_producto") })
	private ProductoEnOrdenTrabajo productoOrdenTrabajo;

	@MapsId(value = "paquete")
	@ManyToOne
	@JoinColumn(name = "id_paquete", referencedColumnName = "id_paquete")
	private Paquete paquete;

	@Column(name = "unidades_producto")
	private int unidadesProducto;

	// ======================================
	// Constructores
	// ======================================

	protected ProductoEnPaquete() {

	}

	public ProductoEnPaquete(ProductoEnOrdenTrabajo productoPedido, Paquete paquete) {
		Asociacion.Empaquetar.link(paquete, productoPedido, this);
	}

	// ======================================
	// Id de la clase
	// ======================================

	public ProductoPaqueteIds getId() {
		return id;
	}

	// ======================================
	// Producto de una Orden de Trabajo
	// ======================================

	public ProductoEnOrdenTrabajo getProductoOrdenTrabajo() {
		return productoOrdenTrabajo;
	}

	void _setProductoOrdenTrabajo(ProductoEnOrdenTrabajo productoOrdenTrabajo) {
		this.productoOrdenTrabajo = productoOrdenTrabajo;
	}

	// ======================================
	// Producto de una Orden de Trabajo
	// ======================================

	public Paquete getPaquete() {
		return paquete;
	}

	void _setPaquete(Paquete paquete) {
		this.paquete = paquete;
	}

	// ======================================
	// Unidades del producto
	// ======================================

	public int getUnidadesProducto() {
		return unidadesProducto;
	}

	public void setUnidadesProducto(int unidadesProducto) {
		this.unidadesProducto = unidadesProducto;
	}

	// ======================================
	// HashCode - Equals
	// ======================================

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

		ProductoEnPaquete other = (ProductoEnPaquete) obj;

		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		return true;
	}

}