package business.impl.almacenero;

import javax.persistence.PersistenceException;

import business.exception.BusinessException;
import business.impl.util.Command;

import persistence.AlmaceneroFinder;
import persistence.exception.MyPersistenceException;

public class LoginAlmacenero implements Command {

	private String nombreUsuario;

	public LoginAlmacenero(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	@Override
	public Object execute() throws BusinessException {
		try {
			return AlmaceneroFinder.findByNombreUsuario(nombreUsuario);
		}

		catch (MyPersistenceException | PersistenceException e) {
			throw new BusinessException("Ha ocurrido un error al intentar loguer a un almacenero", e);
		}
	}

}