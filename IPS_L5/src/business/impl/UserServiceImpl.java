package business.impl;

import java.util.List;

import business.UserService;
import business.exception.BusinessException;
import business.impl.usuario.CargadorABaseDeDatos;
import business.impl.usuario.HayProductosEnCategoria;
import business.impl.usuario.HayUsuarioEnBase;
import business.impl.usuario.ListarCategoriasHijo;
import business.impl.usuario.ListarCategoriasPadre;
import business.impl.usuario.ListarProductos;
import business.impl.usuario.UsuarioEnBase;
import business.impl.util.CommandExecutor;
import model.Categoria;
import model.Cliente;
import model.Producto;
import model.types.MetodosPago;
import ui.usuario.logica.ClasesAuxiliares.ModeloProductosPedidos;

@SuppressWarnings("unchecked")
public class UserServiceImpl implements UserService {

	private CommandExecutor executor = new CommandExecutor();

	@Override
	public List<Producto> getListaProducto(Categoria categoria) throws BusinessException {
		return (List<Producto>) executor.execute(new ListarProductos(categoria));
	}

	@Override
	public void cargarBaseDeDatos(String direccion, String nombre, List<ModeloProductosPedidos> listaCesta,MetodosPago metodoPago)
			throws BusinessException {

		executor.execute(new CargadorABaseDeDatos(direccion, nombre, listaCesta,metodoPago));//<-----vamos a tener que pasar más parámetros cuando se añadan los log-in y los diferentes tipos de usuario
	}

	@Override
	public List<Categoria> getListaCategoriasPadre() throws BusinessException {
		return (List<Categoria>)executor.execute(new ListarCategoriasPadre());
	}

	@Override
	public List<Categoria> getListaCategoriasHijo(Categoria categoriaPadre) throws BusinessException {
		return (List<Categoria>)executor.execute(new ListarCategoriasHijo(categoriaPadre));
	}

	@Override
	public boolean isProductoEnCategoria(Categoria categoria) throws BusinessException {
		return (boolean) executor.execute(new HayProductosEnCategoria(categoria));
	}
	
	public boolean isUsuarioEnBase(String nombre) throws BusinessException{
		return (boolean)executor.execute(new HayUsuarioEnBase(nombre));
	}
	
	public Cliente getUsuarioEnBase(String nombre) throws BusinessException{
		return (Cliente)executor.execute(new UsuarioEnBase(nombre));
	}
	

}