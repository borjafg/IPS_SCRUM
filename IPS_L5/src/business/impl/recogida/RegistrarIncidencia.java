package business.impl.recogida;

import java.util.Date;

import javax.persistence.PersistenceException;

import business.exception.BusinessException;
import business.impl.util.Command;
import model.Incidencia;
import model.OrdenTrabajo;
import persistence.OrdenTrabajoFinder;
import persistence.exception.MyPersistenceException;
import persistence.util.Jpa;

public class RegistrarIncidencia implements Command {

	private OrdenTrabajo ordenTrabajo;
	private String causa;
	
	public RegistrarIncidencia(OrdenTrabajo ordenTrabajo, String causa) {
		this.ordenTrabajo = ordenTrabajo;
		this.causa = causa;
	}

	@Override
	public Object execute() throws BusinessException {
		try {
			OrdenTrabajo ot = OrdenTrabajoFinder.find(ordenTrabajo);
			
			Incidencia incidencia = new Incidencia(ot);
			
			incidencia.setCausa(causa);
			incidencia.setFecha(new Date());
			
			Jpa.getManager().persist(incidencia);
		}
		
		catch (MyPersistenceException | PersistenceException e) {
			throw new BusinessException(e);
		}
		
		return null;
	}

}