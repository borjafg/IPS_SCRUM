package business.impl.empaquetado;

import business.exception.BusinessException;
import business.impl.util.Command;
import model.OrdenTrabajo;
import model.types.EstadoOrdenTrabajo;
import persistence.OrdenTrabajoFinder;
import persistence.exception.MyPersistenceException;

public class TerminarOrdenTrabajo implements Command {

	private OrdenTrabajo ordenTrabajo;

	public TerminarOrdenTrabajo(OrdenTrabajo ordenTrabajo) {
		this.ordenTrabajo = ordenTrabajo;
	}

	@Override
	public Object execute() throws BusinessException {

		try {
			OrdenTrabajo ot = OrdenTrabajoFinder.find(ordenTrabajo);

			ot.setEstadoOrdenTrabajo(EstadoOrdenTrabajo.TERMINADA);
		}

		catch (MyPersistenceException e) {
			throw new BusinessException("No se pudo marcar la OT como terminada", e);
		}

		return null;
	}

}