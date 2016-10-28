package infrastructure;

import business.AlmaceneroService;
import business.EmpaquetadoService;
import business.RecogidaService;
import business.UserService;

import business.impl.AlmacenServiceImpl;
import business.impl.AlmaceneroServiceImpl;
import business.impl.EmpaquetadoServiceImpl;
import business.impl.UserServiceImpl;

public class ServiceFactory {

	/**
	 * Esto evita que se pueda crear una instancia de esta clase
	 * 
	 */
	private ServiceFactory() {

	}

	public static AlmaceneroService getAlmaceneroService() {
		return new AlmaceneroServiceImpl();
	}

	public static RecogidaService getRecogidaService() {
		return new AlmacenServiceImpl();
	}

	public static EmpaquetadoService getEmpaquetadoService() {
		return new EmpaquetadoServiceImpl();
	}

	public static UserService getUserService() {
		return new UserServiceImpl();
	}
}