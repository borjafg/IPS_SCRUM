package test.business.acciones;

import javax.persistence.EntityManager;

import model.Categoria;
import model.PosicionProducto;
import model.Producto;
import test.business.TestAction;

public class NuevoProducto implements TestAction {

	private String nombre;
	private Categoria categoria;
	private Double precio;
	private String descripcion;
	private PosicionProducto posicion;
	private double IVA;
	private double peso;
	private double volumen;

	public NuevoProducto(String nombre, Categoria categoria, double precio, String descripcion,
			PosicionProducto posicion, double IVA, double peso, double volumen) {
		this.nombre = nombre;
		this.categoria = categoria;
		this.precio = precio;
		this.descripcion = descripcion;
		this.posicion = posicion;
		this.IVA = IVA;
		this.peso = peso;
		this.volumen = volumen;
	}

	@Override
	public String doTest(EntityManager ent) {
		StringBuilder sb = new StringBuilder("Se ha creado el producto con su posici�n");

		Categoria cat = ent.merge(categoria);

		Producto producto = new Producto(posicion, cat);
		producto.setDescripcion(descripcion);
		producto.setNombre(nombre);
		producto.setPrecio(precio);
		producto.setIva(IVA);
		producto.setPeso(peso);
		producto.setVolumen(volumen);

		ent.persist(posicion);
		ent.persist(producto);

		return sb.toString();
	}
}