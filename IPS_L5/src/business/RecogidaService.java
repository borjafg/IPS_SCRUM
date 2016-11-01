package business;

import java.util.HashMap;
import java.util.List;

import business.exception.BusinessException;
import model.Incidencia;
import model.OrdenTrabajo;
import model.Pedido;
import model.PosicionProducto;
import model.Producto;
import model.ProductoEnOrdenTrabajo;
import ui.almacen.myTypes.model.MyPedido;

public interface RecogidaService {

	public List<MyPedido> obtenerListaPedidosSinOrdenTrabajo() throws BusinessException;
	
	// Métodos de un versión inestable de la aplicación (Pendientes de depuración)
	
	public OrdenTrabajo insertOrdenTrabajo(Pedido pedido) throws BusinessException;
	
	public List<ProductoEnOrdenTrabajo> obtenerProductosPorOrdenTrabajo(OrdenTrabajo ordenTrabajo) throws BusinessException;
	
	public HashMap<PosicionProducto, Producto> obtenerPosicionesProductosPorOrdenTrabajo(OrdenTrabajo ordenTrabajo) throws BusinessException;
	
	public Incidencia insertarIncidencia(OrdenTrabajo ordenTrabajo, String causa) throws BusinessException;
	
	public void actualizarProductoEnOrdenTrabajo(ProductoEnOrdenTrabajo producto) throws BusinessException;
}