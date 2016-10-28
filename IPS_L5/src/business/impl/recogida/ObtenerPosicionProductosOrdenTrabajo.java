package business.impl.recogida;

import java.util.HashMap;
import java.util.Set;

import business.exception.BusinessException;
import business.impl.util.Command;
import model.OrdenTrabajo;
import model.PosicionProducto;
import model.Producto;
import model.ProductoEnOrdenTrabajo;
import persistence.OrdenTrabajoFinder;

public class ObtenerPosicionProductosOrdenTrabajo implements Command {

	private OrdenTrabajo ordenTrabajo;

	public ObtenerPosicionProductosOrdenTrabajo(OrdenTrabajo ordenTrabajo) {
		this.ordenTrabajo = ordenTrabajo;
	}

	@Override
	public Object execute() throws BusinessException {
		
		OrdenTrabajo ordenTrabajo = OrdenTrabajoFinder.findbyId(this.ordenTrabajo.getId());
		HashMap<PosicionProducto, Producto> diccionario = new HashMap<PosicionProducto, Producto>();


		Set<ProductoEnOrdenTrabajo> productosOrdenTrabajo = ordenTrabajo.getProductosOrdenTrabajo();

		for (ProductoEnOrdenTrabajo productoOrdenTrabajo : productosOrdenTrabajo) {
			diccionario.put(productoOrdenTrabajo.getproductoPedido().getProducto().getPosicion(),
					productoOrdenTrabajo.getproductoPedido().getProducto());
		}

		return diccionario;
	}

}