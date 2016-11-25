package infrastructure;

import business.AdministracionService;
import business.AlmaceneroService;
import business.EmpaquetadoService;
import business.EnvioService;
import business.RecogidaService;
import business.UserService;
import business.impl.AdministracionServiceImpl;
import business.impl.AlmacenServiceImpl;
import business.impl.AlmaceneroServiceImpl;
import business.impl.EmpaquetadoServiceImpl;
import business.impl.EnvioServiceImpl;
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

	public static EnvioService getEnvioService() {
		return new EnvioServiceImpl();
	}

	public static UserService getUserService() {
		return new UserServiceImpl();
	}
	
	public static AdministracionService getAdministracionService() {
		return new AdministracionServiceImpl();
	}

}