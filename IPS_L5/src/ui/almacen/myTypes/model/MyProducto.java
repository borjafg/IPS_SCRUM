package ui.almacen.myTypes.model;

import model.Producto;

public class MyProducto {
	
	private Producto producto;
	
	public MyProducto(Producto producto) {
		this.producto = producto;
	}
	
	public String toString() {
		return "Producto:  Nombre: " + this.producto.getNombre() + " - Categor�a: " + this.producto.getCategoria() + " - Precio: " + this.producto.getPrecio();
	}

}
