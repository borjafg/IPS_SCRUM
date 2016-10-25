package test.business.acciones;

import javax.persistence.EntityManager;

import model.Almacenero;
import model.Categoria;
import model.Cliente;
import model.Incidencia;
import model.OrdenTrabajo;
import model.Paquete;
import model.PosicionProducto;
import model.ProductoEnOrdenTrabajo;
import model.ProductoEnPaquete;
import model.ProductoEnPedido;
import persistence.PedidoFinder;
import persistence.ProductoFinder;
import test.business.TestAction;

public class ProbarAccesoMapeador implements TestAction {

	@Override
	public String doTest(EntityManager ent) {
		StringBuilder sb = new StringBuilder();

		try {
			ent.createQuery("select c from Cliente c", Cliente.class).getResultList();
			sb.append("---- Acceso correcto a Clientes ----").append("\n\n");

			PedidoFinder.findAll();
			sb.append("---- Acceso correcto a Pedidos ----").append("\n\n");
			
			ent.createQuery("select p from PosicionProducto p", PosicionProducto.class).getResultList();
			sb.append("---- Acceso correcto a PosicionProductos ----").append("\n\n");

			ent.createQuery("select c from Categoria c", Categoria.class).getResultList();
			sb.append("---- Acceso correcto a Categorias ----").append("\n\n");
			
			ProductoFinder.findAll();
			sb.append("---- Acceso correcto a Productos ----").append("\n\n");
			
			ent.createQuery("select p from ProductoEnPedido p", ProductoEnPedido.class).getResultList();
			sb.append("---- Acceso correcto a ProductosPedido correcto ----").append("\n\n");
			
			ent.createQuery("select a from Almacenero a", Almacenero.class).getResultList();
			sb.append("---- Acceso correcto a Almaceneros ----").append("\n\n");
			
			ent.createQuery("select o from OrdenTrabajo o", OrdenTrabajo.class).getResultList();
			sb.append("---- Acceso correcto a OrdenesTrabajo ----").append("\n\n");
			
			ent.createQuery("select p from ProductoEnOrdenTrabajo p", ProductoEnOrdenTrabajo.class).getResultList();
			sb.append("---- Acceso correcto a ProductosOrdenTrabajo ----").append("\n\n");
			
			ent.createQuery("select i from Incidencia i", Incidencia.class).getResultList();
			sb.append("---- Acceso correcto a Incidencias ----").append("\n\n");

			ent.createQuery("select p from Paquete p", Paquete.class).getResultList();
			sb.append("---- Acceso correcto a Paquetes ----").append("\n\n");

			
			ent.createQuery("select p from ProductoEnPaquete p", ProductoEnPaquete.class).getResultList();
			sb.append("---- Acceso correcto a ProductosPaquete ----").append("\n\n");

			sb.append("=============================");

		} catch (Exception excep) {
			sb.append("\nHa ocurrido un error: " + excep.getMessage());
		}

		return sb.toString();
	}
}