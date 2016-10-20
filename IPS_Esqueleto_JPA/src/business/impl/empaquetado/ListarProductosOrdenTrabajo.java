package business.impl.empaquetado;

import business.exception.BusinessException;
import business.impl.util.Command;
import model.ProductoEnOrdenTrabajo;
import persistence.ProductoEnOrdenTrabajoFinder;

import java.util.List;

public class ListarProductosOrdenTrabajo implements Command {

	private long id;
	
	public ListarProductosOrdenTrabajo(long id) {
		this.id = id;
	}

	@Override
	public Object execute() throws BusinessException {
		List<ProductoEnOrdenTrabajo> listaProductos = null;
		
		listaProductos = ProductoEnOrdenTrabajoFinder.findByIdOrdenTrabajo(id);
		
		// Para que se carguen los datos en memoria
		
		for(ProductoEnOrdenTrabajo producto : listaProductos) {
			producto.getproductoPedido().getPedido();
			producto.getproductoPedido().getProducto();
		}
		
		return listaProductos;
	}
}