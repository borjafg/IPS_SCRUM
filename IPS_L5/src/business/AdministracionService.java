package business;

import java.util.List;

import business.exception.BusinessException;
import ui.administracion.myTypes.DatosInformeAlmacenero;

public interface AdministracionService {

	public List<DatosInformeAlmacenero> generarInformeRecogida() throws BusinessException;

}