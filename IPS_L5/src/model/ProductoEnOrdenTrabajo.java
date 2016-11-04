package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import model.idClasses.ProductoOrdenTrabajoIds;

@Entity
@Table(name = "ProductosOrdenTrabajo")
public class ProductoEnOrdenTrabajo implements Serializable {

	private static final long serialVersionUID = -45732110L;

	@EmbeddedId
	private ProductoOrdenTrabajoIds id = new ProductoOrdenTrabajoIds();

	@MapsId(value = "ordenTrabajo")
	@ManyToOne
	@JoinColumn(name = "id_ordenTrabajo", referencedColumnName = "id_ordenTrabajo")
	private OrdenTrabajo ordenTrabajo;

	@MapsId(value = "productoPedido")
	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "id_pedido", referencedColumnName = "id_pedido"),
			@JoinColumn(name = "id_producto", referencedColumnName = "id_producto") })
	private ProductoEnPedido productoPedido;

	@Column(nullable = false)
	private String ref_OrdenTrabajo;

	@Column(name = "unidades_recoger")
	private int unidadesRecoger;

	@Column(name = "unidades_recogidas")
	private int unidadesRecogidas;

	@OneToMany(mappedBy = "productoOrdenTrabajo")
	private Set<ProductoEnPaquete> productoPaquete;

	// =======================================
	// Constructores
	// =======================================

	protected ProductoEnOrdenTrabajo() {

	}

	public ProductoEnOrdenTrabajo(OrdenTrabajo ordenTrabajo, ProductoEnPedido productoPedido, int unidadesARecoger) {
		Asociacion.AsignarProducto_OrdenTrabajo.link(ordenTrabajo, this, productoPedido);
		this.unidadesRecoger = unidadesARecoger;
	}

	// =======================================
	// Id de la clase
	// =======================================

	public ProductoOrdenTrabajoIds getId() {
		return id;
	}

	// =======================================
	// Orden de Trabajo
	// =======================================

	public OrdenTrabajo getOrdenTrabajo() {
		return ordenTrabajo;
	}

	void _setOrdenTrabajo(OrdenTrabajo ordenTrabajo) {
		this.ordenTrabajo = ordenTrabajo;
	}

	// =======================================
	// Producto de un pedido
	// =======================================

	public ProductoEnPedido getproductoPedido() {
		return productoPedido;
	}

	void _setProducto(ProductoEnPedido productoPedido) {
		this.productoPedido = productoPedido;
	}

	// =======================================
	// Referencia dentro de la OT
	// =======================================

	public String getRef_OrdenTrabajo() {
		return ref_OrdenTrabajo;
	}

	public void setRef_OrdenTrabajo(String ref_OrdenTrabajo) {
		this.ref_OrdenTrabajo = ref_OrdenTrabajo;
	}

	// =======================================
	// Unidades que hay que recoger
	// =======================================

	public int getUnidadesRecoger() {
		return unidadesRecoger;
	}

	public void setUnidadesRecoger(int unidadesRecoger) {
		this.unidadesRecoger = unidadesRecoger;
	}

	// =======================================
	// Unidades que lleva recogidas
	// =======================================

	public int getUnidadesRecogidas() {
		return unidadesRecogidas;
	}

	public void recoger(int unidades) {
		if (unidades + unidadesRecogidas <= unidadesRecoger) {
			this.unidadesRecogidas = unidadesRecogidas + unidades;
		}

		else {
			throw new IllegalArgumentException("La cantidad indicada supera la requerida por la orden de trabajo");
		}
	}

	// =======================================
	// Paquetes en los que está el producto
	// =======================================

	public Set<ProductoEnPaquete> getPaquetes() {
		return new HashSet<ProductoEnPaquete>(productoPaquete);
	}

	Set<ProductoEnPaquete> _getPaquetes() {
		return productoPaquete;
	}

	// =======================================
	// HashCode - Equals
	// =======================================

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

		ProductoEnOrdenTrabajo other = (ProductoEnOrdenTrabajo) obj;

		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		return true;
	}
}