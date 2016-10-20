package persistence;

import model.Paquete;
import persistence.util.Jpa;

public class PaqueteFinder {

	public static Paquete findById(Long id) {
		return Jpa.getManager().find(Paquete.class, id);
	}
}
