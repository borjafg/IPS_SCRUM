package business.impl;


import java.util.List;

import business.UserService;
import business.exception.BusinessException;
import business.impl.usuario.CargadorABaseDeDatos;
import business.impl.usuario.ListarProductos;
import business.impl.util.CommandExecutor;
import model.Producto;
import model.types.MetodosPago;
import ui.usuario.logica.ClasesAuxiliares.ModeloProductosPedidos;

@SuppressWarnings("unchecked")
public class UserServiceImpl implements UserService {

	private CommandExecutor executor = new CommandExecutor();
	

	@Override
	public List<Producto> getListaProducto() throws BusinessException {
		return (List<Producto>) executor.execute( new ListarProductos());
	}

	@Override
	public void cargarBaseDeDatos(String direccion, String nombre, List<ModeloProductosPedidos> listaCesta) throws BusinessException {
		 executor.execute(new CargadorABaseDeDatos(direccion,  nombre, listaCesta));
		
	}
		
}
