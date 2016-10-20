package ui.usuario.logica.ClasesAuxiliares;

import model.Producto;

public class ModeloProductosPedidos {
	/*Clase para hcer validaciones con los productos en la cesta, hasta que hagamos la creacion de las nuevas
	entidades en la base de datos
	*/
	
	private Producto producto;
	private int unidades;
	private double precioProductosTotal;
	
	
	
	
	
	public ModeloProductosPedidos(Producto producto, int unidades) {
		super();
		this.producto = producto;
		sumarUnidades(unidades);

	}


	public void sumarUnidades(int unidades){
		this.unidades = this.unidades + unidades;
		calcularPrecio();
	}
	
	public void restarUnidades(int unidades){
		this.unidades = this.unidades - unidades;
		calcularPrecio();
	}
	
	private void calcularPrecio(){
		precioProductosTotal= unidades*producto.getPrecio();
	}
	
	public Producto getProducto(){
		return this.producto;
	}

	public double getPrecioProductosTotal() {
		return precioProductosTotal;
	}


	public int getUnidades(){
		return this.unidades;
	}


	@Override
	public String toString() {
		return producto.getNombre()+"  ("+getUnidades()+")  ->  "+this.precioProductosTotal;
	}

	
	
	
}
