package ui.almacen.myTypes.model;

import model.OrdenTrabajo;

public class MyOrdenTrabajo_Retomar {

	private OrdenTrabajo ordenTrabajo;
	
	private int numPedidosFaltan;
	private int numProductosFaltan;

	public MyOrdenTrabajo_Retomar(OrdenTrabajo ordenTrabajo, int numPedidosFaltan,
			int numProductosFaltan) {

		this.ordenTrabajo = ordenTrabajo;

		this.numPedidosFaltan = numPedidosFaltan;
		this.numProductosFaltan = numProductosFaltan;
	}

	public OrdenTrabajo getOrdenTrabajo() {
		return ordenTrabajo;
	}

	public int getNumPedidosFaltan() {
		return numPedidosFaltan;
	}

	public int getNumProductosFaltan() {
		return numProductosFaltan;
	}
	
}