package ui.almacen.myTypes.model;

import model.OrdenTrabajo;

public class MyOrdenTrabajo_Retomar {

	private OrdenTrabajo ordenTrabajo;

	private int numPedidosFaltanRecoger;
	private int numProductosFaltanRecoger;

	public MyOrdenTrabajo_Retomar(OrdenTrabajo ordenTrabajo, int numPedidosFaltanRecoger,
			int numProductosFaltanRecoger) {

		this.ordenTrabajo = ordenTrabajo;

		this.numPedidosFaltanRecoger = numPedidosFaltanRecoger;
		this.numProductosFaltanRecoger = numProductosFaltanRecoger;
	}

	public OrdenTrabajo getOrdenTrabajo() {
		return ordenTrabajo;
	}

	public int getNumPedidosFaltanRecoger() {
		return numPedidosFaltanRecoger;
	}

	public int getNumProductosFaltanRecoger() {
		return numProductosFaltanRecoger;
	}
}