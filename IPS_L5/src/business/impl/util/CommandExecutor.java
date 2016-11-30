package business.impl.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import business.exception.BusinessException;
import infrastructure.Log;
import persistence.util.Jpa;

public class CommandExecutor {
	public Object execute(Command command) throws BusinessException {
		EntityManager em = null;

		try {
			em = Jpa.createEntityManager();
		}

		catch (PersistenceException excep) {
			Log.error("Error al crear el entity manager", excep);

			throw new BusinessException("Ha ocurrido un error al intentar conectar a la base de datos. Compruebe "
					+ "la conexi�n y si el problema persiste avise a un t�cnico o administrador.", excep);
		}

		EntityTransaction trx = em.getTransaction();
		trx.begin();

		Object obj = null;

		try {
			obj = command.execute();

			trx.commit();
		}

		catch (PersistenceException excep) {
			if (trx != null && trx.isActive()) {
				trx.rollback();
			}

			Log.error("Ha ocurrido un error durante una transacci�n", excep);

			throw new BusinessException("Ha ocurrido un error al intentar conectar a la base de datos. Compruebe "
					+ "la conexi�n y si el problema persiste avise a un t�cnico o administrador.", excep);
		}

		catch (BusinessException excep) {
			Log.error("Ha ocurrido un error durante la transacci�n", excep);

			throw excep;
		}

		finally {
			if (em != null && em.isOpen()) {
				em.close();
			}
		}

		return obj;
	}

}