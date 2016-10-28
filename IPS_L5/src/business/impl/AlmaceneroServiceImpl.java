package business.impl;

import business.AlmaceneroService;
import business.exception.BusinessException;
import business.impl.almacenero.LoginAlmacenero;
import business.impl.util.CommandExecutor;
import model.Almacenero;

public class AlmaceneroServiceImpl implements AlmaceneroService {

	private CommandExecutor executor = new CommandExecutor();
	
	@Override
	public Almacenero login(String nombreUsuario) throws BusinessException {
		return (Almacenero) executor.execute(new LoginAlmacenero(nombreUsuario));
	}

}