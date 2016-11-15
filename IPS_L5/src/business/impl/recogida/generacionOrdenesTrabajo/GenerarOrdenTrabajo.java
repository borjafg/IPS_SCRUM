package business.impl.recogida.generacionOrdenesTrabajo;

import javax.persistence.PersistenceException;

import business.exception.BusinessException;
import business.impl.util.Command;
import model.Almacenero;
import model.OrdenTrabajo;
import model.Pedido;
import persistence.AlmaceneroFinder;
import persistence.PedidoFinder;
import persistence.exception.MyPersistenceException;

public class GenerarOrdenTrabajo implements Command {

	private Pedido pedido;
	private Almacenero almacenero;

	// ----------------------------------------
	// Variables que se usarán en las clases
	// asociadas con esta
	// ----------------------------------------

	private double pesoMaximo = 30.55; // kg
	private double volumenMaximo = 80.20; // cm3

	public GenerarOrdenTrabajo(Pedido pedido, Almacenero almacenero) {
		this.pedido = pedido;
		this.almacenero = almacenero;
	}

	@Override
	public OrdenTrabajo execute() throws BusinessException {

		try {
			Pedido ped = PedidoFinder.find(pedido);
			Almacenero al = AlmaceneroFinder.find(almacenero);

			// ------------------------------------------------------
			// Si el pedido cabe entero en la orden de trabajo
			// ------------------------------------------------------

			if (PedidoFinder.cabeEntero(ped, pesoMaximo, volumenMaximo)) {
				return (new CrearOrdenTrabajo_UnionPedidos(ped, al, pesoMaximo, volumenMaximo)).crear();
			}

			// ------------------------------------------------------
			// Si el pedido no caben en una única orden de trabajo
			// ------------------------------------------------------

			else {
				return (new CrearOrdenesTrabajo_FragmentacionPedidos(ped, al, pesoMaximo, volumenMaximo)).crear();
			}
		}

		catch (MyPersistenceException | PersistenceException e) {
			throw new BusinessException("Ha ocurrido un error al generar OT sobre el pedido indicado", e);
		}
	}

}