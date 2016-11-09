package persistence;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import model.OrdenTrabajo;
import model.Paquete;
import persistence.exception.MyPersistenceException;
import persistence.util.Jpa;

public class PaqueteFinder {

	/**
	 * Si el paquete pasado no estaba sincronizado con la base de datos, este
	 * método devolverá un nuevo objeto Paquete que sí estará sincronizado con
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
			List<Integer> numsCaja = Jpa.getManager().createNamedQuery("Paquete.findUltimoNumCaja", Integer.class)
					.setParameter("ordenTrabajo", ordenTrabajo).getResultList();
			
			if(numsCaja.isEmpty()) {
				return 0;
			}
			
			else {
				return numsCaja.get(0);
			}
		}

		catch (PersistenceException e) {
			throw new MyPersistenceException("Ha ocurrido un problema al generar un numero de caja");
		}
	}

}
