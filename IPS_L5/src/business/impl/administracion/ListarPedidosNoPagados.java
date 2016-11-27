package business.impl.administracion;

import java.util.List;

import business.exception.BusinessException;
import business.impl.util.Command;
import model.Pedido;
import persistence.util.Jpa;

public class ListarPedidosNoPagados implements Command{

	@Override
	public Object execute() throws BusinessException {
List<Pedido> listado = null;
		
		listado = Jpa.getManager().createNamedQuery("Pedido.findNoPagados",Pedido.class).getResultList();
		
		return listado;
	}

}
