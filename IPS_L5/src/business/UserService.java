package business;

import java.util.List;

import business.exception.BusinessException;
import model.Producto;
import model.types.MetodosPago;
import ui.usuario.logica.ClasesAuxiliares.ModeloProductosPedidos;

public interface UserService {
	public List<Producto> getListaProducto() throws BusinessException;
	
	public  void cargarBaseDeDatos(String direccion,  String nombre, List<ModeloProductosPedidos> listaCesta)throws BusinessException;
}
