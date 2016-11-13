package business.impl.recogida;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import business.exception.BusinessException;
import business.impl.util.Command;
import model.Pedido;
import model.ProductoEnPedido;
import persistence.PedidoFinder;
import persistence.exception.MyPersistenceException;
import ui.almacen.myTypes.model.MyPedido_OT_Retomar;

public class ObtenerListaPedidosSinOrdenTrabajo implements Command {

	@Override
	public Object execute() throws BusinessException {

		List<MyPedido_OT_Retomar> pedidos = new ArrayList<MyPedido_OT_Retomar>();

		try {
			List<Pedido> pedidosPosibleRecoger = PedidoFinder.findPosibleRecoger();

			int totalProdSinOT = 0;

			for (Pedido ped : pedidosPosibleRecoger) {
				totalProdSinOT = 0;

				for (ProductoEnPedido pp : ped.getListaProductosPedidos()) {
					totalProdSinOT = totalProdSinOT + (pp.getCantidad() - pp.getCantidadAsociadaOT());
				}

				if (totalProdSinOT > 0) {
					pedidos.add(new MyPedido_OT_Retomar(ped, totalProdSinOT));
				}
			}
		}

		catch (MyPersistenceException | PersistenceException e) {
			throw new BusinessException("Ha ocurrido un error al buscar la lista de pedidos sin OT", e);
		}

		return pedidos;
	}

}