package business.impl;

import java.util.List;

import business.EmpaquetadoService;
import business.exception.BusinessException;
import business.impl.empaquetado.AsignarProductoPaquete;
import business.impl.empaquetado.ListarProductosOrdenTrabajo;
import business.impl.empaquetado.ObtenerCliente;
import business.impl.util.CommandExecutor;
import model.Cliente;
import model.Paquete;
import model.Pedido;
import model.ProductoEnOrdenTrabajo;

@SuppressWarnings("unchecked") // Eliminar advertencias por los Cast explícitos
public class EmpaquetadoServiceImpl implements EmpaquetadoService {

	private CommandExecutor executor = new CommandExecutor();
	
	@Override
	public List<ProductoEnOrdenTrabajo> getListaProductosOrdenTrabajo(long id) throws BusinessException {
		return (List<ProductoEnOrdenTrabajo>) executor.execute(new ListarProductosOrdenTrabajo(id));
	}

	@Override
	public void asignarProductoPaquete(ProductoEnOrdenTrabajo producto, Paquete paqueteActual)
			throws BusinessException {
		executor.execute(new AsignarProductoPaquete(producto, paqueteActual));
	}

	@Override
	public Cliente getClientePedido(Pedido pedido) throws BusinessException {
		return (Cliente) executor.execute(new ObtenerCliente(pedido));
	}
	
}