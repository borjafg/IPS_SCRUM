package persistence;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import model.OrdenTrabajo;
import model.Pedido;
import model.ProductoEnOrdenTrabajo;
import persistence.exception.MyPersistenceException;
import persistence.util.Jpa;

public class ProductoEnOrdenTrabajoFinder {

	/**
	 * Si el producto de una OT pasado no estaba sincronizado con la base de
	 * datos, este método devolverá un nuevo objeto ProductoEnOrdenTrabajo que
	 * sí estará sincronizado con la base de datos
	 * 
	 * @param pot
	 *            producto de una orden de trabajo, que hay que sincronizar con
	 *            la base de datos.
	 * 
	 * @return copia del producto de una OT (sincronizado con la base de datos),
	 *         o el mismo objeto si ya estaba sincronizado
	 * 
	 * @throws MyPersistenceException
	 * 
	 */
	public static ProductoEnOrdenTrabajo find(ProductoEnOrdenTrabajo pot) throws MyPersistenceException {
		try {
			return Jpa.getManager().find(ProductoEnOrdenTrabajo.class, pot.getId());
		}

		catch (NoResultException e) {
			StringBuilder sb = new StringBuilder();

			sb.append("No se ha encontrado el producto de una OT");

			throw new MyPersistenceException(sb.toString());
		}

		catch (PersistenceException e) {
			StringBuilder sb = new StringBuilder();

			sb.append("Ha ocurrido un problema al buscar el producto de una OT");

			throw new MyPersistenceException(sb.toString());
		}
	}

	/**
	 * Busca que productos de la orden de trabajo que se le indica no se han
	 * empaquetado.
	 * 
	 * @param ordenTrabajo
	 *            orden de trabajo de la que buscar productos
	 * 
	 * @return lista de productos que falta empaquetar y que pertenecen a la
	 *         orden de trabajo.
	 * 
	 * @throws MyPersistenceException
	 * 
	 */
	public static List<ProductoEnOrdenTrabajo> findPendientesEmpaquetar(OrdenTrabajo ordenTrabajo)
			throws MyPersistenceException {

		try {
			return Jpa.getManager()
					.createNamedQuery("ProductoEnOrdenTrabajo.findPendientesEmpaquetar", ProductoEnOrdenTrabajo.class)
					.setParameter("ordenTrabajo", ordenTrabajo).getResultList();
		}

		catch (PersistenceException e) {
			StringBuilder sb = new StringBuilder();

			sb.append("Ha ocurrido un problema al buscar el producto de una OT");

			throw new MyPersistenceException(sb.toString());
		}
	}

	/**
	 * Busca que productos de la orden de trabajo que se le indica no se han
	 * empaquetado y pertenecen a un determinada pedido.
	 * 
	 * @param ordenTrabajo
	 *            orden de trabajo de la que buscar productos
	 * 
	 * @return lista de productos que falta empaquetar y que pertenecen a la
	 *         orden de trabajo.
	 * 
	 * @throws MyPersistenceException
	 * 
	 */
	public static List<ProductoEnOrdenTrabajo> findPendientesEmpaquetarPedido(OrdenTrabajo ordenTrabajo, Pedido pedido)
			throws MyPersistenceException {

		try {
			return Jpa.getManager()
					.createNamedQuery("ProductoEnOrdenTrabajo.findPendientesEmpaquetarPedido",
							ProductoEnOrdenTrabajo.class)
					.setParameter("ordenTrabajo", ordenTrabajo).setParameter("pedido", pedido).getResultList();
		}

		catch (PersistenceException e) {
			StringBuilder sb = new StringBuilder();

			sb.append("Ha ocurrido un problema al buscar el producto de una OT");

			throw new MyPersistenceException(sb.toString());
		}
	}
}