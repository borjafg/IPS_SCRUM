package ui.almacen.myTypes.model;

import model.PosicionProducto;
import model.ProductoEnOrdenTrabajo;
import model.types.EstanteriaProducto;

public class MyProducto_OrdenadoPosicion implements Comparable<MyProducto_OrdenadoPosicion> {

	private PosicionProducto posProducto;
	private ProductoEnOrdenTrabajo producto;

	public MyProducto_OrdenadoPosicion(ProductoEnOrdenTrabajo producto) {
		this.posProducto = producto.getproductoPedido().getProducto().getPosicion();
		this.producto = producto;
	}

	public PosicionProducto getPosicionProducto() {
		return this.posProducto;
	}

	public ProductoEnOrdenTrabajo getProducto() {
		return producto;
	}

	@Override
	public int compareTo(MyProducto_OrdenadoPosicion otroProducto) {

		// ------------------------------
		// Si están en distinto pasillo
		// ------------------------------

		if (posProducto.getPasillo() < otroProducto.posProducto.getPasillo())
			return -1;

		else if (posProducto.getPasillo() > otroProducto.getPosicionProducto().getPasillo())
			return 1;

		// ------------------------------
		// Si están en el mismo pasillo
		// ------------------------------

		else {
			// -------------------------------------
			// Si están en una estanteria distinta
			// -------------------------------------

			if (posProducto.getEstanteriaPoducto().equals(EstanteriaProducto.IZQUIERDA)
					&& otroProducto.getPosicionProducto().getEstanteriaPoducto().equals(EstanteriaProducto.DERECHA))
				return -1;

			else if (posProducto.getEstanteriaPoducto().equals(EstanteriaProducto.DERECHA)
					&& otroProducto.getPosicionProducto().getEstanteriaPoducto().equals(EstanteriaProducto.IZQUIERDA))
				return 1;

			// -------------------------------------
			// Si están en la misma estanteria
			// -------------------------------------

			else {
				// -------------------------------------
				// Si están en una posicion distinta
				// -------------------------------------

				if (posProducto.getPosicionX() < otroProducto.getPosicionProducto().getPosicionX())
					return -1;

				else if (posProducto.getPosicionX() > otroProducto.getPosicionProducto().getPosicionX())
					return 1;

				// -------------------------------------------------
				// Si están en la misma posicion posicion distinta
				// -------------------------------------------------

				else {
					if (posProducto.getAltura() < otroProducto.getPosicionProducto().getAltura())
						return -1;

					else if (posProducto.getAltura() > otroProducto.getPosicionProducto().getAltura())
						return 1;
				}

				return 0;
			}
		}
	}

	/**
	 * Devuelve la informacion sobre la posision de un producto en el almacen
	 * 
	 * @return posicion del producto dentro del almacen
	 * 
	 */
	public String getInfo() {
		PosicionProducto pos = producto.getproductoPedido().getProducto().getPosicion();

		StringBuilder sb = new StringBuilder();

		sb.append("Pasillo: ").append(pos.getPasillo());

		sb.append("Estant: ").append(pos.getEstanteriaPoducto().name().toLowerCase());

		sb.append("Pos: ").append(pos.getPosicionX());

		sb.append("Alt: ").append(pos.getAltura());

		return sb.toString();
	}

}