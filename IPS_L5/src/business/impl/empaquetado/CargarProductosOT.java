package business.impl.empaquetado;

import javax.persistence.PersistenceException;

import business.exception.BusinessException;
import business.impl.util.Command;
import model.OrdenTrabajo;
import model.Pedido;
import persistence.ProductoEnOrdenTrabajoFinder;
import persistence.exception.MyPersistenceException;

public class CargarProductosOT implements Command {

	private OrdenTrabajo ordenTrabajo;
	private Pedido pedido;

	public CargarProductosOT(OrdenTrabajo ordenTrabajo, Pedido pedido) {
		this.ordenTrabajo = ordenTrabajo;
		this.pedido = pedido;
	}

	@Override
	public Object execute() throws BusinessException {
		try {

			// No hay productos en el paquete o no hay ningún paquete abierto
			if (pedido == null) {
				return ProductoEnOrdenTrabajoFinder.findPendientesEmpaquetar(ordenTrabajo);
			}

			// Si hay un paquete abierto buscar en la OT productos de ese pedido
			else {
				return ProductoEnOrdenTrabajoFinder.findPendientesEmpaquetarPedido(ordenTrabajo, pedido);
			}

		} catch (MyPersistenceException | PersistenceException e) {
			throw new BusinessException("Ha ocurrido un error al cargar los productos de una OT", e);
		}
	}

}