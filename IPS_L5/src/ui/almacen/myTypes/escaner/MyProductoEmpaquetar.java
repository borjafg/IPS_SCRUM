package ui.almacen.myTypes.escaner;

import model.ProductoEnOrdenTrabajo;

public class MyProductoEmpaquetar {

	private ProductoEnOrdenTrabajo prodOT;
	
	public MyProductoEmpaquetar(ProductoEnOrdenTrabajo prodOT) {
		this.prodOT = prodOT;
	}
	
	public ProductoEnOrdenTrabajo getProductoOT() {
		return prodOT;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Cod. pedido: ").append(prodOT.getproductoPedido().getPedido().getId()).append(", ");
		sb.append("Referencia: ").append(prodOT.getRef_OrdenTrabajo()).append(", ");
		sb.append("descripcion: ").append(prodOT.getproductoPedido().getProducto().getDescripcion());
		
		return sb.toString();
	}
}