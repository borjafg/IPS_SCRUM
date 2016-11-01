package ui.almacen.myTypes.model;

import model.Pedido;

public class MyPedido {

	private Pedido pedido;
	private int numProductosSinOT;
	
	public MyPedido(Pedido pedido, int numProductosSinOT) {
		this.pedido = pedido;
		this.numProductosSinOT = numProductosSinOT;
	}
	
	public Pedido getPedido() {
		return pedido;
	}
	
	public int getNumProductoSinOT() {
		return numProductosSinOT;
	}
}