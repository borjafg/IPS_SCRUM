package persistence;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import model.Producto;
import persistence.exception.MyPersistenceException;
import persistence.util.Jpa;

public class ProductoFinder {

	
	
	
	/**
	 * Si el producto pasado no estaba sincronizado con la base de datos, este
	 * método devolverá un nuevo objeto Producto que sí estará sincronizado con
	 * la base de datos
	 * 
	 * @param producto
	 *            producto que hay que buscar
	 * 
	 * @return copia del producto (sincronizado con la base de datos), o el
	 *         mismo objeto si ya estaba sincronizado
	 * 
	 * @throws MyPersistenceException
	 * 
	 */
	public static Producto findById(Producto producto) throws MyPersistenceException {
		try {
			return Jpa.getManager().find(Producto.class, producto.getId());
		}

		catch (NoResultException e) {
			StringBuilder sb = new StringBuilder();

			sb.append("No se ha encontrado el producto con id = ");
			sb.append(producto.getId());

			throw new MyPersistenceException(sb.toString());
		}

		catch (PersistenceException e) {
			StringBuilder sb = new StringBuilder();

			sb.append("Ha ocurrido un problema al buscar el producto con id = ");
			sb.append(producto.getId());

			throw new MyPersistenceException(sb.toString());
		}
	}

	public static List<Producto> findAll() {
		return Jpa.getManager().createNamedQuery("Producto.findAll", Producto.class).getResultList();
	}
}