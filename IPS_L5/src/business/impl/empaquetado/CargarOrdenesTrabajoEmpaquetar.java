package business.impl.empaquetado;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import business.exception.BusinessException;
import business.impl.util.Command;
import model.Almacenero;
import model.OrdenTrabajo;
import persistence.OrdenTrabajoFinder;
import persistence.exception.MyPersistenceException;
import ui.almacen.myTypes.model.MyOrdenTrabajo_Retomar;

public class CargarOrdenesTrabajoEmpaquetar implements Command {

	private Almacenero almacenero;

	public CargarOrdenesTrabajoEmpaquetar(Almacenero almacenero) {
		this.almacenero = almacenero;
	}

	@Override
	public Object execute() throws BusinessException {
		try {
			List<MyOrdenTrabajo_Retomar> ordenesTrabajo = new ArrayList<MyOrdenTrabajo_Retomar>();
			List<OrdenTrabajo> ots = OrdenTrabajoFinder.findOT_EmpaquetarAlmacenero(almacenero);

			MyOrdenTrabajo_Retomar otRet;

			for (OrdenTrabajo ot : ots) {
				int numPedidosFaltan = (int) OrdenTrabajoFinder.findNumPedidosFaltaEmpaquetar(ot);
				int numProductosFaltan = (int) OrdenTrabajoFinder.findNumProductosFaltaEmpaquetar(ot);

				otRet = new MyOrdenTrabajo_Retomar(ot, numPedidosFaltan, numProductosFaltan);

				ordenesTrabajo.add(otRet);
			}

			return ordenesTrabajo;
		}

		catch (MyPersistenceException | PersistenceException e) {
			throw new BusinessException("Ha ocurrido un error al cargar la lista de OT para empaquetar", e);
		}
	}

}