package business.impl;

import java.util.List;

import business.EmpaquetadoService;
import business.exception.BusinessException;
import business.impl.empaquetado.AbrirPaquete;
import business.impl.empaquetado.AsignarProductoPaquete;
import business.impl.empaquetado.CargarOrdenesTrabajoEmpaquetar;
import business.impl.empaquetado.CargarPaquete;
import business.impl.empaquetado.CargarProductosOT;
import business.impl.empaquetado.CerrarPaquete;
import business.impl.empaquetado.ListarProductosOrdenTrabajo;
import business.impl.empaquetado.ObtenerCliente;
import business.impl.empaquetado.ObtenerPedidosOrdenTrabajo;
import business.impl.empaquetado.SePuedeCerrarPaquete;
import business.impl.util.CommandExecutor;
import model.Almacenero;
import model.Cliente;
import model.OrdenTrabajo;
import model.Paquete;
import model.Pedido;
import model.ProductoEnOrdenTrabajo;
import ui.almacen.myTypes.model.MyOrdenTrabajo_Retomar;
import ui.almacen.myTypes.model.MyPedido_OT_Retomar;

@SuppressWarnings("unchecked") // Eliminar advertencias por los Cast explícitos
public class EmpaquetadoServiceImpl implements EmpaquetadoService {

	private CommandExecutor executor = new CommandExecutor();

	// --------------------------------------------
	// Retomar una orden de trabajo
	// --------------------------------------------

	@Override
	public List<MyOrdenTrabajo_Retomar> cargarOT_empaquetar(Almacenero almacenero) throws BusinessException {
		return (List<MyOrdenTrabajo_Retomar>) executor.execute(new CargarOrdenesTrabajoEmpaquetar(almacenero));
	}
	
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
	public void asignarProductoPaquete(ProductoEnOrdenTrabajo producto, Paquete paquete, int unidades)
			throws BusinessException {
		
		executor.execute(new AsignarProductoPaquete(producto, paquete, unidades));
	}

	@Override
	public Paquete abrirPaquete(OrdenTrabajo ordenTrabajo) throws BusinessException {
		return (Paquete) executor.execute(new AbrirPaquete(ordenTrabajo));
	}

	@Override
	public boolean sePuedeCerrarPaquete(Paquete paqueteActual) throws BusinessException {
		return (boolean) executor.execute(new SePuedeCerrarPaquete(paqueteActual));
	}
	
	@Override
	public void cerrarPaquete(Paquete paquete) throws BusinessException {
		executor.execute(new CerrarPaquete(paquete));
	}

	@Override
	public Cliente getClientePedido(Pedido pedido) throws BusinessException {
		return (Cliente) executor.execute(new ObtenerCliente(pedido));
	}

}