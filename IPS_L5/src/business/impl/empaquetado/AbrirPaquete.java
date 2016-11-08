package business.impl.empaquetado;

import business.exception.BusinessException;
import business.impl.util.Command;
import model.OrdenTrabajo;
import model.Paquete;
import persistence.OrdenTrabajoFinder;
import persistence.PaqueteFinder;
import persistence.exception.MyPersistenceException;
import persistence.util.Jpa;

public class AbrirPaquete implements Command {

	private OrdenTrabajo ordenTrabajo;

	public AbrirPaquete(OrdenTrabajo ordenTrabajo) {
		this.ordenTrabajo = ordenTrabajo;
	}

	@Override
	public Object execute() throws BusinessException {

		try {
			OrdenTrabajo ot = OrdenTrabajoFinder.find(ordenTrabajo);

			// -----------------------------

			Paquete paquete = new Paquete(ot);

			int numCaja = PaqueteFinder.findUltimoNumCaja(ot);
			numCaja++;

			paquete.setNumCaja(numCaja);

			Jpa.getManager().persist(paquete);

			// ------------------------------

			return paquete;
		}

		catch (MyPersistenceException e) {
			throw new BusinessException(e);
		}
	}

}