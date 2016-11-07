package business;

import java.util.List;

import business.exception.BusinessException;
import model.Categoria;
import model.Producto;
import ui.usuario.logica.ClasesAuxiliares.ModeloProductosPedidos;

public interface UserService {
	public List<Producto> getListaProducto(Categoria categoria) throws BusinessException;
	
	public  void cargarBaseDeDatos(String direccion,  String nombre, List<ModeloProductosPedidos> listaCesta)throws BusinessException;
	
	public List<Categoria> getListaCategoriasPadre() throws BusinessException;
	
	public List<Categoria> getListaCategoriasHijo(Categoria categoriaPadre) throws BusinessException;
	
	public boolean isProductoEnCategoria(Categoria categoria) throws BusinessException;
}
