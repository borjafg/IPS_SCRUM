package persistence;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import model.Almacenero;
import persistence.exception.MyPersistenceException;
import persistence.util.Jpa;

public class AlmaceneroFinder {

	/**
	 * Si el almacenero pasado no estaba sincronizado con la base de datos, este
	 * m�todo devolver� un nuevo objeto Almacenero que s� estar� sincronizado
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

			throw new MyPersistenceException(sb.toString());
		}
	}

	public static Almacenero findByNombreUsuario(String nombreUsuario) {
		try {
			return Jpa.getManager().createNamedQuery("Almacenero.findByLogin", Almacenero.class)
					.setParameter("login", nombreUsuario).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}