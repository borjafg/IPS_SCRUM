package test.business.acciones;

import javax.persistence.EntityManager;

import model.Categoria;
import model.PosicionProducto;
import model.Producto;
import test.business.TestAction;

public class NuevoProducto implements TestAction {

	private Producto producto;

	public NuevoProducto(Object... producto) {
		PosicionProducto posProd = new PosicionProducto();
		Categoria cat = new Categoria();

		Producto prod = new Producto(posProd, cat);

		this.producto = prod;
	}

	@Override
	public String doTest(EntityManager ent) {
		
//		ent.persist(producto);
		
		
		return "Todavia no esta implementado";
	}
}