package persistence;

import model.OrdenTrabajo;
import persistence.util.Jpa;

public class OrdenTrabajoFinder {

	public static OrdenTrabajo findbyId(long id) {
		return Jpa.getManager().find(OrdenTrabajo.class, id);
	}
}