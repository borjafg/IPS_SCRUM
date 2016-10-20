package business.impl.usuario;


import java.util.List;

import business.exception.BusinessException;
import business.impl.util.Command;
import model.Producto;
import persistence.ProductoFinder;

public class ListarProductos implements Command {

	@Override
	public Object execute() throws BusinessException {
		List<Producto> listaProducto = null;
		
		listaProducto = ProductoFinder.findAll();				
		
		return listaProducto;
		
	}
	
	

}
