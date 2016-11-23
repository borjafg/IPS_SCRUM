package business.impl.envios;

import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceException;

import business.exception.BusinessException;
import business.impl.util.Command;
import model.Envio;
import model.Paquete;
import persistence.PaqueteFinder;
import persistence.exception.MyPersistenceException;
import persistence.util.Jpa;

public class CerrarEnvio implements Command {

	private List<Paquete> paquetesEnvio;

	public CerrarEnvio(List<Paquete> paquetesEnvio) {
		this.paquetesEnvio = paquetesEnvio;
	}

	@Override
	public Object execute() throws BusinessException {
		try {
			Paquete paq;

			Envio envio = new Envio();
			envio.setFecha(new Date());

			Jpa.getManager().persist(envio);

			for (Paquete paquete : paquetesEnvio) {
				paq = PaqueteFinder.find(paquete);
				paq.setEnvio(envio);
			}

			return null;
		}

		catch (MyPersistenceException | PersistenceException excep) {
			throw new BusinessException("Ha ocurrido un error al cerrar el envío", excep);
		}
	}

}