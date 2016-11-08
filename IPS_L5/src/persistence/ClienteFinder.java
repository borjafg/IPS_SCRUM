package persistence;

import javax.persistence.NoResultException;

import model.Cliente;
import persistence.util.Jpa;

public class ClienteFinder {

	
	
	
	
	
	
	/**
	 * @param nombreCliente
	 * 	El nombre del cliente que estamos buscando
	 * @return
	 * 	 El clinete que se encuentra con ese nombre
	 */
	public static Cliente findByNombre(String nombreCliente) {
		try {
			return Jpa.getManager().createNamedQuery("Cliente.findByName", Cliente.class)
					.setParameter("nombre", nombreCliente).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
