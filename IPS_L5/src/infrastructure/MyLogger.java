package infrastructure;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MyLogger {

	private static Logger myLogger = Logger.getLogger("org.hibernate");

	private MyLogger() {

	}

	public static void log(String msg) {
		myLogger.log(Level.INFO, msg);
	}
	
}