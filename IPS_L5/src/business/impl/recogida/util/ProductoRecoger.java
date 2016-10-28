package business.impl.recogida.util;

import model.ProductoEnPedido;

public class ProductoRecoger {
	private ProductoEnPedido producto;
	private int unidades;
	
	public ProductoRecoger(ProductoEnPedido producto, int unidades) {
		this.producto = producto;
		this.unidades = unidades;
	}
	
	
	public ProductoEnPedido getProducto(){
		return producto;
	}


	public int getUnidades() {
		return unidades;
	}
}