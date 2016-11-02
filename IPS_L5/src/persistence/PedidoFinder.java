package persistence;

import java.util.List;

import model.Pedido;

import persistence.util.Jpa;

public class PedidoFinder {

	public static Pedido findById(Long id) {
		return Jpa.getManager().find(Pedido.class, id);
	}

	public static List<Pedido> findAll() {
		return Jpa.getManager().createNamedQuery("Pedido.findAll", Pedido.class).getResultList();
	}

	public static List<Pedido> findPosibleRecoger() {
		return Jpa.getManager().createNamedQuery("Pedido.findPosibleRecoger", Pedido.class).getResultList();
	}

	public static List<Pedido> findPosibleRecoger_NoPedido(Pedido p) {
		return Jpa.getManager().createNamedQuery("Pedido.findPosibleRecoger_NoPedido", Pedido.class)
				.setParameter("pedido", p).getResultList();
	}
}