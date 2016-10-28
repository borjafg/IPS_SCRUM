package business.impl.almacenero;

import business.exception.BusinessException;
import business.impl.util.Command;

import persistence.AlmaceneroFinder;

public class LoginAlmacenero implements Command {

	private String nombreUsuario;
	
	public LoginAlmacenero(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
	@Override
	public Object execute() throws BusinessException {
		return AlmaceneroFinder.findByNombreUsuario(nombreUsuario);
	}

}