package test.business.acciones;

import javax.persistence.EntityManager;

import model.Categoria;
import model.PosicionProducto;
import model.Producto;
import test.business.TestAction;

public class NuevoProducto implements TestAction {

	private Producto producto;

	public NuevoProducto(String nombre, Categoria categoria, double precio, String descripcion, PosicionProducto posicion) {
		producto = new Producto(posicion, categoria);//faltan parametros???
	}

	@Override
	public String doTest(EntityManager ent) {
		StringBuilder sb = new StringBuilder();

		try{
			ent.persist(producto);
			sb.append("Se ha creado el producto correctamente \n\n");
		}catch(Exception ex){
			sb.append("\n Ha ocurrido un error \n");
		}
		return sb.toString();
	}
}