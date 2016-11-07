package business.impl.empaquetado;

import javax.persistence.PersistenceException;

import business.exception.BusinessException;
import business.impl.util.Command;
import model.OrdenTrabajo;
import model.Paquete;
import model.types.EstadoPaquete;
import persistence.OrdenTrabajoFinder;
import persistence.exception.MyPersistenceException;

public class CargarPaquete implements Command {

	private OrdenTrabajo ordenTrabajo;

	public CargarPaquete(OrdenTrabajo ordenTrabajo) {
		this.ordenTrabajo = ordenTrabajo;
	}

	@Override
	public Object execute() throws BusinessException {

		try {
			OrdenTrabajo ot = OrdenTrabajoFinder.find(ordenTrabajo);

			for (Paquete paquete : ot.getPaquetes()) {

				if (paquete.getEstado() == EstadoPaquete.ABIERTO) {
					return paquete;
				}
			}

		} catch (MyPersistenceException | PersistenceException e) {
			throw new BusinessException(e);
		}

		return null;
	}

}