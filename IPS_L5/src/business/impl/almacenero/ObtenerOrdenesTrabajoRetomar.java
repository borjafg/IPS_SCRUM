package business.impl.almacenero;

import javax.persistence.PersistenceException;

import business.exception.BusinessException;
import business.impl.util.Command;
import model.Almacenero;
import persistence.OrdenTrabajoFinder;
import persistence.exception.MyPersistenceException;

public class ObtenerOrdenesTrabajoRetomar implements Command {

	private Almacenero almacenero;

	public ObtenerOrdenesTrabajoRetomar(Almacenero almacenero) {
		this.almacenero = almacenero;
	}

	@Override
	public Object execute() throws BusinessException {
		try {
			return OrdenTrabajoFinder.findOrdenesTrabajoRetomar(almacenero);
		}

		catch (MyPersistenceException | PersistenceException pe) {
			throw new BusinessException("Ha ocurrido un error al buscar OT para retomar", pe);
		}
	}

}