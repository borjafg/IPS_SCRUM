package business.impl.recogida;

import javax.persistence.PersistenceException;

import business.exception.BusinessException;
import business.impl.util.Command;
import model.OrdenTrabajo;
import persistence.OrdenTrabajoFinder;
import persistence.exception.MyPersistenceException;

public class HuboIncidencias implements Command {

	private OrdenTrabajo ordenTrabajo;

	public HuboIncidencias(OrdenTrabajo ordenTrabajo) {
		this.ordenTrabajo = ordenTrabajo;
	}

	@Override
	public Object execute() throws BusinessException {
		try {
			OrdenTrabajo ot = OrdenTrabajoFinder.find(ordenTrabajo);

			return ot.getIncidencias().size() > 0;
		}

		catch (MyPersistenceException | PersistenceException e) {
			throw new BusinessException("Ha ocurrido un error al comprobar si hay incidencias", e);
		}
	}

}