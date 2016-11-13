package business.impl.empaquetado;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import business.exception.BusinessException;
import business.impl.util.Command;
import model.OrdenTrabajo;
import model.Pedido;
import persistence.PedidoFinder;
import persistence.exception.MyPersistenceException;
import ui.almacen.myTypes.model.MyPedido_OT_Retomar;

public class ObtenerPedidosOrdenTrabajo implements Command {

	private OrdenTrabajo ordenTrabajo;

	public ObtenerPedidosOrdenTrabajo(OrdenTrabajo ordenTrabajo) {
		this.ordenTrabajo = ordenTrabajo;
	}

	@Override
	public Object execute() throws BusinessException {

		try {
			List<MyPedido_OT_Retomar> listaPedidos = new ArrayList<MyPedido_OT_Retomar>();

			List<Pedido> pedidos = PedidoFinder.findPosibleEmpaquetarOT(ordenTrabajo);

			for (Pedido pedOT : pedidos) {
				int numProductos = (int) PedidoFinder.findNumProductosPosibleEmpaquetar(ordenTrabajo, pedOT);

				listaPedidos.add(new MyPedido_OT_Retomar(pedOT, numProductos));
			}

			return listaPedidos;
		}

		catch (MyPersistenceException | PersistenceException e) {
			throw new BusinessException("Ha ocurrido un error al buscar OT que se pueda empaquetar", e);
		}
	}

}