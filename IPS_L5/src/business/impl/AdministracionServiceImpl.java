package business.impl;

import java.util.List;

import business.AdministracionService;
import business.exception.BusinessException;
import business.impl.administracion.GenerarInformeRecogida;
import business.impl.util.CommandExecutor;
import ui.administracion.myTypes.DatosInformeAlmacenero;

@SuppressWarnings("unchecked")
public class AdministracionServiceImpl implements AdministracionService {

	private CommandExecutor executor = new CommandExecutor();

	@Override
	public List<DatosInformeAlmacenero> generarInformeRecogida() throws BusinessException {
		return (List<DatosInformeAlmacenero>) executor.execute(new GenerarInformeRecogida());
	}

}