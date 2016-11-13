package business.impl.empaquetado;

import javax.persistence.PersistenceException;

import business.exception.BusinessException;
import business.impl.util.Command;
import model.Almacenero;
import model.OrdenTrabajo;
import persistence.AlmaceneroFinder;
import persistence.OrdenTrabajoFinder;
import persistence.exception.MyPersistenceException;

public class AsignarAlmaceneroOrdenTrabajo implements Command {

	private Almacenero almacenero;
	private OrdenTrabajo ordenTrabajo;

	public AsignarAlmaceneroOrdenTrabajo(Almacenero almacenero, OrdenTrabajo ordenTrabajo) {
		this.almacenero = almacenero;
		this.ordenTrabajo = ordenTrabajo;
	}

	@Override
	public Object execute() throws BusinessException {

		try {
			Almacenero alm = AlmaceneroFinder.find(almacenero);
			OrdenTrabajo ot = OrdenTrabajoFinder.find(ordenTrabajo);

			ot.setAlmaceneroEmpaquetar(alm);
		}

		catch (MyPersistenceException | PersistenceException e) {
			throw new BusinessException("Ha ocurrido un error al asignar un almcacenero a una OT", e);
		}

		return null;
	}

}