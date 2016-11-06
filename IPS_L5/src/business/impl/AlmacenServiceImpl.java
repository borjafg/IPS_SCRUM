package business.impl;

import java.util.List;
import java.util.Set;

import business.RecogidaService;
import business.exception.BusinessException;
import business.impl.recogida.GenerarOrdenTrabajo;
import business.impl.recogida.HuboIncidencias;
import business.impl.recogida.ObtenerListaPedidosSinOrdenTrabajo;
import business.impl.recogida.ObtenerProductosOT;
import business.impl.util.CommandExecutor;
import model.Almacenero;
import model.OrdenTrabajo;
import model.Pedido;
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
	
	public boolean huboIncidencias(OrdenTrabajo ordenTrabajo) throws BusinessException {
		return (boolean) executor.execute(new HuboIncidencias(ordenTrabajo));
	}

	@Override
	public Set<ProductoEnOrdenTrabajo> obtenerProductosOT(OrdenTrabajo ordenTrabajo) throws BusinessException {
		return (Set<ProductoEnOrdenTrabajo>) executor.execute(new ObtenerProductosOT(ordenTrabajo));
	}

}