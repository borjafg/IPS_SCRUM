package test.business.acciones;

import javax.persistence.EntityManager;

import model.Pedido;
import model.types.PedidoPagado;
import test.business.TestAction;

public class PagarPedidos implements TestAction{

	Pedido pedidoNoPagado;
	
	
	public PagarPedidos(Pedido pedidoNoPagado) {
		this.pedidoNoPagado = pedidoNoPagado;
	}
	
	@Override
	public String doTest(EntityManager ent) {
		StringBuilder sb = new StringBuilder();
		Pedido pedidoPagado = ent.merge(pedidoNoPagado);
		pedidoPagado.setPagado(PedidoPagado.SI);
		
		
		return sb.toString();
	}

}
