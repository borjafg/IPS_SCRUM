package business.impl.recogida.generacionOrdenesTrabajo.myTypes;

import model.Producto;
import model.ProductoEnPedido;

public class ProductoRecoger implements Comparable<ProductoRecoger> {
	private ProductoEnPedido producto;
	private int unidades;

	public ProductoRecoger(ProductoEnPedido producto) {
		this.producto = producto;
		this.unidades = producto.getCantidad();
	}

	public ProductoEnPedido getProductoPedido() {
		return producto;
	}

	// ---------------------------------------
	// Control de las unidades del pedido
	// ---------------------------------------

	public int getUnidades() {
		return unidades;
	}

	public void quitar(int unidades) {
		this.unidades = this.unidades - unidades;
	}

	// ----------------------------------------
	// Ordenación según peso / volumen
	// ----------------------------------------

	@Override
	public int compareTo(ProductoRecoger pep) {
		Producto prod = producto.getProducto();
		Producto prodRec = pep.getProductoPedido().getProducto();

		return new Double((prod.getPeso() / prod.getVolumen())).compareTo(prodRec.getPeso() / prodRec.getVolumen());
	}

}