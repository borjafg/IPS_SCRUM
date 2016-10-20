package business.impl;

import java.util.HashMap;
import java.util.List;

import business.AlmacenService;
import business.exception.BusinessException;
import business.impl.almacen.ActualizarProductoEnOrden;
import business.impl.almacen.GetAllPedidos;
import business.impl.almacen.InsertIncidencia;
import business.impl.almacen.InsertOrdenTrabajo;
import business.impl.almacen.ObtenerPosicionProductosOrdenTrabajo;
import business.impl.almacen.ObtenerProductosOrdenTrabajo;
import business.impl.util.CommandExecutor;
import model.Incidencia;
import model.OrdenTrabajo;
import model.Pedido;
import model.PosicionProducto;
import model.Producto;
import model.ProductoEnOrdenTrabajo;

@SuppressWarnings("unchecked")
public class AlmacenServiceImpl implements AlmacenService {
	
	private CommandExecutor executor = new CommandExecutor();

	
	@Override
	public List<Pedido> getAllPedidos() throws BusinessException {
		return (List<Pedido>) executor.execute(new GetAllPedidos());
	}

	@Override
	public OrdenTrabajo insertOrdenTrabajo(Pedido pedido) throws BusinessException {
		return (OrdenTrabajo) executor.execute(new InsertOrdenTrabajo(pedido));
	}

	@Override
	public List<ProductoEnOrdenTrabajo> obtenerProductosPorOrdenTrabajo(OrdenTrabajo ordenTrabajo) throws BusinessException {
		return (List<ProductoEnOrdenTrabajo>) executor.execute(new ObtenerProductosOrdenTrabajo(ordenTrabajo));
	}

	@Override
	public HashMap<PosicionProducto, Producto> obtenerPosicionesProductosPorOrdenTrabajo(OrdenTrabajo ordenTrabajo) throws BusinessException {
		return (HashMap<PosicionProducto, Producto>) executor.execute(new ObtenerPosicionProductosOrdenTrabajo(ordenTrabajo));
	}

	@Override
	public Incidencia insertarIncidencia(OrdenTrabajo ordenTrabajo, String causa) throws BusinessException {
		return (Incidencia) executor.execute(new InsertIncidencia(ordenTrabajo, causa));
		
	}

	@Override
	public void actualizarProductoEnOrdenTrabajo(ProductoEnOrdenTrabajo producto) throws BusinessException {
		executor.execute(new ActualizarProductoEnOrden(producto));
		
	}
}