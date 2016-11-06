package business.impl.recogida;

import java.util.Set;

import javax.persistence.PersistenceException;

import business.exception.BusinessException;
import business.impl.util.Command;
import model.OrdenTrabajo;
import model.ProductoEnOrdenTrabajo;
import persistence.OrdenTrabajoFinder;
import persistence.exception.MyPersistenceException;

public class ObtenerProductosOT implements Command {

	private OrdenTrabajo ordenTrabajo;

	public ObtenerProductosOT(OrdenTrabajo ordenTrabajo) {
		this.ordenTrabajo = ordenTrabajo;
	}

	@Override
	public Set<ProductoEnOrdenTrabajo> execute() throws BusinessException {
		try {
			OrdenTrabajo ot = OrdenTrabajoFinder.find(ordenTrabajo);

			return ot.getProductosOrdenTrabajo();
		}

		catch (MyPersistenceException e) {
			throw new BusinessException(e);
		}

		catch (PersistenceException e) {
			throw new BusinessException(
					"Error al buscar los productos de la Orden de trabajo con id = " + ordenTrabajo.getId());
		}
	}

}