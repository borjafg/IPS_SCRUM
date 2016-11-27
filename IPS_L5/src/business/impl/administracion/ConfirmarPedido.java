package business.impl.administracion;

import business.exception.BusinessException;
import business.impl.util.Command;
import model.Pedido;
import model.types.PedidoPagado;
import persistence.PedidoFinder;
import persistence.exception.MyPersistenceException;
import persistence.util.Jpa;

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
			Jpa.getManager().merge(ped);
		} catch (MyPersistenceException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
