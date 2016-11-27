package persistence;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import model.Almacenero;
import persistence.exception.MyPersistenceException;
import persistence.util.Jpa;

public class AlmaceneroFinder {

	/**
	 * Si el almacenero pasado no estaba sincronizado con la base de datos, este
	 * método devolverá un nuevo objeto Almacenero que sí estará sincronizado
	 * con la base de datos
	 * 
	 * @param almacenero
	 *            almacenero que hay que buscar
	 * 
	 * @return copia del almacenero (sincronizada con la base de datos), o el
	 *         mismo objeto si ya estaba sincronizado
	 * 
	 * @throws MyPersistenceException
	 * 
	 */
	public static Almacenero find(Almacenero almacenero) throws MyPersistenceException {
		try {
			return Jpa.getManager().find(Almacenero.class, almacenero.getId());
		}

		catch (NoResultException e) {
			StringBuilder sb = new StringBuilder();

			sb.append("No se ha encontrado el almacenero con id = ");
			sb.append(almacenero.getId());

			throw new MyPersistenceException(sb.toString());
		}

		catch (PersistenceException e) {
			StringBuilder sb = new StringBuilder();

			sb.append("Ha ocurrido un problema al buscar el almacenero con id = ");
			sb.append(almacenero.getId());

			throw new MyPersistenceException(sb.toString(), e);
		}
	}

	public static List<Almacenero> findAll() throws MyPersistenceException {
		try {
			return Jpa.getManager().createNamedQuery("Almacenero.findAll", Almacenero.class).getResultList();
		}

		catch (PersistenceException e) {
			throw new MyPersistenceException("Ha ocurrido un error al buscar la lista de almaceneros", e);
		}
	}

	public static Almacenero findByNombreUsuario(String nombreUsuario) throws MyPersistenceException {
		try {
			return Jpa.getManager().createNamedQuery("Almacenero.findByLogin", Almacenero.class)
					.setParameter("login", nombreUsuario).getSingleResult();
		}

		catch (NoResultException e) {
			return null;
		}

		catch (PersistenceException e) {
			throw new MyPersistenceException("Ha ocurrido un error al buscar un almacenero", e);
		}
	}
}