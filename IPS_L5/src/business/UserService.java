package business;

import java.util.List;

import business.exception.BusinessException;
import model.Categoria;
import model.Cliente;
import model.Producto;
import model.types.MetodosPago;
import model.types.TipoEnvio;
import model.types.TipoTarjeta;
import ui.usuario.logica.ClasesAuxiliares.ModeloProductosPedidos;

public interface UserService {
	public List<Producto> getListaProducto(Categoria categoria) throws BusinessException;
	
	public  void cargarBaseDeDatosNoRegistrado(String direccion,  String nombre, List<ModeloProductosPedidos> listaCesta,MetodosPago metodoPago,TipoEnvio tipoEnvio)throws BusinessException;
	
	public void cargarBaseDeDatosNoRegistradoTarjeta(String direccion, String nombre, List<ModeloProductosPedidos> listaCesta,TipoEnvio tipoEnvio,Long numeroTarjeta,int codigoSec,TipoTarjeta tipoTarjeta, String fecha)throws BusinessException;
	
	public List<Categoria> getListaCategoriasPadre() throws BusinessException;
	
	public List<Categoria> getListaCategoriasHijo(Categoria categoriaPadre) throws BusinessException;
	
	public boolean isProductoEnCategoria(Categoria categoria) throws BusinessException;
	
	public boolean isUsuarioEnBase (String nombre) throws BusinessException;

	public Cliente getUsuarioEnBase(String nombre) throws BusinessException;
	
	public void cargarBaseUsuarioMinorista(Cliente cliente, List<ModeloProductosPedidos> listaCesta,TipoEnvio tipoEnvio) throws BusinessException;
	
	public void cargarBaseDeDatosParticular(Cliente cliente,String direccion, String nombre, List<ModeloProductosPedidos> listaCesta,MetodosPago metodoPago,TipoEnvio tipoEnvio,Long numeroTarjeta,int codigoSec,TipoTarjeta tipoTarjeta, String fecha)throws BusinessException;

}
