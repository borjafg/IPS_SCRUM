package persistence;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import model.Cliente;
import persistence.exception.MyPersistenceException;
import persistence.util.Jpa;

public class ClienteFinder {

	
	

	public static Cliente find(Cliente cliente) throws MyPersistenceException{
		try {
 
			return Jpa.getManager().find(Cliente.class, cliente.getId());
		}

		catch (NoResultException e) {
			StringBuilder sb = new StringBuilder();
			
			sb.append("No se ha encontrado el cliente con id = ");
			sb.append(cliente.getId());
			
			throw new MyPersistenceException(sb.toString());
		}

		catch (PersistenceException e) {
			StringBuilder sb = new StringBuilder();
			
			sb.append("Ha ocurrido un problema al buscar el cliente con id = ");
			sb.append(cliente.getId());
			
			throw new MyPersistenceException(sb.toString());
		}
	}
	
	
	
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
