package business.impl.recogida;

import javax.persistence.PersistenceException;

import business.exception.BusinessException;
import business.impl.util.Command;
import model.Almacenero;
import model.OrdenTrabajo;
import model.types.EstadoOrdenTrabajo;
import persistence.AlmaceneroFinder;
import persistence.OrdenTrabajoFinder;
import persistence.exception.MyPersistenceException;

public class MarcarOT_empaquetado implements Command {

	private OrdenTrabajo ordenTrabajo;
	private Almacenero almacenero;

	public MarcarOT_empaquetado(OrdenTrabajo ordenTrabajo, Almacenero almacenero) {
		this.ordenTrabajo = ordenTrabajo;
		this.almacenero = almacenero;
	}

	@Override
	public Object execute() throws BusinessException {

		try {
			OrdenTrabajo ot = OrdenTrabajoFinder.find(ordenTrabajo);

			ot.setEstadoOrdenTrabajo(EstadoOrdenTrabajo.EMPAQUETADO);

			if (almacenero != null) {
				ot.setAlmaceneroEmpaquetar(AlmaceneroFinder.find(almacenero));
			}
		}

		catch (MyPersistenceException | PersistenceException e) {
			throw new BusinessException("Ha ocurrido un error al marcar para empaquetado la OT", e);
		}

		return null;
	}

}