package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import model.keys.ProductoEnOrdenTrabajoKey;

@Entity
@Table(name = "ProductosOrdenTrabajo", uniqueConstraints = @UniqueConstraint(columnNames = { "id_ordenTrabajo",
		"ref_ordenTrabajo" }))
@IdClass(ProductoEnOrdenTrabajoKey.class)
public class ProductoEnOrdenTrabajo implements Serializable {

	private static final long serialVersionUID = -45732110L;

	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_ordenTrabajo", referencedColumnName = "id_ordenTrabajo")
	private OrdenTrabajo ordenTrabajo;

	/*
	 * name = nombre de la columna en esta tabla referencedColumnName = nombre
	 * de la columna de la tabla referenciada
	 */
	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({ @JoinColumn(name = "id_pedido", referencedColumnName = "id_pedido"),
			@JoinColumn(name = "id_producto", referencedColumnName = "id_producto") })
	private ProductoEnPedido productoPedido;

	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REF_ORDEN_TRABAJO_SEQ")
	@SequenceGenerator(name = "REF_ORDEN_TRABAJO_SEQ", sequenceName = "REF_ORDEN_TRABAJO_SEQ", allocationSize = 1)
	private long ref_OrdenTrabajo;

	@Column(name = "unidades_recoger")
	private int unidadesRecoger;

	@Column(name = "unidades_recogidas")
	private int unidadesRecogidas;

	ProductoEnOrdenTrabajo() {

	}

	public ProductoEnOrdenTrabajo(OrdenTrabajo ordenTrabajo, ProductoEnPedido productoPedido, int unidadesARecoger) {
		Asociacion.AsignarProducto_OrdenTrabajo.link(ordenTrabajo, this, productoPedido);
		this.unidadesRecoger = unidadesARecoger;
	}

	public OrdenTrabajo getOrdenTrabajo() {
		return ordenTrabajo;
	}

	void _setOrdenTrabajo(OrdenTrabajo ordenTrabajo) {
		this.ordenTrabajo = ordenTrabajo;
	}

	public ProductoEnPedido getproductoPedido() {
		return productoPedido;
	}

	void _setProducto(ProductoEnPedido productoPedido) {
		this.productoPedido = productoPedido;
	}
	
	public long getRef_OrdenTrabajo() {
		return ref_OrdenTrabajo;
	}

	public int getUnidadesRecoger() {
		return unidadesRecoger;
	}

	public void setUnidadesRecoger(int unidadesRecoger) {
		this.unidadesRecoger = unidadesRecoger;
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + ((ordenTrabajo == null) ? 0 : ordenTrabajo.hashCode());
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

		ProductoEnOrdenTrabajo other = (ProductoEnOrdenTrabajo) obj;

		if (ordenTrabajo == null) {
			if (other.ordenTrabajo != null)
				return false;
		} else if (!ordenTrabajo.equals(other.ordenTrabajo))
			return false;

		if (productoPedido == null) {
			if (other.productoPedido != null)
				return false;
		} else if (!productoPedido.equals(other.productoPedido))
			return false;

		return true;
	}
}
