package business.impl;

import java.util.List;

import business.AlmaceneroService;
import business.exception.BusinessException;
import business.impl.almacenero.LoginAlmacenero;
import business.impl.almacenero.ObtenerOrdenesTrabajoRetomar;
import business.impl.util.CommandExecutor;
import model.Almacenero;
import model.OrdenTrabajo;

@SuppressWarnings("unchecked")
public class AlmaceneroServiceImpl implements AlmaceneroService {

	private CommandExecutor executor = new CommandExecutor();

	@Override
	public Almacenero login(String nombreUsuario) throws BusinessException {
		return (Almacenero) executor.execute(new LoginAlmacenero(nombreUsuario));
	}

	@Override
	public List<OrdenTrabajo> ObtenerOrdenesTrabajoRetomar(Almacenero alm) throws BusinessException {
		return (List<OrdenTrabajo>) executor.execute(new ObtenerOrdenesTrabajoRetomar(alm));
	}

}