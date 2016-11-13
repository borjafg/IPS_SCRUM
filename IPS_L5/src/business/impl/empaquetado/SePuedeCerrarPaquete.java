package business.impl.empaquetado;

import javax.persistence.PersistenceException;

import business.exception.BusinessException;
import business.impl.util.Command;
import model.Paquete;
import persistence.PaqueteFinder;
import persistence.exception.MyPersistenceException;

public class SePuedeCerrarPaquete implements Command {

	private Paquete paquete;

	public SePuedeCerrarPaquete(Paquete paquete) {
		this.paquete = paquete;
	}

	@Override
	public Object execute() throws BusinessException {
		try {
			Paquete paq = PaqueteFinder.find(paquete);
			return paq.getProductosPaquete().size() > 0;
		}

		catch (MyPersistenceException | PersistenceException e) {
			throw new BusinessException("Ha ocurrio un error al buscar información del paquete", e);
		}
	}

}