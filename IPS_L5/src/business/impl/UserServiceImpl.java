package business.impl;

import java.util.List;

import business.UserService;
import business.exception.BusinessException;
import business.impl.usuario.CargadorABaseDeDatosNoRegistrado;
import business.impl.usuario.CargarABaseDeDatosNoRegistradoConTarjeta;
import business.impl.usuario.CargarBaseUsuarioMinorista;
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
import model.types.TipoEnvio;
import model.types.TipoTarjeta;
import ui.usuario.logica.ClasesAuxiliares.ModeloProductosPedidos;

@SuppressWarnings("unchecked")
public class UserServiceImpl implements UserService {

	private CommandExecutor executor = new CommandExecutor();

	@Override
	public List<Producto> getListaProducto(Categoria categoria) throws BusinessException {
		return (List<Producto>) executor.execute(new ListarProductos(categoria));
	}

	@Override
	public void cargarBaseDeDatosNoRegistrado(String direccion, String nombre, List<ModeloProductosPedidos> listaCesta,MetodosPago metodoPago)
			throws BusinessException {

		executor.execute(new CargadorABaseDeDatosNoRegistrado(direccion, nombre, listaCesta,metodoPago));
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

	@Override
	public void cargarBaseUsuarioMinorista(Cliente cliente, List<ModeloProductosPedidos> listaCesta,TipoEnvio tipoEnvio)
			throws BusinessException {
		executor.execute(new CargarBaseUsuarioMinorista(cliente,listaCesta,tipoEnvio));
		
	}

	@Override
	public void cargarBaseDeDatosNoRegistradoTarjeta(String direccion, String nombre, List<ModeloProductosPedidos> listaCesta, TipoEnvio tipoEnvio,
			Long numeroTarjeta, int codigoSec, TipoTarjeta tipoTarjeta, String fecha) throws BusinessException {
		executor.execute(new CargarABaseDeDatosNoRegistradoConTarjeta( direccion,  nombre, listaCesta, tipoEnvio,
				numeroTarjeta,  codigoSec, tipoTarjeta,  fecha));
		
	}
	

}