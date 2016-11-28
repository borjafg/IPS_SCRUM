package business.impl.administracion;

import business.exception.BusinessException;
import business.impl.util.Command;
import model.Pedido;
import model.types.PedidoPagado;
import persistence.PedidoFinder;
import persistence.exception.MyPersistenceException;

public class ConfirmarPedido implements Command {

	private Pedido pedido;

	public ConfirmarPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	@Override
	public Object execute() throws BusinessException {

		try {
			Pedido ped = PedidoFinder.find(pedido);
			ped.setPagado(PedidoPagado.SI);
		}

		catch (MyPersistenceException e) {
			e.printStackTrace();
		}

		return null;
	}

}