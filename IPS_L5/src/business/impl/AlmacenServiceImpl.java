package business.impl;

import java.util.HashMap;
import java.util.List;

import business.RecogidaService;
import business.exception.BusinessException;
import business.impl.recogida.GenerarOrdenTrabajo;
import business.impl.recogida.ObtenerListaPedidosSinOrdenTrabajo;
import business.impl.recogida.versionInestable.ActualizarProductoEnOrden;
import business.impl.recogida.versionInestable.InsertIncidencia;
import business.impl.recogida.versionInestable.InsertOrdenTrabajo;
import business.impl.recogida.versionInestable.ObtenerPosicionProductosOrdenTrabajo;
import business.impl.recogida.versionInestable.ObtenerProductosOrdenTrabajo;
import business.impl.util.CommandExecutor;
import model.Almacenero;
import model.Incidencia;
import model.OrdenTrabajo;
import model.Pedido;
import model.PosicionProducto;
import model.Producto;
import model.ProductoEnOrdenTrabajo;
import ui.almacen.myTypes.model.MyPedido;

@SuppressWarnings("unchecked")
public class AlmacenServiceImpl implements RecogidaService {

	private CommandExecutor executor = new CommandExecutor();

	@Override
	public List<MyPedido> obtenerListaPedidosSinOrdenTrabajo() throws BusinessException {
		return (List<MyPedido>) executor.execute(new ObtenerListaPedidosSinOrdenTrabajo());
	}

	@Override
	public OrdenTrabajo generarOrdenTrabajo(Pedido pedido, Almacenero almacenero) throws BusinessException {
		return (OrdenTrabajo) executor.execute(new GenerarOrdenTrabajo(pedido, almacenero));
	}

	// ==========================================
	// Métodos sin revisar (versión inestable)
	// ==========================================

	@Override
	public OrdenTrabajo insertOrdenTrabajo(Pedido pedido) throws BusinessException {
		return (OrdenTrabajo) executor.execute(new InsertOrdenTrabajo(pedido));
	}

	@Override
	public List<ProductoEnOrdenTrabajo> obtenerProductosPorOrdenTrabajo(OrdenTrabajo ordenTrabajo)
			throws BusinessException {
		return (List<ProductoEnOrdenTrabajo>) executor.execute(new ObtenerProductosOrdenTrabajo(ordenTrabajo));
	}

	@Override
	public HashMap<PosicionProducto, Producto> obtenerPosicionesProductosPorOrdenTrabajo(OrdenTrabajo ordenTrabajo)
			throws BusinessException {
		return (HashMap<PosicionProducto, Producto>) executor
				.execute(new ObtenerPosicionProductosOrdenTrabajo(ordenTrabajo));
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