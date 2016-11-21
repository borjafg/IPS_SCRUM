package business.impl;

import java.util.List;

import business.EmpaquetadoService;
import business.exception.BusinessException;
import business.impl.empaquetado.AsignarAlmaceneroOrdenTrabajo;
import business.impl.empaquetado.AsignarProductoPaquete;
import business.impl.empaquetado.CargarPaquete;
import business.impl.empaquetado.CargarProductosOT;
import business.impl.empaquetado.CerrarPaquete;
import business.impl.empaquetado.ObtenerCliente;
import business.impl.empaquetado.ObtenerPedidosOrdenTrabajo;
import business.impl.empaquetado.SePuedeCerrarPaquete;
import business.impl.empaquetado.TerminarOrdenTrabajo;
import business.impl.util.CommandExecutor;
import model.Almacenero;
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
	public List<MyPedido_OT_Retomar> getPedidosOT(OrdenTrabajo ot) throws BusinessException {
		return (List<MyPedido_OT_Retomar>) executor.execute(new ObtenerPedidosOrdenTrabajo(ot));
	}

	@Override
	public void asignarAlmaceneroOT(Almacenero almacenero, OrdenTrabajo ordenTrabajo) throws BusinessException {
		executor.execute(new AsignarAlmaceneroOrdenTrabajo(almacenero, ordenTrabajo));
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
	public void asignarProductoPaquete(ProductoEnOrdenTrabajo producto, Paquete paquete, int unidades)
			throws BusinessException {

		executor.execute(new AsignarProductoPaquete(producto, paquete, unidades));
	}

	@Override
	public boolean sePuedeCerrarPaquete(Paquete paqueteActual) throws BusinessException {
		return (boolean) executor.execute(new SePuedeCerrarPaquete(paqueteActual));
	}

	@Override
	public Paquete cerrarPaquete(Paquete paquete, OrdenTrabajo ordenTrabajo) throws BusinessException {
		return (Paquete) executor.execute(new CerrarPaquete(paquete, ordenTrabajo));
	}

	@Override
	public Cliente getClientePedido(Pedido pedido) throws BusinessException {
		return (Cliente) executor.execute(new ObtenerCliente(pedido));
	}

	@Override
	public void terminarOrdenTrabajo(OrdenTrabajo ordenTrabajo) throws BusinessException {
		executor.execute(new TerminarOrdenTrabajo(ordenTrabajo));
	}

}