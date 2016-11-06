package persistence;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

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
}