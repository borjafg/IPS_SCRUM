package infrastructure;

import business.AlmacenService;
import business.EmpaquetadoService;
import business.impl.AlmacenServiceImpl;
import business.impl.EmpaquetadoServiceImpl;

public class ServiceFactory {

	/**
	 * Esto evita que se pueda crear una instancia de esta clase
	 * 
	 */
	private ServiceFactory() {

	}

	public static AlmacenService getAlmacenService() {
		return new AlmacenServiceImpl();
	}

	public static EmpaquetadoService getEmpaquetadoService() {
		return new EmpaquetadoServiceImpl();
	}
}