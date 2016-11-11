package infrastructure;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Clase que genera mensajes de log.<br>
 * <br>
 * Cambiando un fichero de configuración se puede evitar que salgan mensajes por
 * <br>
 * pantalla. También se puede filtrar la salida de mensajes para sacarlo en
 * un<br>
 * fichero o en varios si fuera neseario.
 * 
 * 
 */
public class Log {

	private static final Logger logger = LogManager.getLogger("MyLogger");

	/**
	 * Envia un mensaje con nivel de <i><b>debug</b></i>
	 * 
	 * @param mensaje
	 *            mensaje que se quiere mandar por la salida del log
	 * 
	 */
	public static void debug(String mensaje) {
		logger.log(Level.DEBUG, mensaje);
	}

	/**
	 * Envia un mensaje con nivel de <i><b>warning</b></i>
	 * 
	 * @param mensaje
	 *            mensaje que se quiere mandar por la salida del log
	 * 
	 */
	public static void warn(String mensaje) {
		logger.log(Level.WARN, mensaje);
	}

	/**
	 * Envia un mensaje con nivel de <i><b>error</b></i>
	 * 
	 * @param mensaje
	 *            mensaje que se quiere mandar por la salida del log
	 * @param excepcion
	 *            excepcion de la que se quiere mostrar la información
	 * 
	 */
	public static void error(String mensaje, Throwable excepcion) {
		logger.log(Level.ERROR, mensaje, excepcion);
	}
}