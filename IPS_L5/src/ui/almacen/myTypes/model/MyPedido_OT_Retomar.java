package ui.almacen.myTypes.model;

import model.Pedido;

public class MyPedido_OT_Retomar {

	private Pedido pedido;
	private int numProductosFaltan;

	public MyPedido_OT_Retomar(Pedido pedido, int numProductosFaltan) {
		this.pedido = pedido;
		this.numProductosFaltan = numProductosFaltan;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public int getNumProductosFaltan() {
		return numProductosFaltan;
	}
}