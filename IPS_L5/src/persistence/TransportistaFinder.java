package persistence;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import business.exception.BusinessException;
import model.Transportista;
import persistence.exception.MyPersistenceException;
import persistence.util.Jpa;

public class TransportistaFinder {

	/**
	 * Si el transportista pasado no estaba sincronizado con la base de datos,
	 * este método devolverá un nuevo objeto Transportista que sí estará
	 * sincronizado con la base de datos
	 * 
	 * @param pedido
	 *            transportista que hay que buscar
	 * 
	 * @return copia del transportista (sincronizado con la base de datos), o el
	 *         mismo objeto si ya estaba sincronizado
	 * 
	 * @throws BusinessException
	 * 
	 */
	public static Transportista find(Transportista transportista) throws MyPersistenceException {
		try {
			return Jpa.getManager().find(Transportista.class, transportista.getId());
		}

		catch (NoResultException e) {
			StringBuilder sb = new StringBuilder();

			sb.append("No se ha encontrado el transportista de nombre '");
			sb.append(transportista.getNombre());
			sb.append("'");

			throw new MyPersistenceException(sb.toString());
		}

		catch (PersistenceException e) {
			StringBuilder sb = new StringBuilder();

			sb.append("Ha ocurrido un problema al buscar el transportista de nombre '");
			sb.append(transportista.getNombre());
			sb.append("'");

			throw new MyPersistenceException(sb.toString());
		}
	}

	public static List<Transportista> findAll() throws MyPersistenceException {
		try {
			return Jpa.getManager().createNamedQuery("Transportista.findAll", Transportista.class).getResultList();
		}

		catch (PersistenceException e) {
			throw new MyPersistenceException("Ha ocurrido un error al buscar todos los pedidos", e);
		}
	}

}