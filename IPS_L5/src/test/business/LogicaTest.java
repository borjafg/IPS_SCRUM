package test.business;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

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
import persistence.util.Jpa;

public class LogicaTest {

	public static final String TEST_ACCESO = "accesoBaseDatos";

	/**
	 * Realiza un test u otro según el valor del parámetro prueba.
	 * 
	 * @param prueba
	 *            string que indica que prueba se quiere realizar
	 * 
	 * @return resultado de realizar la prueba
	 * 
	 */
	public String ejecutarPrueba(String prueba) {
		String resultado = "";

		EntityManager ent;
		EntityTransaction trx;

		try {
			Jpa.createEntityManager();

			ent = Jpa.getManager();
			trx = ent.getTransaction();

			trx.begin();
		} catch (Exception e) {
			return "Error al conectar a la base de datos";
		}

		// =========================
		// Ejecutar la prueba
		// =========================
		
		try {
			analizarEntrada(prueba, ent);

			trx.commit();
		} catch (Exception excep) {
			if (trx.isActive()) {
				trx.rollback();
			}

			resultado = "Ha ocurrido un error + \n\n" + excep.getMessage();
		} finally {
			ent.close();
		}

		// =========================
		// Devolver el resultado
		// =========================
		
		return resultado;
	}

	private String analizarEntrada(String prueba, EntityManager ent) {
		switch (prueba) {

		case "accesoBaseDatos":
			return probarAccesoMapeador(ent);

		default:
			return "Acción desconocida";
		}
	}

	public String probarAccesoMapeador(EntityManager ent) {
		StringBuilder sb = new StringBuilder();

		try {
			ent.createQuery("select c from Cliente c", Cliente.class).getResultList();
			sb.append("---- Acceso correcto a TClientes ----").append("\n\n");

			ent.createQuery("select i from Incidencia i", Incidencia.class).getResultList();
			sb.append("---- Acceso correcto a TIncidencias ----").append("\n\n");

			ent.createQuery("select o from OrdenTrabajo o", OrdenTrabajo.class).getResultList();
			sb.append("---- Acceso correcto a TOrdenesTrabajo ----").append("\n\n");

			ent.createQuery("select p from Paquete p", Paquete.class).getResultList();
			sb.append("---- Acceso correcto a TPaquetes----").append("\n\n");

			PedidoFinder.findAll();
			sb.append("---- Acceso correcto a TPedidos----").append("\n\n");

			ent.createQuery("select p from PosicionProducto p", PosicionProducto.class).getResultList();
			sb.append("---- Acceso correcto a TPosicionProductos----").append("\n\n");

			ProductoFinder.findAll();
			sb.append("---- Acceso correcto a TProductos----").append("\n\n");

			ent.createQuery("select p from ProductoEnOrdenTrabajo p", ProductoEnOrdenTrabajo.class).getResultList();
			sb.append("---- Acceso correcto a TProductosOrdenTrabajo----").append("\n\n");

			ent.createQuery("select p from ProductoEnPaquete p", ProductoEnPaquete.class).getResultList();
			sb.append("---- Acceso correcto a TProductosPaquete----").append("\n\n");

			ent.createQuery("select p from ProductoEnPedido p", ProductoEnPedido.class).getResultList();
			sb.append("---- Acceso correcto a ProductosPedido correcto ----").append("\n\n");

			sb.append("=============================");

		} catch (Exception excep) {
			sb.append("\nHa ocurrido un error: " + excep.getMessage());
		}

		return sb.toString();
	}
}