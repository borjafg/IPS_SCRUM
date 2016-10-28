package ui.almacen.recogida.logica;

import business.exception.BusinessException;
import infrastructure.ServiceFactory;
import model.Incidencia;
import model.OrdenTrabajo;

/**
 * Clase que genera incidencias para órdenes de trabajo
 * @author Nacho
 *
 */

public class TerminalIncidencia {

	private OrdenTrabajo ordenTrabajo;
	
	public TerminalIncidencia(OrdenTrabajo ordenTrabajo) {
		this.ordenTrabajo = ordenTrabajo;
	}
	
	
	public Incidencia generarIncidencia(String causa) throws BusinessException { 
		return ServiceFactory.getRecogidaService().insertarIncidencia(ordenTrabajo, causa);
	}
}
