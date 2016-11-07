package business.impl;

import java.util.List;

import business.EmpaquetadoService;
import business.exception.BusinessException;
import business.impl.empaquetado.AsignarProductoPaquete;
import business.impl.empaquetado.CargarPaquete;
import business.impl.empaquetado.CargarProductosOT;
import business.impl.empaquetado.ListarProductosOrdenTrabajo;
import business.impl.empaquetado.ObtenerCliente;
import business.impl.empaquetado.ObtenerPedidosOrdenTrabajo;
import business.impl.util.CommandExecutor;
import model.Cliente;
import model.OrdenTrabajo;
import model.Paquete;
import model.Pedido;
import model.ProductoEnOrdenTrabajo;
import ui.almacen.myTypes.model.MyPedido_OT_Retomar;

@SuppressWarnings("unchecked") // Eliminar advertencias por los Cast explícitos
public class EmpaquetadoServiceImpl implements EmpaquetadoService {

	private CommandExecutor executor = new CommandExecutor();

	// --------------------------------------------
	// Retomar una orden de trabajo
	// --------------------------------------------

	@Override
	public List<ProductoEnOrdenTrabajo> getListaProductosOrdenTrabajo(long id) throws BusinessException {
		return (List<ProductoEnOrdenTrabajo>) executor.execute(new ListarProductosOrdenTrabajo(id));
	}

	@Override
	public List<MyPedido_OT_Retomar> getPedidosOT(OrdenTrabajo ot) throws BusinessException {
		return (List<MyPedido_OT_Retomar>) executor.execute(new ObtenerPedidosOrdenTrabajo(ot));
	}

	// --------------------------------------------
	// Carga de una orden de trabajo
	// --------------------------------------------

	@Override
	public Paquete cargarPaquete(OrdenTrabajo ordenTrabajo) throws BusinessException {
		return (Paquete) executor.execute(new CargarPaquete(ordenTrabajo));
	}

	@Override
	public List<ProductoEnOrdenTrabajo> cargarProductosOT(OrdenTrabajo ordenTrabajo, Pedido pedido)
			throws BusinessException {

		return (List<ProductoEnOrdenTrabajo>) executor.execute(new CargarProductosOT(ordenTrabajo, pedido));
	}

	// --------------------------------------------
	// Proceso de empaquetado
	// --------------------------------------------

	@Override
	public void asignarProductoPaquete(ProductoEnOrdenTrabajo producto, Paquete paquete) throws BusinessException {

		executor.execute(new AsignarProductoPaquete(producto, paquete));
	}

	@Override
	public Cliente getClientePedido(Pedido pedido) throws BusinessException {
		return (Cliente) executor.execute(new ObtenerCliente(pedido));
	}

}