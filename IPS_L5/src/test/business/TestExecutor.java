package test.business;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import persistence.util.Jpa;

public class TestExecutor {

	/**
	 * Realiza un test u otro según el valor del parámetro prueba.
	 * 
	 * @param prueba
	 *            string que indica que prueba se quiere realizar
	 * 
	 * @return resultado de realizar la prueba
	 * 
	 */
	public String ejecutarPrueba(TestAction test) {
		String resultado = "";

		EntityManager ent;
		EntityTransaction trx;

		// =========================
		// Crear una transacción
		// =========================

		try {
			Jpa.createEntityManager();

			ent = Jpa.getManager();
			trx = ent.getTransaction();

			trx.begin();
		} catch (Exception excep) {
			return "Ha ocurrido un error: " + excep.getCause();
		}

		// =========================
		// Ejecutar la prueba
		// =========================

		try {
			resultado = test.doTest(ent);

			trx.commit();
		} catch (Exception excep) {
			if (trx.isActive()) {
				trx.rollback();
			}

			resultado = "Ha ocurrido un error + \n\n" + excep.getCause();
		} finally {
			ent.close();
		}

		// =========================
		// Devolver el resultado
		// =========================

		return resultado;
	}
}