package business.impl.recogida;

import java.util.Date;
import business.exception.BusinessException;
import business.impl.util.Command;
import model.Incidencia;
import model.OrdenTrabajo;
import persistence.OrdenTrabajoFinder;
import persistence.util.Jpa;

public class InsertIncidencia implements Command {
	
	private OrdenTrabajo ordenTrabajo;
	private String causa;
	
	public InsertIncidencia(OrdenTrabajo ordenTrabajo, String causa) {
		this.ordenTrabajo = ordenTrabajo;
		this.causa = causa;
	}

	@Override
	public Object execute() throws BusinessException {
		
		OrdenTrabajo ordenTrabajo= OrdenTrabajoFinder.findbyId(this.ordenTrabajo.getId());

		Incidencia incidencia = new Incidencia(ordenTrabajo);
		incidencia.setCausa(causa);
		
		Date date = new Date();
		incidencia.setFecha(date);

		Jpa.getManager().persist(incidencia);

		return incidencia;
	}

}