package business.impl.empaquetado;

import javax.persistence.PersistenceException;

import business.exception.BusinessException;
import business.impl.util.Command;
import model.OrdenTrabajo;
import model.Paquete;
import model.types.EstadoPaquete;
import persistence.OrdenTrabajoFinder;
import persistence.exception.MyPersistenceException;
import persistence.util.Jpa;

public class CargarPaquete implements Command {

	private OrdenTrabajo ordenTrabajo;

	public CargarPaquete(OrdenTrabajo ordenTrabajo) {
		this.ordenTrabajo = ordenTrabajo;
	}

	@Override
	public Object execute() throws BusinessException {

		try {
			Paquete paq = null;

			OrdenTrabajo ot = OrdenTrabajoFinder.find(ordenTrabajo);

			for (Paquete paquete : ot.getPaquetes()) {

				if (paquete.getEstado() == EstadoPaquete.ABIERTO) {
					paq = paquete;
					break;
				}
			}

			// -----------------------------------
			// Si no hay ninún paquete abierto
			// -----------------------------------

			if (paq == null) {
				paq = new Paquete(ot);

				paq.setEstado(EstadoPaquete.ABIERTO);
				paq.setNumCaja(1);

				Jpa.getManager().persist(paq);
			}

			return paq;

		} catch (MyPersistenceException | PersistenceException e) {
			throw new BusinessException("Ha ocurrido un error al buscar un paquete asociado a la OT", e);
		}
	}

}