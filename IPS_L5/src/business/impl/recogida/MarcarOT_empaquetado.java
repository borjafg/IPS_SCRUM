package business.impl.recogida;

import javax.persistence.PersistenceException;

import business.exception.BusinessException;
import business.impl.util.Command;
import model.OrdenTrabajo;
import model.types.EstadoOrdenTrabajo;
import persistence.OrdenTrabajoFinder;
import persistence.exception.MyPersistenceException;

public class MarcarOT_empaquetado implements Command {

	private OrdenTrabajo ordenTrabajo;

	public MarcarOT_empaquetado(OrdenTrabajo ordenTrabajo) {
		this.ordenTrabajo = ordenTrabajo;
	}

	@Override
	public Object execute() throws BusinessException {

		try {
			OrdenTrabajo ot = OrdenTrabajoFinder.find(ordenTrabajo);
			
			ot.setEstadoOrdenTrabajo(EstadoOrdenTrabajo.EMPAQUETADO);
		}

		catch (MyPersistenceException e) {
			throw new BusinessException("Error al marcar para empaquetado la OT");
		}

		catch (PersistenceException e) {
			throw new BusinessException("Error al marcar para empaquetado la OT");
		}

		return null;
	}

}