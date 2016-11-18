package business.impl.empaquetado;

import javax.persistence.PersistenceException;

import business.exception.BusinessException;
import business.impl.util.Command;
import model.Almacenero;
import persistence.OrdenTrabajoFinder;
import persistence.exception.MyPersistenceException;

public class CargarOrdenesTrabajoEmpaquetar implements Command {

	private Almacenero almacenero;

	public CargarOrdenesTrabajoEmpaquetar(Almacenero almacenero) {
		this.almacenero = almacenero;
	}

	@Override
	public Object execute() throws BusinessException {
		try {
			return OrdenTrabajoFinder.findOT_EmpaquetarAlmacenero(almacenero);
		}

		catch (MyPersistenceException | PersistenceException e) {
			throw new BusinessException("Ha ocurrido un error al cargar la lista de OT para empaquetar", e);
		}
	}

}