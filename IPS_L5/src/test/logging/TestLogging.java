package test.logging;

import infrastructure.Log;

public class TestLogging {

	public static void main(String args[]) {

		probarLogging();
	}

	private static void probarLogging() {
		Log.debug("Esto es un mensaje de log -------");
		Log.debug("Esto es un mensaje de log -- 1 --");
		Log.debug("Esto es un mensaje de log -------");

		Log.info("Esto es un mensaje de log -------");
		Log.info("Esto es un mensaje de log -- 1 --");
		Log.info("Esto es un mensaje de log -------");

		Log.warn("Esto es un mensaje de log -------");
		Log.warn("Esto es un mensaje de log -- 1 --");
		Log.warn("Esto es un mensaje de log -------");

		Log.error("Esto es un mensaje de log -------");
		Log.error("Esto es un mensaje de log -- 1 --");
		Log.error("Esto es un mensaje de log -------");

		try {
			Thread.sleep(5100);
		}

		catch (InterruptedException e) {
			Log.error("No se ha podido esperar los 5 segundos");
		}

		Log.debug("Esto es un mensaje de log -------");
		Log.debug("Esto es un mensaje de log -- 2 --");
		Log.debug("Esto es un mensaje de log -------");

		Log.info("Esto es un mensaje de log -------");
		Log.info("Esto es un mensaje de log -- 2 --");
		Log.info("Esto es un mensaje de log -------");

		Log.warn("Esto es un mensaje de log -------");
		Log.warn("Esto es un mensaje de log -- 2 --");
		Log.warn("Esto es un mensaje de log -------");

		Log.error("Esto es un mensaje de log -------");
		Log.error("Esto es un mensaje de log -- 2 --");
		Log.error("Esto es un mensaje de log -------");

		try {
			Thread.sleep(5100);
		}

		catch (InterruptedException e) {
			Log.error("No se ha podido esperar los 5 segundos");
		}

		Log.debug("Esto es un mensaje de log -------");
		Log.debug("Esto es un mensaje de log -- 3 --");
		Log.debug("Esto es un mensaje de log -------");

		Log.info("Esto es un mensaje de log -------");
		Log.info("Esto es un mensaje de log -- 3 --");
		Log.info("Esto es un mensaje de log -------");

		Log.warn("Esto es un mensaje de log -------");
		Log.warn("Esto es un mensaje de log -- 3 --");
		Log.warn("Esto es un mensaje de log -------");

		Log.error("Esto es un mensaje de log -------");
		Log.error("Esto es un mensaje de log -- 3 --");
		Log.error("Esto es un mensaje de log -------");
	}

}