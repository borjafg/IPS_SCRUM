package business.impl.empaquetado;

import business.exception.BusinessException;
import business.impl.util.Command;
import model.OrdenTrabajo;

public class ObtenerPedidosOrdenTrabajo implements Command {

	private OrdenTrabajo ordenTrabajo;
	
	public ObtenerPedidosOrdenTrabajo(OrdenTrabajo ordenTrabajo) {
		this.ordenTrabajo = ordenTrabajo;
	}

	@Override
	public Object execute() throws BusinessException {
		return null;
	}

}