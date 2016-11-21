package business.impl.envios;

import javax.persistence.PersistenceException;

import business.exception.BusinessException;
import business.impl.util.Command;
import persistence.PaqueteFinder;
import persistence.exception.MyPersistenceException;

public class ObtenerListaPaquetesSinEnvio implements Command {

	@Override
	public Object execute() throws BusinessException {
		try {
			return PaqueteFinder.findPaquetesNoAsociadosEnvio();
		}

		catch (MyPersistenceException | PersistenceException pe) {
			throw new BusinessException("Ha ocurrido un error al buscar los paquetes no asociados a un pedido", pe);
		}
	}

}