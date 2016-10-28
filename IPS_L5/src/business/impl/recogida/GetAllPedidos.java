package business.impl.recogida;

import business.exception.BusinessException;
import business.impl.util.Command;
import model.Pedido;
import persistence.util.Jpa;

public class GetAllPedidos implements Command {

	@Override
	public Object execute() throws BusinessException {
		return Jpa.getManager().createQuery("select p from Pedido p where p.estado = 'SIN_ORDEN_TRABAJO'", Pedido.class).getResultList();
	}

}
