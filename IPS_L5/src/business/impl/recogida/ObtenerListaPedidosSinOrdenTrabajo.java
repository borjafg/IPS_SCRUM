package business.impl.recogida;

import java.util.ArrayList;
import java.util.List;

import business.exception.BusinessException;
import business.impl.util.Command;
import model.Pedido;
import model.ProductoEnPedido;
import persistence.PedidoFinder;
import ui.almacen.myTypes.model.MyPedido;

public class ObtenerListaPedidosSinOrdenTrabajo implements Command {

	@Override
	public Object execute() throws BusinessException {
		List<Pedido> pedidosPosibleRecoger = PedidoFinder.findPosibleRecoger();
		List<MyPedido> pedidos = new ArrayList<MyPedido>();

		int totalProdSinOT = 0;

		for (Pedido ped : pedidosPosibleRecoger) {
			totalProdSinOT = 0;

			for (ProductoEnPedido pp : ped.getListaProductosPedidos()) {
				totalProdSinOT = totalProdSinOT + (pp.getCantidad() - pp.getCantidadAsociadaOT());
			}

			if (totalProdSinOT > 0) {
				pedidos.add(new MyPedido(ped, totalProdSinOT));
			}
		}

		return pedidos;
	}

}