package ui.almacen.recogida.util;

import model.PosicionProducto;
import model.ProductoEnOrdenTrabajo;
import model.types.EstanteriaProducto;

public class MyPosicionProducto implements Comparable<MyPosicionProducto> {

	private PosicionProducto posProducto;
	private ProductoEnOrdenTrabajo producto;

	public MyPosicionProducto(ProductoEnOrdenTrabajo producto) {
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
	public int compareTo(MyPosicionProducto arg0) {

		if (posProducto.getPasillo() < arg0.posProducto.getPasillo())
			return -1;

		else if (posProducto.getPasillo() > arg0.getPosicionProducto().getPasillo())
			return 1;

		else {
			if (posProducto.getEstanteriaPoducto().equals(EstanteriaProducto.IZQUIERDA)
					&& arg0.getPosicionProducto().getEstanteriaPoducto().equals(EstanteriaProducto.DERECHA))
				return -1;

			else if (posProducto.getEstanteriaPoducto().equals(EstanteriaProducto.DERECHA)
					&& arg0.getPosicionProducto().getEstanteriaPoducto().equals(EstanteriaProducto.IZQUIERDA))
				return 1;

			else {
				if (posProducto.getPosicionX() < arg0.getPosicionProducto().getPosicionX())
					return -1;

				else if (posProducto.getPosicionX() > arg0.getPosicionProducto().getPosicionX())
					return 1;

				else {
					if (posProducto.getAltura() < arg0.getPosicionProducto().getAltura())
						return -1;

					else if (posProducto.getAltura() > arg0.getPosicionProducto().getAltura())
						return 1;
				}

				return 0;
			}
		}
	}

	public String toString() {
		return "Pasillo: " + posProducto.getPasillo() + " - Estantería: " + posProducto.getEstanteriaPoducto()
				+ " - Posición x: " + posProducto.getPosicionX() + " - Altura: " + posProducto.getAltura();
	}
}