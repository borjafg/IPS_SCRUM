package business.impl.empaquetado;

import business.exception.BusinessException;
import business.impl.util.Command;
import model.Pedido;
import persistence.util.Jpa;

public class ObtenerCliente implements Command {

	private Pedido pedido;
	
	public ObtenerCliente(Pedido pedido) {
		this.pedido = pedido;
	}
	
	@Override
	public Object execute() throws BusinessException {
		Pedido ped = Jpa.getManager().merge(pedido);
		
		return ped.getCliente();
	}

}