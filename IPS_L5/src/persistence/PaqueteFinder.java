package persistence;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import infrastructure.MyLogger;
import model.OrdenTrabajo;
import model.Paquete;
import persistence.exception.MyPersistenceException;
import persistence.util.Jpa;

public class PaqueteFinder {

	/**
	 * Si el paquete pasado no estaba sincronizado con la base de datos, este
	 * m�todo devolver� un nuevo objeto Paquete que s� estar� sincronizado con
	 * la base de datos
	 * 
	 * @param paquete
	 *            paquete que hay que buscar
	 * 
	 * @return copia del paquete (sincronizado con la base de datos), o el mismo
	 *         objeto si ya estaba sincronizado
	 * 
	 * @throws MyPersistenceException
	 * 
	 */
	public static Paquete find(Paquete paquete) throws MyPersistenceException {
		try {
			return Jpa.getManager().find(Paquete.class, paquete.getId());
		}

		catch (NoResultException e) {
			StringBuilder sb = new StringBuilder();

			sb.append("No se ha encontrado el paquete con id = ");
			sb.append(paquete.getId());

			throw new MyPersistenceException(sb.toString());
		}

		catch (PersistenceException e) {
			StringBuilder sb = new StringBuilder();

			sb.append("Ha ocurrido un problema al buscar el paquete con id = ");
			sb.append(paquete.getId());

			throw new MyPersistenceException(sb.toString());
		}
	}

	public static int findUltimoNumCaja(OrdenTrabajo ordenTrabajo) throws MyPersistenceException {
		try {
			Integer numCaja = Jpa.getManager().createNamedQuery("Paquete.findUltimoNumCaja", Integer.class)
					.setParameter("ordenTrabajo", ordenTrabajo).getSingleResult();
			
			MyLogger.log("Obtenido numero de caja " + numCaja);
			
			return numCaja;
		}

		catch(NoResultException e) {
			return 0; // No se habia creado ning�n paquete
		}
		
		catch (PersistenceException e) {
			throw new MyPersistenceException("Ha ocurrido un problema al generar un numero de caja");
		}
	}

}
