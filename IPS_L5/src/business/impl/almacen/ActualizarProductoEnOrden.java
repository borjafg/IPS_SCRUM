package business.impl.almacen;

import business.impl.util.Command;
import model.ProductoEnOrdenTrabajo;
import persistence.ProductoEnOrdenTrabajoFinder;

public class ActualizarProductoEnOrden implements Command {

	private ProductoEnOrdenTrabajo producto;

	public ActualizarProductoEnOrden(ProductoEnOrdenTrabajo producto) {
		this.producto = producto;
	}

	@Override
	public Object execute() {
		ProductoEnOrdenTrabajo prot = ProductoEnOrdenTrabajoFinder.findByIds(producto.getOrdenTrabajo().getId(),
				producto.getproductoPedido().getPedido().getId(), producto.getproductoPedido().getProducto().getId());
		prot.recoger(prot.getUnidadesRecoger());
		return null;

	}

}
