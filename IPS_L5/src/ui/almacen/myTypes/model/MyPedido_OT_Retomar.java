package ui.almacen.myTypes.model;

import model.Pedido;

public class MyPedido_OT_Retomar implements Comparable<MyPedido_OT_Retomar> {

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

	@Override
	public int compareTo(MyPedido_OT_Retomar o) {
		return new Long(pedido.getId()).compareTo(o.getPedido().getId());
	}
	
	
}