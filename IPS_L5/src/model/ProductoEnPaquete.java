package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import model.keys.ProductoEnPaqueteKey;

@Entity
@Table(name = "ProductosPaquete")
@IdClass(ProductoEnPaqueteKey.class)
public class ProductoEnPaquete implements Serializable {

	private static final long serialVersionUID = -371256129L;

	@Id
	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "id_pedido", referencedColumnName = "id_pedido"),
			@JoinColumn(name = "id_producto", referencedColumnName = "id_producto") })
	private ProductoEnPedido productoPedido;

	@Id
	@ManyToOne
	@JoinColumn(name = "id_paquete", referencedColumnName = "id_paquete")
	private Paquete paquete;

	@Column(name = "unidades_producto")
	private int unidadesProducto;

	ProductoEnPaquete() {

	}

	public ProductoEnPaquete(ProductoEnPedido productoPedido, Paquete paquete) {
		Asociacion.Empaquetar.link(paquete, productoPedido, this);
	}

	public ProductoEnPedido getProductoPedido() {
		return productoPedido;
	}

	void _setProductoPedido(ProductoEnPedido productoPedido) {
		this.productoPedido = productoPedido;
	}

	public Paquete getPaquete() {
		return paquete;
	}

	void _setPaquete(Paquete paquete) {
		this.paquete = paquete;
	}

	public int getUnidadesProducto() {
		return unidadesProducto;
	}

	public void setUnidadesProducto(int unidadesProducto) {
		this.unidadesProducto = unidadesProducto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + ((paquete == null) ? 0 : paquete.hashCode());
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

		ProductoEnPaquete other = (ProductoEnPaquete) obj;

		if (paquete == null) {
			if (other.paquete != null)
				return false;
		} else if (!paquete.equals(other.paquete))
			return false;

		if (productoPedido == null) {
			if (other.productoPedido != null)
				return false;
		} else if (!productoPedido.equals(other.productoPedido))
			return false;

		return true;
	}
}