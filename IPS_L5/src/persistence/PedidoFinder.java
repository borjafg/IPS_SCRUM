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
}