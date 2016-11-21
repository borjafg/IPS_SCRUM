package business.impl.envios;

import javax.persistence.PersistenceException;

import business.exception.BusinessException;
import business.impl.util.Command;
import persistence.TransportistaFinder;
import persistence.exception.MyPersistenceException;

public class ObtenerListaTransportistas implements Command {

	@Override
	public Object execute() throws BusinessException {
		try {
			return TransportistaFinder.findAll();
		}

		catch (MyPersistenceException | PersistenceException e) {
			throw new BusinessException("Ha ocurrido un error al buscar los transportistas", e);
		}
	}

}