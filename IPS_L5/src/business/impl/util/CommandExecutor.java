package business.impl.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import business.exception.BusinessException;
import persistence.util.Jpa;

public class CommandExecutor {
	public Object execute(Command command) throws BusinessException {
		EntityManager em = null;

		try {
			em = Jpa.createEntityManager();
		} catch (PersistenceException excep) {
			throw new BusinessException(excep);
		}

		EntityTransaction trx = em.getTransaction();
		trx.begin();

		Object obj = null;

		try {
			obj = command.execute();

			trx.commit();
		}

		catch (BusinessException | PersistenceException excep) {
			if (trx.isActive()) {
				trx.rollback();
			}

			throw new BusinessException("Ha ocurrido un error al intentar conectar a la base de datos. Compruebe "
					+ "la conexión y si el problema persiste avise a un técnico o administrador.", excep);
		}

		finally {
			if (em.isOpen()) {
				em.close();
			}
		}

		return obj;
	}

}