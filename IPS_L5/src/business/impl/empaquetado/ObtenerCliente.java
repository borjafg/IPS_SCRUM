package business.impl.empaquetado;

import business.exception.BusinessException;
import business.impl.util.Command;
import model.Pedido;
import persistence.PedidoFinder;
import persistence.exception.MyPersistenceException;

public class ObtenerCliente implements Command {

	private Pedido pedido;

	public ObtenerCliente(Pedido pedido) {
		this.pedido = pedido;
	}

	@Override
	public Object execute() throws BusinessException {
		try {
			Pedido ped = PedidoFinder.find(pedido);

			return ped.getCliente();
		}

		catch (MyPersistenceException e) {
			throw new BusinessException("Ha ocurrido un error al obtener la información de un cliente", e);
		}
	}

}