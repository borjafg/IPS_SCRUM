package business.impl.envios;

import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceException;

import business.exception.BusinessException;
import business.impl.util.Command;
import model.Envio;
import model.Paquete;
import model.Transportista;
import persistence.PaqueteFinder;
import persistence.TransportistaFinder;
import persistence.exception.MyPersistenceException;
import persistence.util.Jpa;

public class CerrarEnvio implements Command {

	private List<Paquete> paquetesEnvio;
	private Transportista transportista;

	public CerrarEnvio(List<Paquete> paquetesEnvio, Transportista transportista) {
		this.paquetesEnvio = paquetesEnvio;
		this.transportista = transportista;
	}

	@Override
	public Object execute() throws BusinessException {
		try {
			Transportista transp = TransportistaFinder.find(transportista);

			Envio envio = new Envio(transp);
			envio.setFecha(new Date());

			Jpa.getManager().persist(envio);

			Paquete paq;

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