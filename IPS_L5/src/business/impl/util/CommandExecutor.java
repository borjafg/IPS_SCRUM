package business.impl.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import business.exception.BusinessException;
import persistence.util.Jpa;

public class CommandExecutor {
	public Object execute(Command command) throws BusinessException {
		EntityManager em = Jpa.createEntityManager();

		EntityTransaction trx = em.getTransaction();
		trx.begin();

		Object obj = null;

		try {
			obj = command.execute();

			trx.commit();
		}

		catch (BusinessException | RuntimeException excep) {
			if (trx.isActive()) {
				trx.rollback();
			}

			throw excep;
		}

		finally {
			if (em.isOpen()) {
				em.close();
			}
		}

		return obj;
	}
}