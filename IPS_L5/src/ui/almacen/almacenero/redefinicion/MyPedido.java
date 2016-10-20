package ui.almacen.almacenero.redefinicion;

import model.Pedido;

public class MyPedido {

	private Pedido pedido;
	
	public MyPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	public Pedido getPedido() {
		return pedido;
	}
	
	public String toString() {
		return "Pedido: " + pedido.getId() + " -\t Fecha: " + pedido.getFecha();
	}
	
}
