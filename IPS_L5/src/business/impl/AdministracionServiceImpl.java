package business.impl;

import java.util.List;

import business.AdministracionService;
import business.exception.BusinessException;
import business.impl.administracion.ConfirmarPedido;
import business.impl.administracion.GenerarInformeRecogida;
import business.impl.administracion.ListarPedidosNoPagados;
import business.impl.util.CommandExecutor;
import model.Pedido;
import ui.administracion.myTypes.DatosInformeAlmacenero;

@SuppressWarnings("unchecked")
public class AdministracionServiceImpl implements AdministracionService {

	private CommandExecutor executor = new CommandExecutor();

	@Override
	public List<DatosInformeAlmacenero> generarInformeRecogida() throws BusinessException {
		return (List<DatosInformeAlmacenero>) executor.execute(new GenerarInformeRecogida());
	}

	@Override
	public List<Pedido> generarPedidosNoPagados() throws BusinessException {
		return (List<Pedido>)executor.execute(new ListarPedidosNoPagados());
	}

	@Override
	public void confirmarPedido(Pedido pedido) throws BusinessException {
		executor.execute(new ConfirmarPedido(pedido));
		
	}

}