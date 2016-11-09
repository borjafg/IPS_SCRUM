package test.business.acciones;

import javax.persistence.EntityManager;

import model.Pedido;
import model.types.PedidoPagado;
import persistence.PedidoFinder;
import persistence.exception.MyPersistenceException;
import test.business.TestAction;

public class PagarPedidos implements TestAction{

	Pedido pedidoNoPagado;
	
	
	public PagarPedidos(Pedido pedidoNoPagado) {
		this.pedidoNoPagado = pedidoNoPagado;
	}
	
	@Override
	public String doTest(EntityManager ent) {
		StringBuilder sb = new StringBuilder();
		Pedido pedidoPagado;
		try {
			pedidoPagado = PedidoFinder.find(pedidoNoPagado);
			pedidoPagado.setPagado(PedidoPagado.SI);
		} catch (MyPersistenceException e) {
			System.err.println(e.getMessage());
		}
		
		
		
		return sb.toString();
	}

}
